package edu.upc.dsa.minim2_examen_dsa;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("{user}")
    Call<Usuario> getUserInfo(@Path("user") String user);

    @GET ("{user}/repos")
    Call<List<Repo>> getRepositoris(@Path("user") String user);

}
