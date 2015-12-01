package itson.sushivan.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import itson.sushivan.InfoProductoActivity;
import itson.sushivan.R;
import itson.sushivan.Utils;
import itson.sushivan.modelo.Grupo;
import itson.sushivan.modelo.Producto;

/**
 * Created by Alberto on 29/11/2015.
 */
public class PorGrupoAdapter extends RecyclerView.Adapter<PorGrupoAdapter.ViewHolder> {

    private List<Producto> productos;

    public PorGrupoAdapter(Grupo.TipoGrupo tipoGrupo){
        creaProductos(tipoGrupo);
    }

    private void creaProductos(Grupo.TipoGrupo tipoGrupo) {
        switch(tipoGrupo){
            case PLATILLO:
                productos = Utils.PLATILLOS;
                break;
            case BOMBA:
                productos = Utils.BOMBAS;
                break;
            case ENTREMES:
                productos = Utils.ENTREMESES;
                break;
            case ROLLO:
                productos = Utils.ROLLOS;
                break;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.producto_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Producto p = productos.get(position);
        holder.imagen.setImageDrawable(holder.imagen.getContext().getResources().getDrawable(p.getImagen()));
        holder.nombre.setText(p.getTitulo());
        holder.precio.setText("$ " + p.getCosto() + " MXN");
        holder.ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), InfoProductoActivity.class);
                Utils.guardaProducto(v.getContext(), p);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imagen;
        TextView nombre, precio;
        Button ver;


        public ViewHolder(View itemView) {
            super(itemView);
            this.imagen = (ImageView)itemView.findViewById(R.id.producto_imagen);
            this.nombre = (TextView)itemView.findViewById(R.id.producto_nombre);
            this.precio = (TextView)itemView.findViewById(R.id.producto_precio);
            this.ver = (Button)itemView.findViewById(R.id.ver_producto_boton);
        }
    }
}
