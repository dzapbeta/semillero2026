# language: es
# ---------------------------------------------------------------------------------------------
# Feature de CONSULTA DE PRODUCTOS. Endpoints: GET /products/{id} y GET /products/search?q=
# Muestra el NIVEL 2 de data driven: los Ejemplos solo traen el id del CASO; los datos esperados
# (id, título, categoría, precio) se leen del archivo src/test/resources/data/productos.csv
# ---------------------------------------------------------------------------------------------

# @productos agrupa este feature y el de creación
@productos
Característica: Consulta de productos en la API de DummyJSON
  Como analista QA del semillero
  Quiero consultar productos por id y por texto
  Para validar que la API devuelve la información correcta

  # Cada fila de Ejemplos es un caso del CSV. Para probar un producto nuevo: agregar la fila al CSV y su caso aquí
  @consulta @csv
  Esquema del escenario: Consultar el producto del caso <caso> con datos del archivo CSV
    Dado que el analista carga el caso "<caso>" del archivo de productos
    Cuando consulta el producto de ese caso
    Entonces el código de respuesta debe ser 200
    Y el producto devuelto tiene el título, la categoría y el precio del archivo

    # Columna: caso = valor de la columna "caso" en data/productos.csv
    Ejemplos:
      | caso |
      | P01  |
      | P02  |
      | P03  |
      | P04  |
      | P05  |

  # Caso negativo: un id que no existe responde 404 con un mensaje que incluye el id
  @consulta @negativo
  Escenario: Consultar un producto que no existe
    Cuando el analista consulta el producto con id "99999"
    Entonces el código de respuesta debe ser 404
    Y el campo "message" de la respuesta debe ser "Product with id '99999' not found"

  # Búsqueda por texto: se valida el total de resultados (valores reales de la API)
  @busqueda
  Esquema del escenario: Buscar productos con el texto "<texto>"
    Cuando el analista busca productos con el texto "<texto>"
    Entonces el código de respuesta debe ser 200
    Y el campo "total" de la respuesta debe ser "<total>"

    # Columnas: texto buscado y cantidad total de resultados que devuelve la API
    Ejemplos:
      | texto    | total |
      | iphone   | 8     |
      | laptop   | 5     |
      | zzzznada | 0     |
