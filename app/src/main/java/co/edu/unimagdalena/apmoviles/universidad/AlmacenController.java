package co.edu.unimagdalena.apmoviles.universidad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class AlmacenController {
    private BaseDatos bd;
    private Context c;
    public AlmacenController(Context c) {
        this.bd = new BaseDatos(c,1);
        this.c = c;
    }

    public void agregarAlmacen(Almacen e){
        try {
            SQLiteDatabase sql = bd.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(DefBD.col_id, e.getId());
            values.put(DefBD.col_nombre, e.getNombre());
            values.put(DefBD.col_departamento, e.getDepartamento());
            values.put(DefBD.col_ciudad, e.getCiudad());
            values.put(DefBD.col_direccion, e.getDireccion());
            values.put(DefBD.col_latitud, e.getLatitud());
            values.put(DefBD.col_longitud, e.getLongitud());
            long id = sql.insert(DefBD.tabla_almacen, null, values);

            Toast.makeText(c, "Almacen registrado", Toast.LENGTH_LONG).show();
        }
        catch(Exception ex){
            Toast.makeText(c, "Error agregando almacen " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public boolean buscarAlmacen(Almacen e){
        String args[] = new String[] {"" + e.getId()};
        String[] columnas = {DefBD.col_id,DefBD.col_nombre};
        String col[] = new String[] {DefBD.col_id,DefBD.col_nombre};
        SQLiteDatabase sql = bd.getReadableDatabase();
      Cursor c = sql.query(DefBD.tabla_almacen,null,"id=?",args,null,null,null);
        if (c.getCount()>0){
            bd.close();
            return true;
        }
        else{
            bd.close();
            return false;
        }
    }

    public Cursor allHoteles(){// para el listado pero no utilizado
        try{
            SQLiteDatabase sql = bd.getReadableDatabase();
            Cursor c = sql.query(DefBD.tabla_almacen,null,null,null,null,null,null);
            return c;
        }
        catch (Exception ex){
            Toast.makeText(c, "Error consulta hoteles " + ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public Cursor allAlmacenes2(){//para el listado
        try{
            SQLiteDatabase sql = bd.getReadableDatabase();
            Cursor cur = sql.rawQuery("select id as _id , nombre, departamento, ciudad, direccion, latitud, longitud from almacen", null);
            return cur;
        }
        catch (Exception ex){
            Toast.makeText(c, "Error consulta almacenes " + ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public void eliminarAlmacen(int id){
        try{
            SQLiteDatabase sql = bd.getReadableDatabase();
            String[] args = {""+id};
           sql.delete(DefBD.tabla_almacen,"id=?",args);
            Toast.makeText(c, "Almacen eliminado", Toast.LENGTH_LONG).show();
        }
        catch (Exception ex){
            Toast.makeText(c, "Error eliminar almacen " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void actualizarAlmacen(Almacen e){
        try{
            SQLiteDatabase sql = bd.getReadableDatabase();
            String[] args = {""+e.getId()};
            ContentValues valores = new ContentValues();
            //como el id no se modifica aqui no va lo de id
            valores.put(DefBD.col_nombre, e.getNombre());
            valores.put(DefBD.col_departamento, e.getDepartamento());
            valores.put(DefBD.col_ciudad, e.getCiudad());
            valores.put(DefBD.col_direccion, e.getDireccion());//nuevo
            valores.put(DefBD.col_latitud, e.getLatitud());//nuevo
            valores.put(DefBD.col_longitud, e.getLongitud());//nuevo
            sql.update(DefBD.tabla_almacen,valores,"id=?",args);
            Toast.makeText(c, "Almacen actualizado", Toast.LENGTH_LONG).show();
        }
        catch (Exception ex){
            Toast.makeText(c, "Error actualizar almacenes " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public Cursor filtrarAlmacen(CharSequence filtro){
        SQLiteDatabase sql = bd.getWritableDatabase();
        String query = "SELECT id as _id, nombre, departamento, ciudad, estrellas, direccion, latitud, longitud FROM almacen "
                + "where departamento like '%" + filtro + "%' or ciudad like '%" + filtro + "%' or estrellas like '%" + filtro + "%'"
                + "ORDER BY nombre ASC";

        return  sql.rawQuery(query, null);

    }
   }


