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
import com.cc3002.breakout.logic.observer.BonusObserver;
import com.cc3002.breakout.logic.observer.GameObserver;
import com.cc3002.breakout.logic.observer.LevelObserver;
import com.cc3002.breakout.logic.observer.ScoreObserver;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;


public class Flyweight {
  
  BonusHandler bonusHandler;
  LevelHandler levelHandler;
  Player player;
  Score curScore;
  List<GameObserver> observers;
  GameConsole gameConsole;
  boolean autoSwitch;
  
  /**
   * flyweight design pattern.
   */
  public Flyweight() {
    genPlayerAndScore();
    genGameConsoleAndObservers();
    genHandlers();
    autoSwitch = false;
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
    observers = new ArrayList<GameObserver>();
    observers.add(new ScoreObserver(this));
    observers.add(new BonusObserver(this));
    observers.add(new LevelObserver(this));
  }
  
  public List<GameObserver> getObservers() {
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

  public boolean getAutoSwitch() {
    return autoSwitch;
  }

  public void setAutoSwitch(boolean flag) {
    autoSwitch = flag;
  }
  
  public Score getCurScore() {
    return curScore;
  }

  public void updateScore() {
    player.getScore().addNextLevel(curScore.getPoints());
    curScore = new Score(this);
  }

  public LevelHandler getLevelHandler() {
    return levelHandler;
  }

}
