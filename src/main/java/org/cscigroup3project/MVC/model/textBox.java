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
 * Class: textBox
 *
 * Description:
 *
 * ****************************************
 */
package org.cscigroup3project.MVC.model;

import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class textBox {

    private String text;
    private Label label;
    private int BOX_HEIGHT = 64;
    private int BOX_WIDTH = 192;
    private int TEXT_PADDING = 6;
    private Image TEXTBOX_SPRITE = new Image("cscigroup3project/textBoxSprite.png");


    public textBox(String text) {
        this.text = text;
        label = new Label(text);
    }
}