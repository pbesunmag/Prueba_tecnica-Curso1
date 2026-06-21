# Prueba_tecnica_Curso1

# 👥 Sistema de Gestión de Clientes (CRUD)

¡Bienvenido al **Sistema de Gestión de Clientes**! Aquí os presento mi propuesta de código para la prueba técnica del curso 1.

El proyecto ha sido diseñado siguiendo las buenas prácticas de la Programación Orientada a Objetos (POO), separando el modelo de datos, la lógica de negocio y la interfaz de usuario.

---

## 🚀 Características y Funcionalidades

La aplicación implementa las siguientes operaciones en su menú:

1. **Añadir modelo.Cliente:** Registra un nuevo cliente asignándole un **ID automático y secuencial**. Se capturan datos como nombre, apellidos, ciudad, teléfono, email y fecha de nacimiento.
2. **Listar Clientes:** Muestra por consola todos los clientes registrados actualmente.
3. **Actualizar modelo.Cliente (Control Total):** Permite modificar cualquier dato de un cliente existente por su ID. Cuenta con una mejora de UX: **si pulsas ENTER, se mantiene el dato actual** sin obligar a reescribir un dato que no sea necesario modificar.
4. **Eliminar modelo.Cliente:** Borra un registro del sistema de forma segura mediante su ID.
5. **Buscar por Ciudad:** Filtra y muestra la ficha detallada de todos los clientes que pertenecen a una ciudad específica (búsqueda insensible a mayúsculas/minúsculas).

---

## 📦 Estructura del Proyecto

El código está organizado en tres clases principales para garantizar la separación de responsabilidades:

* 📄 `modelo.Cliente.java`: Clase modelo que representa los atributos de un cliente y gestiona la generación automática del ID mediante un contador estático.
* 📄 `repositorio.GestionClientes.java`: El motor del backend. Contiene el mapa de almacenamiento y encapsula toda la lógica de los métodos CRUD.
* 📄 `Main.java`: La interfaz de usuario. Controla el bucle del menú interactivo, la lectura del teclado con `Scanner` y el formateo de las pantallas.

---
