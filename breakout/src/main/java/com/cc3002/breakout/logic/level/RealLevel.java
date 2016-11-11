package com.cc3002.breakout.logic.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cc3002.breakout.logic.bonus.IBonus;
import com.cc3002.breakout.logic.brick.IBrick;
import com.cc3002.breakout.logic.brick.SoftBrick;
import com.cc3002.breakout.logic.brick.StoneBrick;
import com.cc3002.breakout.logic.observer.GameObserver;
import com.cc3002.breakout.logic.observer.ScoreObserver;
/**
 * Representacion de un nivel del juego,
 * se encarga además de generar niveles.
 * @author gabriel
 *
 */

public class RealLevel implements ILevel {
  String name;
  List<IBrick> level;
  Score requiredPoints;
  Player player;
  List<IBonus>bonuses;
  List<GameObserver>Observers;
  
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
    Observers = null;
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
    Observers = new ArrayList<GameObserver>();
    Observers.add(new ScoreObserver(System.out));
    player = newPlayer;
    level = genNewLevel(probability,num);
  }
  
  public void setObservers(final List<GameObserver>newObservers){
    Observers = newObservers;
  }
  private List<IBrick> genNewLevel(double probability, int numberOfBricks) {
    List<IBrick> newlevel = new ArrayList<IBrick>();
    Random rand = new Random();
    
    for (int i = 0 ; i < numberOfBricks ; i++) {
    
      float chance = rand.nextFloat();
      
      if (chance < probability) {
        requiredPoints.add(10);
        newlevel.add(new SoftBrick(player.getScore(),Observers));
      } else {
        requiredPoints.add(50);
        newlevel.add(new StoneBrick(player.getScore(),Observers));
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
  public void setBonuses(final List<IBonus> LBonus) {
     bonuses = LBonus;
  }
  public List<GameObserver> getObservers() {
    return Observers;
  }
  
}

