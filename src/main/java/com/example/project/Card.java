package com.example.project;
import java.util.ArrayList;

public class Card{
    private String rank;
    private String suit;

    public Card(String rank, String suit){ //Set Card with parameter
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank(){return rank;}
    public String getSuit(){return suit;}
    
    @Override
    public String toString(){
        return rank + " of " + suit;
    }

}