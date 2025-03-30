
# Prueba técnica para Inditex

Prueba para Inditex que consiste en la implementacion de un servicio Rest para recuperar la tarifa y el precio a aplicar dado un producto de una cadena en una fecha concreta.

Hecho con java 17, Maven 3.9.9 y Spring Boot 3.4.4

Para definir la Api he usado OpenApi Generator y para la implementacion de mapas MapStructs.

Al arrancar se levantará un Servicio Rest en localhost:8080 cuyo endpoint seria:
```bash
http://localhost:8080/getRates
```

Teniendo como parametros de entrada los siguientes (en este orden):

**dateOfApplication:** Fecha de la que queremos recuperar la tarifa activa, se debe de introducir en el siguiente formato "2020-06-15T00:00:00Z"

- **2020-06-15:** La fecha en formato AAAA-MM-DD (año-mes-día).

- **T:** El separador entre la fecha y la hora.

- **00:00:00:** La hora en formato de 24 horas HH:mm:ss (hora:minuto:segundo).

- **Z:** El indicador de zona horaria UTC (Coordinated Universal Time), también conocido como "Zulu time", que significa que la hora está en el tiempo universal coordinado (UTC), con un desplazamiento de +00:00.

**productId:** Identificador de producto

**chainId:** Identificador de cadena

Si existe una tarifa de ese producto a aplicar en la fecha dada, el servicio devolverá una respuesta en formato JSON como la siguiente:
```bash
{
    "productId": 35455,
    "brandId": 1,
    "rateId": 1,
    "startDate": "2020-06-14T00:00:00Z",
    "endDate": "2020-12-31T23:59:59Z",
    "price": 35.50
}
```