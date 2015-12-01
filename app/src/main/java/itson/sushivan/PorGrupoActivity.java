package itson.sushivan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import itson.sushivan.adapter.PorGrupoAdapter;
import itson.sushivan.modelo.Grupo;

public class PorGrupoActivity extends AppCompatActivity {

    private Grupo.TipoGrupo tipoGrupo;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_por_grupo);
        //obtiene el tipo de grupo
        int tipo = getIntent().getIntExtra("grupo", 1);
        tipoGrupo = Grupo.TipoGrupo.getTipo(tipo);
        recyclerView = (RecyclerView)findViewById(R.id.por_grupo_recycler_view);
        manager = new LinearLayoutManager(this);
        adapter = new PorGrupoAdapter(tipoGrupo);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        TextView grupo = (TextView)findViewById(R.id.tipo_grupo);
        switch(tipoGrupo){
            case BOMBA:
                grupo.setText("Bombas");
                break;
            case ROLLO:
                grupo.setText("Rollos");
                break;
            case ENTREMES:
                grupo.setText("Entremeses");
                break;
            case PLATILLO:
                grupo.setText("Platillos");
                break;
        }
    }
}
