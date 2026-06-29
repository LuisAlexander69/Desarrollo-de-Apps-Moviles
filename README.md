# Plan de Implementación – Novedades de Habitus Tracker

Este plan detalla los cambios y nuevas características a implementar en el proyecto Habitus Tracker para alinearlo con la visión ampliada descrita en el archivo README.

## Cambios Propuestos

### 1. Métricas de Motivación (Rachas y Logros)
- **Modelo `Habit`**: Añadir atributos de `streakCount` (contador de racha) y `lastCompletedTimestamp` (para llevar el control de días seguidos).
- **Logros (Achievements)**: Implementar una clase/objeto `Achievement` para rastrear logros (ej. "Primer Paso", "Constancia de Acero").
- **Interfaz Gráfica (`activity_main.xml` y `item_habit.xml`)**: 
  - Mostrar la racha de cada hábito en la tarjeta del hábito (`item_habit.xml`).
  - Añadir una sección en el Dashboard principal que muestre el total de rachas y los logros desbloqueados.

### 2. Exportación de Datos (CSV/JSON)
- **Helper de Exportación**: Crear `DataExporter.kt` para generar representaciones en formato CSV y JSON de la lista de hábitos.
- **Acción en UI**: Añadir botones en el Dashboard de la actividad principal (`MainActivity`) para exportar a CSV y JSON usando un diálogo de compartir del sistema (Share Intent), permitiendo al usuario enviar el archivo por correo, guardar en archivos, etc.

### 3. Preparación e Integración de Firebase
- **Firebase Messaging/Stub Services**:
  - Crear `MyFirebaseMessagingService.kt` para manejar notificaciones push.
  - Crear `FirebaseSyncManager.kt` para simular y preparar la sincronización de datos de hábitos en la nube.
- **Configuración de Dependencias**: Documentar/agregar dependencias base de Firebase en `build.gradle.kts` (comentadas o declaradas para que estén listas).

### 4. Flujo Real de Diálogo "Añadir Hábito"
- **MainActivity**: Reemplazar la simulación de añadir hábito por una implementación real que infle `dialog_add_habit.xml`, configure los Spinners (`Frequency` y `Category`) y guarde el hábito con los valores reales ingresados por el usuario.

---

## Archivos a Modificar / Crear

### Componente: Modelos de Datos
#### [MODIFY] [Habit.kt](file:///C:/Users/Pool%20Nizama/Downloads/Desarrollo-de-Apps-Moviles-main/Desarrollo-de-Apps-Moviles-main/HabitusTracker/app/src/main/java/com/example/habitustracker/model/Habit.kt)
- Añadir campo `var streakCount: Int` (default 0).
- Añadir campo `var lastCompletedTimestamp: Long?` (default null).

#### [NEW] [Achievement.kt](file:///C:/Users/Pool%20Nizama/Downloads/Desarrollo-de-Apps-Moviles-main/Desarrollo-de-Apps-Moviles-main/HabitusTracker/app/src/main/java/com/example/habitustracker/model/Achievement.kt)
- Clase de datos para definir los logros del usuario (ej. nombre, descripción, si está desbloqueado).

### Componente: Lógica y Datos
#### [MODIFY] [HabitRepository.kt](file:///C:/Users/Pool%20Nizama/Downloads/Desarrollo-de-Apps-Moviles-main/Desarrollo-de-Apps-Moviles-main/HabitusTracker/app/src/main/java/com/example/habitustracker/data/HabitRepository.kt)
- Modificar `toggleHabitCompletion` para manejar el incremento/decremento de rachas.
- Añadir lista y control de logros.
- Añadir métodos para verificar logros según el progreso del usuario.

#### [NEW] [DataExporter.kt](file:///C:/Users/Pool%20Nizama/Downloads/Desarrollo-de-Apps-Moviles-main/Desarrollo-de-Apps-Moviles-main/HabitusTracker/app/src/main/java/com/example/habitustracker/data/DataExporter.kt)
- Funciones para convertir la lista de hábitos a texto formateado en CSV y JSON.

### Componente: Firebase (Stubs de Preparación)
#### [NEW] [FirebaseSyncManager.kt](file:///C:/Users/Pool%20Nizama/Downloads/Desarrollo-de-Apps-Moviles-main/Desarrollo-de-Apps-Moviles-main/HabitusTracker/app/src/main/java/com/example/habitustracker/firebase/FirebaseSyncManager.kt)
- Helper para preparar el respaldo/sincronización en la nube.
#### [NEW] [MyFirebaseMessagingService.kt](file:///C:/Users/Pool%20Nizama/Downloads/Desarrollo-de-Apps-Moviles-main/Desarrollo-de-Apps-Moviles-main/HabitusTracker/app/src/main/java/com/example/habitustracker/firebase/MyFirebaseMessagingService.kt)
- Estructura base para recibir notificaciones en segundo plano.

### Componente: Interfaz Gráfica (UI)
#### [MODIFY] [activity_main.xml](file:///C:/Users/Pool%20Nizama/Downloads/Desarrollo-de-Apps-Moviles-main/Desarrollo-de-Apps-Moviles-main/HabitusTracker/app/src/main/res/layout/activity_main.xml)
- Añadir botones de Exportación (CSV/JSON).
- Añadir sección para mostrar Logros obtenidos y Rachas generales.
#### [MODIFY] [item_habit.xml](file:///C:/Users/Pool%20Nizama/Downloads/Desarrollo-de-Apps-Moviles-main/Desarrollo-de-Apps-Moviles-main/HabitusTracker/app/src/main/res/layout/item_habit.xml)
- Añadir TextView para mostrar la racha actual (ej. `🔥 Racha: 3 días`).
#### [MODIFY] [MainActivity.kt](file:///C:/Users/Pool%20Nizama/Downloads/Desarrollo-de-Apps-Moviles-main/Desarrollo-de-Apps-Moviles-main/HabitusTracker/app/src/main/java/com/example/habitustracker/MainActivity.kt)
- Implementar la inflación real del diálogo de añadir hábito con MaterialAlertDialog.
- Enlazar controles de exportación (CSV/JSON Share Intents).
- Actualizar estadísticas del dashboard (Progreso + Logros + Rachas).
#### [MODIFY] [HabitAdapter.kt](file:///C:/Users/Pool%20Nizama/Downloads/Desarrollo-de-Apps-Moviles-main/Desarrollo-de-Apps-Moviles-main/HabitusTracker/app/src/main/java/com/example/habitustracker/adapter/HabitAdapter.kt)
- Mostrar el contador de racha en la vista del ítem de la lista.

### Componente: Documentación
#### [NEW] [INTEGRATION_GUIDE.md](file:///C:/Users/Pool%20Nizama/Downloads/Desarrollo-de-Apps-Moviles-main/Desarrollo-de-Apps-Moviles-main/INTEGRATION_GUIDE.md)
- Guía detallada explicando la arquitectura y cómo conectar Firebase, Room Database y la exportación de datos de forma definitiva en producción.

---

## Plan de Verificación
1. **Verificación Visual**: Comprobaremos que los layouts compilen y que la estructura de la aplicación sea coherente.
2. **Verificación de Lógica**: Haremos pruebas lógicas de los algoritmos de rachas y logros, así como de la exportación a CSV/JSON.
