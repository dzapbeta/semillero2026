# language: es
# La primera línea le dice a Cucumber que las palabras clave van en español.

# El tag @login queda en todos los escenarios de este archivo.
@login
Característica: Inicio de sesión en Sauce Demo
  Como comprador de la tienda
  Quiero entrar con mi usuario y mi clave
  Para poder ver los productos

  # Antecedentes: este paso se ejecuta antes de cada escenario.
  Antecedentes:
    Dado que "Ana" abre la página de Sauce Demo

  # Camino feliz: un usuario válido entra y ve la página de productos.
  @exitoso
  Escenario: Inicio de sesión exitoso
    Cuando inicia sesión con el usuario "standard_user" y la clave "secret_sauce"
    Entonces debería ver el título "Products"

  # Camino alterno: un usuario bloqueado no entra y la página le dice por qué.
  @bloqueado
  Escenario: Usuario bloqueado
    Cuando inicia sesión con el usuario "locked_out_user" y la clave "secret_sauce"
    Entonces debería ver el mensaje de error "Epic sadface: Sorry, this user has been locked out."
