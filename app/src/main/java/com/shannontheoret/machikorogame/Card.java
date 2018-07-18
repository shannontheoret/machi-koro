package com.shannontheoret.machikorogame;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by shannonbrown on 2017-11-11.
 */

public class Card {
    public Set<Integer> activationNumbers;
    public Integer reward;
    public Integer cost;
    public Boolean rewardAppliesOnPlayersTurn;
    public Boolean rewardAppliesOnOtherPlayersTurn;

    public Card(Set<Integer> activationNumbers, Integer reward, Integer cost, Color color){
        this.activationNumbers = activationNumbers;
        this.reward = reward;
        this.cost = cost;
        this.rewardAppliesOnPlayersTurn = (color == Color.GREEN || color == Color.BLUE);
        this.rewardAppliesOnOtherPlayersTurn = (color == Color.BLUE || color == Color.RED);
    }

    public Card(Integer onlyActivationNumber, Integer reward, Integer cost, Color color){
        this.activationNumbers = new HashSet<>();
        this.activationNumbers.add(onlyActivationNumber);
        this.reward = reward;
        this.cost = cost;
        this.rewardAppliesOnPlayersTurn = (color == Color.GREEN || color == Color.BLUE);
        this.rewardAppliesOnOtherPlayersTurn = (color == Color.BLUE || color == Color.RED);
    }

    public Card(Integer cost){
        this.cost = cost;
        this.rewardAppliesOnOtherPlayersTurn = false;
        this.rewardAppliesOnPlayersTurn = false;
        this.activationNumbers = new HashSet<>();
    }

    public static enum Color{
        BLUE,
        GREEN,
        RED,
        PURPLE,
        YELLOW;
    }

    public Boolean cardApplies(Integer rollValue, Boolean playersTurn){
        if((playersTurn && this.rewardAppliesOnPlayersTurn) || (!playersTurn && this.rewardAppliesOnOtherPlayersTurn)) {
            if(this.activationNumbers.contains(rollValue)){
                return true;
            }
        }
        return false;
    }

}
