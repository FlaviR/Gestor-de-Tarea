package com.example.user.gestordetareas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText nombreEdiText;
    EditText emailEdiText;
    Button guardarButton;

    ListView usuariosListView;

    List<String> usuarios = new ArrayList<String>();

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //downcast and upcast
        nombreEdiText = (EditText) findViewById(R.id.editTextNombre);
        emailEdiText = (EditText)findViewById(R.id.editTextEmail);
        guardarButton = (Button)findViewById(R.id.buttonGuardar);

        //crear la lista
        usuariosListView = (ListView)findViewById(R.id.listViewUsuarios);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,usuarios);

        usuariosListView.setAdapter(adapter);

        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreEdiText.getText().toString();
                String email = emailEdiText.getText().toString();

                //TODO VALIDACION

                if (!validarNombre(nombre)){
                //tratar el error
                    nombreEdiText.setError(getString(R.string.nombre_error));

                }else if (!validarEmail(email)){
                    emailEdiText.setError(getString(R.string.email_error));

                }else{
                    String mensaje = "Bienvenido"+nombre+" "+email;
                    Toast.makeText(getApplicationContext(),mensaje, Toast.LENGTH_LONG).show();

                    //agregar datos
                    String datos = nombre+" "+email;
                    usuarios.add(datos);
                    adapter.notifyDataSetChanged();

                    nombreEdiText.setText(null);
                    emailEdiText.setText(null);
                }

            }
        });
    }
    private boolean validarNombre (String nombre){

        return !nombre.equals("");
    }

    private boolean validarEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
