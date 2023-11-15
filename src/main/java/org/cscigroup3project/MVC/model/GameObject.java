/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hunter Gehman
 * Section: 02 - 10 am
 * Date: 11/13/2023
 * Time: 9:14 AM
 *
 * Project: csci205_final_project
 * Package: org.cscigroup3project.MVC.model
 * Class: GameObject
 *
 * Description:
 *
 * ****************************************
 */
package org.cscigroup3project.MVC.model;

import java.awt.*;

public class GameObject {

    //TODO change to enum??
    private String id;

    //Every object has a position width, and height (for rendering sprite)
    protected int xPos;
    protected int yPos;
    protected int width;
    protected int height;

    /** the current sprite */
    private Image sprite;

    /** all sprites which can be used by the object*/
    private Image[] sprites;

    public GameObject (int x, int y, int w, int h, Image[] sprites){
        this.id = "";
        this.xPos = x;
        this.yPos = y;
        this.width = w;
        this.height = h;
        this.sprites = sprites;

        this.sprite = this.sprites[0];
    }

    public GameObject (int x, int y, int w, int h, Image[] sprites, String id){
        this.id = id;
        this.xPos = x;
        this.yPos = y;
        this.width = w;
        this.height = h;
        this.sprites = sprites;

        this.sprite = this.sprites[0];
    }

    /** Assigns a sprite to a certain sprite in the array.
     * If there is no valid sprite at the index, it adds the default sprite.
     */
    public void setSprite(int pos) {
        if(pos >= 0 & pos < sprites.length) {
            this.sprite = sprites[pos];
            }
        else {this.sprite = sprites[0];}

    }

    public String getId() {
        return id;
    }
}
