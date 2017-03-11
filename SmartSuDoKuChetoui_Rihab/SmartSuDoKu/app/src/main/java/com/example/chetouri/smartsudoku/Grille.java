package com.example.chetouri.smartsudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Grille extends View {

    private int screenWidth;
    private int screenHeight;
    private int n;
    
	// Déclaration des paints utilisé pour le dessin des lignes et le remplissage des cases de la Grille
    private Paint paint1;
    private Paint paintLineG;
    private Paint paintLineR;
    private Paint paintLineB;
    private Paint paintTextR;
    private Paint paintTextB;
    
	// Déclaration de la variable du test gagner (Test=2)/perdu (Test=1)
	private int Test=0;
    // Déclaration de la matrix
	private int[][] matrix = new int[9][9];
    // Déclaration de la matrix
	private boolean[][] fixIdx = new boolean[9][9];

    public Grille(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public Grille(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Grille(Context context) {
        super(context);
        init();
    }

    private void init() {
        set("000105000140000670080002400063070010900000003010090520007200080026000035000409000");
        //set("672145398145983672389762451263574819958621743714398526597236184426817935831459267");
        // set("123456789912345678891234567789123456678912345567891234456789123345678912234567891");
        // set("000400870", 0);
        // set("047092050", 1);
        // set("200600030", 2);
        // set("970500203", 3);
        // set("508024706", 4);
        // set("604007085", 5);
        // set("090308007", 6);
        // set("003240160", 7);
        // set("012000090", 8);
      
	  // Définition des paints utilisé pour le dessin des lignes et le remplissage des cases de la Grille
        paint1 = new Paint();
        paint1.setAntiAlias(true);
        paint1.setColor(Color.BLACK);
        
		paintLineR = new Paint();
        paintLineR.setAntiAlias(true);
        paintLineR.setColor(Color.RED);
        paintLineR.setStrokeWidth(10);
        
		paintLineB = new Paint();
        paintLineB.setAntiAlias(true);
        paintLineB.setColor(Color.BLACK);
        paintLineB.setStrokeWidth(4);
        
		paintLineG = new Paint();
        paintLineG.setAntiAlias(true);
        paintLineG.setColor(Color.GREEN);
        paintLineG.setStrokeWidth(10);

		paintTextR = new Paint();
        paintTextR.setAntiAlias(true);
        paintTextR.setColor(Color.RED);
        paintTextR.setTextSize(60);
        
		paintTextB = new Paint();
        paintTextB.setAntiAlias(true);
        paintTextB.setColor(Color.BLACK);
        paintTextB.setTextSize(60);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        screenWidth = getWidth();
        screenHeight = getHeight();
        int x = Math.min(screenWidth, screenHeight);
        
		n = (x / 9); //- (1 - (x % 2));

        // Dessiner les lignes noires
        

        for(int i=0;i<=x;i=i+n) {
            if (i == 3*n || i==6*n){
                canvas.drawLine(0,i,9*n,i,paintLineR);
            }

            else
                canvas.drawLine(0,i,9*n,i,paintLineB);

        }

        // Dessiner les lignes rouges

		for(int k=0; k<=x;k=k+n){

            if(k == 3*n || k==6*n){
                canvas.drawLine(k,0,k,9*n,paintLineR);
            }
            else
                canvas.drawLine(k,0,k,9*n,paintLineB);
        }
        
		// Test GAGNER/PERDU
        if (Test==1){
            canvas.drawLine(0,0,0,n*9,paintLineR);
            canvas.drawLine(0,0,n*9,0,paintLineR);
            canvas.drawLine(0,n*9,n*9,n*9,paintLineR);
            canvas.drawLine(n*9,0,n*9,n*9,paintLineR);
        }

        if (Test==2){
                canvas.drawLine(0,0,0,n*9,paintLineG);
                canvas.drawLine(0,0,n*9,0,paintLineG);
                canvas.drawLine(0,n*9,n*9,n*9,paintLineG);
                canvas.drawLine(n*9,0,n*9,n*9,paintLineG);
        }
        // Le contenu d'une case
        String s;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                s = "" + (matrix[j][i] == 0 ? "" : matrix[j][i]);
                if (fixIdx[j][i])
                    canvas.drawText(s, i * n + (n / 2) - (n / 10), j * n
                            + (n / 2) + (n / 10), paintTextR);
                else
                    canvas.drawText(s, i * n + (n / 2) - (n / 10), j * n
                            + (n / 2) + (n / 10), paintTextB);
            }
        }
    }

    public int getXFromMatrix(int x) {
        // Renvoie l'indice d'une case Ã  partir du pixel x de sa position h
        return (x / n);
    }

    public int getYFromMatrix(int y) {
        // Renvoie l'indice d'une case Ã  partir du pixel y de sa position v
        return (y / n);
    }

    public void set(String s, int i) {
        // Remplir la iÃ¨me ligne de la matrice matrix avec un vecteur String s
        int v;
        for (int j = 0; j < 9; j++) {
            v = s.charAt(j) - '0';
            matrix[i][j] = v;
            if (v == 0)
                fixIdx[i][j] = false;
            else
                fixIdx[i][j] = true;
        }
    }

    public void set(String s) {
        // Remplir la matrice matrix Ã  partir d'un vecteur String s
        for (int i = 0; i < 9; i++) {
            set(s.substring(i * 9, i * 9 + 9), i);
        }
    }

    public void set(int x, int y, int v) {
        // Affecter la valeur v Ã  la case (y,x)
        matrix [x] [y]=v;
    }

    public boolean isNotFix(int x, int y) {
        // Renvoie si la case (y,x) n'est pas fixe
        if (fixIdx [x] [y]== true)
            return false;
        else
            return true;

    }

    public boolean isValid() {
        // 1. VÃ©rifier l'existence de chaque numÃ©ro (de 1 Ã  9) dans chaque
        // ligne et chaque colonne
        boolean[] rl = { true, true, true, true, true, true, true, true, true };
        boolean[] rc = { true, true, true, true, true, true, true, true, true };
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (matrix[i][j] == 0){
                    Test=1;
                    return false;
                }
                if (rl[j] && rc[j])
                    rl[j] = rc[j] = false;
                else
                {Test=1;
                    return false;}
            }
            for (int j = 0; j < 9; j++) {
                rl[matrix[i][j] - 1] = true;
                rc[matrix[i][j] - 1] = true;
            }
        }
        // ------
        // 2. VÃ©rifier l'existence de chaque numÃ©ro (de 1 Ã  9) dans chacun
        // des 9 carrÃ©s
        boolean[] r = { true, true, true, true, true, true, true, true, true };
        int w;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                w = 0;
                for (int k = i * 3; k < i * 3 + 3; k++) {
                    for (int l = j * 3; l < j * 3 + 3; l++) {
                        if (matrix[k][l] == 0){
                            Test=1;
                            return false;
                        }
                        if (r[w])
                            r[w++] = false;
                        else
                        {Test=1;
                            return false;}
                    }
                }
                for (int k = i * 3; k < i * 3 + 3; k++) {
                    for (int l = j * 3; l < j * 3 + 3; l++) {
                        r[matrix[k][l] - 1] = true;
                    }
                }
            }
        }
        // ------
        
		// Gagner
        Test=2;
        return true;
    }
}

