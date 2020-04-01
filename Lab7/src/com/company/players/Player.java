package com.company.players;

import com.company.Board;
import com.company.Token;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class Player implements Runnable {
    protected String name;
    protected Board board;
    protected List<Token> chosenTokens;

    public Player(String name, Board board) {
        this.name = name;
        this.board = board;
        chosenTokens = new ArrayList<>();
    }

    public int calculateLargestArithmeticProgression() {
        chosenTokens.sort(new Comparator<Token>() {
            @Override
            public int compare(Token o1, Token o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        System.out.println(name +" "+chosenTokens);
//brute force ca Bute in ring
        int lenghtMax = 2;
        Token token = new Token(1);
        for (int i = 0; i < chosenTokens.size() - 1; i++) {
            for (int j = i + 1; j < chosenTokens.size(); j++) {
                final int dif = chosenTokens.get(j).getValue() - chosenTokens.get(i).getValue();
                if (dif != 0) {
                    int lenght = 2;
                    token.setValue(chosenTokens.get(j).getValue() + dif);
                    while (chosenTokens.contains(token)) {
                        lenght++;
                        token.setValue(token.getValue() + dif);
                    }
                    if (lenght > lenghtMax) {
                        lenghtMax = lenght;
                    }
                }
            }
        }
        return lenghtMax;
    }


    public String getName() {
        return name;
    }

    public Token pickToken(Board board){return null;}
    protected void endMsg(){
        System.out.println("Punctaj " + name + " " + calculateLargestArithmeticProgression());
    }
}
