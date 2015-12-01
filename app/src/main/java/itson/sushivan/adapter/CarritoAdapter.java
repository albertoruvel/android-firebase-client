package itson.sushivan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import itson.sushivan.R;
import itson.sushivan.Utils;
import itson.sushivan.modelo.Producto;

/**
 * Created by Alberto on 30/11/2015.
 */
public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.ViewHolder>{

    private List<Producto> productos;

    public CarritoAdapter(Context context){
        this.productos = Utils.obtenCarrito(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.carrito_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Producto p = productos.get(position);
        holder.imagen.setImageDrawable(holder.imagen.getContext().getResources().getDrawable(p.getImagen()));
        holder.precio.setText("Precio: " + p.getCosto() + " MXN");
        holder.nombre.setText(p.getTitulo());
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imagen;
        private TextView nombre, precio;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imagen = (ImageView)itemView.findViewById(R.id.imagen_pedido);
            this.nombre = (TextView)itemView.findViewById(R.id.nombre_pedido);
            this.precio = (TextView)itemView.findViewById(R.id.precio_pedido);
        }
    }

    public double getTotal(){
        double total = 0.0;
        for(Producto p : productos){
            total += p.getCosto();
        }
        return total;
    }
}
