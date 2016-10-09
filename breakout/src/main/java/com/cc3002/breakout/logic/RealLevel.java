package com.cc3002.breakout.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
  /**
   * Constructor del RealLevel.
   * No genera un nivel aleatorio.
   * @param levelName El nombre del nivel.
   * @param bricks Una Lista de IBrick.
   */
  
  public RealLevel(String levelName, List<IBrick> bricks) {
    name = levelName;
    level = bricks;
    requiredPoints = genScore();
  }
  /**
   * Constructor del RealLevel
   * Genera al azar con probability de generar
   * un SoftBrick.
   * @param levelName El nombre del nivel.
   * @param num El numero de bricks.
   * @param probability Probabilidad de generar un SoftBrick.
   */
  
  public RealLevel(String levelName, int num ,double probability) {
    name = levelName;
    requiredPoints = new Score();
    level = genNewLevel(probability,num);
  }
  
  private List<IBrick> genNewLevel(double probability, int numberOfBricks) {
    List<IBrick> level = new ArrayList<IBrick>();
    Random rand = new Random();
    
    for (int i = 0 ; i < numberOfBricks ; i++) {
    
      float chance = rand.nextFloat();
      
      if (chance < probability) {
        requiredPoints.add(10);
        level.add(new SoftBrick());
      } else {
        requiredPoints.add(50);
        level.add(new StoneBrick());
      }
    }
    return level;
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
  
  /**
   * Retoerna el Score ganado en ese nivel.
   */
  public Score getEarnedScore() {
    Score sc = new Score();
    for (int i = 0 ; i < level.size() ; i++) {
      sc.add(level.get(i).getPoints());
    }
    return sc;
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
  
}

