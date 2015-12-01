package itson.sushivan.adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import itson.sushivan.PorGrupoActivity;
import itson.sushivan.R;
import itson.sushivan.modelo.Grupo;

/**
 * Created by Alberto on 29/11/2015.
 */
public class GrupoAdapter extends RecyclerView.Adapter<GrupoAdapter.ViewHolder> {

    private static List<Grupo> grupos;
    static {
        grupos = new ArrayList<>();
        grupos.add(new Grupo(R.drawable.platillo_pechuga_en_crema, "Platillos", Grupo.TipoGrupo.PLATILLO));
        grupos.add(new Grupo(R.drawable.entremes_deditos_de_philadelphia, "Entremeses", Grupo.TipoGrupo.ENTREMES));
        grupos.add(new Grupo(R.drawable.rollo_california_roll, "Rollos", Grupo.TipoGrupo.ROLLO));
        grupos.add(new Grupo(R.drawable.bomba_1, "Bombas", Grupo.TipoGrupo.BOMBA));
    }

    @Override
    public GrupoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grupo_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(GrupoAdapter.ViewHolder holder, int position) {
        final Grupo grupo = grupos.get(position);
        holder.imagen.setImageDrawable(holder.imagen.getContext().getResources().getDrawable(grupo.getImagen()));
        holder.nombre.setText(grupo.getNombre());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PorGrupoActivity.class);
                if(grupo.getTipo() == Grupo.TipoGrupo.PLATILLO){
                    intent.putExtra("grupo", Grupo.TipoGrupo.PLATILLO.getValue());
                }else if(grupo.getTipo() == Grupo.TipoGrupo.ENTREMES){
                    intent.putExtra("grupo", Grupo.TipoGrupo.ENTREMES.getValue());
                }else if(grupo.getTipo() == Grupo.TipoGrupo.ROLLO){
                    intent.putExtra("grupo", Grupo.TipoGrupo.ROLLO.getValue());
                }else if(grupo.getTipo() == Grupo.TipoGrupo.BOMBA){
                    intent.putExtra("grupo", Grupo.TipoGrupo.BOMBA.getValue());
                }
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return grupos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imagen;
        public TextView nombre;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.grupo_card);
            imagen = (ImageView)itemView.findViewById(R.id.imagen_grupo);
            nombre = (TextView)itemView.findViewById(R.id.nombre_grupo);
        }
    }
}
