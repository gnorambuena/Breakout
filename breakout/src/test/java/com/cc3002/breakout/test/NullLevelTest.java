package com.cc3002.breakout.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import com.cc3002.breakout.facade.Flyweight;
import com.cc3002.breakout.logic.level.ILevel;
import com.cc3002.breakout.logic.level.NullLevel;

public class NullLevelTest {

  NullLevel level;
  @Before
  public void setUp() throws Exception {
    level = new NullLevel();
  }

  @Test
  public void testGetLevelName() {
    assertEquals(level.getLevelName(),null);
  }

  @Test
  public void testGetBricks() {
    assertEquals(level.getBricks(),null);
  }

  @Test
  public void testGetNumberOfBricks() {
    assertSame(level.getNumberOfBricks(),0);
  }

  @Test
  public void testGetRequiredPoints() {
    assertSame(level.getRequiredPoints(),0);
  }

  @Test
  public void testSetRequiredPoints() {
    level.setRequiredPoints();
    level.setRequiredPoints(10);
    assertSame(level.getRequiredPoints(),0);
  }

  @Test
  public void testSetNextLevel() {
    level.setNextLevel(new NullLevel());
  }

  @Test
  public void testGetCurrentLevel() {
    assertEquals(level.getCurrentLevel(),null);
  }

  @Test
  public void testHasNextLevel() {
    assertFalse(level.hasNextLevel());
  }

  @Test
  public void testSetCurrentLevel() {
    level.setCurrentLevel(new NullLevel());
    assertEquals(level.getCurrentLevel(),null);
  }

  @Test
  public void testAutoSwitchToNextLevel() {
    level.autoSwitchToNextLevel();
  }

  @Test
  public void testHasCurrentLevel() {
    assertFalse(level.hasCurrentLevel());
  }

  @Test
  public void testSetFlyweight() {
    level.setFlyweight(new Flyweight());
  }

  @Test
  public void isLevel() {
    assertFalse(level.isLevel());
  }


}
