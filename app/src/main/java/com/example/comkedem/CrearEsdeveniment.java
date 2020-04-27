package com.example.comkedem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class CrearEsdeveniment extends AppCompatActivity {
    EditText idesd, nomactivitat, data, data_lim, ubicacio, preu, dades, dniadm, codiact;
    Button crear;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_esdeveniment);

        idesd= (EditText)findViewById(R.id.idText);
        nomactivitat= (EditText)findViewById(R.id.nomactText);
        data= (EditText)findViewById(R.id.dataText);
        data_lim= (EditText)findViewById(R.id.datalimText);
        ubicacio= (EditText)findViewById(R.id.ubicacioText);
        preu= (EditText)findViewById(R.id.preuText);
        dades= (EditText)findViewById(R.id.dadesText);
        dniadm= (EditText)findViewById(R.id.dniadminText);
        codiact= (EditText)findViewById(R.id.actText);
        crear= (Button)findViewById(R.id.botoCrear);

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                execucrea("http://192.168.1.11/BaseDatos/registrar.php");
                MenuAdmin();
            }
        });

    }

    private void execucrea(String url){
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
                parametros.put("idesd",idesd.getText().toString());
                parametros.put("nomactivitat",nomactivitat.getText().toString());
                parametros.put("data",data.getText().toString());
                parametros.put("data_lim",data_lim.getText().toString());
                parametros.put("ubicacio",ubicacio.getText().toString());
                parametros.put("preu",preu.getText().toString());
                parametros.put("dades",dades.getText().toString());
                parametros.put("dniadm",dniadm.getText().toString());
                parametros.put("codiact",codiact.getText().toString());
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void MenuAdmin() {
        Intent ini = new Intent(this,MenuAdministrador.class);
        startActivity(ini);
    }

}
