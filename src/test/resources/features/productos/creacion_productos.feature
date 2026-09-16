# language: es
# ---------------------------------------------------------------------------------------------
# Feature de CREACIÓN DE PRODUCTOS. Endpoint: POST /products/add
# Muestra el NIVEL 3 de data driven: un DataTable de Cucumber que se convierte automáticamente
# en el modelo Producto (ver stepsdefinitions/ConversionDeDatos.java).
# Nota: DummyJSON simula la creación (responde 201 y devuelve los datos con un id), pero no la guarda.
# ---------------------------------------------------------------------------------------------

@productos @crear
Característica: Creación de productos en la API de DummyJSON
  Como analista QA del semillero
  Quiero crear productos enviando sus datos en una tabla
  Para validar que la API responde con el producto creado

  # La primera fila de la tabla son los nombres de los campos; la segunda, los valores del producto
  @datatable
  Escenario: Crear un producto de accesorios con datos de una tabla
    Cuando el analista crea un producto con los datos:
      | titulo              | categoria  | precio | marca     | stock |
      | Teclado Mecánico QA | accesorios | 199.9  | Semillero | 15    |
    Entonces el código de respuesta debe ser 201
    Y la respuesta devuelve el producto creado con un id y los mismos datos enviados

  # Mismo paso con otros datos: el código Java no cambia, solo la tabla
  @datatable
  Escenario: Crear un producto de oficina con datos de una tabla
    Cuando el analista crea un producto con los datos:
      | titulo                | categoria | precio | marca       | stock |
      | Silla Ergonómica Café | oficina   | 450.5  | Semillero   | 3     |
    Entonces el código de respuesta debe ser 201
    Y la respuesta devuelve el producto creado con un id y los mismos datos enviados
