package com.cc3002.breakout.facade;

import java.io.OutputStream;
import java.io.PrintStream;

public class NullOutputStream extends PrintStream {


  public NullOutputStream(OutputStream out) {
    super(out);
  }

  @Override
  public void write(int b) {
  }
  
  @Override
  public void write(byte[] b,int off,int len) {}
}