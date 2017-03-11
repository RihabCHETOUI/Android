package com.example.chetouri.smartsudoku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;



public class ChoixActivity extends AppCompatActivity implements OnItemClickListener{
       private ListView L;
       private ArrayAdapter <String> A;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix);
        L = (ListView) findViewById(R.id.listView);
     //Creation de la liste du choix des chiffres pour remplissage de la grille
        String [] s = {"0","1","2","3","4","5","6","7","8","9"};
        A = new ArrayAdapter < >(this,android.R.layout.simple_list_item_1,android.R.id.text1,s);
        L.setAdapter(A);
        L.setOnItemClickListener(this);
    }
    //L'envoi du chiffre choisi(id) de l'activité ChoixActivity à l'activité JeuActivity
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent send = new Intent(ChoixActivity.this, JeuActivity.class);
        send.putExtra("Data",id);
        setResult(RESULT_OK,send);
        finish();
        



    }
}
