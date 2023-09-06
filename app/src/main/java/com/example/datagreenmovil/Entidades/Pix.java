package com.example.datagreenmovil.Entidades;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Pix {
    private String k;
    private Object v;

    public Pix(String k, Object v){
        this.k=k;
        this.v=v;
    }

    protected Pix(Parcel in) {
        k = in.readString();
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public Object getV() {
        return v;
    }

    public void setV(Object v) {
        this.v = v;
    }

}
