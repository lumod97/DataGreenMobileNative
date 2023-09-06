package com.example.datagreenmovil.Entidades;

//import java.sql.Struct;

import android.database.Cursor;

import java.io.File;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.HashMap;

public class Querys implements Serializable {

    public MiData data;

    public Querys(Cursor c){
        if(c!=null){
            this.data=new MiData(c);
        }else{
            this.data = new MiData();
        }
    }

    public Querys(ResultSet rs){
        if(rs!=null){
            this.data=new MiData(rs);
        }else{
            this.data = new MiData();
        }
    }

    //public HashMap<Integer, MiQuery> filas;
    //public Query[] Qs;
/*
    public Query[] Querys(int n){
        Query[] r = new Query[n];
        for(Query q : this.Qs){
            q = new Query();
           q.idEmpresa="";
            q.id="";
            q.nombre="";
            q.idModulo="";
            q.ordenEjecucion="";
            q.querySqlite="";
            q.nParametros="";
            q.tipoQuery="";
            q.tablaObjetivo="";
            q.crud="";
        }
        return r;
    }

    public Querys(ResultSet rs){
        try{
            //rs.last();
            //Querys r;
//            this.Qs = new Query[rs.getRow()]; //new Querys(rs.getRow());
            this.Qs = this.Querys(rs.getRow()); //new Querys(rs.getRow());
            rs.beforeFirst();
            int i=0;
            filas = new HashMap<>();
            while(rs.next()){
                MiQuery aux = new MiQuery(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
                this.filas.put(this.filas.size(),aux);
            }
            //return r;
        }catch (Exception ex){
            //this.Qs = new Query[1];
        }
    }

    public Querys(Cursor c){
        try{
            this.Qs = new Query[c.getCount()];
            int i=0;
            c.moveToFirst();
            filas = new HashMap<>();
            while(c.moveToNext()){
                MiQuery aux = new MiQuery(c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6),
                        c.getString(7),
                        c.getString(8),
                        c.getString(9));
                this.filas.put(this.filas.size(),aux);
            }
        }catch (Exception ex){
            //this.Qs = new Query[1];
            String aaa="";
        }
    }
*/
    public String obtQueryXNombre(String idEmpresa, String nombre){
        /*for (MiQuery mQ: data.values()){
            if(mQ.idEmpresa.equals(idEmpresa) && mQ.nombre.equals(nombre)){
                return mQ.querySqlite;
            }
        }
        return "";*/
        ////
            int i =this.data.encontrar(nombre,0);
            if(i>=0){
                return this.data.Filas(i).Item("Valor").Valor();
            }else{
                return "";
            }
    }
/*
    public String obtQueryXId(String idEmpresa, String id){
        for (MiQuery mQ: filas.values()){
            if(mQ.idEmpresa.equals(idEmpresa) && mQ.id.equals(id)){
                return mQ.querySqlite;
            }
        }
        return "";
    }
*/
    public HashMap<String, String> obtQuerysParaDescarga(String idEmpresa){
        HashMap<String, String> r = new HashMap<>();
        for (MiFila f: data.Filas ){
            if(f.Item("IdEmpresa").Valor().equals(idEmpresa)
                    && f.Item("NombreQuery").Valor().contains("DESCARGAR DATA")
                    && f.Item("NParametros").Valor().equals("0")
                    && !f.Item("NombreQuery").Valor().equals("DESCARGAR DATA trx_Logs")
                    && !f.Item("NombreQuery").Valor().equals("DESCARGAR DATA trx_Estandares")) {
                r.put(f.Item("TablaObjetivo").Valor(), f.Item("QuerySqlite").Valor());
            }
        }
        return r;
    }
}
