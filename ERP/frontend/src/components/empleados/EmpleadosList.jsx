import React, {useEffect, useState} from 'react';
import { listarEmpleados, eliminarEmpleado } from '../../api/empleadosApi';
import EmpleadoForm from './EmpleadoForm';

export default function EmpleadosList(){
  const [empleados, setEmpleados] = useState([]);
  const [editing, setEditing] = useState(null);
  const load = ()=> listarEmpleados().then(r=>setEmpleados(r.data)).catch(()=>setEmpleados([]));
  useEffect(()=>{load();},[]);
  const borrar = id => eliminarEmpleado(id).then(load);
  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-4"><h2 className="text-xl font-semibold">Empleados</h2><button onClick={()=>setEditing({})} className="bg-green-500 text-white px-3 py-1 rounded">Nuevo</button></div>
      {editing && <EmpleadoForm empleado={editing} onSaved={()=>{setEditing(null); load();}} onCancel={()=>setEditing(null)} />}
      <div className="bg-white shadow rounded overflow-hidden"><table className="w-full table-auto"><thead className="bg-gray-100"><tr><th className="p-3">ID</th><th className="p-3">Nombre</th><th className="p-3">Email</th><th className="p-3">Puesto</th><th className="p-3">Acciones</th></tr></thead><tbody>{empleados.map(e=> (<tr key={e.id} className="border-t"><td className="p-3">{e.id}</td><td className="p-3">{e.nombre||e.name}</td><td className="p-3">{e.email}</td><td className="p-3">{e.puesto}</td><td className="p-3"><button onClick={()=>setEditing(e)} className="text-blue-600 mr-2">Editar</button><button onClick={()=>borrar(e.id)} className="text-red-600">Borrar</button></td></tr>))}</tbody></table></div>
    </div>
  );
}
