package itson.sushivan;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Firebase;

import itson.sushivan.adapter.CarritoAdapter;
import itson.sushivan.modelo.Pedido;
import itson.sushivan.modelo.Perfil;

public class CarritoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private CarritoAdapter adapter;
    private TextView textView, subtotal, iva, total;
    private CoordinatorLayout coordLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        recyclerView = (RecyclerView)findViewById(R.id.carrito_recycler_view);
        manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        coordLayout = (CoordinatorLayout)findViewById(R.id.coord_layout);
        textView = (TextView)findViewById(R.id.no_productos);
        subtotal = (TextView)findViewById(R.id.subtotal);
        iva = (TextView)findViewById(R.id.iva);
        total = (TextView)findViewById(R.id.total);

        //checha si hay productos en el carrito
        if(Utils.obtenCarrito(this) == null || Utils.obtenCarrito(this).isEmpty()){
            //muestra un snackbar
            recyclerView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        }else{
            adapter = new CarritoAdapter(this);
            recyclerView.setAdapter(adapter);
            //setea textos de total, iva y subtotal
            double valorSubtotal = adapter.getTotal();
            subtotal.setText("Subtotal: $ " + valorSubtotal + " MXN");
            //calcula el iva
            double valorIva = valorSubtotal * .16;
            iva.setText("IVA: $ " + Math.round(valorIva) + " MXN");
            //calcula el total
            double valorTotal = valorSubtotal + valorIva;
            total.setText("Total: $ " + valorTotal + " MXN");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.carrito, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.comprar:
                //envia el pedido con firebase
                //crea un pedido
                Pedido pedido = new Pedido();
                Perfil perfil = Utils.getPerfil(this);
                if(perfil == null)
                    perfil = new Perfil();
                pedido.setPerfil(perfil);
                pedido.setCarrito(Utils.obtenCarrito(this));
                Firebase ref = Utils.getFirebase().child("pedidos")
                        .child(pedido.getId());

                ref.setValue(pedido);
                ref.push();
                //limpia el carrito
                Utils.limpiaCarrito(this);
                Snackbar.make(coordLayout, "Se ha hecho el pedido", Snackbar.LENGTH_INDEFINITE)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        })
                        .show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
