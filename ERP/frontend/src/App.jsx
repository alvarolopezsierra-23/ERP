import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Navbar from './components/layout/Navbar';
import Sidebar from './components/layout/Sidebar';
import Dashboard from './pages/Dashboard';
import ClientesList from './components/clientes/ClientesList';
import ProductosList from './components/productos/ProductosList';
import EmpleadosList from './components/empleados/EmpleadosList';
import VentasList from './components/ventas/VentasList';
import ReportesDashboard from './components/reportes/ReportesDashboard';
import NotFound from './pages/NotFound';

export default function App(){
  return (
    <BrowserRouter>
      <div className="min-h-screen bg-gray-50">
        <Navbar />
        <div className="flex">
          <Sidebar />
          <main className="flex-1">
            <Routes>
              <Route path="/" element={<Dashboard/>} />
              <Route path="/clientes" element={<ClientesList/>} />
              <Route path="/productos" element={<ProductosList/>} />
              <Route path="/empleados" element={<EmpleadosList/>} />
              <Route path="/ventas" element={<VentasList/>} />
              <Route path="/reportes" element={<ReportesDashboard/>} />
              <Route path="*" element={<NotFound/>} />
            </Routes>
          </main>
        </div>
      </div>
    </BrowserRouter>
  );
}
