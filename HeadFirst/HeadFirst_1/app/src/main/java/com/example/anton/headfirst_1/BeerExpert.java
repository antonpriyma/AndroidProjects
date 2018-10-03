package com.example.anton.headfirst_1;

import java.util.ArrayList;
import java.util.List;

public class BeerExpert {
    public List<String> getBrands(String color){
        List<String> beerSorts = new ArrayList<>();
        if (color.equals("amber")){
            beerSorts.add("Jack Amber");
            beerSorts.add("Red Moose");
        }else {
            beerSorts.add("Jail Pale Ale");
            beerSorts.add("Gout Stout");
        }
        return beerSorts;
    }
}
