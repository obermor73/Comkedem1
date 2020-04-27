package com.example.comkedem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu_inicial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);
    }

    public void EditarPer(View v) {
        Intent edit = new Intent(this,EditarPerfil.class);
        startActivity(edit);
    }

    public void Calendari(View v) {
        Intent cal = new Intent(this,Calendario.class);
        startActivity(cal);
    }

    public void AreaAdmin(View v) {
        Intent aradm = new Intent(this,MenuAdministrador.class);
        startActivity(aradm);
    }
}
