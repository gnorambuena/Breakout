package com.cc3002.breakout.test;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.Before;

import org.junit.Test;

import com.cc3002.breakout.logic.level.Player;

public class PlayerTest {

  Player pl1;
  
  @Before
  public void setUp() throws Exception {
    pl1 = new Player();
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
    assertSame(pl1.getScore(),0L);
    assertNotSame(pl1.getScore(),100L);
  }
  
  @Test
  public void testAddScore() {
    pl1.addScore(100);
    assertSame(pl1.getScore(),100L);
    pl1.addScore(-100);
    assertSame(pl1.getScore(),0L);
    assertNotSame(pl1.getScore(),10000L);
  }

}
