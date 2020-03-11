package com.algoritmos;

public class Card implements Comparable{
    private String type = "";
    private String name = "";

    public Card(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Card){
            Card card = (Card)o;
            return this.type.compareTo(card.getType());
        }
        return 0;
    }

    public void describe(int count){
        View.getView().print(
                "Nombre: " + name +
                "\nTipo: " + type +
                "\nCantidad: " + count + "\n"
        );
    }
    public void describe(){
        View.getView().print(
                "Nombre: " + name +
                        "\nTipo: " + type + "\n"
        );
    }

    public String toString(){

        return "Nombre: " + name +
                "\nTipo: " + type + "\n";
    }
}
