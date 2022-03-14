package com.example.recept;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Variabele die het aantal personen bevat waavoor het recept berekend moet worden
    int amount_of_people = 1;

    // Variabelen om de knoppen en de TextView met aantal personen vast te houden
    Button plus_btn, minus_btn, share_btn;
    TextView pers_tv;

    // Lijst met alle ingrediënten voor dit recept
    ArrayList<Ingredient> recept_array = new ArrayList<>();
    // Lijst met alle TextViews die we aanmaken om de ingrediënten te tonen in de app
    ArrayList<TextView> tv_array = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Zoek alle UI elementen op (hebben we later nodig)
        pers_tv = findViewById(R.id.person);
        plus_btn = findViewById(R.id.btnplus);
        minus_btn = findViewById(R.id.btnmin);
        share_btn = findViewById(R.id.btnshare);
        // We hebben ook een connectie met de layout nodig, om dynamisch TextViews toe te kunnen voegen
        ViewGroup layout = (ViewGroup) findViewById(R.id.rootlayout);

        // We bouwen het recept op door een reeks ingrediënten aan te maken en elk ingrediënt toe te voegen aan de lijst.
        recept_array.add(new Ingredient("zure room", "zure room", 200, "gram", "gram"));
        recept_array.add(new Ingredient("limoen", "limoenen", 2, "", ""));
        recept_array.add(new Ingredient("tortilla", "tortilla's", 1, "", ""));
        recept_array.add(new Ingredient("cheddar", "cheddar", 50, "gram", "gram"));
        recept_array.add(new Ingredient("ajuin", "ajuinen", 0.5f, "", ""));
        recept_array.add(new Ingredient("spek", "spek", 5f, "reepje", "reepjes"));

        // We lussen over de ingrediëntenlijst en maken voor elk ingrediënt een TextView aan, waarin we dit ingrediënt
        // kunnen tonen in de UI
        for (int i = 0; i<recept_array.size(); i++) {
            TextView tv = new TextView(this); // maak een nieuwe (lege) TextView aan
            layout.addView(tv, i+3);  // i+3 is de volgorde van de TextView in de UI (onder het aantal personen)
            tv_array.add(tv); // voeg de TextView toe aan de lijst met TextViews (hebben we straks nodig)

            final Ingredient current_ingredient = recept_array.get(i);

            // Zet een clickhandler op de textview, zodat we een nieuw scherm kunnen openen wanneer
            // er op het ingredient geklikt wordt
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // We maken een intent aan om een nieuwe Activity te starten en geven
                    // EXPLICIET mee welke klasse we willen starten (in casu DetailActivity.class)
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);

                    // We stoppen wat data in de intent (een string en een float) en we labelen
                    // die data, zodat we ze straks terugvinden.
                    intent.putExtra("INGREDIENT_NAME", current_ingredient.getNaam());
                    intent.putExtra("INGREDIENT_QTY", current_ingredient.getHoeveelheidPP());

                    // we lanceren de nieuwe Activity aan de hand van deze Intent
                    startActivity(intent);
                }
            });
        }

        // Initialiseer de user-interface (zet alle basisinformatie op het scherm)
        update_ui();

        // set een handler op de plus button, om het aantal mensen te verhogen
        plus_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                amount_of_people = amount_of_people + 1;
                MainActivity.this.update_ui();
            }
        });

        // set een handler op de min button, om het aantal mensen te verlagen
        minus_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (amount_of_people == 1) return; // niet verlagen als het aantal mensen 1 is.
                amount_of_people = amount_of_people - 1;
                MainActivity.this.update_ui();
            }
        });

        // set een handler op de share button, om het recept te delen
        share_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // We maken een nieuwe intent, maar geven deze keer NIET mee welke
                // klasse we willen starten; in plaats daarvan geven we onze intentie mee:
                // 'ACTION_SEND': we willen iets sturen/delen via een andere app
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);

                // we geven mee *wat* we willen delen
                intent.putExtra(Intent.EXTRA_TEXT, "Super recept voor burrito's!");
                intent.setType("text/plain");

                // We *vragen aan Android* om op zoek te gaan naar een Activity die in staat
                // is om onze vraag ('ACTION_SEND') te beantwoorden...
                // Let op: dit kan falen, hier hoort eigenlijkkcode bij om fouten af te handelen!
                startActivity(intent);
            }
        });

    }

    // functie om de UI te updaten met de actuele informatie
    void update_ui() {
        String personen = String.format("Personen: %d", amount_of_people);
        pers_tv.setText(personen);

        // We lussen over de lijst met ingrediënten én de corresponderende TextView en updaten
        // de TextViews één voor één
        for (int i = 0; i<recept_array.size(); i++) {
            TextView tv = tv_array.get(i);  // pak de i-de TextView
            Ingredient ing = recept_array.get(i); // pak het i-de ingrediënt
            tv.setText(ing.toString(amount_of_people));  // converteer het ingrediënt naar text
        }
    }
}