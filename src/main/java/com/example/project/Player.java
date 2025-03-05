package com.example.project;
import java.util.ArrayList;


public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){
        allCards = hand;
        for(int i = 0; i < communityCards.size(); i ++) {
            allCards.add(communityCards.get(i));
        }      
        sortAllCards();
        int consecutiveCount = 0;
        for(int i = 0; i < allCards.size() - 1;i ++) {
            if(Utility.getRankValue(allCards.get(i).getRank()) == Utility.getRankValue(allCards.get(i + 1).getRank()) - 1) {
                consecutiveCount ++;
            }
        }

        int pairCount = 0;
        boolean threeKind = false;
        boolean fourKind = false;
        for(int i = 0; i < findRankingFrequency().size(); i ++) {
            if(findRankingFrequency().get(i) == 2) {
                pairCount ++;
            }
            else if(findRankingFrequency().get(i) == 3) {
                threeKind = true;
            }
            else if(findRankingFrequency().get(i) == 4) {
                fourKind = true;
            }
        }

        boolean flush = false;
        for(int i = 0; i < findSuitFrequency().size(); i ++) {
            if(findSuitFrequency().get(i) == 5) {
                flush = true;
            }
        }
        if(flush) {
            if(consecutiveCount == 4) {
                if(Utility.getRankValue(allCards.get(0).getRank()) == 10) {
                    return "Royal Flush";
                }
                return "Straight Flush";
            }
            if(fourKind) {
                return "Four of a Kind";
            }
            else if(pairCount > 0 && threeKind) {
                return "Full House";
            }
            return "Flush";
        }
        else if(fourKind) {
            return "Four of a Kind";
        }
        else if(pairCount > 0 && threeKind) {
            return "Full House";
        }
        else if(consecutiveCount == 4) {
            return "Straight";
        }
        else if(threeKind) {
            return "Three of a Kind";
        }
        else if(pairCount == 2) {
            return "Two Pair";
        }
        else if (pairCount == 1) {
            return "A Pair";
        }

        int playerHigh = 0;
        int communityHigh = 0;
        for(Card card : communityCards) {
            if(Utility.getRankValue(card.getRank()) > communityHigh) {
                communityHigh = Utility.getRankValue(card.getRank());
            }
        }
        for(Card card : hand) {
            if(Utility.getRankValue(card.getRank()) > playerHigh) {
                playerHigh = Utility.getRankValue(card.getRank());
            }
        }

        if(playerHigh > communityHigh) {
            return "High Card";
        }
        return "Nothing";
    }

    public void sortAllCards(){
        for(int i = 1; i < allCards.size(); i ++) {
            int index = i;
            while(index - 1 >=0 && cardInFront(allCards.get(i), allCards.get(index - 1))) {
                index --;
            }
            Card temp = allCards.get(i);
            for(int j = i - 1; j >= index; j --) {
                allCards.set(j + 1, allCards.get(j));
            }
            allCards.set(index, temp);
        }
    } 

    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> rankFreq = new ArrayList<Integer>();
        for(int i = 0; i < Utility.getRanks().length; i ++) {
            rankFreq.add(0);
        }
        
        for(int i = 0; i < allCards.size();i ++) {
            int rankVal = Utility.getRankValue(allCards.get(i).getRank());
            rankFreq.set(rankVal - 2, rankFreq.get((rankVal - 2)) + 1);
        }
        return rankFreq; 
    }

    public ArrayList<Integer> findSuitFrequency(){
        ArrayList<Integer> suitFreq = new ArrayList<Integer>();
        for(int i = 0; i < Utility.getSuits().length; i ++) {
            suitFreq.add(0);
        }    

        for(int i = 0; i < allCards.size();i ++) {
            if(allCards.get(i).getSuit().equals("♠")) {
                suitFreq.set(0, suitFreq.get(0) + 1);

            }
            else if(allCards.get(i).getSuit().equals("♥")) {
                suitFreq.set(1, suitFreq.get(1) + 1);

            }
            else if(allCards.get(i).getSuit().equals("♣")) {
                suitFreq.set(2, suitFreq.get(2) + 1);
                
            }
            else if(allCards.get(i).getSuit().equals("♦")) {
                suitFreq.set(3, suitFreq.get(3) + 1);
                
            }
        }
        return suitFreq; 
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }

    private boolean cardInFront(Card c1, Card c2) {
        if(Utility.getRankValue(c1.getRank()) < Utility.getRankValue(c2.getRank())) {
            return true;
        }
        return false;
    }




}
