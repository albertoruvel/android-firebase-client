package itson.sushivan;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Alberto on 29/11/2015.
 */
public class SushiVanApp extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
