package co.com.semillero.certificacion.dummyjson.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * MODELO de REQUEST y de RESPONSE: representa un producto de DummyJSON.
 * - Como REQUEST: es el cuerpo que se envía a POST /products/add (nivel 3 de data driven: viene de un DataTable).
 * - Como RESPONSE: es lo que devuelve GET /products/{id} y POST /products/add (se convierte con .as(Producto.class)).
 *
 * POO - ENCAPSULAMIENTO: atributos privados, acceso por getters y setters.
 */
@JsonIgnoreProperties(ignoreUnknown = true) // La API devuelve más campos (rating, tags...); los que no están aquí se ignoran.
@JsonInclude(JsonInclude.Include.NON_NULL)  // Al enviar, no manda campos vacíos (por ejemplo "id": null).
public class Producto {

    /** Identificador que asigna la API. Al crear no se envía; la API lo devuelve en la respuesta. */
    @JsonProperty("id")
    private Integer id;

    /** Nombre del producto ("title" en el JSON). */
    @JsonProperty("title")
    private String titulo;

    /** Categoría del producto ("category" en el JSON), por ejemplo "beauty" o "laptops". */
    @JsonProperty("category")
    private String categoria;

    /** Precio del producto ("price" en el JSON). Se usa Double porque tiene decimales. */
    @JsonProperty("price")
    private Double precio;

    /** Marca del producto ("brand" en el JSON). Algunos productos de la API no tienen marca. */
    @JsonProperty("brand")
    private String marca;

    /** Unidades disponibles ("stock" en el JSON). */
    @JsonProperty("stock")
    private Integer stock;

    /** Constructor vacío: Jackson lo necesita para crear el objeto antes de llenarlo con el JSON de la respuesta. */
    public Producto() {
        // Intencionalmente vacío.
    }

    /** Devuelve el id asignado por la API. */
    public Integer getId() {
        return id;
    }

    /** Asigna el id. */
    public void setId(Integer id) {
        this.id = id;
    }

    /** Devuelve el título. */
    public String getTitulo() {
        return titulo;
    }

    /** Asigna el título. */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /** Devuelve la categoría. */
    public String getCategoria() {
        return categoria;
    }

    /** Asigna la categoría. */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /** Devuelve el precio. */
    public Double getPrecio() {
        return precio;
    }

    /** Asigna el precio. */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /** Devuelve la marca. */
    public String getMarca() {
        return marca;
    }

    /** Asigna la marca. */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /** Devuelve el stock. */
    public Integer getStock() {
        return stock;
    }

    /** Asigna el stock. */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * Compara dos productos SOLO por sus datos de negocio (título, categoría, precio, marca y stock).
     * No compara el id porque al crear, el producto esperado no tiene id y la respuesta sí.
     * POO - POLIMORFISMO: sobrescribimos equals() de la clase Object para dar nuestra propia regla de igualdad.
     */
    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof Producto)) {
            return false;
        }
        Producto that = (Producto) otro;
        return Objects.equals(titulo, that.titulo)
                && Objects.equals(categoria, that.categoria)
                && Objects.equals(precio, that.precio)
                && Objects.equals(marca, that.marca)
                && Objects.equals(stock, that.stock);
    }

    /** Siempre que se sobrescribe equals() hay que sobrescribir hashCode() con los mismos campos (regla de Java). */
    @Override
    public int hashCode() {
        return Objects.hash(titulo, categoria, precio, marca, stock);
    }

    /** Texto legible del producto: es lo que se ve en el reporte cuando una validación falla. */
    @Override
    public String toString() {
        return "Producto{id=" + id + ", titulo='" + titulo + "', categoria='" + categoria + "', precio=" + precio
                + ", marca='" + marca + "', stock=" + stock + "}";
    }
}
