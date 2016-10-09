package com.cc3002.breakout.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.cc3002.breakout.logic.Printer;
import com.cc3002.breakout.logic.SoftBrick;


import org.junit.Before;
import org.junit.Test;


public class SoftBrickTest {
  private SoftBrick softBrick;
  private SoftBrick auxBrick;
  
  @Before
  public void setUp() throws Exception {
    softBrick = new SoftBrick();
    auxBrick = new SoftBrick();
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
  
  @Test
  public void testRemainingHits() {
    softBrick.hit();
    assertSame(softBrick.remainingHits(),0);
    assertNotSame(softBrick.remainingHits(),1);
  }
  
  @Test
  public void testPrint(){
    assertEquals(auxBrick.print(new Printer()),"*");
    auxBrick.hit();
    assertEquals(auxBrick.print(new Printer()),"*");
  }
  @Test
  public void testGetPoints(){
    auxBrick = new SoftBrick();
    assertSame(auxBrick.getPoints(),0);
    assertNotSame(auxBrick.getPoints(),10);
    auxBrick.hit();
    assertSame(auxBrick.getPoints(),10);
    assertNotSame(auxBrick.getPoints(),0);
  }
}