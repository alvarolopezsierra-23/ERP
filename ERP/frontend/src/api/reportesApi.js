import API from './axiosConfig';

export const general = () => API.get('/reportes/general');
export const ventasTotales = () => API.get('/reportes/ventas/totales');
export const ingresosTotales = () => API.get('/reportes/ingresos/totales');
export const productosMasVendidos = () => API.get('/reportes/productos/mas-vendidos');
export const ingresosPorProducto = () => API.get('/reportes/ingresos/por-producto');
