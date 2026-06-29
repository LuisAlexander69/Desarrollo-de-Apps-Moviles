\# Habitus Tracker – Extensión del Proyecto  



\*\*Habitus Tracker\*\* es una aplicación minimalista para Android diseñada para la gestión de hábitos y tareas personales.  

Además de la estructura base en Kotlin, se plantea una \*\*visión ampliada\*\* con nuevas funcionalidades y posibilidades de escalabilidad.  



\---



\## Objetivos del Proyecto (Actualizados)  

1\. Proporcionar una interfaz limpia y minimalista para el seguimiento de hábitos.  

2\. Permitir marcar hábitos como completados y visualizar el progreso general.  

3\. Establecer una arquitectura base para la integración futura de recordatorios (Push/Local Notifications).  

4\. Servir como plantilla escalable para desarrolladores que deseen expandir el proyecto.  

5\. \*\*Nuevo:\*\* Integrar métricas de motivación (ej. rachas de días cumplidos, logros desbloqueables).  

6\. \*\*Nuevo:\*\* Preparar la base para sincronización en la nube y exportación de datos en formatos como CSV/JSON.  



\---



\## Herramientas Usadas  

\- \*\*Android Studio\*\*  

\- \*\*Kotlin 1.9+\*\*  

\- \*\*AndroidX \& Material Design Components\*\*  

\- \*\*RecyclerView\*\* para visualización de listas  

\- \*\*GitHub\*\* para control de versiones  

\- \*\*Nuevo:\*\* Preparación para integración con \*\*Firebase\*\* (notificaciones y backup en la nube).  



\---



\## Plan de Implementación – Novedades  



\### 1. Métricas de Motivación (Rachas y Logros)  

\- \*\*Habit.kt\*\*: añadir `streakCount` y `lastCompletedTimestamp`.  

\- \*\*Achievement.kt\*\*: nueva clase para logros (ej. “Primer Paso”, “Constancia de Acero”).  

\- \*\*UI (item\_habit.xml / activity\_main.xml)\*\*: mostrar rachas en tarjetas y logros en el dashboard.  



\### 2. Exportación de Datos (CSV/JSON)  

\- \*\*DataExporter.kt\*\*: generar representaciones en CSV y JSON.  

\- \*\*MainActivity\*\*: botones en el dashboard para exportar y compartir archivos vía \*Share Intent\*.  



\### 3. Preparación e Integración de Firebase  

\- \*\*MyFirebaseMessagingService.kt\*\*: estructura base para notificaciones push.  

\- \*\*FirebaseSyncManager.kt\*\*: helper para sincronización de hábitos en la nube.  

\- \*\*build.gradle.kts\*\*: dependencias de Firebase documentadas y listas para integración.  



\### 4. Flujo Real de Diálogo “Añadir Hábito”  

\- \*\*MainActivity\*\*: implementar `dialog\_add\_habit.xml` con campos reales (frecuencia, categoría).  

\- Guardar hábitos con valores ingresados por el usuario.  



\---



\## Archivos a Modificar / Crear  



\- \*\*Modelos de Datos\*\*  

&#x20; - \[MODIFY] `Habit.kt`: añadir campos de racha y fecha de último cumplimiento.  

&#x20; - \[NEW] `Achievement.kt`: clase para logros.  



\- \*\*Lógica y Datos\*\*  

&#x20; - \[MODIFY] `HabitRepository.kt`: manejar rachas y logros.  

&#x20; - \[NEW] `DataExporter.kt`: exportación CSV/JSON.  



\- \*\*Firebase (Stubs)\*\*  

&#x20; - \[NEW] `FirebaseSyncManager.kt`: sincronización en la nube.  

&#x20; - \[NEW] `MyFirebaseMessagingService.kt`: notificaciones.  



\- \*\*Interfaz Gráfica (UI)\*\*  

&#x20; - \[MODIFY] `activity\_main.xml`: botones de exportación y sección de logros/rachas.  

&#x20; - \[MODIFY] `item\_habit.xml`: mostrar racha actual.  

&#x20; - \[MODIFY] `MainActivity.kt`: implementar diálogo real y exportación.  

&#x20; - \[MODIFY] `HabitAdapter.kt`: mostrar contador de racha en lista.  



\- \*\*Documentación\*\*  

&#x20; - \[NEW] `INTEGRATION\_GUIDE.md`: guía para conectar Firebase, Room Database y exportación de datos.  



\---



\## Plan de Verificación  

\- \*\*Visual:\*\* comprobar que los layouts compilen y la interfaz muestre rachas/logros/exportación.  

\- \*\*Lógica:\*\* pruebas de algoritmos de rachas, logros y exportación CSV/JSON.  

