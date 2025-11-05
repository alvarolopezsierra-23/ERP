import React, {useEffect, useState} from 'react';
import { listarClientes, eliminarCliente } from '../../api/clientesApi';
import ClienteForm from './ClienteForm';

export default function ClientesList(){
  const [clientes, setClientes] = useState([]);
  const [editing, setEditing] = useState(null);

  const load = () => listarClientes().then(r => setClientes(r.data)).catch(() => setClientes([]));

  useEffect(() => { load(); }, []);

  const borrar = (id) => eliminarCliente(id).then(load);

  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-4">
        <h2 className="text-xl font-semibold">Clientes</h2>
        <button onClick={() => setEditing({})} className="bg-green-500 text-white px-3 py-1 rounded">Nuevo</button>
      </div>

      {editing && <ClienteForm cliente={editing} onSaved={() => { setEditing(null); load(); }} onCancel={() => setEditing(null)} />}

      <div className="bg-white shadow rounded overflow-hidden">
        <table className="w-full table-auto">
          <thead className="bg-gray-100">
            <tr>
              <th className="p-3 text-left">ID</th>
              <th className="p-3 text-left">Nombre</th>
              <th className="p-3 text-left">Email</th>
              <th className="p-3">Acciones</th>
            </tr>
          </thead>
          <tbody>
            {clientes.map(c => (
              <tr key={c.id} className="border-t">
                <td className="p-3">{c.id}</td>
                <td className="p-3">{c.nombre || c.name || '-'}</td>
                <td className="p-3">{c.email}</td>
                <td className="p-3 text-center">
                  <button onClick={() => setEditing(c)} className="text-sm mr-2 text-blue-600">Editar</button>
                  <button onClick={() => borrar(c.id)} className="text-sm text-red-600">Borrar</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
