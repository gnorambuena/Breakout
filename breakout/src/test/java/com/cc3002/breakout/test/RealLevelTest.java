package com.cc3002.breakout.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cc3002.breakout.logic.brick.IBrick;
import com.cc3002.breakout.logic.brick.SoftBrick;
import com.cc3002.breakout.logic.brick.StoneBrick;
import com.cc3002.breakout.logic.level.GameConsole;
import com.cc3002.breakout.logic.level.ILevel;
import com.cc3002.breakout.logic.level.Player;
import com.cc3002.breakout.logic.level.RealLevel;

public class RealLevelTest {
  ILevel lv1;
  ILevel lv2;
  List<IBrick> aux;
  long points;
  Player pl;
  GameConsole gameConsole;
  
  @Before
  public void setUp() throws Exception {
    pl = new Player();
    gameConsole = new GameConsole();
    gameConsole.setStream(System.out);
    lv1 = new RealLevel("Level one",16,0.5f,pl,gameConsole);
    aux = new ArrayList<IBrick>();

    points = 0;
    for (int i = 0 ; i < lv1.getBricks().size() ; i++) {
      if (lv1.getBricks().get(i).isSoftBrick()) {
        points += 10; 
      } else {
        points += 50;
      }
    }
    for (int i = 0 ; i < 16 ; i++) {
      aux.add(new SoftBrick(pl.getScore(),lv1.getObservers()));
      aux.add(new StoneBrick(pl.getScore(),lv1.getObservers()));
    }
    lv2 = new RealLevel("Level two",aux,pl,gameConsole);
    lv2.setObservers(lv1.getObservers());
  }

  @Test
  public void testILevelStringListOfIBrick() {
    assertSame(lv2,lv2);
    assertNotSame(lv1,lv2);
    assertNotSame(lv2,new RealLevel("Testing Level",24,0.3f,pl,gameConsole));
  }

  @Test
  public void testILevelStringIntDouble() {
    assertSame(lv1,lv1);
    assertNotSame(lv1,lv2);
    assertNotSame(lv1,new RealLevel("Testing Level",24,0.3f,pl,gameConsole));
  }

  @Test
  public void testGetLevelName() {
    assertEquals(lv1.getLevelName(),"Level one");
    assertEquals(lv2.getLevelName(),"Level two");
    assertNotEquals(lv1.getLevelName(),"Testing string");
    assertNotEquals(lv2.getLevelName(),"Testing string");
    assertNotEquals(lv1.getLevelName(),lv2.getLevelName());
  }

  @Test
  public void testGetBricks() {
    assertSame(lv2.getBricks(),aux);
    assertNotSame(lv1.getBricks(),aux);
    assertNotSame(lv1.getBricks(),lv2.getBricks());
  }

  @Test
  public void testGetNumberOfBricks() {
    assertSame(lv1.getNumberOfBricks(),16);
    assertSame(lv2.getNumberOfBricks(),32);
    assertSame(2 * lv1.getNumberOfBricks(),lv2.getNumberOfBricks());
    assertNotSame(lv1.getNumberOfBricks(),5);
    assertNotSame(lv2.getNumberOfBricks(),5);
  }

  @Test
  public void testGetRequiredPoints() {
    assertEquals(lv2.getRequiredPoints() , 14 * 48 );
    assertNotEquals(lv2.getRequiredPoints(), 20);
    assertEquals(lv1.getRequiredPoints() , (7 * points / 10));
    assertNotEquals(lv2.getRequiredPoints() , -10);
    assertNotEquals(lv2.getRequiredPoints(),-10);
    assertNotEquals(lv1.getRequiredPoints(),-10);
  }

  /*@Test
  public void testSpawnBricks() {
    assertEquals(RealLevel.spawnBricks(lv2),"*#*#*#*#*#*#*#*#" + System.lineSeparator()
        + "*#*#*#*#*#*#*#*#" + System.lineSeparator());
  }*/
  
}
