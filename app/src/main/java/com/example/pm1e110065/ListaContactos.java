package com.example.pm1e110065;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.pm1e110065.procesos.Contactos;
import com.example.pm1e110065.procesos.SQLiteConexion;
import com.example.pm1e110065.procesos.Transacciones;

import java.util.ArrayList;

public class ListaContactos extends AppCompatActivity {
    SQLiteConexion conexion;
    EditText nombres, telefono, nota;
    SearchView buscarContacto;
    //traer la lista de Conatcto
    ArrayList<Contactos> listacontactos;
    ArrayList<String> ArregloContacos;
    ScrollView registros;
    Button btncompartir, btnverImagen, btneliminar, btnactualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contactos);

        init();
        buscarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BuscarContacto();
            }
        });
    }

    private void BuscarContacto() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        //parametros
        String [] params = {nombres.getText().toString()};
        //campos retornar
        String [] fields = {Transacciones.nombres,
                            Transacciones.telefono,
                            Transacciones.nota};
        String WhereCondition = Transacciones.nombres + "=?";

        Cursor cdata = db.query(Transacciones.tablaContactos, fields,
                WhereCondition,params, null, null, null);
        cdata.moveToFirst();

        nombres.setText(cdata.getString(0));
        telefono.setText(cdata.getString(1));
        nota.setText(cdata.getString(2));

        Toast.makeText(getApplicationContext(), "Consulta con exito",Toast.LENGTH_LONG).show();

    }

    private void init(){
        conexion = new SQLiteConexion(this, Transacciones.NameDataBase, null,1);
        //declara los botones
        buscarContacto= (SearchView) findViewById(R.id.buscar);
        //Falta el boton de compartir
        btncompartir= (Button) findViewById(R.id.btncompartir);
        btnverImagen= (Button) findViewById(R.id.btnimagen);
        btneliminar= (Button) findViewById(R.id.btneliminar);
        btnactualizar= (Button) findViewById(R.id.btnactualizar);

        nombres = (EditText) findViewById(R.id.txtnombres);
        telefono = (EditText) findViewById(R.id.txtTelefono);
        nota= (EditText) findViewById(R.id.txtnota);
        registros = (ScrollView) findViewById(R.id.registros);
        conexion = new SQLiteConexion(this, Transacciones.NameDataBase, null,1);
        ObtenerListaContactos();
        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ArregloContacos);
        //registros.setAdapter(adp);

    }

    private void ObtenerListaContactos() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Contactos contacto = null;
        listacontactos = new ArrayList<Contactos>();

        Cursor cursor = db.rawQuery( "SELECT * FROM " + Transacciones.tablaContactos, null);

        while(cursor.moveToNext()){
            contacto = new Contactos();
            contacto.setId(cursor.getInt(0));
            contacto.setPais(cursor.getString(1));
            contacto.setNombres(cursor.getString(2));
            contacto.setTelefono(cursor.getInt(3));
            contacto.setNota(cursor.getString(4));
            listacontactos.add(contacto);
        }
        cursor.close();
        fillList();
    }

    private void fillList() {
        ArregloContacos = new ArrayList<String>();
        for(int i = 0; i<listacontactos.size(); i++){

            ArregloContacos.add(listacontactos.get(i).getId() + " "
                    + listacontactos.get(i).getNombres() + " "
                    + listacontactos.get(i).getTelefono());

        }
    }
}