package py.com.tomapedidos.empresa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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

    private void menu() {
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

    private void listar() {
        setContentView(R.layout.menu2_ac);

        ((Button) findViewById(R.id.usuario)).setText("Listar Usuarios");
        ((Button) findViewById(R.id.cliente)).setText("Listar Clientes");
        ((Button) findViewById(R.id.pedido)).setText("Listar Pedidos");
        ((Button) findViewById(R.id.producto)).setText("Listar Productos");

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
                usuarioListar();
            }
        });
    }

    private void anadir() {
        setContentView(R.layout.menu2_ac);

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
                usuarioAnadir();
            }
        });

        ((Button) findViewById(R.id.pedido)).setText("Añadir Pedidos");
        ((Button) findViewById(R.id.cliente)).setText("Añadir Clientes");
        ((Button) findViewById(R.id.producto)).setText("Añadir Productos");
        ((Button) findViewById(R.id.usuario)).setText("Añadir Usuarios");
    }

    private void anadirPed(){
        
    }

    private void clienteAnadir() {
        setContentView(R.layout.anadir_cli);
        ref = new Firebase(fireUrl).child("cliente");
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cliente cli = new Cliente(
                        Integer.toString(((EditText) findViewById(R.id.ruc)).getText().toString().hashCode()),
                        ((EditText) findViewById(R.id.name)).getText().toString(),
                        ((EditText) findViewById(R.id.ruc)).getText().toString(),
                        ((EditText) findViewById(R.id.pass)).getText().toString().hashCode()
                );
                ref.child(cli.getId());
                ref.setValue(cli);
            }
        });
        anadir();
    }

    private void productoAnadir() {
        setContentView(R.layout.anadir_pro);
        ref = new Firebase(fireUrl).child("producto");
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Producto pro = new Producto(
                        Integer.toString(((EditText) findViewById(R.id.name)).getText().toString().hashCode()),
                        ((EditText) findViewById(R.id.name)).getText().toString(),
                        Integer.parseInt(((EditText) findViewById(R.id.price)).getText().toString())
                );
                ref.child(pro.getId());
                ref.setValue(pro);
            }
        });
        anadir();
    }

    private void pedidoAnadir() {
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
        ListView lista = (ListView) findViewById(R.id.lista);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
                c = (Cliente) pariente.getItemAtPosition(posicion);
            }
        });
        anadirPed();
    }

    private void usuarioAnadir() {
    }

    private void listarPed() {
        setContentView(R.layout.listar_ac);
        findViewById(R.id.menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu();
            }
        });
        ref = new Firebase(fireUrl).child("pedido").child(c.getId());
        array.clear();
        ((TextView) findViewById(R.id.cosa)).setText("Pedidos");
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

    private void clienteListar() {
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

    private void productoListar() {
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

    private void usuarioListar() {

    }

    private void pedidoListar() {
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
        ListView lista = (ListView) findViewById(R.id.lista);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
                c = (Cliente) pariente.getItemAtPosition(posicion);
            }
        });
        listarPed();
    }

    private void listalista(ArrayList<?> listisima) {
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