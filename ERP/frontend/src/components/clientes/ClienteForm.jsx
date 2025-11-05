import React, {useState, useEffect} from 'react';
import { crearCliente, actualizarCliente, obtenerCliente } from '../../api/clientesApi';

export default function ClienteForm({cliente, onSaved, onCancel}){
  const [form, setForm] = useState({nombre:'', email:''});

  useEffect(() => {
    if(cliente && cliente.id){
      obtenerCliente(cliente.id).then(r => setForm({nombre: r.data.nombre || r.data.name, email: r.data.email}));
    } else if(cliente){
      setForm({nombre:'', email:''});
    }
  }, [cliente]);

  const submit = async (e) => {
    e.preventDefault();
    if(form.nombre.trim() === '') return alert('Nombre requerido');
    try{
      if(cliente && cliente.id) await actualizarCliente(cliente.id, {nombre: form.nombre, email: form.email});
      else await crearCliente({nombre: form.nombre, email: form.email});
      onSaved();
    }catch(err){ console.error(err); alert('Error'); }
  }

  return (
    <form onSubmit={submit} className="bg-white p-4 rounded shadow mb-4">
      <div className="grid grid-cols-2 gap-3">
        <input value={form.nombre} onChange={e => setForm({...form, nombre: e.target.value})} placeholder="Nombre" className="border p-2 rounded" />
        <input value={form.email} onChange={e => setForm({...form, email: e.target.value})} placeholder="Email" className="border p-2 rounded" />
      </div>
      <div className="mt-3 flex gap-2">
        <button type="submit" className="bg-blue-600 text-white px-3 py-1 rounded">Guardar</button>
        <button type="button" onClick={onCancel} className="px-3 py-1 border rounded">Cancelar</button>
      </div>
    </form>
  );
}
