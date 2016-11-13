package com.cc3002.breakout.test;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import com.cc3002.breakout.logic.level.Player;
import com.cc3002.breakout.logic.observer.GameObserver;
import com.cc3002.breakout.logic.observer.NullObserver;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class PlayerTest {

  Player pl1;
  List<GameObserver> observers;
  
  /**Setting up para el test del Player.
   * @throws Exception tira una exception al fallar al crear un nuevo Player.
   */
  @Before
  public void setUp() throws Exception {
    pl1 = new Player();
    observers = new ArrayList<GameObserver>();
    observers.add(new NullObserver());
    pl1.setObservers(observers);
  }

  @Test
  public void testPlayer() {
    assertSame(pl1,pl1);
    assertNotSame(pl1,new Player());
  }

  @Test
  public void testGetNumberOfHearts() {
    assertSame(pl1.getNumberOfHearts(),3);
  }

  @Test
  public void testLossOfHeart() {
    pl1.lossOfHeart();
    assertSame(pl1.getNumberOfHearts(),2);
    pl1.lossOfHeart();
    assertSame(pl1.getNumberOfHearts(),1);
    pl1.lossOfHeart();
    assertSame(pl1.getNumberOfHearts(),0);
  }
  
  @Test
  public void testGetScore() {
    assertSame(pl1.getScore().getPoints(),0L);
    assertNotSame(pl1.getScore(),100L);
  }
  
  @Test
  public void testAddScore() {
    pl1.addScore(100);
    assertSame(pl1.getScore().getPoints(),100L);
    pl1.addScore(-100);
    assertSame(pl1.getScore().getPoints(),0L);
    assertNotSame(pl1.getScore().getPoints(),10000L);
  }

}
