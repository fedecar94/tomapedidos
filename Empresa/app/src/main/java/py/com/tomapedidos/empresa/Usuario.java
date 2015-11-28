package py.com.tomapedidos.empresa;

/**
 * Created by tekor on 27/11/2015.
 */
public class Usuario extends Generics {
    private Integer pass;
    private String mail;

    public Usuario() {
        super();
    }

    public Usuario(String id, String name, String mail, Integer pass) {
        super(id, name);
        this.mail = mail;
        this.pass = pass;
    }

    public String getMail() {
        return mail;
    }

    public Integer getPass() {
        return pass;
    }
}
