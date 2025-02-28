package com.example.project;

public class test {
    public static void main(String[] args) {
        Deck a = new Deck();
        a.initializeDeck();
        System.out.println(a.getCards().get(1).getRank());
        System.out.println(a.getCards().get(1).getSuit());
    }
}
