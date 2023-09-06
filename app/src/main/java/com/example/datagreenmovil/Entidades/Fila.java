package com.example.datagreenmovil.Entidades;

import java.util.List;

public class Fila {
    public List<Object> Item;

    public Fila(List<Object> l){
        this.Item=l;
    }
    //@Jota 2022-11-11: RECORDARTORIO, IMPLEMENTAR LA ENTIDAD CELDA CON ATRIBUTOS DE COLUMNA PARA PODER ACCEDER POR EJEMPLO ASI: FILA.CELDAS("NOMBRE_DE_CELDA")
}
