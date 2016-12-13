package com.cc3002.breakout.facade;

import com.cc3002.breakout.logic.bonus.AddLifeModifier;
import com.cc3002.breakout.logic.bonus.AddScoreModifier;
import com.cc3002.breakout.logic.bonus.BonusHandler;
import com.cc3002.breakout.logic.bonus.IBonus;
import com.cc3002.breakout.logic.bonus.LossLifeModifier;
import com.cc3002.breakout.logic.bonus.LossScoreModifier;
import com.cc3002.breakout.logic.brick.IBrick;
import com.cc3002.breakout.logic.level.GameConsole;
import com.cc3002.breakout.logic.level.ILevel;
import com.cc3002.breakout.logic.level.LevelHandler;
import com.cc3002.breakout.logic.level.Player;
import com.cc3002.breakout.logic.level.RealLevel;
import com.cc3002.breakout.logic.level.Score;
import com.cc3002.breakout.logic.observer.GameObserver;
import com.cc3002.breakout.logic.observer.LevelObserver;
import com.cc3002.breakout.logic.state.AutomaticSwitch;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Flyweight design pattern, contiene referencias a los principales objetos del juego.
 * @author gabriel
 *
 */
public class Flyweight {
  
  BonusHandler bonusHandler;
  LevelHandler levelHandler;
  Player player;
  Score curScore;
  List<Observer> observers;
  GameConsole gameConsole;
  AutomaticSwitch autoSwitch;
  
  /**
   * flyweight design pattern.
   */
  public Flyweight() {
    genGameConsoleAndObservers();
    genPlayerAndScore();
    genHandlers();
    autoSwitch = new AutomaticSwitch();
  }
  
  private void genHandlers() {
    bonusHandler = new BonusHandler(this);
    levelHandler = new LevelHandler(this);
  }
  
  private void genPlayerAndScore() {
    player = new Player(this);
    curScore = new Score(this);
  }
  
  private void genGameConsoleAndObservers() {
    gameConsole = new GameConsole();
    observers = new ArrayList<Observer>();
    observers.add(new GameObserver(this));
    observers.add(new LevelObserver(this));
  }
  
  public void addObserver(Observer aobserver) {
    observers.add(aobserver);
  }
  
  public List<Observer> getObservers() {
    return observers;
  }

  public Player getPlayer() {
    return player;
  }

  public GameConsole getGameConsole() {
    return gameConsole;
  }

  public long getEarnedScore() {
    return player.getTotalPoints() + curScore.getPoints();
  }

  public List<IBrick> getBricks() {
    return levelHandler.getBricks();
  }
  
  public ILevel getCurrentLevel() {
    return levelHandler.getCurrentLevel();
  }

  public String getLevelName() {
    return levelHandler.getLevelName();
  }
  
  public int getNumberOfHearts() {
    return player.getNumberOfHearts();
  }
  
  /**
   * Función que se llama para saber el numero requerido de puntos.
   * @return Los puntos necesarios para pasar de nivel.
   */
  public int getRequiredPoints() {
    if (levelHandler.getCurrentLevel() == null) {
      return 1000000000;
    }
    return (int)levelHandler.getRequiredPoints();
  }
  
  public BonusHandler getBonusHandler() {
    return bonusHandler;
  }
  
  public boolean hasNextLevel() {
    return levelHandler.hasNextLevel();
  }

  public int lossOfHeart() {
    player.lossOfHeart();
    return player.getNumberOfHearts();
  }

  public ILevel newLevelWithSoftAndStoneBricks(String levelName, int number, double probability) {
    return new RealLevel(levelName, number, probability,this);
  }
  
  public long getNumberOfBricks() {
    return levelHandler.getNumberOfBricks();
  }
  
  public void setCurrentLevel(final ILevel newLevel) {
    levelHandler.setCurrentLevel(newLevel);
  }
  
  public void setNextLevel() {
    levelHandler.setNextLevel(
        newLevelWithSoftAndStoneBricks("Another Level",32,0.5));
  }
  
  public void setNextLevel(final ILevel newLevel) {
    levelHandler.setNextLevel(newLevel);
  }

  public List<IBonus> newBonuses(int number, double probability) {
    return BonusHandler.genBonuses(number,probability,this);
  }

  public void registerBonuses(final List<IBonus> bonuses) {
    bonusHandler.setBonuses(bonuses);
  }
  
  public void setGameConsoleOutput(final PrintStream printStream) {
    gameConsole.setStream(printStream);
  }
  
  public void autoSwitchToNextLevel() {
    levelHandler.autoSwitchToNextLevel();
  }
  
  public boolean hasCurrentLevel() {
    return levelHandler.hasCurrentLevel();
  }
  
  public IBonus newExtraScore() {
    return new AddScoreModifier(this);
  }
  
  public IBonus newExtraHeart() {
    return new AddLifeModifier(this);
  }
  
  public IBonus newScoreDiscount() {
    return new LossScoreModifier(this);
  }
  
  public IBonus newHeartDiscount() {
    return new LossLifeModifier(this);
  }

  public AutomaticSwitch getAutoSwitch() {
    return autoSwitch;
  }

  /**
   * Cambia el estado del autoSwitch.
   */
  public void setAutoSwitch() {
    if (autoSwitch.isOpen()) {
      autoSwitch.close();
    } else {
      autoSwitch.open();
    }
  }
  
  public Score getCurScore() {
    return curScore;
  }

  public void updateScore() {
    player.getScore().addNextLevel(curScore.getPoints());
    curScore.add(-1 * curScore.getPoints());
  }

  public LevelHandler getLevelHandler() {
    return levelHandler;
  }

  public boolean isLevelCompleted() {
    return levelHandler.getRequiredPoints() <= curScore.getPoints();
  }
}
