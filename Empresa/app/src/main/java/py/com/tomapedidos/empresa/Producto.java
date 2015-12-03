package py.com.tomapedidos.empresa;

/**
 * Created by tekor on 27/11/2015.
 */
public class Producto extends Generics {
    private Integer precio;
    private Integer cantidad=0;

    public Producto() {
        super();
    }

    public Producto(String id, String name, Integer precio) {
        super(id, name);
        this.precio = precio;
    }

    public void cant(Integer c){this.cantidad=c;}
    public Integer canti(){return cantidad;}

    public Integer getPrecio() {
        return precio;
    }

    public String tit() {
        return "Descripcion: " + getName();
    }

    public String sub() {
        return "Precio: " + Integer.toString(getPrecio());
    }
}
