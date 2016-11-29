package com.cc3002.breakout.logic.state;

/**
 * Open state.
 * @author gabriel
 *
 */
class Open extends State {
  
  @Override
  protected void close() {
    this.changeState(new Close());
  }
  
  @Override
  protected boolean isOpen() { 
    return true; 
  }
}