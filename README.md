# Titulars Cloud

Exemple que mostra com fer peticions HTTP (POST, GET, PUT, etc.) a una API REST
Utilitza les següents llibreries:
* [RoboSpice](https://github.com/stephanenicolas/robospice) per a fer les tasques asíncrones mitjançant un Service.
* [Retrofit](http://square.github.io/retrofit/) per a mapejar l'API REST a una interface Java.
* [Gson](https://github.com/google/gson) per a convertir la responsta JSON a objectes del model.

L'API REST que utilitza l'exemple té els següent mètodes:

| URL                | Mètode  | Paràmetres          | Descripció                  |
| ------------------ |---------| --------------------|-----------------------------|
| /pois              | GET     |                     | Obté tots els pois          |
| /pois/:nomCiutat   | GET     |                     | Obté pois d'una única ciutat|

