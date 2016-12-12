package com.cc3002.breakout.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.cc3002.breakout.facade.HomeworkTwoFacade;
import com.cc3002.breakout.logic.level.ILevel;

import org.junit.Before;
import org.junit.Test;


public class HomeworkTwoFacadeTest {
  HomeworkTwoFacade game1;
  HomeworkTwoFacade game2;
  HomeworkTwoFacade aux;
  
  /**
   * Setting up del test para el Facade del juego.
   * @throws Exception Tira una exception si no se puede crear la clase Facade.
   */
  @Before
  public void setUp() throws Exception {
    game1 = new HomeworkTwoFacade();
    game2 = new HomeworkTwoFacade();
    game2.setCurrentLevel(game2.newLevelWithSoftAndStoneBricks("Lvl two",32,0.5));
    aux = new HomeworkTwoFacade();
    aux.setCurrentLevel(aux.newLevelWithSoftAndStoneBricks("Lvl three",32,0.5));
  }
  
  @Test
  public void testHomeworkOneFacade() {
    assertNotEquals(aux,game1);
    assertNotEquals(game1,game2);
  }
  
  @Test
  public void testHomeworkOneFacadeStringIntDouble() {
    assertNotEquals(aux,game1);
    assertNotEquals(aux,game2);
  }
  
  @Test
  public void testEarnedScore() {
    game1.setCurrentLevel(game1.newLevelWithSoftAndStoneBricks("Lvl one", 32 , 0.5));
    assertEquals(game1.earnedScore(),0);
    assertEquals(game2.earnedScore(),0);
  }
  
  @Test
  public void testGetBricks() {
    assertNotEquals(aux.getBricks(),game2.getBricks());
  }
  
  @Test
  public void testGetCurrentLevel() {
    ILevel lvl = game2.getCurrentLevel();
    assertEquals(lvl,game2.getCurrentLevel());
    game1.setCurrentLevel(lvl);
    assertEquals(game1.getCurrentLevel(),game2.getCurrentLevel());
  }
  
  @Test
  public void testGetLevelName() {
    game1.setCurrentLevel(game1.newLevelWithSoftAndStoneBricks("Lvl one", 32 , 0.5));
    assertEquals(game1.getLevelName(),"Lvl one");
    assertEquals(game2.getLevelName(),"Lvl two");
  }
  
  @Test
  public void testGetNumberOfHearts() {
    assertEquals(game2.getNumberOfHearts(),3);
  }
  
  @Test
  public void testGetRequiredPoints() {
    assertNotEquals(aux.getRequiredPoints(),game2.getRequiredPoints());
  }
  
  @Test
  public void testHasNextLevel() {
    assertTrue(game2.hasNextLevel());
  }
  
  @Test
  public void testLossOfHeart() {
    assertSame(game2.lossOfHeart(), 2);
    assertSame(game2.lossOfHeart(), 1);
    assertSame(game2.lossOfHeart(), 0);
  }
  
  @Test
  public void testNewLevelWithSoftAndStoneBricks() {
    assertNotEquals(aux, aux.newLevelWithSoftAndStoneBricks("Testing", 40, 0.8));
  }
  
  @Test
  public void testNumberOfBricks() {
    assertEquals(game2.numberOfBricks(),32);
    assertEquals(aux.numberOfBricks(),32);
    game2.setCurrentLevel(game2.newLevelWithSoftAndStoneBricks("Testing", 64, 0.6));
    assertNotSame(game2.numberOfBricks(),aux.numberOfBricks());
  }
  
  @Test
  public void testSetCurrentLevel() {
    game1.setCurrentLevel(game1.newLevelWithSoftAndStoneBricks("Lvl one", 32, 0.9));
    ILevel lvl = game1.getCurrentLevel();
    assertEquals(game1.getCurrentLevel(),lvl);
    game1.setCurrentLevel(game1.newLevelWithSoftAndStoneBricks("Lvl one", 32, 0.9));
    assertNotEquals(game1.getCurrentLevel(),lvl);
  }
  
  @Test
  public void testSpawnBricks() {
    assertNotEquals(game2.spawnBricks(game2.getCurrentLevel()),
        aux.spawnBricks(aux.getCurrentLevel()));
  }
  
  @Test 
  public void testNewBonuses() {
    boolean flag = true;
    try{
      game1.newBonuses(32, 0.5);
    } catch(Exception e) {
      flag = false;
    }
    assertTrue(flag);
  }
  
  @Test 
  public void testGetGameConsole() {
    boolean flag = true;
    try{
      game1.getGameConsole();
    } catch(Exception e) {
      flag = false;
    }
    assertTrue(flag);
  }
  
  @Test 
  public void testGetGameObservers() {
    boolean flag = true;
    try{
      game1.getGameObservers();
    } catch(Exception e) {
      flag = false;
    }
    assertTrue(flag);
  }
  
  @Test 
  public void testCurrentLevel() {
    assertFalse(game1.hasCurrentLevel());
    ILevel aux = game1.newLevelWithSoftAndStoneBricks("Testing Level", 32, 0.8);
    game1.setCurrentLevel(aux);
    assertTrue(game1.hasCurrentLevel());
    game1.setNextLevel();
  }
}
