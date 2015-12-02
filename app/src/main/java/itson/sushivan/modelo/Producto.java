package itson.sushivan.modelo;

/**
 * Created by Alberto on 29/11/2015.
 */
public class Producto {
    private int imagen;
    private String titulo;
    private String ingredientePrincipal;
    private String descripcion;
    private double costo;
    private String pedidoId; 

    public Producto(int imagen, String titulo, String ingredientePrincipal, String descripcion, double costo) {
        this.imagen = imagen;
        this.titulo = titulo;
        this.ingredientePrincipal = ingredientePrincipal;
        this.descripcion = descripcion;
        this.costo = costo;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIngredientePrincipal() {
        return ingredientePrincipal;
    }

    public void setIngredientePrincipal(String ingredientePrincipal) {
        this.ingredientePrincipal = ingredientePrincipal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public void setPedidoId(String pedidoId){
        this.pedidoId = pedidoId; 
    }

    public String getPedidoId(){
        return this.pedidoId; 
    }
}
