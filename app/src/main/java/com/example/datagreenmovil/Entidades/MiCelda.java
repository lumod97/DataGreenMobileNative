package com.example.datagreenmovil.Entidades;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class MiCelda implements Serializable {
    private int Index;
    private String Nombre;
    private String Valor;

    public MiCelda() {
        this.Index = 0;
        this.Nombre ="Col0";
        this.Valor = "";
    }
    public MiCelda(Integer i) {
        this.Index = i;
        this.Nombre ="Col"+ i.toString();
        this.Valor = "";
    }

    public MiCelda(Integer i, String nombre) {
        this.Index = i;
        this.Nombre =nombre;
        this.Valor = "";
    }

    public MiCelda(Integer i, String nombre, String valor) {
        this.Index = i;
        this.Nombre =nombre;
        this.Valor = valor;
    }
    public int Index(){
        return Index;
    }

    public String Nombre(){
        return Nombre;
    }

    public String Valor(){
        return Valor;
    }

    public void Valor(String v){
        this.Valor=v;
    }


    //////////////
    /*
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.Index);
        parcel.writeString(this.Nombre);
        parcel.writeString(this.Valor);
    }

    protected MiCelda(Parcel in) {
        this.Index=in.readInt();
        this.Nombre=in.readString();
        this.Valor=in.readString();
    }

    public static final Parcelable.Creator<MiCelda> CREATOR = new Parcelable.Creator<MiCelda>(){
        @Override
        public MiCelda createFromParcel(Parcel source){
            return new MiCelda(source);
        }

        @Override
        public MiCelda[] newArray(int size){
            return new MiCelda[size];
        }
    };*/
}
