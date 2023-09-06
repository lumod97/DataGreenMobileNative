package com.example.datagreenmovil.Entidades;

public class MiQuery {
    public String idEmpresa, id, nombre, idModulo, ordenEjecucion, querySqlite, nParametros, tipoQuery, tablaObjetivo, crud;

    public MiQuery() {
        this.idEmpresa = "";
        this.id = "";
        this.nombre = "";
        this.idModulo = "";
        this.ordenEjecucion = "";
        this.querySqlite = "";
        this.nParametros = "";
        this.tipoQuery = "";
        this.tablaObjetivo = "";
        this.crud = "";
    }

    public MiQuery(String idEmpresa, String id, String nombre, String idModulo, String ordenEjecucion, String querySqlite, String nParametros, String tipoQuery, String tablaObjetivo, String crud) {
        this.idEmpresa = idEmpresa;
        this.id = id;
        this.nombre = nombre;
        this.idModulo = idModulo;
        this.ordenEjecucion = ordenEjecucion;
        this.querySqlite = querySqlite;
        this.nParametros = nParametros;
        this.tipoQuery = tipoQuery;
        this.tablaObjetivo = tablaObjetivo;
        this.crud = crud;
    }
}
