package com.example.datagreenmovil.Entidades;

import android.database.Cursor;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class Tabla {
    public List<Fila> Filas;

    public Tabla(){
        this.Filas =new ArrayList<>();
    }

    public Tabla(Cursor c) {
        this.Filas=new ArrayList<>();
        while (c.moveToNext()){
            List<Object> valores=new ArrayList<>(c.getColumnCount());
            for(int i=0; i<c.getColumnCount(); i++){
                valores.add(c.getString(i));
            }
            this.Filas.add(new Fila(valores));
        }
    }

    public Tabla(ResultSet rs) {
        try{
            ResultSetMetaData rsmdAux=rs.getMetaData();
            this.Filas=new ArrayList<>();
            while (rs.next()){
                List<Object> valores=new ArrayList<>(rsmdAux.getColumnCount());
                for(int i=0; i<rsmdAux.getColumnCount(); i++){
                    valores.add(rs.getString(i+1));
                }
                this.Filas.add(new Fila(valores));
            }
        }catch (Exception ex){
            this.Filas = null;
        }
    }

    public List<String> toList(int indexColumn){
        List<String> r = new ArrayList<>();
        if(indexColumn > this.Filas.size()-1){
            try {
                throw new Exception("El indice de la columna supera la cantidad de columnas de la tabla.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            for (Fila f : this.Filas) {
                r.add(f.Item.get(indexColumn).toString());
            }
        }
        return r;
    }

}
