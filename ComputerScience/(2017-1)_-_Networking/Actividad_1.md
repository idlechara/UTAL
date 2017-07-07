# ¿Qué es un protocolo de enrutamiento?
Los protocolos de enrutamiento son rutinas que permiten (en el caso general) optimizar la ruta trazada por un paquete dentro de una red. 

# ¿Qué diferencia hay entre un protocolo estático y dinámico?

* **Estáticos:** Ignoran el estado de la red al momento de realizar el cálculo para la ruta. Un ejemplo de este es utilizar Djikstra sobre un grafo para calcular la ruta mínima entre dos nodos. El problema de los métodos estáticos es el coste del tiemo que implica calcularlas, además del conocimiento completo de la red y la dinamicidad del flujo de red.

* **Dinámicos:** Subsanan el problema el flujo de red, recalculando la ruta a medida que pasa el tiempo. Estos se dividen en tres tipos, dependiendo como la información es procesada:

* Centralizado: Un nodo centraliza los calculos y las decisiones de enrutamiento.
* Distribuido: Cada nodo se hace cargo de calcular por si mismo sus propias rutas.
* Aislado: Se utiliza solo información propia y local de cada nodo. El más similar a laberintos es flood-fill.

