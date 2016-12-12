package com.cc3002.breakout.gui;

import javafx.beans.binding.BooleanBinding;


/**
 * Achievement.
 * @author gabriel
 *
 */
public class AchievementBinding extends BooleanBinding {
  
  /**
   * Interface para hacer un lambda.
   * @author gabriel
   *
   */
  interface TwoArgInterface {

    public boolean operation(long firstarg, long secondarg);
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
