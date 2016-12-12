package com.cc3002.breakout.gui;

import com.almasb.fxgl.audio.AudioPlayer;
import com.almasb.fxgl.audio.Music;
import com.almasb.fxgl.audio.Sound;
import com.almasb.fxgl.settings.UserProfile;
import com.almasb.fxgl.time.UpdateEvent;

import javafx.beans.property.DoubleProperty;

/**
 * Controlador de audio para utilizar sonidos en el juego.
 * @author gabriel
 *
 */
public class AudioController implements AudioPlayer {

  @Override
  public void save(UserProfile profile) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void load(UserProfile profile) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void onUpdateEvent(UpdateEvent event) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public DoubleProperty globalMusicVolumeProperty() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public DoubleProperty globalSoundVolumeProperty() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void playSound(Sound sound) {
    // TODO Auto-generated method stub
    sound.getClip$FXGL().play();;
  }
  
  public void playSound(Sound sound, double volume) {
    // TODO Auto-generated method stub
    sound.getClip$FXGL().play(volume);
  }
  
  @Override
  public void stopSound(Sound sound) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void stopAllSounds() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void playMusic(Music music) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void pauseMusic(Music music) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void resumeMusic(Music music) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void stopMusic(Music music) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void pauseAllMusic() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void resumeAllMusic() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void stopAllMusic() {
    // TODO Auto-generated method stub
    
  }

}
