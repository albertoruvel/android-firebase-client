package itson.sushivan;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import itson.sushivan.modelo.Producto;

public class InfoProductoActivity extends AppCompatActivity {

    private TextView nombre, descripcion, ingrediente, precio;
    private ImageView imagen;
    private Producto producto;
    private CoordinatorLayout coordLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_producto);
        //obtiene el producto guardado
        producto = Utils.obtenProducto(this);
        coordLayout = (CoordinatorLayout)findViewById(R.id.coord_layout);
        if(producto == null){
            finish();
        }
        //obtiene los controles
        nombre = (TextView)findViewById(R.id.nombre_producto);
        descripcion = (TextView)findViewById(R.id.descripcion_producto);
        ingrediente= (TextView)findViewById(R.id.ingrediente_producto);
        precio = (TextView)findViewById(R.id.precio_producto);
        imagen = (ImageView)findViewById(R.id.imagen_info_producto);

        //setea valores
        imagen.setImageDrawable(getResources().getDrawable(producto.getImagen()));
        nombre.setText(producto.getTitulo());
        descripcion.setText(String.format(getString(R.string.descripcion), producto.getDescripcion()));
        ingrediente.setText(String.format(getString(R.string.ingrediente_principal), producto.getIngredientePrincipal()));
        precio.setText(String.format(getString(R.string.precio), producto.getCosto()));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.info_producto, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.carrito:
                //se añade al carrito
                Utils.agregaACarrito(this, producto);
                Snackbar.make(coordLayout, "Se ha agregado el producto al carrito de compras", Snackbar.LENGTH_LONG)
                        .setAction("Ver carrito", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(v.getContext(), CarritoActivity.class);
                                startActivity(intent);
                            }
                        })
                .show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
