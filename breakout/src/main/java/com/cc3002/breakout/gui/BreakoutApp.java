package com.cc3002.breakout.gui;

import com.almasb.ents.Entity;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.audio.Sound;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.GameEntity;
import com.almasb.fxgl.entity.component.CollidableComponent;
import com.almasb.fxgl.entity.component.TypeComponent;
import com.almasb.fxgl.input.ActionType;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.InputMapping;
import com.almasb.fxgl.input.OnUserAction;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.ui.UI;
import com.cc3002.breakout.facade.HomeworkTwoFacade;
import com.cc3002.breakout.gui.collisionhandler.BallWallCollisionHandler;
import com.cc3002.breakout.gui.collisionhandler.BatBallCollisionHandler;
import com.cc3002.breakout.gui.collisionhandler.BrickBallCollisionHandler;
import com.cc3002.breakout.gui.control.BatControl;
import com.cc3002.breakout.gui.observer.BonusObserver;
import com.cc3002.breakout.logic.bonus.IBonus;
import com.cc3002.breakout.logic.level.ILevel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.jbox2d.dynamics.FixtureDef;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Clase principal del juego, desde aqui se inicializan todos los objetos.
 * @author gabriel
 *
 */
public class BreakoutApp extends GameApplication {

  private int width = 600;
  private int height = 600;
  private int curLevel = 0;
  private List<ILevel> levels;
  private List<List<IBonus>> bonusesperlevel;
  private IntegerProperty scorePlayer;
  private IntegerProperty lifesPlayer;
  private HomeworkTwoFacade game = new HomeworkTwoFacade();
  private BatControl playerBat;
  private GameEntity bat;
  private GameEntity ball;
  private GameEntity ball2;
  private Overlay uioverlay;
  private Hashtable<String,Sound> gameSounds;
  boolean canPassNextLevel = false;
  boolean canRestartGame = false;
  //private Sound levelDone;
  //private Sound gamefinished;
  
  /**
   * Metodo que se encarga de hacer pasar al siguiente nivel del juego en la gui.
   */
  @OnUserAction(name = "Pass", type = ActionType.ON_ACTION)
  public void playNextLevel() {
    
    if (canPassNextLevel) {
      canPassNextLevel = false;
      
      if (curLevel + 1 == levels.size()) {
        pause();
        //new AudioController().playSound(gamefinished);
        uioverlay.showMessage("You win!");
        setFlagCanRestartGame();
        
      } else {
        removeLevel();
        initNextLevel();
        scorePlayer.set((int)game.getFlyweight().getCurScore().getPoints());
      }
    }
  }
  
  /**
   * Accion para reinicar el juego.
   */
  @OnUserAction(name = "Restart", type = ActionType.ON_ACTION)
  public void restartGame() {
    if (canRestartGame) {
      canRestartGame = false;
      startNewGame();
    }
  }
  
  @OnUserAction(name = "Left", type = ActionType.ON_ACTION)
  public void left() {
    playerBat.left();
  }

  @OnUserAction(name = "Right", type = ActionType.ON_ACTION)
  public void right() {
    playerBat.right();
  }

  @OnUserAction(name = "Left", type = ActionType.ON_ACTION_END)
  public void stopBat() {
    playerBat.stop();
  }

  @OnUserAction(name = "Right", type = ActionType.ON_ACTION_END)
  public void stopBat2() {
    playerBat.stop();
  }
  
  @Override
  protected void initInput() {
    
    Input input = getInput(); // get input service
    input.addInputMapping(new InputMapping("Left", KeyCode.A));
    input.addInputMapping(new InputMapping("Right", KeyCode.D));
    input.addInputMapping(new InputMapping("Pass", KeyCode.T));
    input.addInputMapping(new InputMapping("Restart", KeyCode.R));
  }
  
  @Override
  protected void initAssets() {
    gameSounds = new Hashtable<String,Sound>();
    gameSounds.put("ballSoftBrickHit", getAssetLoader().loadSound("softbrickhit.wav"));
    gameSounds.put("ballStoneBrickHit", getAssetLoader().loadSound("stonebrickhit.wav"));
    gameSounds.put("ballWallHit", getAssetLoader().loadSound("ballwall.wav"));
    gameSounds.put("batBallHit", getAssetLoader().loadSound("batball.wav"));
    gameSounds.put("ballFalling", getAssetLoader().loadSound("ballfalling.wav"));
    gameSounds.put("bonus", getAssetLoader().loadSound("bonus.wav"));
    gameSounds.put("discount", getAssetLoader().loadSound("discount.wav"));
    gameSounds.put("ballMetalBrickHit", getAssetLoader().loadSound("metalbrickhit.wav"));
    gameSounds.put("ballPoisonBrickHit", getAssetLoader().loadSound("poisonbrickhit.wav"));
    //levelDone = getAssetLoader().loadSound("leveldone.wav");
    //gamefinished = getAssetLoader().loadSound("gamefinished.wav");
  }

  @Override
  protected void initGame() {
    scorePlayer = new SimpleIntegerProperty(0);
    lifesPlayer = new SimpleIntegerProperty(3);
    initBackground();
    initPlayerBat();
    initBall();
    initScreenBounds();
    initLevels();
    initUI();
    initBonus();
  }

  @Override
  protected void initPhysics() {
    getPhysicsWorld().setGravity(0, 0);
    getPhysicsWorld().addCollisionHandler(new BallWallCollisionHandler());
    getPhysicsWorld().addCollisionHandler(new BatBallCollisionHandler());
    getPhysicsWorld().addCollisionHandler(new BrickBallCollisionHandler());
  }

  @Override
  protected void initUI() {
    AppController controller = new AppController();
    UI ui = getAssetLoader().loadUI("main.fxml", controller);
    getGameScene().addUI(ui);
    uioverlay = new Overlay(width,height);
    controller.getLabelScorePlayer().textProperty().bind(scorePlayer.asString());
    controller.getLabelScore().setText("SCORE");
    controller.getLabelLifesPlayer().textProperty().bind(lifesPlayer.asString());
    controller.getLabelLifes().setText("LIFES");
    getGameScene().addUINodes(uioverlay);
  }

  @Override
  protected void onUpdate(double tpf) {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void initSettings(GameSettings settings) {
    settings.setWidth(width);
    settings.setHeight(height);
    settings.setTitle("Breakout App");
    settings.setVersion("0.1");
    settings.setProfilingEnabled(false);
    settings.setIntroEnabled(false); // turn off intro
    settings.setMenuEnabled(true);  // turn off menus
  }
  
  private void initPlayerBat() {
    Entity bat = EntityFactory.newBat(getWidth() / 2, 9 * getHeight() / 11, 80, 15);
    getGameWorld().addEntity(bat);
    this.bat = (GameEntity)bat;
    playerBat = bat.getControlUnsafe(BatControl.class);
  }
  
  private void initBall() {
    Entity ball = EntityFactory.newBall(getWidth() / 2, 5 * getHeight() / 7 - 5);
    this.ball = (GameEntity)ball;
    getGameWorld().addEntity(ball);
  }
  
  private void initScreenBounds() {
    Entity walls = Entities.makeScreenBounds(100);
    walls.addComponent(new TypeComponent(EntityType.WALL));
    walls.addComponent(new CollidableComponent(true));
    
    FixtureDef def = new FixtureDef();
    def.setDensity(1.0f);
    def.setRestitution(1.0f);

    walls.getComponentUnsafe(PhysicsComponent.class).setFixtureDef(def);
    getGameWorld().addEntity(walls);
  }
  
  private void initLevels() {
    levels = new ArrayList<ILevel>();
    int numbricks = 15;
    ILevel levelOne = game.newLevelWithSoftAndStoneBricks("Level One", numbricks, 0.6f);
    game.setCurrentLevel(levelOne);
    ILevel levelTwo = game.newLevelWithSoftAndStoneBricks("Level Two", numbricks, 0.5f);
    game.setNextLevel(levelTwo);
    ILevel levelThree = game.newLevelWithSoftAndStoneBricks("Level Three", numbricks + 15, 0.5f);
    ILevel levelFour = game.newLevelWithSoftAndStoneBricks("Level Four", numbricks + 15, 0.4f);
    ILevel levelFive = game.newLevelWithSoftAndStoneBricks("Level Five", numbricks + 30, 0.4f);
    levels.add(levelOne);
    levels.add(levelTwo);
    levels.add(levelThree);
    levels.add(levelFour);
    levels.add(levelFive);
    initBricks(levelOne);
  }
  
  private void initBricks(ILevel curLevel) {
    int numbricks = curLevel.getBricks().size();
    for ( int j = 0 ; j < numbricks ; j += 15 ) {
      for ( int i = 0 ; i < 15 ; i++ ) {
        getGameWorld().addEntity(EntityFactory
            .newBrick(i * 40 , 100 + j , curLevel.getBricks().get(i + j)));
      }
    }
  }
  
  private void removeLevel() {
    System.out.println("Removing elements from level...");
    getGameWorld().getEntitiesByType(
        EntityType.BALL, EntityType.BRICK, EntityType.PLAYER_BAT)
        .forEach(Entity::removeFromWorld);
    System.out.println("Elements removed! from level...");
  }
  
  private void initNextLevel() {
    game.autoSwitchToNextLevel();
    
    curLevel++;
    ILevel curILevel = levels.get(curLevel);
    initBricks(curILevel);
    game.registerBonuses(bonusesperlevel.get(curLevel));
    
    //new AudioController().playSound(levelDone);
    game.autoSwitchToNextLevel();
    
    if (curLevel < 4) {
      game.setNextLevel(levels.get(curLevel + 1));
    }
    
    //setBall(ball,(GameEntity)EntityFactory.newBall(getWidth() / 2, 5 * getHeight() / 7 - 5));
    initBall();
    initPlayerBat();
    uioverlay.showMessageFlash("Playing " + curILevel.getLevelName(), 2, 0, 0);
  }
  
  private void initBackground() {
    GameEntity bg = new GameEntity();
    bg.getMainViewComponent().setView(new Rectangle(getWidth(), getHeight(), Color.rgb(0, 0, 5)));

    getGameWorld().addEntity(bg);
  }
  
  protected void initBonus() {
    game.getFlyweight().addObserver(new BonusObserver());
    bonusesperlevel = new ArrayList<List<IBonus>>();
    for (ILevel level : levels) {
      List<IBonus> curBonus = game.newBonuses((int)(level.getNumberOfBricks() * 0.4),0.7);
      bonusesperlevel.add(curBonus);
    }
    game.registerBonuses(bonusesperlevel.get(0));
  }
  
  @Override
  protected void initAchievements() {}
  
  public IntegerProperty getScorePlayer() {
    return scorePlayer;
  }
  
  public IntegerProperty getLifesPlayer() {
    return lifesPlayer;
  }
  
  public HomeworkTwoFacade getFacade() {
    return game;
  }
  
  public Overlay getOverlay() {
    return uioverlay;
  }
  
  public GameEntity getBall() {
    return ball;
  }
  
  public GameEntity getBat() {
    return bat;
  }
  
  public Sound getSound(String soundName) {
    return gameSounds.get(soundName);
  }
  
  public void spawnSecondBall() {
    ball2 = (GameEntity) EntityFactory.newBall(bat.getX() + 40 , bat.getY() - 5);
    getGameWorld().addEntities(ball2);
  }
  
  /**
   * Metodo que se encarga de spawnear una nueva pelota.
   * @param newball La pelota ya creada.
   */
  public void setBall(GameEntity oldball ,GameEntity newball) {
    getGameWorld().removeEntity(oldball);
    oldball = newball;
    getGameWorld().addEntities(oldball); 
  }
  
  public void setFlagPassNextLevel() {
    canPassNextLevel = true;
    uioverlay.showMessageFlash("Press <T> to play next Level!", 1, -50, 90);
  }
  
  public void setFlagCanRestartGame() {
    //canRestartGame = true;
    //uioverlay.showMessageFlash("Press <R> to play again!", 1, -10, 90);
  }
  
  /**
   * Metodo para hacer resize del bat.
   */
  public void batResize() {
    Entity newbat = EntityFactory.newBat(this.bat.getX(), 9 * getHeight() / 11, 120, 15);
    getGameWorld().addEntity(newbat);
    playerBat = newbat.getControlUnsafe(BatControl.class);
    getGameWorld().removeEntity(bat);
    bat = (GameEntity)newbat;
  }
  
  public void pause() {
    super.pause();
  }
  
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Metodo que se usa para perder el juego.
   */
  public void loseGame() {
    uioverlay.showMessage("Game Over!");
    lifesPlayer.set(0);
    setFlagCanRestartGame();
    pause();
  }

}
