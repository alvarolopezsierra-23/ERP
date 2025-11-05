import React, {useState, useEffect} from 'react';
import { crearProducto, obtenerProducto, actualizarProducto } from '../../api/productosApi';

export default function ProductoForm({producto, onSaved, onCancel}){
  const [form, setForm] = useState({nombre:'', precio:0, stock:0});
  useEffect(()=>{
    if(producto && producto.id){ obtenerProducto(producto.id).then(r=> setForm({nombre:r.data.nombre, precio:r.data.precio, stock:r.data.stock})); }
    else if(producto) setForm({nombre:'', precio:0, stock:0});
  },[producto]);
  const submit= async(e)=>{ e.preventDefault(); try{ if(producto && producto.id) await actualizarProducto(producto.id, form); else await crearProducto(form); onSaved(); }catch(err){console.error(err);} }
  return (
    <form onSubmit={submit} className="bg-white p-4 rounded shadow mb-4">
      <div className="grid grid-cols-3 gap-3">
        <input value={form.nombre} onChange={e=>setForm({...form,nombre:e.target.value})} placeholder="Nombre" className="border p-2 rounded" />
        <input type="number" value={form.precio} onChange={e=>setForm({...form,precio:parseFloat(e.target.value)})} placeholder="Precio" value={producto.precio || ""} onChange={e => setPrecio({...producto, precio: Number(e.target.value)})} className="border p-2 rounded" />
        <input type="number" value={form.stock} onChange={e=>setForm({...form,stock:parseInt(e.target.value)})} placeholder="Stock" value={producto.stock || ""} onChange={e => setStock({...producto, stock: Number(e.target.stock)})} className="border p-2 rounded" />
      </div>
      <div className="mt-3 flex gap-2"><button type="submit" className="bg-blue-600 text-white px-3 py-1 rounded">Guardar</button><button type="button" onClick={onCancel}className="px-3 py-1 border rounded">Cancelar</button></div>
    </form>
  );
}
