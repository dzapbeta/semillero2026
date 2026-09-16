# language: es
# Palabras clave de Gherkin en español.

# Tags: @compra agrupa el flujo de compra; @regresion lo incluye en la suite completa.
@compra @regresion
# Característica: compra de productos, de principio a fin.
Característica: Compra de productos en Sauce Demo
  Como comprador con sesión iniciada
  Quiero agregar productos al carrito y pagar
  Para recibir mi pedido

  # Antecedentes: cada escenario empieza con la tienda abierta y la sesión iniciada.
  Antecedentes:
    Dado que "Carlos" abre la tienda Sauce Demo
    Y inicia sesión como usuario "estandar"

  # Escenario: al agregar un producto, el ícono del carrito muestra la cantidad.
  @carrito
  Escenario: Agregar un producto al carrito
    Cuando agrega el producto "Sauce Labs Backpack" al carrito
    Entonces el carrito debería mostrar 1 producto

  # Escenario de punta a punta: agregar, diligenciar datos de envío y confirmar la orden.
  @compraExitosa
  Escenario: Compra exitosa de un producto
    Cuando agrega el producto "Sauce Labs Backpack" al carrito
    Y diligencia los datos de envío con nombre "Ana", apellido "Pérez" y código postal "050001"
    Y finaliza la compra
    Entonces debería ver el mensaje de confirmación "Thank you for your order!"
