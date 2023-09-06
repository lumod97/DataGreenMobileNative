package com.example.datagreenmovil.Entidades;


import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.datagreenmovil.Conexiones.ConexionSqlite;

import java.io.Serializable;
import java.util.ArrayList;

public class Rex extends ArrayList<Pix> {
    ArrayList<Rex> Dex;

    public Rex (){

    }

    public Rex (ConexionSqlite objSqlite, String nombreTabla) throws Exception {
        String q = "SELECT cid, name FROM pragma_table_info('"+nombreTabla+"') ORDER BY 1;";
        Cursor c = objSqlite.doItBaby(q,null,"READ");
        if (c.moveToFirst()){
            do{
                this.add(new Pix(c.getString(1),""));
            }while (c.moveToNext());
        }
    }

    public boolean Existe(String k){
        for (Pix i: this){
            if (i.getK().equals(k)){
                return true;
            }
        }
        return  false;
    }

    public int GetIndex(String k){
        for (int i = 0; i < this.size(); i++){
            if (this.get(i).getK().equals(k)){
                return i;
            }
        }
        return -1;
    }

    public void Set(String k, Object v){
        if (Existe(k)){
            this.get(this.GetIndex(k)).setV(v);
        }else {
            this.add(new Pix(k,v));
        }
    }

    public String Get(String k){
        if (Existe(k)){
            return this.get(this.GetIndex(k)).getV().toString();
        }else {
            return null;
        }
    }

    public String Get(int i){
        if (i >= this.size()){
            return null;
        }else {
            return this.get(i).getV().toString();
        }
    }

}
