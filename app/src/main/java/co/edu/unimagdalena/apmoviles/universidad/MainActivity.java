package co.edu.unimagdalena.apmoviles.universidad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Almacen almacen;
    AlmacenController almacenController;

    EditText ciudad, nombre, departamento, estrellas, txtid, direccion, latitud, longitud;//4 nuevos
    Button agregar, mostrar, mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agregar = findViewById(R.id.btnguardar);
        mostrar = findViewById(R.id.btnlistado);
        mapa = findViewById(R.id.btnvermapa);

        txtid = findViewById(R.id.edtcodigo);
        ciudad = findViewById(R.id.edtciudad);
        nombre = findViewById(R.id.edtnombre);
        departamento = findViewById(R.id.edtdepartamento);
        direccion = findViewById(R.id.edtdireccion);
        latitud = findViewById(R.id.edtlatitud);
        longitud = findViewById(R.id.edtlongitud);

        agregar.setOnClickListener(this);
        mostrar.setOnClickListener(this);
        mapa.setOnClickListener(this);

        almacenController = new AlmacenController(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnguardar: //agregarHotel(e);
               if(TextUtils.isEmpty(txtid.getText().toString()) || TextUtils.isEmpty(ciudad.getText().toString()) || TextUtils.isEmpty(nombre.getText().toString()) ||
                       TextUtils.isEmpty(departamento.getText().toString()) ||
                       TextUtils.isEmpty(direccion.getText().toString()) || TextUtils.isEmpty(latitud.getText().toString()) || TextUtils.isEmpty(longitud.getText().toString()) ){
                    //Si ninguno de los campos estaban vacios
                    Toast.makeText(this,"No pueden haber casillas vacias", Toast.LENGTH_LONG).show();

               }else{
                    almacen = new Almacen(Integer.parseInt(txtid.getText().toString()), nombre.getText().toString(), departamento.getText().toString(), ciudad.getText().toString(),
                            direccion.getText().toString(), latitud.getText().toString(), longitud.getText().toString());

                    if (almacenController.buscarAlmacen(almacen)){
                        Toast.makeText(this,"Código ya existe", Toast.LENGTH_LONG).show();
                    }
                    else{
                        almacenController.agregarAlmacen(almacen);

                        txtid.setText("");//nuevo
                        nombre.setText("");
                        departamento.setText("");
                        ciudad.setText("");
                        direccion.setText("");//nuevo
                        latitud.setText("");//nuevo
                        longitud.setText("");//nuevo
                    }
               }
                break;
            case R.id.btnlistado:
                Intent i = new Intent(this, ListadoActivity.class);
                startActivity(i);
                break;
            case R.id.btnvermapa:
                Intent j = new Intent(this, MapsActivity.class);
                startActivity(j);
                break;
        }
    }


}
