package com.cc3002.breakout.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import com.cc3002.breakout.logic.brick.SoftBrick;
import com.cc3002.breakout.logic.brick.StoneBrick;
import com.cc3002.breakout.logic.level.Printer;
import com.cc3002.breakout.logic.level.Score;

public class PrinterTest {
  
  SoftBrick softbrick;
  StoneBrick stonebrick;
  Printer printer;
  
  @Before
  public void setUp() throws Exception {
    softbrick = new SoftBrick(new Score(),null);
    stonebrick = new StoneBrick(new Score(),null);
    printer = new Printer();
  }

  @Test
  public void testPrint() {
    assertEquals(printer.print(softbrick),"*");
    assertEquals(printer.print(stonebrick),"#");
  }

  @Test
  public void testPrintSoftBrick() {
    assertEquals(printer.printSoftBrick(),"*");
    assertNotEquals(printer.printSoftBrick(),"#");
  }

  @Test
  public void testPrintStoneBrick() {
    assertEquals(printer.printStoneBrick(),"#");
    assertNotEquals(printer.printStoneBrick(),"*");
  }

}
