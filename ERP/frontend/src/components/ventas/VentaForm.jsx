import React, { useState, useEffect } from 'react';
import { crearVenta, actualizarVenta } from '../../api/ventasApi';
import { listarClientes } from '../../api/clientesApi';
import { listarEmpleados } from '../../api/empleadosApi';
import { listarProductos } from '../../api/productosApi';

export default function VentaForm({ venta, onSaved, onCancel }) {
    const [form, setForm] = useState({
        clienteId: '',
        empleadoId: '',
        fecha: new Date().toISOString().split('T')[0],
        detalles: [],
    });

    const [clientes, setClientes] = useState([]);
    const [empleados, setEmpleados] = useState([]);
    const [productos, setProductos] = useState([]);
    const [detalleTmp, setDetalleTmp] = useState({ productoId: '', cantidad: 1 });

    // Cargar clientes, empleados y productos
    useEffect(() => {
        listarClientes().then(r => setClientes(r.data || []));
        listarEmpleados().then(r => setEmpleados(r.data || []));
        listarProductos().then(r => setProductos(r || []));
    }, []);

    // Inicializar formulario si se edita
    useEffect(() => {
        if (venta && venta.id) {
            setForm({
                clienteId: venta.cliente?.id || '',
                empleadoId: venta.empleado?.id || '',
                fecha: venta.fecha ? new Date(venta.fecha).toISOString().split('T')[0] : new Date().toISOString().split('T')[0],
                detalles: venta.detalles || [],
            });
        }
    }, [venta]);

    const agregarDetalle = () => {
        if (detalleTmp.productoId && detalleTmp.cantidad > 0) {
            setForm({
                ...form,
                detalles: [...form.detalles, { ...detalleTmp }],
            });
            setDetalleTmp({ productoId: '', cantidad: 1 });
        }
    };

    const eliminarDetalle = (index) => {
        const nuevosDetalles = [...form.detalles];
        nuevosDetalles.splice(index, 1);
        setForm({ ...form, detalles: nuevosDetalles });
    };

    const submit = async (e) => {
        e.preventDefault();
        try {
            if (venta && venta.id) {
                await actualizarVenta(venta.id, form);
            } else {
                await crearVenta(form);
            }
            onSaved();
        } catch (err) {
            console.error(err);
        }
    };

    return (
        <form onSubmit={submit} className="bg-white p-4 rounded shadow mb-4">
            <div className="grid grid-cols-2 gap-3 mb-4">
                <select
                    value={form.clienteId}
                    onChange={e => setForm({ ...form, clienteId: e.target.value })}
                    className="border p-2 rounded"
                    required
                >
                    <option value="">Selecciona Cliente</option>
                    {clientes.map(c => (
                        <option key={c.id} value={c.id}>{c.nombre}</option>
                    ))}
                </select>

                <select
                    value={form.empleadoId}
                    onChange={e => setForm({ ...form, empleadoId: e.target.value })}
                    className="border p-2 rounded"
                    required
                >
                    <option value="">Selecciona Empleado</option>
                    {empleados.map(emp => (
                        <option key={emp.id} value={emp.id}>{emp.nombre}</option>
                    ))}
                </select>

                <input
                    type="date"
                    value={form.fecha}
                    onChange={e => setForm({ ...form, fecha: e.target.value })}
                    className="border p-2 rounded col-span-2"
                    required
                />
            </div>

            {/* Detalles */}
            <div className="mb-4">
                <h3 className="font-semibold mb-2">Detalles de Venta</h3>
                <div className="flex gap-2 mb-2">
                    <select
                        value={detalleTmp.productoId}
                        onChange={e => setDetalleTmp({ ...detalleTmp, productoId: e.target.value })}
                        className="border p-2 rounded flex-1"
                    >
                        <option value="">Selecciona Producto</option>
                        {productos.map(p => (
                            <option key={p.id} value={p.id}>{p.nombre}</option>
                        ))}
                    </select>

                    <input
                        type="number"
                        min="1"
                        value={detalleTmp.cantidad}
                        onChange={e => setDetalleTmp({ ...detalleTmp, cantidad: parseInt(e.target.value) })}
                        className="border p-2 rounded w-24"
                    />

                    <button type="button" onClick={agregarDetalle} className="bg-green-500 text-white px-3 py-1 rounded">
                        Añadir
                    </button>
                </div>

                <ul>
                    {form.detalles.map((d, i) => {
                        const prod = productos.find(p => p.id === d.productoId);
                        return (
                            <li key={i} className="flex justify-between mb-1 border p-2 rounded">
                                {prod?.nombre || 'Producto desconocido'} x {d.cantidad}
                                <button type="button" onClick={() => eliminarDetalle(i)} className="text-red-600">
                                    Borrar
                                </button>
                            </li>
                        );
                    })}
                </ul>
            </div>

            <div className="flex gap-2">
                <button type="submit" className="bg-blue-600 text-white px-3 py-1 rounded">Guardar</button>
                <button type="button" onClick={onCancel} className="px-3 py-1 border rounded">Cancelar</button>
            </div>
        </form>
    );
}
