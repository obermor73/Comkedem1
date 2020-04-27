package com.example.comkedem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuAdministrador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_administrador);
    }

    public void ModUsu(View v) {
        Intent mod = new Intent(this,ModificarUsuari.class);
        startActivity(mod);
    }

    public void EliUsu(View v) {
        Intent eli = new Intent(this,EsborrarUsuari.class);
        startActivity(eli);
    }

    public void NouEsd(View v) {
        Intent esd = new Intent(this,CrearEsdeveniment.class);
        startActivity(esd);
    }

    public void DelEsd(View v) {
        Intent eliesd = new Intent(this,EliminarEsdeveniment.class);
        startActivity(eliesd);
    }

    public void MenuIni(View v) {
        Intent menui = new Intent(this,Menu_inicial.class);
        startActivity(menui);
    }
}
