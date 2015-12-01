package itson.sushivan;

import android.content.Context;
import android.content.SharedPreferences;

import com.firebase.client.Firebase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import itson.sushivan.modelo.Perfil;
import itson.sushivan.modelo.Producto;

/**
 * Created by Alberto on 30/11/2015.
 */
public class Utils {

    static final String SHARED_PREFS = "sushi-van-prefs";
    static final String TMP_PRODUCTO = "sushi-van-tmp-producto";
    static final String CARRITO = "sushi-van-carrito";
    static final String PERFIL = "shushi-van-perfil";

    public static List<Producto> PLATILLOS;
    public static List<Producto> ENTREMESES;
    public static List<Producto> BOMBAS;
    public static List<Producto> ROLLOS;

    private static Firebase firebase;

    static {
        //platillos
        PLATILLOS = new ArrayList<>();
        PLATILLOS.add(new Producto(R.drawable.platillo_pechuga_en_crema, "Pechuga en crema", "Pollo", "Pechuga de pollo bañada en crema de queso acompañada con arroz y ensalada de verdura", 85.0));
        PLATILLOS.add(new Producto(R.drawable.platillo_pollo_gohan, "Pollo Gohan", "Camarón", "Arroz empanizado acopañado de queso phila, tampico, carne, pollo, camarón, pepino, zanahoria y aderezo", 80.0));
        PLATILLOS.add(new Producto(R.drawable.platillo_teriyaki, "Teriyaki", "Salsa teriyaki", "Riquísima cama de arroz acompañada de brócoli, zanahoria, coliflor, carne y pollo bañado en salsa teriyak", 70.0));

        //entremeses
        ENTREMESES = new ArrayList<>();
        ENTREMESES.add(new Producto(R.drawable.entremes_aros_de_cebolla_empanizados, "Aros de cebolla empanizados", "Cebolla", "Deliciosos aros de cebolla con nuestro pan molido especial.", 35.0));
        ENTREMESES.add(new Producto(R.drawable.entremes_boneless, "Boneless", "Pollo", "Deliciosas tiras de pechuga de pollo acompañadas de tu salsa favorita", 75.0));
        ENTREMESES.add(new Producto(R.drawable.entremes_deditos_de_philadelphia, "Deditos de Philadelphia", "Philadelphia", "Deditos del original queso philadelphia empanizados", 35.0));

        //bombas
        BOMBAS = new ArrayList<>();
        BOMBAS.add(new Producto(R.drawable.bomba_mixta, "Bomba mixta", "Pollo", "Delicioso pollo con tocino, acompañado de aguacate, philadelphia, pepino y camarón", 80.0));
        BOMBAS.add(new Producto(R.drawable.bomba_california, "Bomba california", "Camarón", "Camarón fresco acompañado de aguacate, philadephia, pepino y tampico por fuera", 80.0));
        BOMBAS.add(new Producto(R.drawable.bomba4, "Bomba Príncipe", "Surimi", "Surimi hecho en casa con queso garatinado por fuera. Por dentro aguacate, philadelphia, pepino y camarón.", 80.0));
        BOMBAS.add(new Producto(R.drawable.bomba, "Bomba suprema", "Carne", "Delicioso arroz amasado con phila, queso manchego y tocino arriba", 85.0));

        //rollos
        ROLLOS = new ArrayList<>();
        ROLLOS.add(new Producto(R.drawable.rollo_california_roll, "California Roll", "Camarón", "Delicioso camarón acompañado de queso phila, aguacate y pepino", 60.0));
        ROLLOS.add(new Producto(R.drawable.rollo_philip_roll, "Phillip Roll", "Pollo", "Riquísimo pollo acompañado de carne, phila, aguacate, pepino y tocino por fuera", 75.0));
        ROLLOS.add(new Producto(R.drawable.rollo_principe_roll, "Príncipe Roll", "Queso gratinado", "Por fuera queso gratinado y tocino acompañado de aguacate, pepino, camarón y chile toreado", 75.0));
        ROLLOS.add(new Producto(R.drawable.rollo_sonora_roll, "Ronora Roll", "Carne", "Deliciosa cebolla asada arriba con tocino y por dentro carne con phila, aguacate y pepino", 70.0));
        ROLLOS.add(new Producto(R.drawable.rollo_subarachi_roll, "Rubarachi Roll", "Tampico", "Delicioso tampico hecho en casa acompañado de phila, aguacate y pepino", 60.0));
        ROLLOS.add(new Producto(R.drawable.rollo_tocino_roll, "Tocino Roll", "Tocino", "Camarón, carne y tocino por fuera acompañado de phila, aguacate y pepino", 75.0));
    }

    public static void guardaProducto(Context c, Producto p){
        Gson gson = new Gson();
        String json= gson.toJson(p);
        SharedPreferences prefs = c.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        prefs.edit().putString(TMP_PRODUCTO, json).commit();
    }

    public static Producto obtenProducto(Context context){
        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return new Gson().fromJson(prefs.getString(TMP_PRODUCTO, ""), Producto.class);
    }

    public static void agregaACarrito(Context context, Producto p){
        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        //obtiene la lista de productos que hay en el carrito
        Type listType = new TypeToken<ArrayList<Producto>>() {}.getType();

        List<Producto> carrito = new Gson().fromJson(prefs.getString(CARRITO, ""), listType);
        if(carrito == null)
            carrito = new ArrayList<>();
        carrito.add(p);
        //guarda de nuevo la lista
        final String json = new Gson().toJson(carrito);
        prefs.edit().putString(CARRITO, json).commit();
    }

    public static List<Producto> obtenCarrito(Context context){
        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        //obtiene la lista de productos que hay en el carrito
        Type listType = new TypeToken<ArrayList<Producto>>() {}.getType();
        List<Producto> carrito = new Gson().fromJson(prefs.getString(CARRITO, ""), listType);
        return carrito;
    }

    public static void limpiaCarrito(Context context){
        context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
                .edit()
                .remove(CARRITO)
                .commit();
    }

    public static void guardaPerfil(Context context, Perfil perfil){
        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        prefs.edit().putString(PERFIL, new Gson().toJson(perfil)).commit();
    }

    public static Perfil getPerfil(Context context){
        String json = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
                .getString(PERFIL, "");
        Perfil perfil = new Gson().fromJson(json, Perfil.class);
        return perfil;
    }

    public static Firebase getFirebase(){
        if(firebase == null)
            firebase = new Firebase("https://itson-sushivan.firebaseio.com/");
        return firebase;
    }
}
