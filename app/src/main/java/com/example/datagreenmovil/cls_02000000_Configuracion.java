package com.example.datagreenmovil;

import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.datagreenmovil.Conexiones.ConexionBD;
import com.example.datagreenmovil.Conexiones.ConexionSqlite;
import com.example.datagreenmovil.Entidades.ConfiguracionLocal;
import com.example.datagreenmovil.Entidades.Querys;
import com.example.datagreenmovil.Logica.Funciones;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class cls_02000000_Configuracion extends AppCompatActivity {
    //@Jota:2023-05-27 -> INICIO DE LINEAS DE CODIGO COMUNES PARA TODAS LAS ACTIVIDADES
    ConexionSqlite objSqlite;
    ConexionBD objSql;
    ConfiguracionLocal objConfLocal;
    TextView txv_PushTituloVentana, txv_PushRed, txv_NombreApp, txv_PushVersionApp, txv_PushVersionDataBase, txv_PushIdentificador;
    Dialog dlg_PopUp;
    //@Jota:2023-05-27 -> FIN DE LINEAS DE CODIGO COMUNES PARA TODAS LAS ACTIVIDADES
    //METER CODIGO PROPIO DE CADA ACTIVIDAD DESPUES DE ESTA LINEA
    //...

    int flagR=0;
    ConexionBD cnAux;
    ConfiguracionLocal clAux;
    Querys objQuerys;
    private EditText etxHost;
    private EditText etxInstancia;
    private EditText etxNombreBD;
    private EditText etxUsuario;
    private EditText etx_Password;
    private EditText etxPuerto;
    private EditText etxImei;
    private EditText etxMac;
    private EditText etxNroTelefono;
    private EditText etxPropietario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v_02000000_configuracion_002);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //@Jota:2023-05-27 -> INICIO DE LINEAS DE CODIGO COMUNES PARA TODAS LAS ACTIVIDADES

        if(getIntent().getExtras()!=null){
            objConfLocal=(ConfiguracionLocal) getIntent().getSerializableExtra("ConfiguracionLocal");
        }
        objSql = new ConexionBD(objConfLocal);
        objSqlite = new ConexionSqlite(this,objConfLocal);
        objConfLocal.set("ULTIMA_ACTIVIDAD","Configuracion");

        referenciarControles();
        setearControles();
        Funciones.mostrarEstatusGeneral(this.getBaseContext(),
                objConfLocal,
                txv_PushTituloVentana,
                txv_PushRed,
                txv_NombreApp,
                txv_PushVersionApp,
                txv_PushVersionDataBase,
                txv_PushIdentificador
        );
        //@Jota:2023-05-27 -> FIN DE LINEAS DE CODIGO COMUNES PARA TODAS LAS ACTIVIDADES
        //METER CODIGO PROPIO DE CADA ACTIVIDAD DESPUES DE ESTA LINEA
        //...
        mostrarValoresDocumentoActual();
    }

    //@Jota:2023-05-27 -> LINEAS DE CODIGO COMUNES PARA TODAS LAS ACTIVIDADES
     private void setearControles() {
//        builderDialogoCerrarSesion= Funciones.setearAlertDialogParaCerrarSesion_(objConfLocal,objSqlite,this);

        //@Jota:2023-05-27 -> FIN DE LINEAS DE CODIGO COMUNES PARA TODAS LAS ACTIVIDADES
        //METER CODIGO PROPIO DE CADA ACTIVIDAD DESPUES DE ESTA LINEA
        //...
    }

    private void referenciarControles() {
        txv_PushTituloVentana = findViewById(R.id.c002_txv_PushTituloVentana_v);
        txv_PushRed = findViewById(R.id.c002_txv_PushRed_v);
        txv_NombreApp = findViewById(R.id.c002_txv_NombreApp_v);
        txv_PushVersionApp = findViewById(R.id.c002_txv_PushVersionApp_v);
        txv_PushVersionDataBase = findViewById(R.id.c002_txv_PushVersionDataBase_v);
        txv_PushIdentificador = findViewById(R.id.c002_txv_PushIdentificador_v);

        //@Jota:2023-05-27 -> FIN DE LINEAS DE CODIGO COMUNES PARA TODAS LAS ACTIVIDADES
        //METER CODIGO PROPIO DE CADA ACTIVIDAD DESPUES DE ESTA LINEA
        //...
        etxHost = findViewById(R.id.c002_etxHost_v);
        etxInstancia = findViewById(R.id.c002_etxInstancia_v);
        etxNombreBD = findViewById(R.id.c002_etxNombreBD_v);
        etxUsuario = findViewById(R.id.c002_etxUsuario_v);
        etx_Password = findViewById(R.id.c002_etx_Password_v);
        etxPuerto = findViewById(R.id.c002_etxPuerto_v);
        etxImei = findViewById(R.id.c002_etxImei_v);
        etxMac = findViewById(R.id.c002_etxMac_v);
        etxNroTelefono = findViewById(R.id.c002_etxNroTelefono_v);
        etxPropietario = findViewById(R.id.c002_etxPropietario_v);
    }

    public void mostrarMenuUsuario(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this::onMenuItemClick);
        popup.inflate(R.menu.mnu_00000001_menu_usuario);
        popup.show();
    }

    public boolean onMenuItemClick(MenuItem item) {
        try{
            int idControlClickeado = item.getItemId();
            if (idControlClickeado==R.id.opc_00000001_cambiar_clave_usuario_v){
                dlg_PopUp = Funciones.obtenerDialogParaCambiarClave(this,objConfLocal,objSqlite,this);
                assert dlg_PopUp != null;
                dlg_PopUp.show();
            } else if (idControlClickeado==R.id.opc_00000001_cerrar_sesion_v) {
                dlg_PopUp = Funciones.obtenerDialogParaCerrarSesion(this,objConfLocal,objSqlite,this);
                assert dlg_PopUp != null;
                dlg_PopUp.show();
            }else return false;
        }catch(Exception ex){
            Funciones.mostrarError(this,ex);
            return false;
        }
        return true;
    }

    public void onClick(View view) {
        try {
            int idControlClickeado = view.getId();
            if (idControlClickeado == R.id.c002_txv_PushTituloVentana_v){
                Funciones.popUpTablasPendientesDeEnviar(this);
            } else if (idControlClickeado == R.id.c002_txv_PushRed_v) {
                objSql.probarConexion(objConfLocal);
                Funciones.mostrarEstatusGeneral(this.getBaseContext(),
                        objConfLocal,
                        txv_PushTituloVentana,
                        txv_PushRed,
                        txv_NombreApp,
                        txv_PushVersionApp,
                        txv_PushVersionDataBase,
                        txv_PushIdentificador
                );
            } else if (idControlClickeado == R.id.c002_txv_PushVersionApp_v || idControlClickeado == R.id.c002_txv_PushVersionDataBase_v) {
                Funciones.popUpStatusVersiones(this);
            } else if (idControlClickeado == R.id.c002_txv_PushIdentificador_v) {
                mostrarMenuUsuario(this.txv_PushIdentificador);
            }
            //@Jota:2023-05-27 -> FIN DE LINEAS DE CODIGO COMUNES PARA TODAS LAS ACTIVIDADES
            //METER CODIGO PROPIO DE CADA ACTIVIDAD DESPUES DE ESTA LINEA
            //...
            else if (idControlClickeado == R.id.c002_btnProbarConexion_v) {
                actualizarValoresDeConfiguracionAuxiliar();
                probarNuevaConexion();
            } else if (idControlClickeado == R.id.c002_btnRegistrar_v) {
                if (clAux.get("RED_CONFIGURADA").equals("TRUE")){
                    if(registrarDispositivo()){
                        objConfLocal = clAux;
                        objSql = cnAux;
                        Funciones.mostrarEstatusGeneral(this.getBaseContext(),
                                objConfLocal,
                                txv_PushTituloVentana,
                                txv_PushRed,
                                txv_NombreApp,
                                txv_PushVersionApp,
                                txv_PushVersionDataBase,
                                txv_PushIdentificador
                        );
                        mostrarValoresDocumentoActual();
                        Funciones.notificar(this,"Dispositivo Registrado Correctamente.");
                    }
                }else{
                    Funciones.notificar(this,"No se puede registrar el dispositivo con los valores proporcionados.");
                }
            } else if (idControlClickeado == R.id.c002_bntSincronizar_v) {
//                if (objConfLocal.get("EXISTE_DATA_PENDIENTE").equals("TRUE")){
//                    Funciones.notificar(this,"Existe data pendiente de transferir. Imposible sincronizar.");
//                }else{
//                    objSqlite.close();
//                    this.deleteDatabase("DataGreenMovil.db");
//                    objSqlite = new ConexionSqlite(this,objConfLocal);
//                    /////////////////////////////////////
//                    objQuerys = new Querys(objSql.obtenerQuerys());
//                    objSqlite.crearTablas(objQuerys);
//                    descargarData();
//                }

//                PROBAR CONEXION:
                actualizarValoresDeConfiguracionAuxiliar();
                probarNuevaConexion();

                //SINCRONIZAR:
                Cursor c = null;
                try{
                    c = objSqlite.doItBaby(objSqlite.obtQuery("EXISTE DATA PENDIENTE"), null, "READ");
                    c.moveToFirst();
                }catch (Exception ex1){
                    Funciones.notificar(this,ex1.getMessage());
                }
                if (objSqlite.existeConfiguracionLocal() && c.getString(0).equals("1")){
                    //Funciones.notificar(this,"Existe data pendiente de transferir. Imposible sincronizar.");
                    throw new Exception("Existe data pendiente de transferir. Imposible sincronizar.");
                }else {
                    objSqlite.close();
                    this.deleteDatabase("DataGreenMovil.db");
                    objSqlite = new ConexionSqlite(this,objConfLocal);
                    /////////////////////////////////////

                    objQuerys = new Querys(objSql.obtenerQuerys());
                    objSqlite.crearTablas(objQuerys);
                    descargarData();
                }

                //REGISTRAR:
                if (clAux.get("RED_CONFIGURADA").equals("TRUE")){
                    if(registrarDispositivo()){
                        objConfLocal = clAux;
                        objSql = cnAux;
                        Funciones.mostrarEstatusGeneral(this.getBaseContext(),
                                objConfLocal,
                                txv_PushTituloVentana,
                                txv_PushRed,
                                txv_NombreApp,
                                txv_PushVersionApp,
                                txv_PushVersionDataBase,
                                txv_PushIdentificador
                        );
                        mostrarValoresDocumentoActual();
                        Funciones.notificar(this,"Dispositivo Registrado Correctamente.");
                    }
                }else{
                    Funciones.notificar(this,"No se puede registrar el dispositivo con los valores proporcionados.");
                }

                //GUARDAR:
                objConfLocal.set("EQUIPO_CONFIGURADO","TRUE");
                objSqlite.guardarConfiguracionLocal(objConfLocal);
                Funciones.notificar(this,"Configuracion guardada correctamente.");
            } else if (idControlClickeado == R.id.c002_btnMas_v) {
                flagR++;
                if(flagR==10){
                    Funciones.notificar(this,"Estas a punto de borrar la base de datos.");
                }
                if (flagR==20){
                    objSqlite.close();
                    this.deleteDatabase("DataGreenMovil.db");
                    objSqlite = new ConexionSqlite(this,objConfLocal);
                    flagR=0;
                    Funciones.notificar(this,"Base de datos borrada correctamente.");
                }
            } else if (idControlClickeado == R.id.c002_btnGuardar_v) {
                objConfLocal.set("EQUIPO_CONFIGURADO","TRUE");
                objSqlite.guardarConfiguracionLocal(objConfLocal);
                Funciones.notificar(this,"Configuracion guardada correctamente.");
            }
            else throw new IllegalStateException("Click sin programacion: " + view.getId());
        } catch (Exception ex) {
            Funciones.mostrarError(this,ex);
        }
    }

    //@Jota:
    //METER CODIGO PROPIO DE CADA ACTIVIDAD DESPUES DE ESTA LINEA
    //...


    private void mostrarValoresDocumentoActual() {
        etxHost.setText(objConfLocal.get("RED_HOST"));
        etxInstancia.setText(objConfLocal.get("RED_INSTANCIA"));
        etxNombreBD.setText(objConfLocal.get("RED_NOMBRE_DB"));
        etxUsuario.setText(objConfLocal.get("RED_USUARIO"));
        etx_Password.setText(objConfLocal.get("RED_PASSWORD"));
        etxPuerto.setText(objConfLocal.get("RED_PUERTO_CONEXION"));
        etxImei.setText(Funciones.obtenerIdUnico(this));
        etxMac.setText(Funciones.obtenerMac());
        if (etxMac.getText().length()==0 && etxImei.getText().length()>0){
            etxMac.setText(etxImei.getText());
        } else if (etxMac.getText().length()>0 && etxImei.getText().length()==0){
            etxImei.setText(etxMac.getText());
        }
        if(etxImei.getText().length()==0){etxImei.setEnabled(true);}
        if( etxMac.getText().length()==0){ etxMac.setEnabled(true);}
        etxNroTelefono.setText(objConfLocal.get("NRO_TELEFONICO"));
        etxPropietario.setText(objConfLocal.get("PROPIETARIO"));
    }

    private boolean registrarDispositivo() {
        try {
            clAux.set("ID_DISPOSITIVO",cnAux.registrarEquipo(objConfLocal));
            //clAux.actualizarConfiguraciones(cnAux.obtenerConfiguracionesDispositivoMovil());
            return true;
        } catch (Exception ex) {
             Funciones.mostrarError(this,ex);
            return false;
        }
    }

    private void actualizarValoresDeConfiguracionAuxiliar() {
        try {
            clAux = new ConfiguracionLocal();
            clAux.set("RED_HOST",etxHost.getText().toString());
            clAux.set("RED_INSTANCIA",etxInstancia.getText().toString());
            clAux.set("RED_NOMBRE_DB",etxNombreBD.getText().toString());
            clAux.set("RED_PUERTO_CONEXION",etxPuerto.getText().toString());
            clAux.set("RED_USUARIO",etxUsuario.getText().toString());
            clAux.set("RED_PASSWORD",etx_Password.getText().toString());
            clAux.set("ID_EMPRESA","01");
            //clAux.set("IMEI",obtenerImei());
            clAux.set("IMEI",etxImei.getText().toString());
            //clAux.set("MAC",obtenerMac());
            clAux.set("MAC",etxMac.getText().toString());
            clAux.set("NRO_TELEFONICO",etxNroTelefono.getText().toString());
            clAux.set("PROPIETARIO",etxPropietario.getText().toString());
        }catch (Exception ex){
             Funciones.mostrarError(this,ex);
        }
    }

    private void probarNuevaConexion() {
        try {
            cnAux=new ConexionBD(clAux);
            if (cnAux.hayConexion()) {
                clAux.set("RED_CONFIGURADA","TRUE");
                clAux.set("ESTADO_RED","ONLINE");
                Funciones.notificar(this,"Conexion Establecida.");
                objConfLocal = clAux;
                objSql = cnAux;
            }else{
                clAux.set("RED_CONFIGURADA","FALSE");
                clAux.set("ESTADO_RED","OFFLINE");
                Funciones.notificar(this,"Imposible conectar con servidor.");
            }
        } catch (Exception ex) {
             Funciones.mostrarError(this,ex);
        }
    }

    private void descargarData()throws Exception {
        String tablasSincronizadas ="Sincronizacion finalizada correctamente en:";
        try{
            objQuerys = new Querys(objSql.obtenerQuerys());
            HashMap<String, String> hmQuerys = objQuerys.obtQuerysParaDescarga("01"); //PENDIENTE OBTENER DINAMICAMENTE;
            Iterator<Map.Entry<String, String>> it = hmQuerys.entrySet().iterator();
            ResultSet rsAux;
            ResultSetMetaData m;
            String q;
            while(it.hasNext()){
                Map.Entry<String, String> set = (Map.Entry<String, String>) it.next();
                tablasSincronizadas = tablasSincronizadas + "\n" + set.getKey();
                rsAux = objSql.doItBaby(set.getValue(),null);
                m=rsAux.getMetaData();
                while (rsAux.next()){
                    q = "INSERT INTO " + set.getKey() + " VALUES(";
                    for (int i = 0; i<m.getColumnCount(); i++){
                        if(i>0){
                            q = q +",";
                        }
                        q = q + "'" + rsAux.getString(i+1) + "'";
                    }
                    q = q + ")";
                    objSqlite.doItBaby(q,null,"INSERT");
                }
            }
        }catch(Exception ex){
             Funciones.mostrarError(this,ex);
        }finally {
            Funciones.notificar(this, tablasSincronizadas);
        }
    }

}