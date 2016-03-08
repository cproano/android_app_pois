# Titulars Cloud

Exemple que mostra com fer peticions HTTP (POST, GET, PUT, etc.) a una API REST
Utilitza les següents llibreries:
* [RoboSpice](https://github.com/stephanenicolas/robospice) per a fer les tasques asíncrones mitjançant un Service.
* [Retrofit](http://square.github.io/retrofit/) per a mapejar l'API REST a una interface Java.
* [Gson](https://github.com/google/gson) per a convertir la responsta JSON a objectes del model.

L'API REST que utilitza l'exemple té els següent mètodes:

| URL                | Mètode  | Paràmetres          | Descripció                |
| ------------------ |---------| --------------------|---------------------------|
| /titulars          | GET     |                     | Obté tots els titulars    |
| /titulars/:id      | GET     |                     | Obté un únic titular      |
| /titulars          | POST    | titol, subtitol     | Afegir un nou titular     |
| /titulars/:id      | PUT     | titol, subtitol     | Actualitzar un titular    |
| /titulars/:id      | DELETE  |                     | Eliminar un titular       |
