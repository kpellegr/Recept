package com.example.recept;

// Een Ingredient bevat alle informatie én logica om een ingrediënt van ons recept te beheren
public class Ingredient {
        float hoeveelheid_pp;  // hoeveelheid per persoon is een komma-getal (float)
        String naam_enkelvoud, naam_meervoud, eenheid_enkelvoud, eenheid_meervoud;

        Ingredient(String naam_enkelvoud,
                   String naam_meervoud,
                   float hoeveelheid_pp,
                   String eenheid_enkelvoud,
                   String eenheid_meervoud){
            this.naam_enkelvoud = naam_enkelvoud;
            this.naam_meervoud = naam_meervoud;
            this.hoeveelheid_pp = hoeveelheid_pp;
            this.eenheid_enkelvoud = eenheid_enkelvoud;
            this.eenheid_meervoud = eenheid_meervoud;
        }

        // methode om het Ingredient om te zetten naar een tekst-representatie, op basis
        // van het aantal personen.
        public String toString(int amount_of_people) {
            float benodigd = amount_of_people * hoeveelheid_pp;

            // check of we de het ingredient in enkelvoud of meervoud moeten geven
            if (benodigd == 1.0f) {
                return String.format("%.1f %s %s", benodigd, eenheid_enkelvoud, naam_enkelvoud);
            }
            else {
                return String.format("%.1f %s %s", benodigd, eenheid_meervoud, naam_meervoud);
            }
        }
}
