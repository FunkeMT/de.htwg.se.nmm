package de.htwg.se.nmm.model.impl;

import de.htwg.se.nmm.util.NmmRuntimeException;
import de.htwg.se.nmm.model.IJunction;
import de.htwg.se.nmm.model.IPlayer;
import de.htwg.se.nmm.model.IPlayerState;
import de.htwg.se.nmm.model.IPuck;

public class Player implements de.htwg.se.nmm.model.IPlayer {

    private static final int NUM_PUCKS = 9;

    private IPlayerState SET;
    private IPlayerState MOVE;
    private IPlayerState PICK;
    private IPlayerState HOP;

    private IPlayerState currentState;
    boolean gameLost;

    private final Man man;
    private final String name;
    private int numPucks;
    private int numPucksTakenAway;

    public Player(String name, Man man) {
        this.name = name;
        this.man = man;
        this.numPucks = NUM_PUCKS;
        this.numPucksTakenAway = 0;

        this.SET = new PlayerSET(this);
        this.MOVE = new PlayerMOVE(this);
        this.PICK = new PlayerPICK(this);
        this.HOP = new PlayerHOP(this);

        this.currentState = this.SET;
        this.gameLost = false;
    }

    @Override
    public int getNumPucks() {
        return this.numPucks;
    }

    @Override
    public int getPucksTakenAway() {
        return numPucksTakenAway;
    }

    @Override
    public void decrementPucks() {
        if (this.numPucks == 0) {
            throw new NmmRuntimeException("No Pucks left");
        }
        this.numPucks--;
    }

    @Override
    public void incrementPucksTakenAway() {
        this.numPucksTakenAway++;
    }

    @Override
    public boolean hasPucks() {
        return this.numPucks > 0;
    }

    @Override
    public IPlayerState getStatus() {
        return this.currentState;
    }

    @Override
    public boolean isStatus(IPlayerState status) {
        return this.currentState.equals(status);
    }

    @Override
    public void setStatus(IPlayerState status) {
        this.currentState = status;
    }

    @Override
    public boolean hasLost() {
        return gameLost;
    }

    @Override
    public Man getMan() {
        return this.man;
    }

    @Override
    public String getName() {
        return this.name;
    }

    /* State behaviour */
    @Override
    public void setPuck(IJunction j, IPuck puck, IPlayer cur) {
        currentState.setPuck(j, puck, cur);
    }

    @Override
    public void pickPuck(IJunction j, IPlayer cur) {
        currentState.pickPuck(j, cur);
    }

    @Override
    public void movePuck(IJunction jFrom, IJunction jTo, IPlayer currentPlayer) {
        currentState.movePuck(jFrom, jTo, currentPlayer);
    }

    @Override
    public IPlayerState getHOP() {
        return HOP;
    }

    @Override
    public IPlayerState getSET() {
        return SET;
    }

    @Override
    public IPlayerState getMOVE() {
        return MOVE;
    }

    @Override
    public IPlayerState getPICK() {
        return PICK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;
        return getMan() == player.getMan();
    }

    @Override
    public int hashCode() {
        return getMan().hashCode();
    }
}
