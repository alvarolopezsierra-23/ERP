import React from 'react';

export default function Navbar(){
  return (
    <header className="bg-white shadow p-4 flex items-center justify-between">
      <div className="text-xl font-semibold">ERP - Panel</div>
      <div className="flex items-center gap-4">
        <span className="text-sm text-gray-600">Usuario</span>
      </div>
    </header>
  );
}
