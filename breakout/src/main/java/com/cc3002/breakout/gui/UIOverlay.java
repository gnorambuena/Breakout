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
import com.almasb.fxgl.asset.AssetLoader;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class UIOverlay extends Parent {

  private static final Font FONT = Font.font(18);

  private double width;
  private double height;

  private ImageView selectedItemView = new ImageView();

  public UIOverlay(double w, double h) {
      this.width = w;
      this.height = h;

      AssetLoader assetLoader = FXGL.getAssetLoader();
  }

    public void showMessage(String message) {
        HBox letters = new HBox(0);
        System.out.println(message);
        double w = 0;
        for (int i = 0; i < message.length(); i++) {
            Text letter = new Text(message.charAt(i) + "");
            letter.setFill(Color.AZURE);
            letter.setFont(FXGL.getUIFactory().newFont(40));
            w += letter.getLayoutBounds().getWidth();

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

        letters.setTranslateX(width / 2 - w / 2);
        getChildren().add(letters);
    }

    public void showMessageFlash(String message) {
        Text text = new Text(message);
        text.setTranslateX(width / 2 - text.getLayoutBounds().getWidth() / 2 - 100);
        text.setTranslateY(height / 2);
        text.setFill(Color.AZURE);
        text.setFont(FXGL.getUIFactory().newFont(40));
        text.setOpacity(0);
        getChildren().add(text);

        FadeTransition ft = new FadeTransition(Duration.seconds(2), text);
        ft.setToValue(1);
        ft.setAutoReverse(true);
        ft.setCycleCount(2);
        ft.setOnFinished(e -> getChildren().remove(text));
        ft.play();
    }
}
