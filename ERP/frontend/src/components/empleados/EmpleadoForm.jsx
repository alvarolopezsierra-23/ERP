import React, {useState, useEffect} from 'react';
import { crearEmpleado, obtenerEmpleado, actualizarEmpleado } from '../../api/empleadosApi';

export default function EmpleadoForm({empleado, onSaved, onCancel}){
    const [form, setForm] = useState({nombre:'', email:'', puesto:'', salario:0});

    useEffect(()=>{
        if(empleado && empleado.id) {
            obtenerEmpleado(empleado.id).then(r=> setForm({
                nombre: r.data.nombre,
                email: r.data.email,
                puesto: r.data.puesto,
                salario: r.data.salario
            }));
        } else if(empleado) {
            setForm({nombre:'', email:'', puesto:'', salario:0});
        }
    },[empleado]);

    const submit= async(e)=>{
        e.preventDefault();
        try{
            if(empleado && empleado.id) await actualizarEmpleado(empleado.id, form);
            else await crearEmpleado(form);
            onSaved();
        }catch(err){
            console.error(err);
        }
    }

    return (
        <form onSubmit={submit} className="bg-white p-4 rounded shadow mb-4">
            <div className="grid grid-cols-2 gap-3">
                <input
                    value={form.nombre}
                    onChange={e=>setForm({...form,nombre:e.target.value})}
                    placeholder="Nombre"
                    className="border p-2 rounded"
                />
                <input
                    value={form.email}
                    onChange={e=>setForm({...form,email:e.target.value})}
                    placeholder="Email"
                    className="border p-2 rounded"
                />
                <input
                    value={form.puesto}
                    onChange={e=>setForm({...form,puesto:e.target.value})}
                    placeholder="Puesto"
                    className="border p-2 rounded"
                />
                <input
                    type="number"
                    value={form.salario || ""}
                    onChange={e=>setForm({...form,salario:parseFloat(e.target.value)})}
                    placeholder="Salario"
                    className="border p-2 rounded"
                />
            </div>
            <div className="mt-3 flex gap-2">
                <button type="submit" className="bg-blue-600 text-white px-3 py-1 rounded">Guardar</button>
                <button type="button" onClick={onCancel} className="px-3 py-1 border rounded">Cancelar</button>
            </div>
        </form>
    );
}
