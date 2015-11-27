package py.com.tomapedidos.vendedor;

/**
 * Created by tekor on 27/11/2015.
 */
public class Cliente extends Generics {
    private String ruc;
    private Integer pass;//se guarda hasheado en el servidor

    public Cliente() {
        super();
    }

    public Cliente(String id, String name, String ruc, Integer pass) {
        super(id, name);
        this.ruc = ruc;
        this.pass = pass;
    }

    public String getRuc() {
        return ruc;
    }

    public Integer getPass() {
        return pass;
    }

    public Boolean confirm(Integer p) {
        return pass == p;
    }

    public String tit() {
        return "Nombre:" + getName();
    }

    public String sub() {
        return "Ruc:" + getRuc();
    }
}
