/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: MILES MELONI
 * Section: 9:00
 * Date: 11/16/23
 * Time: 12:26 AM
 *
 * Project: csci205_final_project
 * Package: org.cscigroup3project.MVC.model
 * Class: Textbox
 *
 * Description:
 *
 * ****************************************
 */
package org.cscigroup3project.MVC.model;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

public class Textbox {

    private String text;
    private Label label;

    private int X_POS = 0;
    private int Y_POS = 0;
    private int BOX_HEIGHT = 64;
    private int BOX_WIDTH = 192;
    private int TEXT_PADDING = 6;



    /** Textbox Sprite image */
    private Image TEXTBOX_SPRITE = new Image("cscigroup3project/textBoxSprite.png");

    public Textbox(String text) {
        this.text = text;
        label = new Label(text);
    }
}