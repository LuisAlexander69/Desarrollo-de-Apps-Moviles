# Habitus Tracker – Extensión del Proyecto

**Habitus Tracker** es una aplicación minimalista para Android diseñada para la gestión de hábitos y tareas personales.  
Ahora, además de la estructura base en Kotlin, se plantea una **visión ampliada** con nuevas funcionalidades y posibilidades de escalabilidad.

---

## Objetivos del Proyecto (Actualizados)
1. Proporcionar una interfaz limpia y minimalista para el seguimiento de hábitos.  
2. Permitir marcar hábitos como completados y visualizar el progreso general.  
3. Establecer una arquitectura base para la integración futura de recordatorios (Push/Local Notifications).  
4. Servir como plantilla escalable para desarrolladores que deseen expandir el proyecto.  
5. **Nuevo:** Integrar métricas de motivación (ej. rachas de días cumplidos, logros desbloqueables).  
6. **Nuevo:** Preparar la base para sincronización en la nube y exportación de datos en formatos como CSV/JSON.  

---

## Herramientas Usadas
- **Android Studio**  
- **Kotlin 1.9+**  
- **AndroidX & Material Design Components**  
- **RecyclerView** para visualización de listas  
- **GitHub** para control de versiones  
- **Nuevo:** Preparación para integración con **Firebase** (notificaciones y backup en la nube).  

---

## Registro de Cambios (Changelog)

### [Actual] - Módulo 8
- Integración del proyecto y código base con el repositorio de GitHub Classroom.  
- Adición de la sección de Changelog al archivo README.  
- Estructura base completada, incluyendo solución de compatibilidad de Gradle y Java 17.  
- **Nuevo:** Documentación inicial de futuras integraciones (Firebase, métricas de hábitos).  

### [Pasado] - Módulos Anteriores
- Generación de la estructura del proyecto Android Studio (`build.gradle.kts`, `AndroidManifest.xml`).  
- Creación de los modelos de datos y lógica (`Habit.kt`, `HabitRepository.kt`).  
- Diseño e implementación de las interfaces visuales XML (Dashboard, Lista de Hábitos, Dialog de creación).  
- Configuración de la lógica principal de la app en `MainActivity.kt` y `HabitAdapter.kt`.  
- Ejecución en dispositivo físico y emulador Android.  
