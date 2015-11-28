package py.com.tomapedidos.vendedor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    private Firebase ref;
    final private String fireUrl = "https://tomapedidos.firebaseio.com/";
    private Usuario u;
    private Cliente c;
    private ArrayList<Generics> array = new ArrayList<Generics>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        ref = new Firebase(fireUrl);
        setContentView(R.layout.login_ac);
        login();
    }

    public void login() {
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String un = ((EditText) findViewById(R.id.username)).getText().toString();
                String pw = ((EditText) findViewById(R.id.password)).getText().toString();
                ref.authWithPassword(un, pw, new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        ((TextView) findViewById(R.id.message)).setText("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                        u = new Usuario(authData.getUid(),un);
                        menu();
                    }
                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        ((TextView) findViewById(R.id.message)).setText("Error: "+firebaseError.getMessage());
                    }
                });
            }
        });
    }

    public void menu() {
        c=null;
        setContentView(R.layout.menu_ac);


        findViewById(R.id.listar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listar();
            }
        });
        findViewById(R.id.tomar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tomar();
            }
        });
        findViewById(R.id.cliente).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cliente();
            }
        });
        findViewById(R.id.producto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                producto();
            }
        });
    }

    public void tomar() {
    }

    public void listar() {
    }

    public void listarCli() {
        setContentView(R.layout.listar_ac);
        ref = new Firebase(fireUrl).child("pedido").child(c.getId());
        array.clear();
        ((TextView) findViewById(R.id.cosa)).setText("Cliente: "+c.getRuc());
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

    public void cliente() {
        setContentView(R.layout.listar_ac);
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

    public void producto() {
        setContentView(R.layout.listar_ac);
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