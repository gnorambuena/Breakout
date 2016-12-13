package com.cc3002.breakout.logic.level;


import com.cc3002.breakout.facade.Flyweight;
import com.cc3002.breakout.logic.brick.IBrick;

import java.util.List;
import java.util.Observer;


/**
 * Esta clase se encarga de manejar el cambio de niveles del juego.
 * @author gabriel
 *
 */
public class LevelHandler extends GameLevel {
  ILevel currentLevel;
  ILevel nextLevel;
  Flyweight flyweight;
  
  /**
   * Constructor del Objeto que maneja los niveles.
   * @param newFlyweight Flyweight que contiene las referencias.
   */
  public LevelHandler(Flyweight newFlyweight) {
    flyweight = newFlyweight;
    for (Observer observer: flyweight.getObservers()) {
      addObserver(observer);
    }
    currentLevel = new NullLevel();
    nextLevel = new NullLevel();
  }

  /**
   *  Setea el nivel actual.
   */
  @Override
  public void setCurrentLevel(final ILevel newCurrentLevel) {
    currentLevel = newCurrentLevel;
    if (currentLevel.isLevel()) {
      currentLevel.setFlyweight(flyweight);
      currentLevel.setRequiredPoints();
      setChanged();
      notifyObservers(currentLevel.getLevelName());
      clearChanged();
    }
  }
  
  public String getLevelName() {
    return currentLevel.getLevelName();
  }

  public List<IBrick> getBricks() {
    return currentLevel.getBricks();
  }

  public int getNumberOfBricks() {
    return currentLevel.getNumberOfBricks();
  }

  public int getRequiredPoints() {
    return currentLevel.getRequiredPoints();
  }

  public void setRequiredPoints() {
    currentLevel.setRequiredPoints();
  }
  
  public void setRequiredPoints(int newPoints) {
    currentLevel.setRequiredPoints(newPoints);
  }
  
  public void setNextLevel(ILevel newLevel) {
    nextLevel = newLevel;
  }
  
  public ILevel getCurrentLevel() {
    return currentLevel;
  }
  
  /** Función estática que transforma un Objeto ILevel,
   * en un String para mostrar por consola de texto.
   * 
   * @param rlevel El nivel que se quiere retornar como String.
   * @return El nivel construido como String.
   */
  public static String spawnBricks(final ILevel rlevel) {
    List<IBrick> level = rlevel.getBricks();
    StringBuilder strB = new StringBuilder();
    Printer printer = new Printer();
    for (int i = 0 ; i < level.size() ; i++) {
      if (i % 16 == 0 && i != 0) {
        strB.append(System.lineSeparator());
      }
      IBrick brick = level.get(i);
      strB.append(printer.print(brick));
    }
    strB.append(System.lineSeparator());
    return strB.toString();
  }

  public boolean hasNextLevel() {
    return nextLevel.isLevel();
  }
  
  /**
  * Metodo que setea el autoSwitch, y chequea si ya se cumplio la condicion.
  */
  public void autoSwitchToNextLevel() {
    if (flyweight.getAutoSwitch().isOpen()
        && currentLevel.getRequiredPoints() <= flyweight.getCurScore().getPoints()) {
      flyweight.getBonusHandler().autoSwitch();
      switchToNextLevel();
      flyweight.updateScore();
    }
  }
  

  /**
   * Metodo que realiza el autoSwitch.
   */
  public void switchToNextLevel() {
    setCurrentLevel(nextLevel);
    nextLevel = new NullLevel();
  }

  public boolean hasCurrentLevel() {
    return currentLevel.isLevel();
  }

  public void setFlyweight(Flyweight flyweight) {
    //filler comment
  }

  public boolean isLevel() {
    return true;
  }
}
