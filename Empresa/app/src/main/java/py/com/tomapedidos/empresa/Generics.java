package py.com.tomapedidos.vendedor;

/**
 * Created by tekor on 27/11/2015.
 */
public class Generics {
    private String id;
    private String name;

    public Generics() {
    }

    public Generics(String i, String s) {
        id = i;
        name = s;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String tit() {
        return name;
    }

    public String sub() {
        return id;
    }
}
