package py.com.tomapedidos.vendedor;

/**
 * Created by tekor on 27/11/2015.
 */
public class Usuario extends Generics {

    public Usuario() {
        super();
    }

    public Usuario(String id, String name) {
        super(id, name);
    }

    public String tit(){return "Uid: "+getId();}
    public String sub(){return "mail: "+getName();}

}
