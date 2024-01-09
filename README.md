# CatBreedsApp - Explorador de Razas de Gatos y Votaciones

CatBreedsApp es una aplicación Android desarrollada en Kotlin que permite a los usuarios explorar información sobre diversas razas de gatos utilizando la API REST proporcionada por The Cat API. La aplicación incluye funcionalidades para obtener una lista de razas, ver detalles de una raza específica, realizar votaciones, ver la lista de votos y eliminar votos.

## Configuración del Proyecto

1. **Clonar el Repositorio:**
   ```bash
   git clone https://github.com/VicentePM/CatBreedsApp.git
   ```

2. **Configurar Token API:**
   - Registrarse en [The Cat API](https://thecatapi.com/) para obtener un token.
   - Agregar el token en la aplicación como se indica en las instrucciones proporcionadas.

3. **Ejecutar la Aplicación:**
   - Abrir el proyecto en Android Studio.
   - Conectar un dispositivo Android o utilizar un emulador.
   - Ejecutar la aplicación.

## Funcionalidades de la Aplicación

### Pantalla Principal - Lista de Razas de Gatos

- **Obtener Lista de Razas:**
  - Realiza una solicitud GET al endpoint "breeds" de la API.
  - Muestra un listado de razas de gatos con información detallada.
  - Funcionalidad "tirar para actualizar" para actualizar la lista.

- **Buscar Razas:**
  - Implementa un buscador en el listado para encontrar razas específicas.

- **Acceder a Mis Votaciones:**
  - Botón para acceder a la pantalla que muestra la lista de votos realizados.

### Pantalla Detalle de Raza

- **Visualizar Información de Raza:**
  - Muestra información detallada sobre una raza seleccionada.

- **Votar Imagen:**
  - Permite votar positiva o negativamente una imagen de la raza.
  - Realiza una solicitud POST al endpoint "votes" con el formato JSON especificado.
  - Incluye un botón para votar en la pantalla de detalles de la raza.

### Pantalla Lista de Votos

- **Obtener Lista de Votos:**
  - Realiza una solicitud GET al endpoint "votes" de la API.
  - Muestra un listado de votos realizados con información importante.

- **Eliminar Voto:**
  - Ofrece la opción de eliminar un voto específico.
  - Realiza una solicitud DELETE al endpoint "votes/{id_voto}".

### Pantalla con Pestañas (TabLayout)

- **Implementar TabLayout:**
  - Utiliza un TabLayout para organizar la información en pestañas.
  - Dos fragmentos en la pantalla de detalles de la raza para mostrar información adicional.

## Dependencias Principales

- [Retrofit](https://square.github.io/retrofit/) - Cliente HTTP para realizar solicitudes a la API.
- [Moshi](https://github.com/square/moshi) - Biblioteca para convertir JSON a objetos Kotlin.
- [RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview) - Para mostrar listas de elementos de manera eficiente.
- [CoordinatorLayout](https://developer.android.com/reference/androidx/coordinatorlayout/widget/CoordinatorLayout) - Diseño coordinado con animaciones.

## Contribuciones

¡Contribuciones son bienvenidas! Si encuentras algún problema o tienes sugerencias, por favor, abre un issue o envía un pull request.
