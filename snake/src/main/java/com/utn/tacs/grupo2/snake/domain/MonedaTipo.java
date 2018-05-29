package com.utn.tacs.grupo2.snake.domain;

public enum MonedaTipo {

    BITCOIN("bitcoin"),
    DOGECOIN("dogecoin"),
    ETHEREUM("ethereum");

    private final String nombre;

    MonedaTipo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

}
