package com.example.project;
import java.util.ArrayList;
import java.util.Collections;

public class Deck{
    private ArrayList<Card> cards;

    public Deck(){
        cards = new ArrayList<>();
        initializeDeck();
        shuffleDeck();
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    public  void initializeDeck(){ //hint.. use the utility class
        for(int i = 0; i < Utility.getRanks().length; i ++) {
            for(int j = 0; j < Utility.getSuits().length; j ++) {
                cards.add(new Card(Utility.getRanks()[i], Utility.getSuits()[j]));

            }
        }
    }

    public  void shuffleDeck(){ //You can use the Collections library or another method. You do not have to create your own shuffle algorithm
        Collections.shuffle(cards);
    }

    public  Card drawCard(){
       Card draw = cards.get(0);
       cards.remove(0);
       return draw;
    }

    public  boolean isEmpty(){
        return cards.isEmpty();
    }

   


}