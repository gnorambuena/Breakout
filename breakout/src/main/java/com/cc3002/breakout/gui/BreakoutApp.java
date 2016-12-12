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
import com.cc3002.breakout.logic.level.ILevel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.jbox2d.dynamics.FixtureDef;

import java.util.ArrayList;
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
  private IntegerProperty scorePlayer;
  private IntegerProperty lifesPlayer;
  private HomeworkTwoFacade game = new HomeworkTwoFacade();
  private BatControl playerBat;
  private GameEntity bat;
  private GameEntity ball;
  private Overlay uioverlay;
  private Sound ballBrickHit;
  private Sound ballWallHit;
  private Sound batBallHit;
  //private Sound levelDone;
  //private Sound gamefinished;
  
  @Override
  protected void initInput() {
    
    Input input = getInput(); // get input service
    input.addInputMapping(new InputMapping("Left", KeyCode.A));
    input.addInputMapping(new InputMapping("Right", KeyCode.D));
  }
  
  @Override
  protected void initAssets() {
    ballBrickHit = getAssetLoader().loadSound("ballbrickhit.wav");
    ballWallHit = getAssetLoader().loadSound("ballwall.wav");
    batBallHit = getAssetLoader().loadSound("batball.wav");
    //levelDone = getAssetLoader().loadSound("leveldone.wav");
    //gamefinished = getAssetLoader().loadSound("gamefinished.wav");
  }

  @Override
  protected void initGame() {
    scorePlayer = new SimpleIntegerProperty(0);
    lifesPlayer = new SimpleIntegerProperty(30);
    initBackground();
    initPlayerBat();
    initBall();
    initScreenBounds();
    initLevels();
    initUI();
  }

  @Override
  protected void initPhysics() {
    getPhysicsWorld().setGravity(0, 0);
    getPhysicsWorld().addCollisionHandler(new BallWallCollisionHandler(this));
    getPhysicsWorld().addCollisionHandler(new BatBallCollisionHandler(this));
    getPhysicsWorld().addCollisionHandler(new BrickBallCollisionHandler(this));
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
    Entity bat = EntityFactory.newBat(getWidth() / 2, 9 * getHeight() / 11);
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
  
  /**
   * Metodo que se encarga de hacer pasar al siguiente nivel del juego en la gui.
   */
  public void playNextLevel() {
    
    if (curLevel + 1 == levels.size()) {
      pause();
      //new AudioController().playSound(gamefinished);
      uioverlay.showMessage("You win!");
    } else {
      removeLevel();
      game.autoSwitchToNextLevel();
      curLevel++;
      ILevel curILevel = levels.get(curLevel);
      initBricks(curILevel);
      //new AudioController().playSound(levelDone);
      game.autoSwitchToNextLevel();
      if (curLevel < 4) {
        game.setNextLevel(levels.get(curLevel + 1));
      }
      setBall((GameEntity)EntityFactory.newBall(getWidth() / 2, 5 * getHeight() / 7 - 5));
      getGameWorld().removeEntities(bat);
      initPlayerBat();
      uioverlay.showMessageFlash("Playing " + curILevel.getLevelName() );
      scorePlayer.set((int)game.getFlyweight().getCurScore().getPoints());
    }
  }
  
  private void removeLevel() {
    List<Entity> bricks = getGameWorld().getEntitiesByType(EntityType.BRICK);
    for (Entity brick : bricks) {
      getGameWorld().removeEntity(brick);
    }
  }
  
  private void initBackground() {
    GameEntity bg = new GameEntity();
    bg.getMainViewComponent().setView(new Rectangle(getWidth(), getHeight(), Color.rgb(0, 0, 5)));

    getGameWorld().addEntity(bg);
  }
  
  @Override
  protected void initAchievements() {
    //Achievement a = new Achievement("Game Finished", "Finishing the game, Congratulations.");
    //getAchievementManager().registerAchievement(a);
  }
  
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
  
  public Sound getBrickHitSound() {
    return ballBrickHit;
  }
  
  public Sound getWallHitSound() {
    return ballWallHit;
  }
  
  public Sound getBatHitSound() {
    return batBallHit;
  }
  
  /**
   * Metodo que se encarga de spawnear una nueva pelota.
   * @param newball La pelota ya creada.
   */
  public void setBall(GameEntity newball) {
    getGameWorld().removeEntities(ball);
    ball = newball;
    getGameWorld().addEntities(ball); 
  }
  
  public void pause() {
    super.pause();
  }
  
  @OnUserAction(name = "Left", type = ActionType.ON_ACTION)
  public void up() {
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
  
  public static void main(String[] args) {
    launch(args);
  }

}
