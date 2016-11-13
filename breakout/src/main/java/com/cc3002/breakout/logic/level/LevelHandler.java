package com.cc3002.breakout.logic.level;

import com.cc3002.breakout.logic.brick.IBrick;
import com.cc3002.breakout.logic.observer.GameObserver;

import java.util.List;

/**
 * Esta clase se encarga de manejar el cambio de niveles del juego.
 * @author gabriel
 *
 */
public class LevelHandler extends GameLevel {
  ILevel currentLevel;
  ILevel nextLevel;
  Score totalScore;
  List<GameObserver> observers;
  
  /**
   * Constructor del Objeto que maneja los niveles.
   * @param newLevel Nivel que debe manejar como currentLevel.
   */
  public LevelHandler(ILevel newLevel) {
    currentLevel = newLevel;
    observers = null;
    nextLevel = null;
    totalScore = new Score();
  }
  
  /**
   *  Setea el nivel actual.
   */
  @Override
  public void setCurrentLevel(final ILevel newCurrentLevel) {
    currentLevel = newCurrentLevel;
    if (currentLevel != null) {
      currentLevel.setObservers(observers);
      Player player = getPlayer();
      totalScore.add(player.getTotalPoints());
      currentLevel.setRequiredPoints();
      for (GameObserver observer: currentLevel.getObservers()) {
        observer.levelUpdate(currentLevel.getLevelName());
        observer.setLevelHandler(this);
      }
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

  /**
   * Setea los Observers para el nivel actual.
   * 
   */
  public void setObservers(List<GameObserver> newObservers) {
    observers = newObservers;
    if (currentLevel != null) {
      currentLevel.setObservers(newObservers);
    }
  }

  public List<GameObserver> getObservers() {
    return currentLevel.getObservers();
  }

  public void setRequiredPoints() {
    currentLevel.setRequiredPoints();
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
    return nextLevel != null;
  }

  public void autoSwitchToNextLevel() {
    setCurrentLevel(nextLevel);
    nextLevel = null;
  }

  public boolean hasCurrentLevel() {
    return currentLevel != null;
  }

  @Override
  public Player getPlayer() {
    return currentLevel.getPlayer();
  }
}
