package com.algoritmos;

import java.util.*;

public class MapFactory {
    private static MapFactory factory = null;
    private String decision = "";
    private ArrayList<String> mapOptions = new ArrayList<>(){{add("HashMap"); add("TreeMap"); add("LinkedHashMap");}};
    public static MapFactory getFactory(){
        if (factory == null) factory = new MapFactory();
        return factory;
    }



    public <K> Map<String, K> getMap(){
        if (decision.equalsIgnoreCase("")) decision = View.getView().selectOptions(mapOptions,
                "Ingrese que implementacion de map desea utilizar.\nDe manera textual!");
        switch (decision){
            case "HashMap":
                return new HashMap<>();
            case "TreeMap":
                return new TreeMap<>();
            default:
                return new LinkedHashMap<>();
        }
    }
}
