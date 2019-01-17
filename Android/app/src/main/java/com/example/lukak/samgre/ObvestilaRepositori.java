package com.example.lukak.samgre;

import java.util.ArrayList;
import java.util.List;

public class ObvestilaRepositori {


    public List<Obvestilo> PridobiObvestila(){
        List<Obvestilo> obvestilos = new ArrayList<Obvestilo>();
        obvestilos.add(new Obvestilo("12.1.2019","asdasdasdasdasd"));
        obvestilos.add(new Obvestilo("12.1.2019","asdasdasdasdasd"));
        obvestilos.add(new Obvestilo("12.1.2019","asdasdasdasdasd"));
        obvestilos.add(new Obvestilo("12.1.2019","asdasdasdasdasd"));
        obvestilos.add(new Obvestilo("12.1.2019","asdasdasdasdasd"));
        return  obvestilos;
    }
}
