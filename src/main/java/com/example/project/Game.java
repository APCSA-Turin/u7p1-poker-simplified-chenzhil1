package com.example.project;
import java.util.ArrayList;
import java.util.logging.Handler;


public class Game{
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        if(Utility.getHandRanking(p1Hand) > Utility.getHandRanking(p2Hand)) {
            return "Player 1 wins!";
        }
        else if(Utility.getHandRanking(p1Hand) < Utility.getHandRanking(p2Hand)) {
            return "Player 2 wins!";
        }
        else {
            int highP1 = 0;
            int highP2 = 0;
            for(Card card : p1.getHand()) {
                if(Utility.getRankValue(card.getRank()) > highP1) {
                    highP1 = Utility.getRankValue(card.getRank());
                }
            }

            for(Card card : p2.getHand()) {
                if(Utility.getRankValue(card.getRank()) > highP2) {
                    highP2 = Utility.getRankValue(card.getRank());
                }
            }
            if(highP1 > highP2) {
                return "Player 1 wins!";
            }
            else if(highP2 > highP1) {
                return "Player 2 wins!";
            }
            return "Tie!";
        }
    }

    public static void main(String args[]){ //simulate card playing
                // Deck deck = new Deck();
        // for(int i = 0; i < 2; i ++) {
        //     p1.addCard(deck.drawCard());
        //     p2.addCard(deck.drawCard());
        // }
        // for(int i = 0; i < 3; i ++) {
        //     communityCards.add(deck.drawCard());
        // }
        Player player = new Player();
        player.addCard(new Card("3", "♠"));
        player.addCard(new Card("6", "♦"));
        
        // Community Cards
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("5", "♣"));
        communityCards.add(new Card("2", "♠"));
        communityCards.add(new Card("A", "♠"));
        
        
        System.out.println(player.playHand(communityCards));

    }
        
        

}