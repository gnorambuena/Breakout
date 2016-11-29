package com.cc3002.breakout.logic.state;

/**
 * Close state.
 * @author gabriel
 *
 */
class Close extends State {
  
  @Override
  protected void open() { 
    this.changeState(new Open());
  }
  
  @Override
  protected boolean isClosed() { 
    return true;
  }
}