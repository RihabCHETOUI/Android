package com.example.chetouri.smartsudoku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // Atteindre l'activité JeuActivity
    public void lancer(View view) {
        Intent intent = new Intent(MainActivity.this, JeuActivity.class);
        startActivity(intent);
    }
    // Atteindre l'activité AboutActivityActivity
    public void apropos(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}
