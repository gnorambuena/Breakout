package com.cc3002.breakout.logic.level;

import java.util.List;

import com.cc3002.breakout.logic.brick.IBrick;
import com.cc3002.breakout.logic.bonus.IBonus;;
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

  void setBonuses(final List<IBonus> LBonus);
}