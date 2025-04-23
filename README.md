[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/tc38IXJF)
# 📚 Trabajo Práctico: Sistema de Gestión de Biblioteca Digital (Java 21+)

## 📌 Objetivo General

Desarrollar un sistema de gestión de biblioteca digital que implemente los cinco principios SOLID, programación orientada a objetos, y conceptos avanzados de Java. El sistema deberá manejar diferentes tipos de recursos digitales, préstamos, reservas, y notificaciones en tiempo real.

## 👨‍🎓 Información del Alumno
- **Nombre y Apellido**: Ian Olmedo

## Documentacion Particular

## DESCRIPCIÓN GENERAL

Este sistema permite gestionar usuarios, recursos digitales, préstamos, reservas, recordatorios y reportes dentro de una biblioteca digital. Diseñado utilizando principios SOLID, el sistema está estructurado en distintas etapas modulares y permite una fácil extensión y mantenimiento.

## ARQUITECTURA DEL SISTEMA

### Estructura General:

- **Modelo**: Entidades principales como Usuario, RecursoDigital, Libro, Revista, Audiolibro, Prestamo, Reserva.
- **Gestores**: Encargados de manejar la lógica de negocio. Ej: GestorUsuarios, GestorRecursos, GestorPrestamos, GestorReservas, GestorRecordatorios, GestorReportes.
- **Consola**: Interfaces de usuario por línea de comandos. Ej: Consola, BuscadorRecursos, BuscadorReportes, MenuPrestamos, MenuReservas.
- **Alertas**: Sistema de notificaciones y recordatorios, con niveles de urgencia.

### Principios SOLID Aplicados:

- **SRP**: Cada clase tiene una sola responsabilidad.
- **OCP**: Nuevas funcionalidades agregadas mediante decoradores e interfaces sin modificar el código existente.
- **LSP**: Las subclases de RecursoDigital pueden usarse sin alterar la funcionalidad.
- **ISP**: Interfaces como Prestable, Renovable segregan funcionalidades.
- **DIP**: ServicioNotificaciones permite inyectar notificaciones por Email o SMS.

## FLUJO DE TRABAJO DEL SISTEMA

1. Registrar usuarios y recursos digitales.
2. Realizar préstamos, devoluciones y reservas.
3. Generar reportes y ver alertas.
4. Mostrar recordatorios y vencimientos.
5. Visualizar el historial de interacciones.

## REQUISITOS PREVIOS

- Java SDK 17 o superior.
- IDE como IntelliJ IDEA, Eclipse, NetBeans o terminal con javac y java.

## PUESTA EN MARCHA

1. **Clonar el repositorio**

   ```bash
   git clone git@github.com:um-programacion-ii/programacion-2-trabajo-practico-2-IanOlmedo.git
   ```

2. **Descargar el compilador de Java (javac)**

   ```bash
   sudo apt install default-jdk
   ```

3. **Compilar el proyecto**

   ```bash
   find src -name "*.java" > sources.txt
   javac -d out @sources.txt
   ```

4. **Ejecutar la aplicación**

   ```bash
   java -cp out app.Main
   ```

   O desde tu IDE, ejecutar la clase `app.Main`.

## GUÍA DE USO Y CASOS DE PRUEBA

### USUARIOS

- Registrar nuevo usuario: ingresar DNI, nombre y correo.
- Visualizar usuarios registrados desde el menú.

### RECURSOS

- Registrar libros, revistas o audiolibros.
- Listarlos o buscarlos por título o categoría.

### PRÉSTAMOS Y RESERVAS

- Realizar préstamo ingresando ID de usuario y título.
- Devolver recurso y liberar su estado.
- Reservar recurso si está en uso (con cola de espera).
- Procesar reservas.

### RECORDATORIOS

- Alertas por préstamos cercanos al vencimiento (24h antes y el día exacto).
- Historial disponible desde el menú principal.

### REPORTES

- Ver recursos más prestados.
- Usuarios más activos.
- Categorías más utilizadas.
- Generación asincrónica usando ExecutorService.

## EJEMPLOS DE FLUJO COMPLETO

### 1. Registro y Préstamo

- Registrar usuario: DNI 123, Nombre Ian, Email ian@gmail.com
- Registrar libro: Título Harry Potter, Autor Daniel Quinteros, Páginas 561
- Realizar préstamo del libro Harry Potter al usuario Ian

### 2. Reserva y Devolución

- Usuario Reynier intenta reservar el libro Harry Potter
- Ian devuelve el recurso
- Reynier recibe el recurso mediante sistema de reservas

### 3. Reportes y Estadísticas

- Ir a "Menú de Reportes" y seleccionar:
  - Recursos más prestados
  - Usuarios más activos
  - Categorías más utilizadas
## 📋 Requisitos Adicionales

### Documentación del Sistema
Como parte del trabajo práctico, deberás incluir en este README una guía de uso que explique:

1. **Cómo funciona el sistema**:
   - Descripción general de la arquitectura
   - Explicación de los componentes principales
   - Flujo de trabajo del sistema

2. **Cómo ponerlo en funcionamiento**:
   - Deberás incluir las instrucciones detalladas de puesta en marcha
   - Explicar los requisitos previos necesarios
   - Describir el proceso de compilación
   - Detallar cómo ejecutar la aplicación

3. **Cómo probar cada aspecto desarrollado**:
   - Deberás proporcionar ejemplos de uso para cada funcionalidad implementada
   - Incluir casos de prueba que demuestren el funcionamiento del sistema
   - Describir flujos de trabajo completos que muestren la interacción entre diferentes componentes

La guía debe ser clara, concisa y permitir a cualquier usuario entender y probar el sistema. Se valorará especialmente:
- La claridad de las instrucciones
- La completitud de la documentación
- La organización de la información
- La inclusión de ejemplos prácticos

### Prueba de Funcionalidades

#### 1. Gestión de Recursos
- **Agregar Libro**: 
  - Proceso para agregar un nuevo libro al sistema
  - Verificación de que el libro se agregó correctamente
  - Validación de los datos ingresados

- **Buscar Recurso**:
  - Proceso de búsqueda de recursos
  - Verificación de resultados de búsqueda
  - Manejo de casos donde no se encuentran resultados

- **Listar Recursos**:
  - Visualización de todos los recursos
  - Filtrado por diferentes criterios
  - Ordenamiento de resultados

#### 2. Gestión de Usuarios
- **Registrar Usuario**:
  - Proceso de registro de nuevos usuarios
  - Validación de datos del usuario
  - Verificación del registro exitoso

- **Buscar Usuario**:
  - Proceso de búsqueda de usuarios
  - Visualización de información del usuario
  - Manejo de usuarios no encontrados

#### 3. Préstamos
- **Realizar Préstamo**:
  - Proceso completo de préstamo
  - Verificación de disponibilidad
  - Actualización de estados

- **Devolver Recurso**:
  - Proceso de devolución
  - Actualización de estados
  - Liberación del recurso

#### 4. Reservas
- **Realizar Reserva**:
  - Proceso de reserva de recursos
  - Gestión de cola de reservas
  - Notificación de disponibilidad

#### 5. Reportes
- **Ver Reportes**:
  - Generación de diferentes tipos de reportes
  - Visualización de estadísticas
  - Exportación de datos

#### 6. Alertas
- **Verificar Alertas**:
  - Sistema de notificaciones
  - Diferentes tipos de alertas
  - Gestión de recordatorios

### Ejemplos de Prueba
1. **Flujo Completo de Préstamo**:
   - Registrar un usuario
   - Agregar un libro
   - Realizar un préstamo
   - Verificar el estado del recurso
   - Devolver el recurso
   - Verificar la actualización del estado

2. **Sistema de Reservas**:
   - Registrar dos usuarios
   - Agregar un libro
   - Realizar una reserva con cada usuario
   - Verificar la cola de reservas
   - Procesar las reservas

3. **Alertas y Notificaciones**:
   - Realizar un préstamo
   - Esperar a que se acerque la fecha de vencimiento
   - Verificar las alertas generadas
   - Probar la renovación del préstamo

## 🧩 Tecnologías y Herramientas

- Java 21+ (LTS)
- Git y GitHub
- GitHub Projects
- GitHub Issues
- GitHub Pull Requests

## 📘 Etapas del Trabajo

### Etapa 1: Diseño Base y Principios SOLID
- **SRP**: 
  - Crear clase `Usuario` con atributos básicos (nombre, ID, email)
  - Crear clase `RecursoDigital` como clase base abstracta
  - Implementar clase `GestorUsuarios` separada de `GestorRecursos`
  - Cada clase debe tener una única responsabilidad clara
  - Implementar clase `Consola` para manejar la interacción con el usuario

- **OCP**: 
  - Diseñar interfaz `RecursoDigital` con métodos comunes
  - Implementar clases concretas `Libro`, `Revista`, `Audiolibro`
  - Usar herencia para extender funcionalidad sin modificar código existente
  - Ejemplo: agregar nuevo tipo de recurso sin cambiar clases existentes
  - Implementar menú de consola extensible para nuevos tipos de recursos

- **LSP**: 
  - Asegurar que todas las subclases de `RecursoDigital` puedan usarse donde se espera `RecursoDigital`
  - Implementar métodos comunes en la clase base
  - Validar que el comportamiento sea consistente en todas las subclases
  - Crear métodos de visualización en consola para todos los tipos de recursos

- **ISP**: 
  - Crear interfaz `Prestable` para recursos que se pueden prestar
  - Crear interfaz `Renovable` para recursos que permiten renovación
  - Implementar solo las interfaces necesarias en cada clase
  - Diseñar menús de consola específicos para cada tipo de operación

- **DIP**: 
  - Crear interfaz `ServicioNotificaciones`
  - Implementar `ServicioNotificacionesEmail` y `ServicioNotificacionesSMS`
  - Usar inyección de dependencias en las clases que necesitan notificaciones
  - Implementar visualización de notificaciones en consola

### Etapa 2: Gestión de Recursos y Colecciones
- Implementar colecciones:
  - Usar `ArrayList<RecursoDigital>` para almacenar recursos
  - Usar `Map<String, Usuario>` para gestionar usuarios
  - Implementar métodos de búsqueda básicos
  - Crear menú de consola para gestión de recursos

- Crear servicios de búsqueda:
  - Implementar búsqueda por título usando Streams
  - Implementar filtrado por categoría
  - Crear comparadores personalizados para ordenamiento
  - Diseñar interfaz de consola para búsquedas con filtros

- Sistema de categorización:
  - Crear enum `CategoriaRecurso`
  - Implementar método de asignación de categorías
  - Crear búsqueda por categoría
  - Mostrar categorías disponibles en consola

- Manejo de excepciones:
  - Crear `RecursoNoDisponibleException`
  - Crear `UsuarioNoEncontradoException`
  - Implementar manejo adecuado de excepciones en los servicios
  - Mostrar mensajes de error amigables en consola

### Etapa 3: Sistema de Préstamos y Reservas
- Implementar sistema de préstamos:
  - Crear clase `Prestamo` con atributos básicos
  - Implementar lógica de préstamo y devolución
  - Manejar estados de los recursos (disponible, prestado, reservado)
  - Diseñar menú de consola para préstamos

- Sistema de reservas:
  - Crear clase `Reserva` con atributos necesarios
  - Implementar cola de reservas usando `BlockingQueue`
  - Manejar prioridad de reservas
  - Mostrar estado de reservas en consola

- Notificaciones:
  - Implementar sistema básico de notificaciones
  - Crear diferentes tipos de notificaciones
  - Usar `ExecutorService` para enviar notificaciones
  - Mostrar notificaciones en consola

- Concurrencia:
  - Implementar sincronización en operaciones de préstamo
  - Usar `synchronized` donde sea necesario
  - Manejar condiciones de carrera
  - Mostrar estado de operaciones concurrentes en consola

### Etapa 4: Reportes y Análisis
- Generar reportes básicos:
  - Implementar reporte de recursos más prestados
  - Crear reporte de usuarios más activos
  - Generar estadísticas de uso por categoría
  - Diseñar visualización de reportes en consola

- Sistema de alertas:
  - Implementar alertas por vencimiento de préstamos:
    - Crear clase `AlertaVencimiento` que monitorea fechas de devolución
    - Implementar lógica de recordatorios (1 día antes, día del vencimiento)
    - Mostrar alertas en consola con formato destacado
    - Permitir renovación desde la alerta
  
  - Crear notificaciones de disponibilidad:
    - Implementar `AlertaDisponibilidad` para recursos reservados
    - Notificar cuando un recurso reservado está disponible
    - Mostrar lista de recursos disponibles en consola
    - Permitir préstamo inmediato desde la notificación
  
  - Manejar recordatorios automáticos:
    - Implementar sistema de recordatorios periódicos
    - Crear diferentes niveles de urgencia (info, warning, error)
    - Mostrar historial de alertas en consola
    - Permitir configuración de preferencias de notificación

- Concurrencia en reportes:
  - Implementar generación de reportes en segundo plano
  - Usar `ExecutorService` para tareas asíncronas
  - Manejar concurrencia en acceso a datos
  - Mostrar progreso de generación de reportes en consola

## 📋 Detalle de Implementación

### 1. Estructura Base
```java
// Interfaces principales
public interface RecursoDigital {
    String getIdentificador();
    EstadoRecurso getEstado();
    void actualizarEstado(EstadoRecurso estado);
}

public interface Prestable {
    boolean estaDisponible();
    LocalDateTime getFechaDevolucion();
    void prestar(Usuario usuario);
}

public interface Notificable {
    void enviarNotificacion(String mensaje);
    List<Notificacion> getNotificacionesPendientes();
}

// Clase base abstracta
public abstract class RecursoBase implements RecursoDigital, Prestable {
    // Implementación común
}
```

### 2. Gestión de Biblioteca
```java
public class GestorBiblioteca {
    private final Map<String, RecursoDigital> recursos;
    private final List<Prestamo> prestamos;
    private final ExecutorService notificador;
    // Implementación de gestión
}
```

### 3. Sistema de Préstamos
```java
public class SistemaPrestamos {
    private final BlockingQueue<SolicitudPrestamo> colaSolicitudes;
    private final ExecutorService procesadorPrestamos;
    // Implementación de préstamos
}
```

## ✅ Entrega y Flujo de Trabajo con GitHub

1. **Configuración del Repositorio**
   - Proteger la rama `main`
   - Crear template de Issues y Pull Requests

2. **Project Kanban**
   - `To Do`
   - `In Progress`
   - `Code Review`
   - `Done`

3. **Milestones**
   - Etapa 1: Diseño Base
   - Etapa 2: Gestión de Recursos
   - Etapa 3: Sistema de Préstamos
   - Etapa 4: Reportes

4. **Issues y Pull Requests**
   - Crear Issues detallados para cada funcionalidad
   - Asociar cada Issue a un Milestone
   - Implementar en ramas feature
   - Revisar código antes de merge

## 📝 Ejemplo de Issue

### Título
Implementar sistema de préstamos concurrente

### Descripción
Crear el sistema de préstamos que utilice hilos y el patrón productor-consumidor para procesar solicitudes de préstamo en tiempo real.

#### Requisitos
- Implementar `BlockingQueue` para solicitudes de préstamo
- Crear procesador de solicitudes usando `ExecutorService`
- Implementar sistema de notificaciones
- Asegurar thread-safety en operaciones de préstamo

#### Criterios de Aceptación
- [ ] Sistema procesa préstamos concurrentemente
- [ ] Manejo adecuado de excepciones
- [ ] Documentación de diseño

### Labels
- `enhancement`
- `concurrency`

## ✅ Requisitos para la Entrega

- ✅ Implementación completa de todas las etapas
- ✅ Código bien documentado
- ✅ Todos los Issues cerrados
- ✅ Todos los Milestones completados
- ✅ Pull Requests revisados y aprobados
- ✅ Project actualizado

> ⏰ **Fecha de vencimiento**: 23/04/2025 a las 13:00 hs

## 📚 Recursos Adicionales

- Documentación oficial de Java 21
- Guías de estilo de código
- Ejemplos de implementación concurrente
- Patrones de diseño aplicados

## 📝 Consideraciones Éticas

### Uso de Inteligencia Artificial
El uso de herramientas de IA en este trabajo práctico debe seguir las siguientes pautas:

1. **Transparencia**
   - Documentar claramente qué partes del código fueron generadas con IA
   - Explicar las modificaciones realizadas al código generado
   - Mantener un registro de las herramientas utilizadas

2. **Aprendizaje**
   - La IA debe usarse como herramienta de aprendizaje, no como reemplazo
   - Comprender y ser capaz de explicar el código generado
   - Utilizar la IA para mejorar la comprensión de conceptos

3. **Integridad Académica**
   - El trabajo final debe reflejar tu aprendizaje y comprensión personal
   - No se permite la presentación de código generado sin comprensión
   - Debes poder explicar y defender cualquier parte del código

4. **Responsabilidad**
   - Verificar la corrección y seguridad del código generado
   - Asegurar que el código cumple con los requisitos del proyecto
   - Mantener la calidad y estándares de código establecidos

5. **Desarrollo Individual**
   - La IA puede usarse para facilitar tu proceso de aprendizaje
   - Documentar tu proceso de desarrollo y decisiones tomadas
   - Mantener un registro de tu progreso y aprendizaje

### Consecuencias del Uso Inadecuado
El uso inadecuado de IA puede resultar en:
- Calificación reducida o nula
- Sanciones académicas
- Pérdida de oportunidades de aprendizaje
- Impacto negativo en tu desarrollo profesional

## 📝 Licencia

Este trabajo es parte del curso de Programación Avanzada de Ingeniería en Informática. Uso educativo únicamente.
