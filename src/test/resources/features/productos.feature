# language: es

# Pruebas del servicio de productos: GET https://dummyjson.com/products/{id}
# Los títulos esperados no están aquí: viven en src/test/resources/datos/productos.csv
@productos
Característica: Consulta de productos con datos de un archivo
  Como analista QA
  Quiero consultar productos usando datos de un archivo CSV
  Para agregar casos nuevos sin tocar el código

  # En Ejemplos solo va el id. El título esperado lo busca el lector CSV con ese id.
  Esquema del escenario: Consultar el producto <id>
    Cuando el analista consulta el producto con id "<id>"
    Entonces el código de respuesta debe ser 200
    Y el título del producto debe ser el que dice el archivo para el id "<id>"

    # Cada id debe existir también en el archivo productos.csv.
    Ejemplos:
      | id |
      | 1  |
      | 2  |
      | 3  |
