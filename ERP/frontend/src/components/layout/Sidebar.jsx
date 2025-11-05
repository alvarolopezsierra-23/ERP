import React from 'react';
import { NavLink } from 'react-router-dom';

const LinkItem = ({to, children}) => (
  <NavLink to={to} className={({isActive}) => `block px-4 py-2 rounded ${isActive ? 'bg-blue-600 text-white' : 'text-gray-700 hover:bg-gray-100'}`}>
    {children}
  </NavLink>
);

export default function Sidebar(){
  return (
    <aside className="w-64 bg-white border-r p-4">
      <nav className="space-y-2">
        <LinkItem to="/">Dashboard</LinkItem>
        <LinkItem to="/clientes">Clientes</LinkItem>
        <LinkItem to="/productos">Productos</LinkItem>
        <LinkItem to="/empleados">Empleados</LinkItem>
        <LinkItem to="/ventas">Ventas</LinkItem>
        <LinkItem to="/reportes">Reportes</LinkItem>
      </nav>
    </aside>
  );
}
