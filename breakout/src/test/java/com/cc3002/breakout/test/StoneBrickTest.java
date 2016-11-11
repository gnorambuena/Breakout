package com.cc3002.breakout.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.cc3002.breakout.logic.brick.StoneBrick;
import com.cc3002.breakout.logic.level.Printer;
import com.cc3002.breakout.logic.level.Score;



public class StoneBrickTest {

  private StoneBrick stoneBrick;
  private StoneBrick auxBrick;

  @Before
  public void setUp() throws Exception {
    stoneBrick = new StoneBrick(new Score(),null);
    auxBrick = new StoneBrick(new Score(),null);
  }

  @Test
  public void testIBrick() {
    assertSame(stoneBrick,stoneBrick);
    assertNotSame(auxBrick,stoneBrick);
    //fail("Not yet implemented");
  }

  @Test
  public void testIsSoft() {
    assertFalse(stoneBrick.isSoftBrick());
  }

  @Test
  public void testIsStone() {
    assertTrue(stoneBrick.isStoneBrick());
  }
  @Test(expected = NullPointerException.class)
  public void testRemainingHits() {
    stoneBrick.hit();
    assertSame(stoneBrick.remainingHits(),2);
    assertNotSame(stoneBrick.remainingHits(),1);
    stoneBrick.hit();
    stoneBrick.hit();
    assertSame(stoneBrick.remainingHits(),0);
  }
  
  @Test(expected = NullPointerException.class)
  public void testToString(){
    assertEquals(auxBrick.print(new Printer()),"#");
    auxBrick.hit();
    assertEquals(auxBrick.print(new Printer()),"#");
    auxBrick.hit();
    auxBrick.hit();
    assertEquals(auxBrick.print(new Printer()),"#");
  }
}
