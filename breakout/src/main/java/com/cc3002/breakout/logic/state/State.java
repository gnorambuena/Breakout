package com.cc3002.breakout.logic.state;

class State {
  private AutomaticSwitch autoSwitch;
  
  protected void setAutoSwitch(AutomaticSwitch newAutoSwitch) {
    autoSwitch = newAutoSwitch;
  }
  
  protected void changeState(State state) {
    autoSwitch.setState(state);
  }
  
  protected void error() { 
    throw new RuntimeException();
  }
  
  protected void open() { 
    error();
  }
  
  protected void close() {
    error();
  }
  
  protected boolean isOpen() { 
    return false;
  }
  
  protected boolean isClosed() {
    return false;
  }

}
