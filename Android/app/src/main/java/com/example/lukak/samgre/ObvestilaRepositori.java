package com.example.lukak.samgre;

public class ObvestilaRepositori {


    public Obvestilo[] PridobiObvestila(){
        Obvestilo[] obvestilos = new Obvestilo[5];
        obvestilos[0] = new Obvestilo("12.1.2019","asdasdasdasdasd");
        obvestilos[1] = new Obvestilo("5.1.2019","asdasdasdasdasd");
        obvestilos[2] = new Obvestilo("20.12.2018","asdasdasdasdasd");
        obvestilos[3] = new Obvestilo("11.12.2018","asdadssssssssssss");
        obvestilos[4] = new Obvestilo("11.11.2018","ssssssssssssssssssss");
        return  obvestilos;
    }
}
