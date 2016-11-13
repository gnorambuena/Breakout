package com.cc3002.breakout.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.cc3002.breakout.logic.brick.SoftBrick;
import com.cc3002.breakout.logic.level.Printer;
import com.cc3002.breakout.logic.level.Score;

import org.junit.Before;
import org.junit.Test;

public class SoftBrickTest {
  private SoftBrick softBrick;
  private SoftBrick auxBrick;
  
  @Before
  public void setUp() throws Exception {
    softBrick = new SoftBrick(new Score(),null);
    auxBrick = new SoftBrick(new Score(),null);
  }

  @Test
  public void testIBrick() {
    assertSame(softBrick,softBrick);
    assertNotSame(auxBrick,softBrick);
    //fail("Not yet implemented");
  }

  @Test
  public void testIsSoft() {
    assertTrue(softBrick.isSoftBrick());
  }

  @Test
  public void testIsStone() {
    assertFalse(softBrick.isStoneBrick());
  }
  
  @Test(expected = NullPointerException.class)
  public void testRemainingHits() {
    softBrick.hit();
    assertSame(softBrick.remainingHits(),0);
    assertNotSame(softBrick.remainingHits(),1);
  }
  
  @Test(expected = NullPointerException.class)
  public void testPrint() {
    assertEquals(auxBrick.print(new Printer()),"*");
    auxBrick.hit();
    assertEquals(auxBrick.print(new Printer()),"*");
  }

}