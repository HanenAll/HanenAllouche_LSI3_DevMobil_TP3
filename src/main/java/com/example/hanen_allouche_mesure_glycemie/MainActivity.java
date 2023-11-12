package com.example.hanen_allouche_mesure_glycemie;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etValeur; //l’EditText qui comporte la valeur mesurée.
    private TextView tvAge , tvJeunez; //le TextView qui comporte le question de  l'age(tvAge)
    //le texteView qui comporte le question Est-ce que vous jeunez ? (tvJeunez)
    private SeekBar sbAge; // le SeekBar qui comporte l'age.
    private RadioButton rbIsFasting,rbIsNotFasting; // les 2 boutons qui repondent
    // au question:Est-ce que vous jeunez ?
    private Button btnConsulter; // le bouton de consultation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* cette methode est un thread qui fait le lancement de l'interface , elle prend
        en parametre un Bundle contenant l'etat precedent de l'activité */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // on fait appel au contenu qui ce trouvedans
        // le fichier activity_main.xml
        init();
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            /*cette methode est un listener sur le changement du seekbar , en fait lorsque
            l'utilisateur diminue ou augmente la valeur de seekbar, Le TextView, dont l’ID
            est “tvAge” affiche un message à l’utilisateur qui comporte : Votre âge + la valeur choisie
             et ça est fait par la methode onProgressChanged(..) qui est à l'interieure de la methode
              OnseekBarChangeListner() */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                Log.i("Information", "onProgressChanged "+progress);
                // affichage dans le Log de Android studio pour voir les changements détectés sur le SeekBar ..
                tvAge.setText("Votre âge : "+ progress);
                // Mise à jour du TextView par la valeur : progress à chaque changement.
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        btnConsulter.setOnClickListener(new View.OnClickListener(){
            // cette methode est un listner sur l'action de cliquer sur le bouton consulter
            public void onClick(View v){
                calculer(v);
            }
        });
    }
    public void calculer(View v){
        /* dans cette methode on va verifier l'age et la valeur mesurer entréés par l'utilisateur
         et puis on va afficher le resultat selon ces informations */
        Log.i("Information","button cliqué");
        int age;
        float valeurMesuree;
        boolean verifAge=false, verifValeur=false;
        if(sbAge.getProgress()!=0)
            verifAge=true;
        else
            Toast.makeText(MainActivity.this,"veuillez saisir votre age!",
                           Toast.LENGTH_SHORT).show();
        /* le Toast c'est un message qui s'affiche au utilisateur lorsque il n'a pas entrer
         son age convenablement */
        if(!etValeur.getText().toString().isEmpty())
            verifValeur=true;
        else
            Toast.makeText(MainActivity.this, "Veuillez saisir votre valeur mesurée !",
                           Toast.LENGTH_LONG).show();
        if(verifAge && verifValeur){
            age =sbAge.getProgress();
            valeurMesuree=Float.valueOf(etValeur.getText().toString());
            boolean isFasting=rbIsFasting.isChecked();
            if(isFasting){
                if (age >= 13) {
                    if (valeurMesuree < 5.0)
                        tvJeunez.setText("Niveau de glycémie est trop bas");
                    else if (valeurMesuree >= 5.0 && valeurMesuree <= 7.2)
                        tvJeunez.setText("Niveau de glycémie est normale");
                    else
                        tvJeunez.setText("Niveau de glycémie est trop élevé");
                } else if (age >= 6 && age <= 12) {
                    if (valeurMesuree < 5.0)
                        tvJeunez.setText("Niveau de glycémie est trop bas");
                    else if (valeurMesuree >= 5.0 && valeurMesuree <= 10.0)
                        tvJeunez.setText("Niveau de glycémie est normale");
                    else
                        tvJeunez.setText("Niveau de glycémie est trop élevé");
                } else if (age < 6) {
                    if (valeurMesuree < 5.5)
                        tvJeunez.setText("Niveau de glycémie est trop bas");
                    else if (valeurMesuree >= 5.5 && valeurMesuree <= 10.0)
                        tvJeunez.setText("Niveau de glycémie est normale");
                    else
                        tvJeunez.setText("Niveau de glycémie est trop élevé");
                }
            } else {
                if (valeurMesuree > 10.5)
                    tvJeunez.setText("Niveau de glycémie est trop élevé");
                else
                    tvJeunez.setText("Niveau de glycémie est normale");
            }
        }
    }
    private void init(){
        /* dans cette methode on va initialiser les attributs de la classe MainActivity en
         recuperant leurs references dans la mise en page */
        etValeur=findViewById(R.id.etValeur); /*la methode findViewById permet de rechercher
        suivant son ID dans la mise en page associée à l’activité.*/
        tvAge=findViewById(R.id.tvAge);
        sbAge=findViewById(R.id.sbAge);
        tvJeunez=findViewById(R.id.tvJeunez);
        rbIsFasting=findViewById(R.id.rbOui);
        rbIsNotFasting=findViewById(R.id.rbNon);
        btnConsulter=findViewById(R.id.btnConsulter);

    }
}