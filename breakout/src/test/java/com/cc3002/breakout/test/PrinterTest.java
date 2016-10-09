package com.cc3002.breakout.test;

import com.cc3002.breakout.logic.Printer;
import com.cc3002.breakout.logic.SoftBrick;
import com.cc3002.breakout.logic.StoneBrick;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PrinterTest {
  
  SoftBrick softbrick;
  StoneBrick stonebrick;
  Printer printer;
  
  @Before
  public void setUp() throws Exception {
    softbrick = new SoftBrick();
    stonebrick = new StoneBrick();
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
