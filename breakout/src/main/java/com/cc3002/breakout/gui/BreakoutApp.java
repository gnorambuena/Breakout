package com.cc3002.breakout.gui;

import java.util.List;

import org.jbox2d.dynamics.FixtureDef;

import com.almasb.ents.Entity;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.effect.ParticleControl;
import com.almasb.fxgl.effect.ParticleEmitter;
import com.almasb.fxgl.effect.ParticleEmitters;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.EntityView;
import com.almasb.fxgl.entity.GameEntity;
import com.almasb.fxgl.entity.component.BoundingBoxComponent;
import com.almasb.fxgl.entity.component.CollidableComponent;
import com.almasb.fxgl.entity.component.TypeComponent;
import com.almasb.fxgl.entity.control.ExpireCleanControl;
import com.almasb.fxgl.gameplay.Achievement;
import com.almasb.fxgl.input.ActionType;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.InputMapping;
import com.almasb.fxgl.input.OnUserAction;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.ui.UI;
import com.cc3002.breakout.gui.event.DeathEvent;
import com.cc3002.breakout.gui.event.Events;
import com.cc3002.breakout.gui.EntityType;
import com.cc3002.breakout.facade.HomeworkTwoFacade;
import com.cc3002.breakout.gui.EntityFactory;
import com.cc3002.breakout.gui.control.BatControl;
import com.cc3002.breakout.logic.level.ILevel;
import com.cc3002.breakout.logic.level.Player;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.Event;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class BreakoutApp extends GameApplication {

  private int width = 600;
  private int height = 600;
  private IntegerProperty scorePlayer;
  private IntegerProperty lifesPlayer;
  private HomeworkTwoFacade game = new HomeworkTwoFacade();
  private BatControl playerBat;
  private GameEntity bat;
  private GameEntity ball;
  private Entity walls;
  
  @Override
  protected void initInput() {
    
    Input input = getInput(); // get input service
    input.addInputMapping(new InputMapping("Left", KeyCode.A));
    input.addInputMapping(new InputMapping("Right", KeyCode.D));
  }
  
  @Override
  protected void initAssets() {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void initGame() {
    scorePlayer = new SimpleIntegerProperty(0);
    lifesPlayer = new SimpleIntegerProperty(3);
    initBackground();
    initPlayerBat();
    initBall();
    initScreenBounds();
    initBricks();
    initUI();
    
    Achievement a = getAchievementManager().getAchievementByName("Game Finished");
 // then, bind it to trigger, which is a property (x > getWidth())
    a.bind(new AchievementBinding((first,second) -> first >= second,
        game.getFlyweight().getCurScore().getPoints(),game.getRequiredPoints()));
  }

  @Override
  protected void initPhysics() {
    getPhysicsWorld().setGravity(0, 0);
    
    getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.BALL, EntityType.WALL) {
      
      protected void onHitBoxTrigger(Entity a, Entity b, HitBox boxA, HitBox boxB) {
          System.out.println(boxB.getName() + " hitted by "+ boxA.getName());
          if(boxB.getName().equals("TOP")){
            System.out.println("Event fired!");
            lifesPlayer.set(game.lossOfHeart());
            Point2D pos = bat.getPosition();
            System.out.println(pos.getX()+" "+pos.getY());
            pos = pos.add(35, -15);
            System.out.println(pos.getX()+" "+pos.getY());
            getGameWorld().removeEntity(ball);
            ball = (GameEntity)EntityFactory.newBall(pos.getX(),pos.getY());
            getGameWorld().addEntity(ball);
            ParticleEmitter emitter = ParticleEmitters.newSparkEmitter();
            emitter.setColorFunction(() -> Color.GOLD);

            Entities.builder()
                    .at(pos)
                    .with(new ParticleControl(emitter))
                    .with(new ExpireCleanControl(Duration.seconds(1)))
                    .buildAndAttach(getGameWorld());
          }
      }
    });

    getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER_BAT, EntityType.BALL) {
      @Override
      protected void onHitBoxTrigger(Entity a, Entity b, HitBox boxA, HitBox boxB) {
          System.out.println(boxB.getName() + " hitted by "+ boxA.getName());
      }
    });
    
    getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.BRICK, EntityType.BALL) {
      @Override
      protected void onHitBoxTrigger(Entity a, Entity b, HitBox boxA, HitBox boxB) {
          System.out.println(boxB.getName() + " hitted by "+ boxA.getName());
          BrickEntity brick = (BrickEntity)a;
          brick.getRefBrick().hit();
          if (brick.getRefBrick().isDestroyed()) {
            getGameWorld().removeEntity(a);
            scorePlayer.set((int)game.getFlyweight().getCurScore().getPoints());
          } else if (brick.getRefBrick().remainingHits() == 2) {
            brick.getMainViewComponent().setView(new EntityView(new Rectangle(35, 10,Color.DARKGOLDENROD)));
          } else {
            brick.getMainViewComponent().setView(new EntityView(new Rectangle(35, 10,Color.BISQUE)));
          }
      }
    });
  }

  @Override
  protected void initUI() {
    AppController controller = new AppController();
    UI ui = getAssetLoader().loadUI("main.fxml", controller);

    controller.getLabelScorePlayer().textProperty().bind(scorePlayer.asString());
    controller.getLabelScore().setText("SCORE");
    controller.getLabelLifesPlayer().textProperty().bind(lifesPlayer.asString());
    controller.getLabelLifes().setText("LIFES");
    getGameScene().addUI(ui);
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
    settings.setIntroEnabled(false); // turn off intro
    settings.setMenuEnabled(false);  // turn off menus
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
    this.walls = walls;
    walls.addComponent(new TypeComponent(EntityType.WALL));
    walls.addComponent(new CollidableComponent(true));
    
    FixtureDef def = new FixtureDef();
    def.setDensity(1.0f);
    def.setRestitution(1.0f);

    walls.getComponentUnsafe(PhysicsComponent.class).setFixtureDef(def);
    getGameWorld().addEntity(walls);
  }
  
  private void initBricks() {
    int numbricks = 90;
    ILevel level = game.newLevelWithSoftAndStoneBricks("First Level", numbricks, 0.6f);
    game.setCurrentLevel(level);
    for(int j = 0 ; j < numbricks ; j+=15){
      for(int i = 0 ; i < 15 ; i++){
        getGameWorld().addEntity(EntityFactory.newBrick(i*40, 100+j, level.getBricks().get(i+j)));
      }
    }
  }
  
  private void initBackground() {
    GameEntity bg = new GameEntity();
    bg.getMainViewComponent().setView(new Rectangle(getWidth(), getHeight(), Color.rgb(0, 0, 5)));

    getGameWorld().addEntity(bg);
}
  
  @Override
  protected void initAchievements() {
    Achievement a = new Achievement("Game Finished", "Finishing the game, Congratulations.");
    getAchievementManager().registerAchievement(a);
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
