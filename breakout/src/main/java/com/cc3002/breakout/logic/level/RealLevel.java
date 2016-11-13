package com.cc3002.breakout.logic.level;

import com.cc3002.breakout.logic.bonus.IBonus;
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
  List<IBonus> bonuses;
  List<GameObserver> observers;
  ILevel nextLvl;
  
  /**
   * Constructor del RealLevel.
   * No genera un nivel aleatorio.
   * @param levelName El nombre del nivel.
   * @param bricks Una Lista de IBrick.
   */
  
  public RealLevel(String levelName, List<IBrick> bricks, Player newPlayer) {
    name = levelName;
    level = bricks;
    requiredPoints = genScore();
    player = newPlayer;
    observers = null;
    nextLvl = null;
  }
  /**
   * Constructor del RealLevel
   * Genera al azar con probability de generar
   * un SoftBrick.
   * @param levelName El nombre del nivel.
   * @param num El numero de bricks.
   * @param probability Probabilidad de generar un SoftBrick.
   */
  
  public RealLevel(String levelName, int num ,double probability, Player newPlayer) {
    name = levelName;
    requiredPoints = new Score();
    observers = new ArrayList<GameObserver>();
    observers.add(new ScoreObserver(System.out));
    player = newPlayer;
    level = genNewLevel(probability,num);
    nextLvl = null;
  }
  
  public void setObservers(final List<GameObserver> newObservers) {
    observers = newObservers;
  }
  
  private List<IBrick> genNewLevel(double probability, int numberOfBricks) {
    List<IBrick> newlevel = new ArrayList<IBrick>();
    Random rand = new Random();
    
    for (int i = 0 ; i < numberOfBricks ; i++) {
    
      float chance = rand.nextFloat();
      
      if (chance < probability) {
        requiredPoints.add(10);
        newlevel.add(new SoftBrick(player.getScore(),observers));
      } else {
        requiredPoints.add(50);
        newlevel.add(new StoneBrick(player.getScore(),observers));
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
  
  public void setBonuses(final List<IBonus> newBonus) {
    bonuses = newBonus;
  }
  
  public List<GameObserver> getObservers() {
    return observers;
  }
  
  public void setRequiredPoints(int newRequiredPoints) {
    requiredPoints = new Score(newRequiredPoints);
  }
  
}

