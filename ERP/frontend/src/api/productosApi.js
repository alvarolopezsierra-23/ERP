// src/api/productosApi.js


const API_URL = "http://localhost:8080/productos"

// 🔹 Obtener todos los productos
export async function listarProductos() {
    const response = await fetch(API_URL)
    if (!response.ok) throw new Error("Error al obtener productos")
    return response.json()
}

// 🔹 Obtener producto por ID
export async function obtenerProducto(id) {
    const response = await fetch(`${API_URL}/${id}`)
    if (!response.ok) throw new Error("Error al obtener el producto")
    return response.json()
}

// 🔹 Crear un nuevo producto
export async function crearProducto(producto) {
    const response = await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(producto),
    })
    if (!response.ok) throw new Error("Error al crear el producto")
    return response.json()
}

// 🔹 Actualizar stock de un producto (PUT)
export async function actualizarProducto(id, cantidad) {
    const response = await fetch(`${API_URL}/${id}?cantidad=${cantidad}`, {
        method: "PUT",
    })
    if (!response.ok) throw new Error("Error al actualizar el stock")
    return response.json()
}

// 🔹 Eliminar producto
export async function eliminarProducto(id) {
    const response = await fetch(`${API_URL}/${id}`, { method: "DELETE" })
    if (!response.ok) throw new Error("Error al eliminar el producto")
}
