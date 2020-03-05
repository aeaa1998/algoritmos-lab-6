package com.algoritmos;

import java.util.ArrayList;
import java.util.Map;

public class Controller {
    private Map<String, Card> pool;
    private ArrayList<String> menuOptions = new ArrayList<>(){{
        add("Agarrar carta");
        add("Mostrar tipo de una carta especifica");
        add("Mostrar nombre, tipo  y cantidad de cada carta que se tiene en colección de usuario");
        add("Mostrar nombre, tipo  y cantidad de cada carta que se tiene en colección ordenadas por tipo");
        add("Mostrar el nombre y tipo de todas las cartas existentes");
        add("Mostrar el nombre y tipo de todas las cartas existentes, ordenadas por tipo");
        add("Salir");
    }};
    private ArrayList<String> types = new ArrayList<>(){{add("Mounstruo"); add("Trampa");add("Hechizo");}};
    private User user;

    public Controller() {
        user = new User();
        pool = MapFactory.getFactory().getMap();
    }
    public void init(){
        int decision = -1;
        while (decision != (menuOptions.size() - 1)){
            decision = View.getView().selectOptions(menuOptions, "Escoja el numero de una de las siguientes opciones",
                    "Escoja una opcion valida");
            switch (decision){
                case 0:
                    if (pool.isEmpty()) View.getView().print("Ya no hay cartas que jalar");
                    else {
                        var selectedCard = getCard();
                        user.addCard(selectedCard);
                        View.getView().print("Carta agregada exitosamente");
                    }
                    break;
                case 1:
                    var typeSingle = View.getView().selectObject(mergeCards()).getType();
                    View.getView().print("Tipo de Carta selecctionada "  + typeSingle);
                    break;
                case 2:
                    if (user.getCollectionOfCard().isEmpty()) View.getView().print("El usuario no tiene cartas");
                    else user.getCollectionOfCard().forEach((name, card) -> card.describe(1));
                    break;
                case 3:
                    if (user.getCollectionOfCard().isEmpty()) View.getView().print("El usuario no tiene cartas");
                    else {
                        types.forEach((type) -> {
                            View.getView().print("Tipo: " + type + "Cartas:\n");
                            user.getCollectionOfCard().forEach((name, card) -> {
                                if (type.equalsIgnoreCase(card.getType())) card.describe(1);
                            });
                            View.getView().print("\n\n");
                        });
                    }
                    break;
                case 4:
                    View.getView().print("Mostrando nombre y tipo de todas las cartas\n");
                    mergeCards().forEach((name, card) -> {
                        card.describe();
                    });
                    View.getView().print("\n");
                    break;

                case 5:
//                        ACA VA EL CODIGO DE TODAS LAS CARTAS FILTRADAS POR TIPO Y MOSTRANDO TODOS SUS CASOS ES COMO EL
//                        CODIGO DEL CASE 3
                    break;


            }
        }
    }

    private Card getCard(){
        String name = View.getView().selectKey(pool);
        final var holder = pool.get(name);
        pool.remove(name);
        return holder;
    }

    private Map<String, Card> mergeCards(){
        Map<String, Card> holder = MapFactory.getFactory().getMap();
        holder.putAll(user.getCollectionOfCard());
        holder.putAll(pool);
        return holder;
    }
}
