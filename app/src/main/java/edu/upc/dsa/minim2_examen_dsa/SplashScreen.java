package edu.upc.dsa.minim2_examen_dsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    private Handler mHandler;
    private Runnable mRunnable;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Context context = getApplicationContext();

        sharedPref = context.getSharedPreferences("minimo2", Context.MODE_PRIVATE);
        String user = sharedPref.getString("user", "");

        mRunnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("usuario", user);
                startActivity(intent);
            }
        };

        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRunnable != null && mHandler != null)
            mHandler.removeCallbacks(mRunnable);
    }
}