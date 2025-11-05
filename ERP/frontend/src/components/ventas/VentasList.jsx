import React, { useEffect, useState } from 'react';
import VentaForm from './VentaForm';
import { listarVentas, eliminarVenta } from '../../api/ventasApi';

export default function VentasList() {
    const [ventas, setVentas] = useState([]);
    const [editing, setEditing] = useState(null);
    const [expandedIds, setExpandedIds] = useState([]);

    const load = () => {
        listarVentas()
            .then(res => setVentas(Array.isArray(res.data) ? res.data : res.data.ventas || []))
            .catch(() => setVentas([]));
    };

    useEffect(() => { load(); }, []);

    const borrar = (id) => eliminarVenta(id).then(load);

    const toggleExpand = (id) => {
        if (expandedIds.includes(id)) {
            setExpandedIds(expandedIds.filter(eid => eid !== id));
        } else {
            setExpandedIds([...expandedIds, id]);
        }
    };

    const calcularTotal = (venta) => {
        return venta.detalles?.reduce((sum, d) => sum + (d.producto?.precio || 0) * d.cantidad, 0) || 0;
    };

    return (
        <div className="p-6">
            <div className="flex justify-between items-center mb-4">
                <h2 className="text-xl font-semibold">Ventas</h2>
                <button onClick={() => setEditing({})} className="bg-green-500 text-white px-3 py-1 rounded">Nueva Venta</button>
            </div>

            {editing && <VentaForm
                venta={editing}
                onSaved={() => { setEditing(null); load(); }}
                onCancel={() => setEditing(null)}
            />}

            <div className="bg-white shadow rounded overflow-hidden">
                <table className="w-full table-auto">
                    <thead className="bg-gray-100">
                    <tr>
                        <th className="p-3">ID</th>
                        <th className="p-3">Cliente</th>
                        <th className="p-3">Empleado</th>
                        <th className="p-3">Fecha</th>
                        <th className="p-3">Total</th>
                        <th className="p-3">Detalles</th>
                        <th className="p-3">Acciones</th>
                    </tr>
                    </thead>
                    <tbody>
                    {ventas.map(v => (
                        <React.Fragment key={v.id}>
                            <tr className="border-t">
                                <td className="p-3">{v.id}</td>
                                <td className="p-3">{v.cliente?.nombre || 'Sin Cliente'}</td>
                                <td className="p-3">{v.empleado?.nombre || 'Sin Empleado'}</td>
                                <td className="p-3">{v.fecha ? new Date(v.fecha).toLocaleDateString() : '-'}</td>
                                <td className="p-3">{calcularTotal(v)}€</td>
                                <td className="p-3">
                                    {v.detalles && v.detalles.length > 0 ? (
                                        <button
                                            onClick={() => toggleExpand(v.id)}
                                            className="text-blue-600 underline"
                                        >
                                            {expandedIds.includes(v.id) ? 'Ocultar' : 'Ver'} detalles
                                        </button>
                                    ) : 'Sin detalles'}
                                </td>
                                <td className="p-3">
                                    <button onClick={() => setEditing(v)} className="text-blue-600 mr-2">Editar</button>
                                    <button onClick={() => borrar(v.id)} className="text-red-600">Borrar</button>
                                </td>
                            </tr>
                            {expandedIds.includes(v.id) && (
                                <tr className="bg-gray-50">
                                    <td colSpan="7" className="p-3">
                                        <table className="w-full table-auto border">
                                            <thead>
                                            <tr>
                                                <th className="p-2 border">Producto</th>
                                                <th className="p-2 border">Cantidad</th>
                                                <th className="p-2 border">Precio Unit.</th>
                                                <th className="p-2 border">Subtotal</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            {v.detalles.map((d, idx) => (
                                                <tr key={idx}>
                                                    <td className="p-2 border">{d.producto?.nombre || 'Producto desconocido'}</td>
                                                    <td className="p-2 border">{d.cantidad}</td>
                                                    <td className="p-2 border">{d.producto?.precio || 0}€</td>
                                                    <td className="p-2 border">{(d.producto?.precio || 0) * d.cantidad}€</td>
                                                </tr>
                                            ))}
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                            )}
                        </React.Fragment>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}
