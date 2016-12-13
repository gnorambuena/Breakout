package com.cc3002.breakout.gui;

/*
 * The MIT License (MIT)
 *
 * FXGL - JavaFX Game Library
 *
 * Copyright (c) 2015-2016 AlmasB (almaslvl@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import com.almasb.fxgl.app.FXGL;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Overlay para mostrar mensajes en el juego.
 * @author gabriel
 *
 */
public class Overlay extends Parent {


  private double width;
  private double height;

  
  /**
   * Construye el Overlay del juego.
   * @param width El ancho del screen.
   * @param height El alto del screen.
   */
  public Overlay(double width, double height) {
    this.width = width;
    this.height = height;
  }

  /**
   * Metodo que se encarga de mostrar en el screen un mensaje.
   * @param message Mensaje a mostrar en el screen.
   */
  public void showMessage(String message) {
    HBox letters = new HBox(0);
    System.out.println(message);
    double auxwidth = 0;
    for (int i = 0; i < message.length(); i++) {
      Text letter = new Text(message.charAt(i) + "");
      letter.setFill(Color.AZURE);
      letter.setFont(FXGL.getUIFactory().newFont(40));
      auxwidth += letter.getLayoutBounds().getWidth();

      TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), letter);
      tt.setDelay(Duration.seconds(i * 0.1));
      tt.setToY(height / 2);
      tt.play();

      letters.getChildren().add(letter);

      if (i == message.length() - 1) {
        tt.setOnFinished(e -> {
          FadeTransition ft = new FadeTransition(Duration.seconds(30), letters);
          ft.setToValue(0);
          ft.setOnFinished(e2 -> getChildren().remove(letters));
          ft.play();
        });
      }
    }

    letters.setTranslateX(width / 2 - auxwidth / 2);
    getChildren().add(letters);
  }

  /**
   * Al igual que showMessage se encarga de mostrar un mensaje en pantalla, solo 
   * que este se muestra rapidamente.
   * @param message Mensaje a mostrar.
   */
  public void showMessageFlash(String message, double duration, int offsetx, int offsety) {
    Text text = new Text(message);
    text.setTranslateX(width / 2 - text.getLayoutBounds().getWidth() / 2 - 100 + offsetx);
    text.setTranslateY(height / 2 + offsety);
    text.setFill(Color.AZURE);
    text.setFont(FXGL.getUIFactory().newFont(40));
    text.setOpacity(0);
    getChildren().add(text);

    FadeTransition ft = new FadeTransition(Duration.seconds(duration), text);
    ft.setToValue(1);
    ft.setAutoReverse(true);
    ft.setCycleCount(2);
    ft.setOnFinished(e -> getChildren().remove(text));
    ft.play();
  }
}
