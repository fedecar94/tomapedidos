package py.com.tomapedidos.vendedor;

/**
 * Created by tekor on 27/11/2015.
 */
public class Empresa extends Generics {
    private String slogan;

    public Empresa() {
        super();
    }

    public Empresa(String id, String name, String slogan) {
        super(id, name);
        this.slogan = slogan;
    }

    public String getSlogan() {
        return slogan;
    }
}