package com.cc3002.breakout.test;


import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import com.cc3002.breakout.logic.level.Score;

public class ScoreTest {
  Score testingLong;
  Score testing;

  @Before
  public void setUp() throws Exception {
    testingLong = new Score(100);
    testing = new Score();
  }
  
  @Test
  public void testScore() {
    assertNotSame(testing,testingLong);
    assertSame(testing,testing);
    assertSame(testingLong,testingLong);
  }
  
  @Test
  public void testGetPoints() {
    assertNotSame(testingLong.getPoints(),0L);
    assertNotSame(testing.getPoints(),100L);
    assertSame(testingLong.getPoints(),100L);
    assertSame(testing.getPoints(),0L);
  }
  
  @Test
  public void testScoreLong() {
    assertSame(testingLong.getPoints(),100L);
    assertNotSame(testing,testingLong);
  }


  @Test
  public void testAddLong() {
    testingLong.add(-100);
    assertSame(testingLong.getPoints(),0L);
    testingLong.add(100);
    assertSame(testingLong.getPoints(),100L);
  }


}
