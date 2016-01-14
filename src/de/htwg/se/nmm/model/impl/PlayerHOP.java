package de.htwg.se.nmm.model.impl;

import de.htwg.se.nmm.model.IJunction;
import de.htwg.se.nmm.model.IPlayer;
import de.htwg.se.nmm.model.IPlayerState;
import de.htwg.se.nmm.model.IPuck;

public class PlayerHOP implements IPlayerState {

    Player player;

    public PlayerHOP(Player player) {
        this.player = player;
    }

    @Override
    public void setPuck(IJunction j, IPuck puck, IPlayer cur) {
        throw new RuntimeException("No more Pucks to set.");
    }

    @Override
    public void pickPuck(IJunction j, IPlayer cur) {
        throw new RuntimeException("You don't have a valid mill, you can't pick yet.");
    }

    @Override
    public void movePuck(IJunction jFrom, IJunction jTo, IPlayer cur) {

        if(jFrom == null || jTo == null) {
            throw new RuntimeException("Illegal move, please check your coordinates.");
        }
        if(jTo.hasPuck()) {
            throw new RuntimeException("There already is a Puck.");
        }
        if(!jFrom.getPuck().getPlayer().equals(cur)) {
            throw new RuntimeException("That's not your puck unfortunately.");
        }
    }

    @Override
    public String toString() {
        return "HOP";
    }
}
