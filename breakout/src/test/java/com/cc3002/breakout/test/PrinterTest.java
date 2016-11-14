package com.cc3002.breakout.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.cc3002.breakout.logic.bonus.BonusHandler;
import com.cc3002.breakout.logic.brick.SoftBrick;
import com.cc3002.breakout.logic.brick.StoneBrick;
import com.cc3002.breakout.logic.level.Printer;
import com.cc3002.breakout.logic.level.Score;

import org.junit.Before;
import org.junit.Test;

public class PrinterTest {
  
  SoftBrick softbrick;
  StoneBrick stonebrick;
  Printer printer;
  
  /**
   * Setting up para el test del Printer.
   * @throws Exception tira una exception al fallar la creacion del Printer.
   */
  @Before
  public void setUp() throws Exception {
    BonusHandler bonusHandler = new BonusHandler();
    softbrick = new SoftBrick(new Score(),null,bonusHandler);
    stonebrick = new StoneBrick(new Score(),null,bonusHandler);
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
