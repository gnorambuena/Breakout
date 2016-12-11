package com.cc3002.breakout.logic.state;

/**
 * Automatic Switch for gameChange.
 * @author gabriel
 *
 */
public class AutomaticSwitch {
  private State state;
  
  public AutomaticSwitch() {
    this.setState(new Close());
  }
  
  public void setState(State newState) {
    state = newState;
    state.setAutoSwitch(this);
  }
  
  public void open() { 
    state.open(); 
  }
  
  public void close() {
    state.close();
  }
  
  public boolean isOpen() {
    return state.isOpen(); 
  }
  
  public boolean isClosed() { 
    return state.isClosed(); 
  }
}
