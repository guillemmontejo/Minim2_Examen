package edu.upc.dsa.minim2_examen_dsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    EditText username;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);

        String usuario = getIntent().getExtras().getString("usuario");
        if(!usuario.equals("")){
            username.setText(usuario);
        }

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        progressBar = findViewById(R.id.progressBar);

    }

    public void sendUser(View view){
        sharedPref = getApplicationContext().getSharedPreferences("minimo2", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        String uname = username.getText().toString();

        if (uname.equals(""))
            Toast.makeText(getApplicationContext(), "Introduce un usuario", Toast.LENGTH_LONG).show();
        else {
            progressBar.setVisibility(View.VISIBLE);
            editor.putString("user", uname);
            editor.commit();
            Call<Usuario> userCall = apiInterface.getUserInfo(uname);
            userCall.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if(response.code()==201 || response.code()==200){
                        Usuario user1 = response.body();
                        Call<List<Repo>> reposCall = apiInterface.getRepositoris(uname);
                        reposCall.enqueue(new Callback<List<Repo>>() {
                            @Override
                            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                                if(response.code()==201 || response.code()==200) {
                                    user1.setUserRepos(response.body());
                                    instanciaUsuario.getInstance().setUser(user1);
                                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                                    progressBar.setVisibility(View.GONE);
                                    startActivity(intent);
                                }
                                else{
                                    Toast toast = Toast.makeText(getApplicationContext(),"Repos request failed!", Toast.LENGTH_LONG);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            toast.show();
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onFailure(Call<List<Repo>> call, Throwable t) {
                                Toast toast = Toast.makeText(getApplicationContext(),"Repos request failed!", Toast.LENGTH_LONG);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        toast.show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                });
                            }
                        });
                    }
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(),"Github request failed!", Toast.LENGTH_LONG);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                toast.show();
                                progressBar.setVisibility(View.GONE);
                            }
                        });
                    }

                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Toast toast = Toast.makeText(getApplicationContext(),"Github request failed!", Toast.LENGTH_LONG);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toast.show();
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                }
            });
        }
    }
}