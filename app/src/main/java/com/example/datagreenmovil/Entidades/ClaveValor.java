package com.example.datagreenmovil.Entidades;

import android.database.Cursor;

import java.sql.ResultSet;

public class ClaveValor {
    private String clave;
    private String valor;

    public ClaveValor(){
    }

    public ClaveValor(String c, String v){
        this.clave = c;
        this.valor = v;
    }

    //PENDIENTE: PARECE HABER UN PORBLEMA DE SOBRE CONSUMOD DE MEMORIA AQUI, SE CREA UN ARRAY DE ESTA CLASE PERO SOLO SE UNA UN ELEMENTO DE LA MISMA, REVISAR DESDE LA CLASE SUPERIOR QUE LA LLAMA
    public static ClaveValor[] getArrayClaveValor(Cursor c, ClaveValor[] cv, int iC, int iV) throws Exception{
        try {
            if(c.moveToFirst()){
                do {
                    //aqui esta el problema, CV pasa como null
                    cv = agregar(cv, new ClaveValor(c.getString(iV), c.getString(iC)));
                    //cv . (new ClaveValor(rs.getString("Clave"),rs.getString("Valor")));
                } while (c.moveToNext());
            }
            return cv;
        }catch (Exception ex){
            throw  ex;
        }
    }

//    public static ClaveValor[] getArrayClaveValor(ResultSet rs, ClaveValor[] cv, int iC, int iV) throws Exception{
//        try {
//            //rs.first();
//            while (rs.next()) {
//                cv = agregar(cv, new ClaveValor(rs.getString(iV), rs.getString(iC)));
//            }
//            return cv;
//        }catch (Exception ex){
//            throw  ex;
//        }
//    }

    public static ClaveValor[] getArrayClaveValor(ResultSet rs, int iC, int iV) throws Exception{
        try {
            ClaveValor cvAux[]=null;
            while (rs.next()) {
                cvAux = agregar(cvAux,new ClaveValor(rs.getString(iV), rs.getString(iC)));
            }
            return cvAux;
        }catch (Exception ex){
            throw  ex;
        }
    }

    public static ClaveValor[] getArrayClaveValor(MiData rs, int iC, int iV) throws Exception{
        try {
            ClaveValor cvAux[]=null;
            for(MiFila f: rs.Filas){
                cvAux = agregar(cvAux,new ClaveValor(f.Data.get(iC).Valor(), f.Data.get(iV).Valor()));
                }
            return cvAux;
        }catch (Exception ex){
            throw  ex;
        }
    }

    public static ClaveValor[] getArrayClaveValor(Tabla t, int iC, int iV) throws Exception{
        try {
            ClaveValor[] cvAux =null;
            for(Fila f: t.Filas){
                cvAux = agregar(cvAux,new ClaveValor(f.Item.get(iC).toString(), f.Item.get(iV).toString()));
            }
            return cvAux;
        }catch (Exception ex){
            throw  ex;
        }
    }

    public void setClave(String c){
        this.clave = c;
    }

    public String getClave(){
        return this.clave;
    }

    public void setValor(String v){
        this.valor = v;
    }

    public String getValor(){
        return this.valor;
    }
/*
    public ArrayList<ClaveValor> ClaveValor(ResultSet rs, ArrayList<ClaveValor> cv) throws Exception{
        if(rs.next()){
            cv.add(new ClaveValor(rs.getString("Clave"),rs.getString("Valor")));
        }
        return cv;
    }*/

    public static ClaveValor[] agregar(ClaveValor[] cv, ClaveValor x){
        int o = cv == null ? 0 : cv.length;
        ClaveValor aux[] = new ClaveValor[o+1];
        for(int i=0; i < o; i++)
            aux[i]=cv[i];
        aux[o]=x;
        return aux;
    }

//    public static ClaveValor[] agregar(ClaveValor x){
//
//        int o = cv == null ? 0 : cv.length;
//        ClaveValor aux[] = new ClaveValor[o+1];
//        for(int i=0; i < o; i++)
//            aux[i]=cv[i];
//        aux[o]=x;
//        return aux;
//    }


    public static String[] obtenerClaves(ClaveValor[] cv){
        if(cv!=null){
            String[] r = new String[cv.length];
            for(int i=0; i < cv.length;i++){
                r[i]=cv[i].getValor();
            }
            return r;
        }
        return null;
    }

        public static String[] obtenerValores(ClaveValor[] cv){
        if(cv!=null){
            String[] r = new String[cv.length];
            for(int i=0; i < cv.length;i++){
                r[i]=cv[i].getClave();
            }
            return r;
        }
        return null;
    }

    public static String obtenerValorDesdeClave(String c, ClaveValor[] cv){
        String r = "";
        if(cv!=null){
            for(int i=0; i < cv.length;i++){
                if(cv[i].getClave().equals(c)){
                    return cv[i].getValor();
                }
            }
        }
        return r;
    }

    public static String obtenerClaveDesdeValor(String v, ClaveValor[] cv){
        //String r = "";
        if(cv!=null){
            for(int i=0; i < cv.length;i++){
                if(cv[i].getValor().equals(v)){
                    return cv[i].getClave();
                }
            }
        }
        return "";
    }
}
