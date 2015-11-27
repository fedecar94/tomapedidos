package py.com.tomapedidos.vendedor;

import java.util.Calendar;

/**
 * Created by tekor on 27/11/2015.
 */
public class Pedido extends Generics {
    //en name se va a guardar el nombre del cliente
    private String idCli;
    private String idUsr;
    private String fecha;
    private Integer total;

    public Pedido() {
        super();
    }

    public Pedido(String name, String idCli, String idUsr, Integer total) {
        super(Integer.toString(Calendar.YEAR) + Integer.toString(Calendar.MONTH) + Integer.toString(Calendar.DATE) + idCli + Integer.toString((int) Math.random() * 1000 + 1000), name);
        this.idCli = idCli;
        this.idUsr = idUsr;
        this.fecha = Integer.toString(Calendar.DATE) + "/" + Integer.toString(Calendar.MONTH) + "/" + Integer.toString(Calendar.YEAR);
        this.total = total;
    }

    public Pedido(String id, String name, String idCli, String idUsr, String fecha, Integer total) {
        super(id, name);
        this.idCli = idCli;
        this.idUsr = idUsr;
        this.fecha = fecha;
        this.total = total;
    }

    public String getIdCli() {
        return idCli;
    }

    public String getIdUsr() {
        return idUsr;
    }

    public String getFecha() {
        return fecha;
    }

    public Integer getTotal() {
        return total;
    }

    public String tit() {
        return fecha;
    }

    public String sub() {
        return "Total: " + Integer.toString(total);
    }
}
