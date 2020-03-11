package com.algoritmos;

import java.io.Console;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private ArrayList<String> types = new ArrayList<>(){{add("Monstruo"); add("Trampa");add("Hechizo");}};
    private User user;

    public Controller() {

        user = new User();
        pool = MapFactory.getFactory().getMap();
        fillPool("cards_desc.txt");

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
                    types.forEach((type) -> {
                        View.getView().print("Tipo: " + type + "Cartas:\n");
                        mergeCards().forEach((name, card) -> {
                            if (type.equalsIgnoreCase(card.getType())) card.describe(1);
                        });
                        View.getView().print("\n\n");
                    });

                    break;
                default:
                    decision = 6;
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
    private void fillPool(String text){
        try {

            var mainPath = Controller.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            if (getOsName().startsWith("Windows")){
                if(String.valueOf(mainPath.charAt(0)).equals("/")) { mainPath = mainPath.substring(1, mainPath.length());}
            }
            List<String> strings = Files.readAllLines(Path.of(mainPath + text));
            for (String line:
                    strings) {

                var holder = new ArrayList<String>(){{ addAll(List.of(line.split("\\|")));}};
                var name = holder.get(0);
                var type = holder.get(1);
                pool.put(name, new Card(type, name));
            }

        } catch(URISyntaxException | IOException e){
            System.out.print(e);
            System.out.println("Revise bien que su archivo txt  exista");
        }
    }
    private  String getOsName()
    {
        return System.getProperty("os.name");
    }
}
