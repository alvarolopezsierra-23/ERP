import React, {useEffect, useState} from 'react';
import { general, ventasTotales, ingresosTotales, productosMasVendidos } from '../../api/reportesApi';

export default function ReportesDashboard(){
  const [reporte, setReporte] = useState({});
  useEffect(()=>{ general().then(r=>setReporte(r.data)).catch(()=>setReporte({})); },[]);

  return (
    <div className="p-6">
      <h2 className="text-xl font-semibold mb-4">Reportes</h2>
      <div className="grid grid-cols-3 gap-4">
        <div className="bg-white p-4 rounded shadow">Total Ventas: <div className="text-2xl font-bold">{reporte.totalVentas || 0}</div></div>
        <div className="bg-white p-4 rounded shadow">Ingresos: <div className="text-2xl font-bold">{reporte.ingresosTotales || 0}€</div></div>
        <div className="bg-white p-4 rounded shadow">Producto top: <div className="text-2xl font-bold">{reporte.productoMasVendido?.nombre || '—'}</div></div>
      </div>
    </div>
  );
}
