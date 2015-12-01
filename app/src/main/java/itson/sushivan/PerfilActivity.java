package itson.sushivan;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import itson.sushivan.modelo.Perfil;

public class PerfilActivity extends AppCompatActivity {

    private Perfil perfil;
    private EditText nombre, direccion, telefono, correo, cuenta;
    private CoordinatorLayout coordLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        //checa si ya existe un perfil
        perfil = Utils.getPerfil(this);
        nombre = (EditText)findViewById(R.id.nombre_perfil);
        direccion = (EditText)findViewById(R.id.direccion_perfil);
        telefono = (EditText)findViewById(R.id.telefono_perfil);
        correo = (EditText)findViewById(R.id.email_perfil);
        cuenta = (EditText)findViewById(R.id.cuenta_perfil);
        coordLayout = (CoordinatorLayout)findViewById(R.id.coord_layout);
        if(perfil != null){
            //llena los valores del perfil guardado
            nombre.setText(perfil.getNombre());
            direccion.setText(perfil.getDireccion());
            telefono.setText(perfil.getTelefono());
            correo.setText(perfil.getEmail());
            cuenta.setText(perfil.getNoCuenta());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.perfil, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.guardar:
                //guarda el perfil
                perfil = new Perfil();
                perfil.setDireccion(direccion.getText().toString());
                perfil.setEmail(correo.getText().toString());
                perfil.setNoCuenta(cuenta.getText().toString());
                perfil.setNombre(nombre.getText().toString());
                perfil.setTelefono(telefono.getText().toString());
                Utils.guardaPerfil(this, perfil);
                Snackbar.make(coordLayout, "Se ha guardado el perfil de cliente", Snackbar.LENGTH_LONG)
                    .show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
