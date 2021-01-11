package edu.upc.dsa.minim2_examen_dsa;

public class instanciaUsuario {
    private static instanciaUsuario instance;
    private Usuario user;

    private instanciaUsuario() {
        this.user = null;
    }

    public static instanciaUsuario getInstance() {
        if (instance == null)
            instance = new instanciaUsuario();
        return instance;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}
