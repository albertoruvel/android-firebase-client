package itson.sushivan.modelo;

/**
 * Created by Alberto on 29/11/2015.
 */
public class Grupo {
    private int imagen;
    private String nombre;
    private TipoGrupo tipo;

    public Grupo(int imagen, String nombre, TipoGrupo tipo) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoGrupo getTipo() {
        return tipo;
    }

    public void setTipo(TipoGrupo tipo) {
        this.tipo = tipo;
    }

    public static enum TipoGrupo{
        ENTREMES(1), PLATILLO(2), ROLLO(3), BOMBA(4);

        int value;
        TipoGrupo(int value){
            this.value = value;
        }

        public int getValue(){
            return value;
        }

        public static TipoGrupo getTipo(int value){
            switch(value){
                case 1:
                    return ENTREMES;
                case 2: return PLATILLO;
                case 3: return ROLLO;
                case 4: return BOMBA;
                default: return null;
            }
        }
    }
}
