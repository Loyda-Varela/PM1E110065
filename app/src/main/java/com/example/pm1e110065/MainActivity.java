package com.example.pm1e110065;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pm1e110065.procesos.SQLiteConexion;
import com.example.pm1e110065.procesos.Transacciones;

public class MainActivity extends AppCompatActivity {
    Spinner cmbpais;
    EditText txtnombres, txtTelefono, txtnota;
    Button btnguardar, btnmostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //casteo de elemntos
        init();

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarContactos();
            }
        });

        btnmostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ListaContactos.class);
                intent.putExtra("nombres", txtnombres.getText().toString());
                intent.putExtra("telefono", txtTelefono.getText().toString());
                intent.putExtra("nota", txtnota.getText().toString());
                startActivity(intent);
            }
        });


    }
    private void init(){
        cmbpais = (Spinner) findViewById(R.id.cmbpais);
        txtnombres = (EditText) findViewById(R.id.txtnombres);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        txtnota = (EditText) findViewById(R.id.txtnota);
        btnguardar = (Button) findViewById(R.id.btnguardar);
        btnmostrar = (Button) findViewById(R.id.btnmostrar);



    }

    private void AgregarContactos() {
        //conexion e insercion a la base de datos
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDataBase, null, 1);
        //hacer la conecion a bd
        SQLiteDatabase db = conexion.getWritableDatabase();

        //contenedores de informacion
        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres,txtnombres.getText().toString());
        valores.put(Transacciones.telefono,txtTelefono.getText().toString());
        valores.put(Transacciones.nota,txtnota.getText().toString());


        Long resultado = db.insert(Transacciones.tablaContactos, Transacciones.id, valores);
        //ver resultado
        Toast.makeText(getApplicationContext(), "Registro Ingresado", Toast.LENGTH_LONG).show();
        //cerrar conexion
        db.close();
        //limpiamos la pantala
        
        ClearScreen();
    }

    private void ClearScreen() {
        txtnombres.setText("");
        txtTelefono.setText("");
        txtnota.setText("");
    }
}