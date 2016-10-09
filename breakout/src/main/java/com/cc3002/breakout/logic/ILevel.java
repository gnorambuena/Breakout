package com.cc3002.breakout.logic;

import java.util.List;
/**
 * Interface de la clase RealLevel.
 * @author gabriel
 *
 */

public interface ILevel {

  String getLevelName();

  List<IBrick> getBricks();

  int getNumberOfBricks();

  int getRequiredPoints();
  
  public Score getEarnedScore();
}