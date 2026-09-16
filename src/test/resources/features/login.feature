# language: es
# La línea anterior le dice a Cucumber que las palabras clave están en español
# (Característica, Antecedentes, Escenario, Dado, Cuando, Entonces, Y).

# Tags de la característica: todos los escenarios de este archivo heredan @login y @regresion.
@login @regresion
# Característica: describe QUÉ funcionalidad se prueba y POR QUÉ le importa al negocio.
Característica: Inicio de sesión en Sauce Demo
  Como comprador de la tienda Sauce Demo
  Quiero iniciar sesión con mi usuario
  Para poder ver y comprar los productos

  # Antecedentes: pasos que se ejecutan ANTES de cada escenario de este archivo.
  Antecedentes:
    Dado que "Carlos" abre la tienda Sauce Demo

  # Escenario feliz: un usuario válido entra y ve la sección de productos.
  @loginExitoso
  Escenario: Inicio de sesión exitoso con un usuario válido
    Cuando inicia sesión como usuario "estandar"
    Entonces debería ver la sección "Products"

  # Escenario alterno: un usuario bloqueado NO entra y la página explica por qué.
  @usuarioBloqueado
  Escenario: Inicio de sesión rechazado para un usuario bloqueado
    Cuando inicia sesión como usuario "bloqueado"
    Entonces debería ver el mensaje de error "Epic sadface: Sorry, this user has been locked out."
