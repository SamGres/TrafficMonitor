package com.example.lukak.samgre;

import java.util.ArrayList;
import java.util.List;

public class ObvestilaRepositori {


    public List<Obvestilo> PridobiObvestila(){
        List<Obvestilo> obvestilos = new ArrayList<Obvestilo>();
        obvestilos.add(new Obvestilo("12.1.2019 - Posodobitev mape v1.1","Od 22.1.2019 bo na voljo novi pogled na opciji mapa tudi podroben pregled vseh dogotkov."));
        obvestilos.add(new Obvestilo("5.1.2019 - Lorem ipsum text 123sadasdasdasd asd as","lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum"));
        obvestilos.add(new Obvestilo("20.12.2018 - Lorem ipsum 2","lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsumc lorem ipsum lorem ipsum lorem ipsum lorem ipsum"));
        obvestilos.add(new Obvestilo("12.12.2018 - Lorem ipsum 3","lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsumc lorem ipsum lorem ipsum lorem ipsum lorem ipsum"));
        obvestilos.add(new Obvestilo("5.12.2018 - Lorem ipsum 4","lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsumc lorem ipsum lorem ipsum lorem ipsum lorem ipsum"));
        obvestilos.add(new Obvestilo("22.11.2018 - Lorem ipsum 4","lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsumc lorem ipsum lorem ipsum lorem ipsum lorem ipsum"));
        obvestilos.add(new Obvestilo("15.11.2018 - Lorem ipsum 4","lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsumc lorem ipsum lorem ipsum lorem ipsum lorem ipsum"));
        obvestilos.add(new Obvestilo("4.11.2018 - Lorem ipsum 4","lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsumc lorem ipsum lorem ipsum lorem ipsum lorem ipsum"));
        return  obvestilos;
    }
}
