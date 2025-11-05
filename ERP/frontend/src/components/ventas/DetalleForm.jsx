import React, { useEffect, useState } from 'react';
import { listarProductos } from '../../api/productosApi';

export default function DetalleForm({ onAdd }) {
    const [productos, setProductos] = useState([]);
    const [detalle, setDetalle] = useState({ productoId: '', cantidad: 1 });

    useEffect(() => {
        listarProductos()
            .then(r => setProductos(r || []))
            .catch(() => setProductos([]));
    }, []);

    const agregarDetalle = () => {
        if (!detalle.productoId || detalle.cantidad <= 0) return;
        onAdd(detalle);
        setDetalle({ productoId: '', cantidad: 1 });
    };

    return (
        <div className="grid grid-cols-3 gap-2 mb-4">
            <select
                value={detalle.productoId}
                onChange={e => setDetalle({ ...detalle, productoId: e.target.value })}
                className="border p-2 rounded"
            >
                <option value="">Selecciona producto</option>
                {productos?.map(p => (
                    <option key={p.id} value={p.id}>{p.nombre}</option>
                ))}
            </select>
            <input
                type="number"
                min="1"
                value={detalle.cantidad}
                onChange={e => setDetalle({ ...detalle, cantidad: parseInt(e.target.value) || 1 })}
                className="border p-2 rounded"
            />
            <button
                type="button"
                onClick={agregarDetalle}
                className="bg-green-500 text-white px-3 py-1 rounded"
            >
                Agregar
            </button>
        </div>
    );
}
