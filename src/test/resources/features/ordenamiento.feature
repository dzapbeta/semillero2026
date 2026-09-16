# language: es
# Palabras clave de Gherkin en español.

# Tags: @ordenamiento para ejecutar solo este archivo; @regresion para la suite completa.
@ordenamiento @regresion
# Característica: orden del catálogo de productos.
Característica: Ordenamiento del catálogo de productos
  Como comprador
  Quiero ordenar los productos
  Para encontrar más rápido lo que busco

  # Antecedentes: tienda abierta y sesión iniciada antes de cada escenario.
  Antecedentes:
    Dado que "Carlos" abre la tienda Sauce Demo
    Y inicia sesión como usuario "estandar"

  # Escenario: al ordenar por precio ascendente, el producto más barato queda primero.
  Escenario: Ordenar los productos por precio de menor a mayor
    Cuando ordena los productos por "precio de menor a mayor"
    Entonces el primer producto de la lista debería ser "Sauce Labs Onesie"
