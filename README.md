# BanQuito - Gestión de Turnos de Caja


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
 `http://localhost:8080/swagger-ui.html`. 
 
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

