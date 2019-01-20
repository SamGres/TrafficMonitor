package com.example.lukak.samgre;

import java.util.ArrayList;
import java.util.List;

public class ObvestilaRepositori {


    public List<Obvestilo> PridobiObvestila(){
        List<Obvestilo> obvestilos = new ArrayList<Obvestilo>();
        obvestilos.add(new Obvestilo("14.1.2019 - Možnost dodajanja dogodkov","Od 15.1.2019 bo na voljo dodajanja dogodkov."));
        obvestilos.add(new Obvestilo("12.1.2019 - Posodobitev mape v1.1","Od 22.1.2019 bo na voljo novi pogled na opciji mapa tudi podroben pregled vseh dogotkov."));
        obvestilos.add(new Obvestilo("5.1.2019 - Začetek razvoja android odjemalne aplikacije","Ekipa SamGreš je pričela razvoj odjemalca v obliki android aplikacije. Kasneje je planirana še iOS aplikacija."));
        return  obvestilos;
    }
}
