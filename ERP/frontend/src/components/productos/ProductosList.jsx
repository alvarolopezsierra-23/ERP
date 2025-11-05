import React, {useEffect, useState} from 'react';
import { listarProductos, eliminarProducto } from '../../api/productosApi';
import ProductoForm from './ProductoForm';

export default function ProductosList(){
  const [productos, setProductos] = useState([]);
  const [editing, setEditing] = useState(null);
    const load = () =>
        listarProductos()
            .then(r => {
                // Asegúrate de que r.data sea un array, si no usa r directamente
                setProductos(r.data || r || []);
            })
            .catch(() => setProductos([]));
  useEffect(()=>{load();},[]);
  const borrar = (id)=> eliminarProducto(id).then(load);
  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-4">
        <h2 className="text-xl font-semibold">Productos</h2>
        <button onClick={()=>setEditing({})} className="bg-green-500 text-white px-3 py-1 rounded">Nuevo</button>
      </div>
      {editing && <ProductoForm producto={editing} onSaved={()=>{setEditing(null); load();}} onCancel={()=>setEditing(null)} />}
      <div className="bg-white shadow rounded overflow-hidden">
        <table className="w-full table-auto">
          <thead className="bg-gray-100"><tr><th className="p-3">ID</th><th className="p-3">Nombre</th><th className="p-3">Precio</th><th className="p-3">Stock</th><th className="p-3">Acciones</th></tr></thead>
          <tbody>{productos.map(p=> (
            <tr key={p.id} className="border-t"><td className="p-3">{p.id}</td><td className="p-3">{p.nombre||p.name}</td><td className="p-3">{p.precio||p.price}€</td><td className="p-3">{p.stock}</td><td className="p-3"><button onClick={()=>setEditing(p)} className="text-blue-600 mr-2">Editar</button><button onClick={()=>borrar(p.id)} className="text-red-600">Borrar</button></td></tr>
          ))}</tbody>
        </table>
      </div>
    </div>
  );
}
