import API from './axiosConfig';
import axios from 'axios';

const API_URL = 'http://localhost:8080/ventas';

export const listarVentas = () => API.get('/ventas');
export const obtenerVenta = (id) => API.get(`/ventas/${id}`);
export const crearVenta = (v) => API.post('/ventas', v);
export const agregarDetalle = (id, detalle) => API.post(`/ventas/${id}/detalles`, detalle);
export const actualizarVenta = (id, venta) => axios.put(`${API_URL}/${id}`, venta);
export const eliminarVenta = (id) => axios.delete(`${API_URL}/${id}`);
export const totalVentas = () => API.get('/ventas/total');
export const agregarDetalleVenta = (ventaId, detalle) =>
    axios.post(`${API_URL}/${ventaId}/detalles`, detalle);