package py.com.tomapedidos.empresa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class Main extends AppCompatActivity {
    Firebase ref;
    String fireUrl = "https://tomapedidos.firebaseio.com";
    ArrayList<Generics> array = new ArrayList<Generics>();
    Cliente c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_ac);
        Firebase.setAndroidContext(this);
        ref = new Firebase(fireUrl);
        ref.authWithPassword("empresa@ejemplo.com", "nadiesabe", new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                menu();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                // there was an error
            }
        });

    }
    public void menu(){
        setContentView(R.layout.menu_ac);

        findViewById(R.id.listar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listar();
            }
        });
        findViewById(R.id.anadir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anadir();
            }
        });

    }
    
    public void listar() {
        setContentView(R.layout.menu2_ac);
        
        ((TextView) findViewById(R.id.usuario)).setText("Listar Usuarios");
        ((TextView) findViewById(R.id.cliente)).setText("Listar Clientes");
        ((TextView) findViewById(R.id.pedido)).setText("Listar Pedidos");
        ((TextView) findViewById(R.id.producto)).setText("Listar Productos");
        
        findViewById(R.id.cliente).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clienteListar();
            }
        });
        findViewById(R.id.producto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productoListar();
            }
        });
        findViewById(R.id.pedido).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedidoListar();
            }
        });
        findViewById(R.id.usuario).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedidoListar();
            }
        });
    }
    
    public void anadir() {
        setContentView(R.layout.menu2_ac);
        
        ((TextView) findViewById(R.id.usuario)).setText("Añadir Usuarios");
        ((TextView) findViewById(R.id.cliente)).setText("Añadir Clientes");
        ((TextView) findViewById(R.id.pedido)).setText("Añadir Pedidos");
        ((TextView) findViewById(R.id.producto)).setText("Añadir Productos");
        
        findViewById(R.id.cliente).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clienteAnadir();
            }
        });
        findViewById(R.id.producto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productoAnadir();
            }
        });
        findViewById(R.id.pedido).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedidoAnadir();
            }
        });
        findViewById(R.id.usuario).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedidoAnadir();
            }
        });
    }

    public void listarCli() {
        setContentView(R.layout.listar_ac);
        findViewById(R.id.menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu();
            }
        });
        ref = new Firebase(fireUrl).child("pedido").child(c.getId());
        array.clear();
        ((TextView) findViewById(R.id.cosa)).setText("Clientes");
        ((TextView) findViewById(R.id.message)).setText("Cargando");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Pedido u = postSnapshot.getValue(Pedido.class);
                    array.add(u);
                    listalista(array);
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    public void clienteListar() {
        setContentView(R.layout.listar_ac);
        findViewById(R.id.menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu();
            }
        });
        ref = new Firebase(fireUrl).child("cliente");
        array.clear();
        ((TextView) findViewById(R.id.cosa)).setText("Clientes");
        ((TextView) findViewById(R.id.message)).setText("Cargando");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Cliente u = postSnapshot.getValue(Cliente.class);
                    array.add(u);
                    listalista(array);
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    public void productoListar() {
        setContentView(R.layout.listar_ac);
        findViewById(R.id.menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu();
            }
        });
        ref = new Firebase(fireUrl).child("producto");
        array.clear();
        ((TextView) findViewById(R.id.cosa)).setText("Productos");
        ((TextView) findViewById(R.id.message)).setText("Cargando");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Producto u = postSnapshot.getValue(Producto.class);
                    array.add(u);
                    listalista(array);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    public void listalista(ArrayList<?> listisima){
        ListView lista = (ListView) findViewById(R.id.lista);
        lista.setAdapter(new listaAdaptador(this, R.layout.itemlista, listisima) {
            @Override
            public void onEntrada(Object entrada, View view) {
                TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textTitulo);
                texto_superior_entrada.setText(((Generics) entrada).tit());

                TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.textSubTitulo);
                texto_inferior_entrada.setText(((Generics) entrada).sub());
            }
        });
        ((TextView) findViewById(R.id.message)).setText("Lista completa");
    }
}
