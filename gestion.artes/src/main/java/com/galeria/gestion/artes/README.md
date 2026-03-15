# 🎨 API REST - Gestión de Galería de Arte

Backend desarrollado con **Java Spring Boot** y **MongoDB** para la gestión integral de una galería de arte. El sistema permite administrar el inventario de obras, el catálogo de artistas y un sistema de reservas temporales (holds) para coleccionistas o instituciones.

Esta API ha sido diseñada teniendo en cuenta el ecosistema de la gestión cultural, el mercado del arte y el peritaje/tasación.

---

## 🚀 Características Técnicas Destacadas (Requisitos AD06)

* **Bases de Datos Documentales (MongoDB):** Uso de repositorios de Spring Data MongoDB.
* **Múltiples Colecciones:** El sistema gestiona las colecciones `artistas`, `obras` y `holds`.
* **Documentos Anidados:** El historial de alquileres (`ArtLeasing`) se guarda como un array anidado dentro de cada `Obra`.
* **Referencias Cruzadas (`@DBRef`):** Las obras hacen referencia a su `Artista` creador, y los holds referencian a la `Obra` reservada, evitando la duplicación de datos.
* **Patrón DTO:** Se utilizan `ObraFichaTecnicaDTO` y `ObraInventarioDTO` para filtrar información financiera (precios y clientes) en los endpoints públicos.
* **Índices TTL:** La colección `holds` tiene un índice que destruye automáticamente la reserva pasadas 48 horas (`expireAfter = "48h"`).
* **Robustez de Datos (Wrappers):** Uso de clases Wrapper (Integer, Double) en los modelos para permitir valores nulos, esencial para distinguir entre obras sin tasar y obras con valor cero.
---

## 🌐 Endpoints de la API

### 🧑‍🎨 Colección: Artistas
Gestión de los creadores de las obras.

* `GET /api/artistas` : Obtiene la lista de todos los artistas.
* `GET /api/artistas/{id}` : Obtiene los datos de un artista específico (validando su existencia con `Optional`).
* `POST /api/artistas` : Registra un nuevo artista.
* `PUT /api/artistas/{id}` : Actualiza los datos de un artista existente (asegurando la integridad del ID de la URL con `setId`).
* `DELETE /api/artistas/{id}` : Elimina un artista del sistema de forma segura.

### 🖼️ Colección: Obras (Gestión Granular y DTOs)
Gestión del inventario y catálogo. Incluye el historial anidado de alquileres (`ArtLeasing`).

* `GET /api/obras` : Obtiene la lista general de obras sin filtrar.
* `GET /api/obras/catalogo` : Obtiene el catálogo público (Usa FichaTecnicaDTO para ocultar precios e historial de clientes).
* `GET /api/obras/inventario` : Obtiene el inventario interno completo para peritaje (Usa InventarioDTO con toda la información financiera).
* `GET /api/obras/{id}` : Obtiene la información detallada de una obra por su ID.
* `POST /api/obras` : Registra una nueva obra (requiere el ID del artista en el body para el `@DBRef`).
* `PUT /api/obras/{id}` : Actualiza la información general de una obra.
* `POST /api/obras/{id}/leasing` : **(Add)** Añade un nuevo contrato de alquiler al historial de la obra sin sobreescribir el resto del documento.
* `DELETE /api/obras/{id}/leasing/{cliente}` : **(Remove)** Elimina un contrato de alquiler específico buscando por el nombre del cliente.
* `DELETE /api/obras/{id}` : Retira una obra del inventario.

### ⏳ Colección: Holds (Reservas)
Gestión de reservas temporales de obras.

* `GET /api/holds` : Obtiene todas las reservas activas.
* `GET /api/holds/{id}` : Obtiene una reserva específica.
* `POST /api/holds` : Crea una nueva reserva para un coleccionista (Genera automáticamente la fecha de inicio del contador TTL de 48h).
* `DELETE /api/holds/{id}` : Cancela/elimina una reserva manualmente antes de que expire.