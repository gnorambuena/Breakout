package com.cc3002.breakout.logic.level;

import com.cc3002.breakout.logic.bonus.BonusHandler;
import com.cc3002.breakout.logic.brick.IBrick;
import com.cc3002.breakout.logic.brick.SoftBrick;
import com.cc3002.breakout.logic.brick.StoneBrick;
import com.cc3002.breakout.logic.observer.GameObserver;
import com.cc3002.breakout.logic.observer.ScoreObserver;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Representacion de un nivel del juego,
 * se encarga además de generar niveles.
 * @author gabriel
 *
 */
public class RealLevel extends GameLevel {
  String name;
  List<IBrick> level;
  Score requiredPoints;
  Player player;
  List<GameObserver> observers;
  GameConsole gameConsole;
  BonusHandler bonusHandler;
  
  /**
   * Constructor del RealLevel.
   * No genera un nivel aleatorio.
   * @param levelName El nombre del nivel.
   * @param bricks Una Lista de IBrick.
   */
  
  public RealLevel(String levelName, List<IBrick> bricks,
      Player newPlayer, GameConsole newGameConsole) {
    name = levelName;
    level = bricks;
    requiredPoints = genScore();
    player = newPlayer;
    player.getScore().setRequiredPoints(requiredPoints.getPoints());
    observers = null;
    gameConsole = newGameConsole;
  }
  
  /**
   * Constructor del RealLevel
   * Genera al azar con probability de generar
   * un SoftBrick.
   * @param levelName El nombre del nivel.
   * @param num El numero de bricks.
   * @param probability Probabilidad de generar un SoftBrick.
   */
  
  public RealLevel(String levelName, int num ,double probability,
      Player newPlayer, GameConsole newGameConsole) {
    name = levelName;
    observers = new ArrayList<GameObserver>();
    gameConsole = newGameConsole;
    observers.add(new ScoreObserver(newGameConsole));
    player = newPlayer;
    level = genNewLevel(probability,num);
    requiredPoints = genScore();
    player.getScore().setRequiredPoints(requiredPoints.getPoints());
  }
  
  /**
   * Setea el bonusHandler para el level.
   * @param newBonusHandler el bonusHandler que se usara.
   */
  public void setBonusHandler(BonusHandler newBonusHandler) {
    bonusHandler = newBonusHandler;
    for (IBrick brick : level) {
      brick.setBonusHandler(bonusHandler);
    }
  }
  
  public void setObservers(final List<GameObserver> newObservers) {
    observers = newObservers;
    player.setObservers(newObservers);
  }
  
  public void setRequiredPoints() {
    player.getScore().setCurrentPoints(0);
    player.getScore().setRequiredPoints(getRequiredPoints());
  }
  
  public void setRequiredPoints(int newPoints) {
    player.getScore().setRequiredPoints(newPoints);
    requiredPoints.setCurrentPoints(newPoints);
  }
  
  /**
   * Metodo privado que se encarga de generar un level random.
   * @param probability Probabilidad de ocurrencia de un SoftBrick.
   * @param numberOfBricks Numero de Bricks del nivel.
   * @return La lista de IBricks con los bricks generados.
   */
  private List<IBrick> genNewLevel(double probability, int numberOfBricks) {
    requiredPoints = new Score();
    List<IBrick> newlevel = new ArrayList<IBrick>();
    Random rand = new Random();
    
    for (int i = 0 ; i < numberOfBricks ; i++) {
    
      float chance = rand.nextFloat();
      
      if (chance < probability) {
        requiredPoints.add(10);
        newlevel.add(new SoftBrick(player.getScore(),observers,bonusHandler));
      } else {
        requiredPoints.add(50);
        newlevel.add(new StoneBrick(player.getScore(),observers,bonusHandler));
      }
    }
    return newlevel;
  }
  
  private Score genScore() {
    int points = 0;
    for (int i = 0 ; i < level.size() ; i++) {
      if (level.get(i).isSoftBrick()) {
        points += 10; 
      } else {
        points += 50;
      }
    }
    return new Score(points);
  }

  public String getLevelName() {
    return name;
  }
  
  public List<IBrick> getBricks() {
    return level;
  }
  
  public int getNumberOfBricks() {
    return level.size();
  }
  
  public int getRequiredPoints() {
    return 7 * (int)requiredPoints.getPoints() / 10;
  }
  
  public List<GameObserver> getObservers() {
    return observers;
  }
  
  /**
   * RealLevel no ejecuta nada con este mensaje.
   * @param newLevel El level a setear.
   */
  @Override
  public void setNextLevel(ILevel newLevel) {
    //Filler comment.
  }

  /**
   * RealLevel no ejecuta nada con este mensaje.
   * @param newLevel El level a setear.
   */
  @Override
  public void setCurrentLevel(ILevel newLevel) {
    //Filler comment.
  }

  /**
   * RealLevel no ejecuta nada con este mensaje.
   * 
   */
  @Override
  public void autoSwitchToNextLevel() {
    //Filler comment. 
  }
  
  @Override
  public Player getPlayer() {
    return player;
  }

  @Override
  public ILevel getCurrentLevel() {
    //Filler comment.
    return null;
  }

  @Override
  public boolean hasNextLevel() {
    //Filler comment.
    return false;
  }

  @Override
  public boolean hasCurrentLevel() {
    //Filler comment.
    return false;
  }
  
}

