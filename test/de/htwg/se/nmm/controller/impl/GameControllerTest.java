package de.htwg.se.nmm.controller.impl;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.se.nmm.GameModule;
import de.htwg.se.nmm.controller.IGameController;
import de.htwg.se.nmm.model.IJunction;
import de.htwg.se.nmm.model.IPlayer;
import de.htwg.se.nmm.model.impl.Board;
import de.htwg.se.nmm.model.impl.Junction;
import de.htwg.se.nmm.model.impl.Player;
import de.htwg.se.nmm.model.impl.Puck;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GameControllerTest extends TestCase {

    Map<String, IJunction> board;
    Junction j;
    GameController controller;
    IJunction mill1, mill2;

    @Before
    public void setUp() {
        controller = new GameController(new Board());
        controller.createPlayer("p1", "p2");
        this.board =  controller.getBoard().getBoardMap();
        mill1 = this.board.get("a1");
        mill2 = this.board.get("a4");
    }

    @Test
    public void testGetOtherPlayer() throws Exception {
        IPlayer p = controller.getCurrentIPlayer();
        IPlayer o = controller.getOtherPlayer();
        assertNotEquals(p, o);
    }
    @Test
    public void testCreatePlayer() throws Exception {
        controller.createPlayer("name1", "name2");
        assertTrue(controller.getCurrentIPlayer().getName().equals("name1"));
        assertTrue(controller.getOtherPlayer().getName().equals("name2"));
    }
    @Test
    public void testGetStatus() throws Exception {
        assertNotNull(controller.getStatus());
    }

    @Test
    public void testCheckformill() throws Exception {
        Puck p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("a1", p); // p1

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("a4", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("a7", p); // p1

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("b4", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("d7", p); // p1

        mill1 = this.board.get("a1"); // p2
        assertFalse(controller.checkformill(mill1, controller.getCurrentIPlayer())); // p2
        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("c4", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("g1", p); // p1
        mill2 = this.board.get("c4"); // p2
        assertTrue(controller.checkformill(mill2, controller.getCurrentIPlayer())); // p2
    }

    @Test
    public void testEmptyPucks() throws Exception {
        Puck p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("a1", p); // p1

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("a4", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("a7", p); // p1

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("b2", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("b4", p); // p1

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("b6", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("c3", p); // p1

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("c4", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("c5", p); // p1

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("e3", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("e4", p); // p1

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("e5", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("f2", p); // p1

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("f4", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("f6", p); // p1

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("g1", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("g4", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("g7", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("d1", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("d7", p); // p2

        IJunction check;

        check = this.board.get("g4");
        assertTrue(check.hasPuck());
        check = this.board.get("g7");
        assertTrue(check.hasPuck());

        check = this.board.get("d1");
        assertFalse(check.hasPuck());
        check = this.board.get("d7");
        assertFalse(check.hasPuck());
    }

    @Test
    public void testMovePucks() throws Exception {

        Puck p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.setPuck("g7", p); // p1

        p.setPlayer(controller.getOtherPlayer());
        controller.setPuck("g4", p); // p2

        controller.getCurrentIPlayer().setStatus(controller.getCurrentIPlayer().getMOVE()); // p1
        controller.movePuck("g7", "d7");

        IJunction check;
        check = this.board.get("g7");
        assertFalse(check.hasPuck());
        check = this.board.get("d7");
        assertTrue(check.hasPuck());
    }

    @Test
    public void TestInjector() {
        Injector injector = Guice.createInjector(new GameModule());

        IGameController controller = injector.getInstance(IGameController.class);
        controller.setInjector(injector);

        assertEquals(controller.getInjector(), injector);
    }


}