package com.example.comkedem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText user, contra;
    Button iniciar, registrar;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user= (EditText)findViewById(R.id.usuText);
        contra= (EditText)findViewById(R.id.passText);
        iniciar= (Button)findViewById(R.id.botoIS);
        registrar= (Button)findViewById(R.id.botoReg);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarUsuari("http://192.168.1.11/BaseDatos/iniciar_sesion.php");
                IniciarSessioAct();
            }
        });
    }

    public void IniciarSessioAct() {
        Intent ini = new Intent(this,Menu_inicial.class);
        startActivity(ini);
    }

    public void RegistrarAct(View v) {
        Intent reg = new Intent(this,Registrar.class);
        startActivity(reg);
    }

    private void buscarUsuari(String url){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        user.setText(jsonObject.getString("nomusuari"));
                        contra.setText(jsonObject.getString("contrasenya"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error de connexio",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}
