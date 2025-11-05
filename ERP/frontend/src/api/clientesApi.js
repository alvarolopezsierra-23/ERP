import API from './axiosConfig';

import axios from 'axios';

export const listarClientes = () => API.get('/clientes');
export const obtenerCliente = (id) => API.get(`/clientes/${id}`);
export const crearCliente = (cliente) => API.post('/clientes', cliente);
export const actualizarCliente = (id, cliente) => API.put(`/clientes/${id}`, cliente);
export const eliminarCliente = (id) => API.delete(`/clientes/${id}`);
