package com.example.pruebafinal;

public class registrarUsuario {
    private String user;
    private String password;
    private String pregunta;
    private String respuesta;

    public registrarUsuario(String user, String password,String pregunta, String respuesta) {
        this.user = user;
        this.password = password;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }
    public boolean isNull(){
        if(user.equals("")&& password.equals("")&&respuesta.equals("")){
            return false;
        }else{
            return true;
        }
    }

    ///CONSTRUCTORES




    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getPregunta() {
        return pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}