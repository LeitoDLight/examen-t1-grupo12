# Examen T1 - Desarrollo de Aplicaciones Web II - Grupo 12

Evaluación de Laboratorio T1 (Cibertec, sección T6DO) - Curso 4697, profesor Luis Angel Salvatierra Aquino.

Parte correspondiente: Sincronización usando RabbitMQ (cálculo de Fibonacci entre Productor y Consumidor).

## Integrantes y partes desarrolladas

| Integrante | Parte |
|---|---|
| I201924621 Leonardo Fabricio Dorregaray Guevara | Consumidor RabbitMQ (Fibonacci) |
| I201623265 Alberto Muzaurieta | Productor RabbitMQ (envío de la lista de números) |

## Estructura del repositorio

examen-t1-grupo12/
├── appGrupo12Consumidor/ → Microservicio consumidor RabbitMQ
└── appGrupo12Productor/ → Microservicio productor RabbitMQ


## Tecnologías

- Spring Boot 4.1.1
- Java 25
- Spring Cloud 2025.1.3
- RabbitMQ

## Configuración RabbitMQ

| Parámetro | Valor |
|---|---|
| Queue | Grupo12Queue |
| Exchange | Grupo12Exchange |
| Routing Key | Grupo12Routing |

## Cómo ejecutar

### Requisitos previos
- RabbitMQ corriendo en `localhost:5672` (usuario/clave `guest`/`guest`)
- JDK 25 instalado

### Pasos
1. Levantar el proyecto **Consumidor** (`appGrupo12Consumidor`) — se conecta automáticamente a RabbitMQ y crea la cola `Grupo12Queue`.
2. Levantar el proyecto **Productor** (`appGrupo12Productor`) en el puerto `8081`.
3. Llamar al endpoint del Productor:

GET http://localhost:8081/api/numeros?numbers=1;2;15;8

4. El Consumidor recibe el mensaje, espera 20 segundos y calcula la secuencia de Fibonacci, imprimiendo el resultado en consola.

## Prueba realizada (Consumidor)

Se probó publicando el mensaje `1;2;15;8` directamente en la cola `Grupo12Queue` desde el panel de administración de RabbitMQ, obteniendo el resultado esperado:

Mensaje recibido: 1;2;15;8
Resultado Fibonacci: [1, 1, 610, 21]

## Prueba realizada (Productor)

Con RabbitMQ y el Consumidor levantados, se llamó al endpoint del paso 3 y el Productor respondió:

```
Lista enviada a RabbitMQ correctamente.
```

El mensaje `1;2;15;8` se publicó en `Grupo12Exchange` con la routing key `Grupo12Routing`, llegó a la cola `Grupo12Queue` y el Consumidor lo recibió:

```
Mensaje recibido: 1;2;15;8
```
