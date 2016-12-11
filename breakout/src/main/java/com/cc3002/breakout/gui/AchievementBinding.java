package com.cc3002.breakout.gui;

import javafx.beans.binding.BooleanBinding;



public class AchievementBinding extends BooleanBinding {
  
  interface TwoArgInterface {

    public boolean operation(long a, long b);
  }
  
  TwoArgInterface bindingOperator;
  long first;
  long second;
  
  AchievementBinding(TwoArgInterface operator, long first, long second) {
    bindingOperator = operator;
    this.first = first;
    this.second = second;
  }
  
  @Override
  protected boolean computeValue() {
    return bindingOperator.operation(first,second);
  }

}
