# language: es
# ---------------------------------------------------------------------------------------------
# Feature de AUTENTICACIÓN. Endpoints: POST /auth/login y GET /auth/me (https://dummyjson.com).
# Muestra el NIVEL 1 de data driven: Esquema del escenario + Ejemplos (los datos viven en el feature).
# Cada fila de Ejemplos se convierte en un escenario independiente en el reporte.
# ---------------------------------------------------------------------------------------------

# @autenticacion agrupa todos los escenarios de este archivo para ejecutarlos con un solo tag
@autenticacion
Característica: Inicio de sesión en la API de DummyJSON
  Como analista QA del semillero
  Quiero validar el servicio de autenticación
  Para asegurar que solo las credenciales correctas obtienen un token de acceso

  # Caso positivo con 3 usuarios reales de DummyJSON: la API responde 200, devuelve el usuario y un token
  @login @exitoso
  Esquema del escenario: Iniciar sesión con el usuario válido "<usuario>"
    Cuando el analista inicia sesión con el usuario "<usuario>" y la clave "<clave>"
    Entonces el código de respuesta debe ser 200
    Y el campo "username" de la respuesta debe ser "<usuario>"
    Y la respuesta debe traer un token de acceso

    # Columnas: usuario y clave que se envían en el cuerpo del POST
    Ejemplos:
      | usuario  | clave        |
      | emilys   | emilyspass   |
      | michaelw | michaelwpass |
      | sophiab  | sophiabpass  |

  # Casos negativos: la API responde 400 y un mensaje distinto según el error (valores reales consultados con curl)
  @login @negativo
  Esquema del escenario: Rechazar el inicio de sesión por <motivo>
    Cuando el analista inicia sesión con el usuario "<usuario>" y la clave "<clave>"
    Entonces el código de respuesta debe ser <codigo>
    Y el campo "message" de la respuesta debe ser "<mensaje>"

    # Columnas: motivo (solo para el nombre del escenario), datos enviados y respuesta esperada (código y mensaje)
    Ejemplos:
      | motivo              | usuario  | clave      | codigo | mensaje                        |
      | clave incorrecta    | emilys   | clave123   | 400    | Invalid credentials            |
      | usuario inexistente | noexiste | emilyspass | 400    | Invalid credentials            |
      | campos vacíos       |          |            | 400    | Username and password required |

  # Usa la interacción propia ConsultarConToken: envía la cabecera Authorization: Bearer <token>
  @perfil
  Escenario: Consultar el perfil con el token obtenido al iniciar sesión
    Dado que el analista inició sesión con el usuario "emilys" y la clave "emilyspass"
    Cuando consulta su perfil con el token de acceso
    Entonces el código de respuesta debe ser 200
    Y el campo "username" de la respuesta debe ser "emilys"

  # Caso negativo: sin token la API responde 401
  @perfil @negativo
  Escenario: Consultar el perfil sin token de acceso
    Cuando el analista consulta su perfil sin token de acceso
    Entonces el código de respuesta debe ser 401
    Y el campo "message" de la respuesta debe ser "Access Token is required"
