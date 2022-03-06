package com.example.recept;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int amount = 1;
    Button plus;
    Button min;
    TextView pers, bac, creme, limo, tor, kaas, ado, ajuin, toma;

    /*ingredienten*/
    int spek, room, limoen, tortilla, cheddar, avocado, ui, tomaat;

    class Ingredient{
        int hoeveel;

        Ingredient(int h){
            hoeveel = h;
        }

        int totaal(){
            return amount * hoeveel;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pers = findViewById(R.id.person);
        plus = findViewById(R.id.btnplus);
        min = findViewById(R.id.btnmin);
        bac = findViewById(R.id.spek);
        creme = findViewById(R.id.room);
        limo = findViewById(R.id.limoen);
        tor = findViewById(R.id.tortilla);
        kaas = findViewById(R.id.cheddar);
        ado = findViewById(R.id.avocado);
        ajuin = findViewById(R.id.ui);
        toma = findViewById(R.id.tomaat);

        Ingredient i1 = new Ingredient(5);
        Ingredient i2 = new Ingredient(200);
        Ingredient i3 = new Ingredient(2);
        Ingredient i4 = new Ingredient(1);
        Ingredient i5 = new Ingredient(50);
        Ingredient i6 = new Ingredient(1);
        Ingredient i7 = new Ingredient(1);
        Ingredient i8 = new Ingredient(10);

        adapt();

        plus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                amount = amount + 1;
                spek = i1.totaal();
                room = i2.totaal();
                limoen = i3.totaal();
                tortilla = i4.totaal();
                cheddar = i5.totaal();
                avocado= i6.totaal();
                ui = i7.totaal();
                tomaat = i8.totaal();
                MainActivity.this.adapt();
            }
        });

        min.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                amount = amount - 1;
                spek = i1.totaal();
                room = i2.totaal();
                limoen = i3.totaal();
                tortilla = i4.totaal();
                cheddar = i5.totaal();
                avocado= i6.totaal();
                ui = i7.totaal();
                tomaat = i8.totaal();
                MainActivity.this.adapt();
            }
        });
    }

    void adapt() {
        String personen = String.format("Personen: %d", amount);
        pers.setText(personen);
        String bacon = String.format("%s %s", spek, " sneetjes ontbijtspek");
        String cream = String.format("%s %s", room, "g zure room");
        String lime = String.format("%s %s", limoen, " limoenen");
        String tort;
        if(tortilla > 1) {
            tort = String.format("%s %s", tortilla, " tortilla's");
        } else{
            tort = String.format("%s %s", tortilla, " tortilla");
        }
        String cheese = String.format("%s %s", cheddar, " cheddar");
        String avo;
        if (avocado > 1) {
            avo = String.format("%s %s", avocado, "avocado's");
        } else {
            avo = String.format("%s %s", avocado, "avocado");
        }
        String onion = String.format("%s %s", ui, " rode ui");
        String tomato = String.format("%s %s", tomaat, " trostomaten");
        bac.setText(bacon);
        creme.setText(cream);
        limo.setText(lime);
        tor.setText(tort);
        kaas.setText(cheese);
        ado.setText(avo);
        ajuin.setText(onion);
        toma.setText(tomato);
        min.setEnabled(amount > 1);
    }
}