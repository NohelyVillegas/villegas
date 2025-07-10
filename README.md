# BanQuito - Gestión de Turnos de Caja

## Descripción
Aplicación Spring Boot + MongoDB para la gestión de turnos de caja y transacciones en ventanillas bancarias. Permite abrir turnos, procesar transacciones (depósitos/retiros) y cerrar turnos con validación de efectivo.

---

## Requisitos
- Java 17 o superior
- Maven
- Docker (para MongoDB)

---

## 1. Levantar MongoDB con Docker
```sh
docker run --name mongo-banquito -d -p 27017:27017 mongo:latest
```
Esto deja MongoDB escuchando en `localhost:27017`.

---

## 2. Configuración de la aplicación
En `src/main/resources/application.properties`:
```
spring.data.mongodb.uri=mongodb://localhost:27017/banquito
server.port=8080
```

---

## 3. Compilar y correr la aplicación
```sh
mvn clean install
mvn spring-boot:run
```

---

## 4. Probar la API (Swagger UI)
Abre en tu navegador:
```
http://localhost:8080/swagger-ui.html
```

---

## 5. Casos de prueba recomendados

### a) Iniciar turno
`POST /v1/turnos-caja/iniciar`
```json
{
  "id": "CAJ01-USU01-20250709",
  "codigoCaja": "CAJ01",
  "codigoCajero": "USU01",
  "montoInicial": 1000.00
}
```

### b) Procesar transacción
`POST /v1/turnos-caja/transacciones`
```json
{
  "codigoTurno": "CAJ01-USU01-20250709",
  "codigoCaja": "CAJ01",
  "codigoCajero": "USU01",
  "tipoTransaccion": "DEPOSITO",
  "montoTotal": 500.00,
  "denominaciones": [
    { "billete": 50, "cantidad": 10, "monto": 500.00 }
  ]
}
```

### c) Cerrar turno
`PATCH /v1/turnos-caja/cerrar/CAJ01-USU01-20250709`
```json
[
  { "billete": 50, "cantidad": 10, "monto": 500.00 },
  { "billete": 20, "cantidad": 25, "monto": 500.00 }
]
```

### d) Consultar turnos y transacciones
- `GET /v1/turnos-caja`
- `GET /v1/turnos-caja/CAJ01-USU01-20250709/transacciones`

---

## 6. Buenas prácticas y tips
- No envíes el campo `version` ni campos calculados al crear turnos/transacciones.
- Usa IDs reales y únicos para los turnos.
- Si ves errores 404 o 500, revisa que los datos enviados sean correctos y que el turno exista.

---

## 7. Dockerizar la aplicación (opcional)

### a) Crear el archivo Dockerfile

```
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/villegas-*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

### b) Crear el archivo docker-compose.yml

```
version: '3.8'
services:
  mongo:
    image: mongo:latest
    container_name: mongo-banquito
    ports:
      - "27017:27017"
    volumes:
      - mongo_data:/data/db
  app:
    build: .
    container_name: villegas-app
    ports:
      - "8080:8080"
    depends_on:
      - mongo
    environment:
      - SPRING_DATA_MONGODB_URI=mongodb://mongo:27017/banquito
volumes:
  mongo_data:
```

### c) Cambia la URI de MongoDB en application.properties para producción Docker
```
spring.data.mongodb.uri=mongodb://mongo:27017/banquito
```

### d) Construir y levantar todo
```sh
docker-compose up --build
```

---

¡Listo! Tu app y MongoDB estarán corriendo en contenedores y accesibles en `http://localhost:8080/swagger-ui.html`. 