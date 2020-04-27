package com.example.comkedem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class EditarPerfil extends AppCompatActivity {
    EditText dni, contrassenya, poblacio, direccio, edat, sexe, telefon, instagram;
    Button edita;
    private Spinner spinner;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        dni= (EditText)findViewById(R.id.dni1Text);
        contrassenya= (EditText)findViewById(R.id.contrasenyaText);
        poblacio= (EditText)findViewById(R.id.poblacioText);
        direccio= (EditText)findViewById(R.id.direccioText);
        edat= (EditText)findViewById(R.id.edatText);
        sexe= (EditText)findViewById(R.id.sexeText);
        telefon= (EditText)findViewById(R.id.telefonText);
        instagram= (EditText)findViewById(R.id.instagramText);
        edita= (Button)findViewById(R.id.botoSave);
        spinner = (Spinner)findViewById(R.id.activitat1Spinner);

        String [] grup = {"Hoquei","Basquet","Lleure","Hoquei i lleure","Basquet i lleure"};

        ArrayAdapter<String> array1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, grup);
        spinner.setAdapter(array1);

        edita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutar("http://192.168.1.11/BaseDatos/editar.php");
            }
        });

    }
    private void ejecutar(String url){
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Base de datos conectada", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("dni",dni.getText().toString());
                parametros.put("contrasenya",contrassenya.getText().toString());
                parametros.put("poblacio",poblacio.getText().toString());
                parametros.put("direccio",direccio.getText().toString());
                parametros.put("edat",edat.getText().toString());
                parametros.put("sexe",sexe.getText().toString());
                parametros.put("telefon",telefon.getText().toString());
                parametros.put("instagram",instagram.getText().toString());
                parametros.put("activitat",spinner.getSelectedItem().toString());
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
