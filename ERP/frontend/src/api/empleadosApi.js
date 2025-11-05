import API from './axiosConfig';
import axios from 'axios';

const API_URL = 'http://localhost:8080/empleados';

export const listarEmpleados = () => axios.get(API_URL);
export const obtenerEmpleado = (id) => API.get(`/empleados/${id}`);
export const crearEmpleado = (empleado) => axios.post(API_URL, empleado);
export const actualizarEmpleado = (id, empleado) => axios.put(`${API_URL}/${id}`, empleado);
export const eliminarEmpleado = (id) => axios.delete(`${API_URL}/${id}`);