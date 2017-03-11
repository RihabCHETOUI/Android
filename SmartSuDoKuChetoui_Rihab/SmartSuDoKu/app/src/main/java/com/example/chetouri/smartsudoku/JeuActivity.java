package com.example.chetouri.smartsudoku;

/**
 * Created by chetouri on 05/10/2016.
 */



        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.MotionEvent;
        import android.view.View;
        import android.widget.Button;
        import android.widget.RelativeLayout;

public class JeuActivity extends AppCompatActivity {

    private final int CHOIX_NUM_FENETRE = 0;
    // Declarer la grille et une intent
    private int x = 0;
    private int y = 0;
    Grille grille;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);
        Context cont = getApplicationContext();
        grille = new Grille(cont);

        // Intent à  initialiser ici
        intent = new Intent(JeuActivity.this, ChoixActivity.class);
        grille.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    x = grille.getXFromMatrix((int) event.getX());
                    y = grille.getYFromMatrix((int) event.getY());
                     // vÃ©rifier si la case n'est pas fixe, on lui affecte le numÃ©ro result
					if (x < 9 && y < 9 && grille.isNotFix(y, x))
                        startActivityForResult(intent, CHOIX_NUM_FENETRE);
                }
                return true;

            }
        });

        // creation du bouton valider
		final Button Validate = (Button) findViewById(R.id.button3);
        Validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valider(grille);
            }
        });
		//l'ajout de la view grille dans le RelativeLayout
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.myLayout);
        layout.addView(grille);
        
    }


    //Réception du chiffre envoyé de l'activité ChoixActivity
    @Override
    public void onActivityResult(int request, int result, Intent intent) {
        if (request==CHOIX_NUM_FENETRE){
            if (result==ChoixActivity.RESULT_OK && intent != null){
                long receiving = intent.getLongExtra("Data",0);
                    grille.set(y,x,(int) receiving);
            }
        }
       
    }

    public void valider(View v) {
        grille.invalidate();
        boolean resultat = grille.isValid() ;
        System.out.println("Résultat : "+resultat);
    }

}




