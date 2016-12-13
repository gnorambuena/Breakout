package com.cc3002.breakout.logic.level;

import com.cc3002.breakout.facade.Flyweight;
import com.cc3002.breakout.logic.brick.IBrick;
import com.cc3002.breakout.logic.brick.MetalBrick;
import com.cc3002.breakout.logic.brick.PoisonBrick;
import com.cc3002.breakout.logic.brick.SoftBrick;
import com.cc3002.breakout.logic.brick.StoneBrick;


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
  int requiredPoints;
  Flyweight flyweight;
  
  /**
   * Constructor del RealLevel.
   * No genera un nivel aleatorio.
   * @param levelName El nombre del nivel.
   * @param bricks Una Lista de IBrick.
   */
  
  public RealLevel(String levelName, List<IBrick> bricks, Flyweight newFlyweight) {
    name = levelName;
    level = bricks;
    requiredPoints = genRequiredPoints();
    flyweight = newFlyweight;
  }
  
  /**
   * Constructor que se encarga de generar un level random.
   * @param levelName Nombre del nivel.
   * @param probability Probabilidad de ocurrencia de un SoftBrick.
   * @param number Numero de Bricks del nivel.
   */
  public RealLevel(String levelName, int number, double probability,Flyweight newFlyweight) {
    name = levelName;
    flyweight = newFlyweight;
    level = genNewLevel(probability,number);
    requiredPoints = genRequiredPoints();
  }


  /**
   * Metodo privado que se encarga de generar un level random.
   * @param probability Probabilidad de ocurrencia de un SoftBrick.
   * @param numberOfBricks Numero de Bricks del nivel.
   * @return La lista de IBricks con los bricks generados.
   */
  private List<IBrick> genNewLevel(double probability, int numberOfBricks) {
    requiredPoints = 0;
    List<IBrick> newlevel = new ArrayList<IBrick>();
    Random rand = new Random();
    newlevel.add(new PoisonBrick(flyweight));
    for (int i = 1 ; i < numberOfBricks ; i++) {
    
      float chance = rand.nextFloat();
      
      if (chance < probability) {
        requiredPoints += 10;
        newlevel.add(new SoftBrick(flyweight));
      } else {
        requiredPoints += 50;
        if (rand.nextFloat() > 0.95) {
          newlevel.add(new MetalBrick(flyweight));
        } else {
          newlevel.add(new StoneBrick(flyweight));
        }
      }
    }
    return newlevel;
  }
  
  private int genRequiredPoints() {
    int points = 0;
    for (int i = 0 ; i < level.size() ; i++) {
      if (level.get(i).isSoftBrick()) {
        points += 10; 
      } else if (level.get(i).isStoneBrick()) {
        points += 50;
      }
    }
    return points;
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
    return 7 * (int)requiredPoints / 10;
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

  public void setFlyweight(Flyweight newFlyweight) {
    flyweight = newFlyweight;
  }


  public void setRequiredPoints(int newPoints) {
    requiredPoints = newPoints;
  }


  @Override
  public void setRequiredPoints() {
    genRequiredPoints();
  }
  
  public boolean isLevel() {
    return true;
  }
}

