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
package org.cscigroup3project.MVC.model.gameObject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import org.cscigroup3project.MVC.GameMain;
import org.cscigroup3project.MVC.model.room.SpriteType;

import java.util.ArrayList;

public class GameObject {

    //TODO change to enum??
    private String id;

    //Every object has a position width, and height (for rendering sprite)
    protected int xPos;
    protected int yPos;
    protected int width;
    protected int height;

    //The array of text displayed when an object is interacted with.
    // If object is not interactive, the array is left blank.
    protected ArrayList<String> textArray;

    /** the current sprite */
    private Image sprite;

    /** all sprites which can be used by the object*/
    private ArrayList<Image> sprites;

    /** image view of the sprite (only used for non-grid objects, which can change rooms */
    private ImageView imageOfObject = null;

    /** Used when there are multiple items that would otherwise be interchangeable,
     * to keep the game from seeing them as interchangeable. Defaults to zero for other items. */
    protected int duplicateID;

    public GameObject (int x, int y, int w, int h, ArrayList<Image> sprites){
        this.id = "";
        this.xPos = x;
        this.yPos = y;
        this.width = w;
        this.height = h;
        this.sprites = sprites;
        this.duplicateID = 0;

        if (sprites == null){
            this.sprite = new Image(GameMain.class.getResourceAsStream("AmogusTest.png"));
        }
        else {
            this.sprite = this.sprites.get(0);
        }
    }

    public GameObject (int x, int y, int w, int h, ArrayList<Image> sprites, String id){
        this.id = id;
        this.xPos = x;
        this.yPos = y;
        this.width = w;
        this.height = h;
        this.sprites = sprites;
        this.duplicateID = 0;

        if (sprites == null){
            this.sprite = new Image(GameMain.class.getResourceAsStream("AmogusTest.png"));
        }
        else {
            this.sprite = this.sprites.get(0);
        }
    }

    /** Assigns a sprite to a certain sprite in the array.
     * If there is no valid sprite at the index, it adds the default sprite.
     */
    public void setSprite(SpriteType spriteType) {
        switch (spriteType){
            case BACK -> this.sprite = this.sprites.get(0);
            case BACK_LEFT -> this.sprite = this.sprites.get(1);
            case BACK_RIGHT -> this.sprite = this.sprites.get(2);

            case FRONT -> this.sprite = this.sprites.get(3);
            case FRONT_LEFT -> this.sprite = this.sprites.get(4);
            case FRONT_RIGHT -> this.sprite = this.sprites.get(5);

            case LEFT -> this.sprite = this.sprites.get(6);
            case RIGHT -> this.sprite = this.sprites.get(7);

            case TOP_LEFT -> this.sprite = this.sprites.get(8);
            case TOP_RIGHT -> this.sprite = this.sprites.get(9);
        }

    }

    public Image getSprite() {
        return sprite;
    }

    // TODO for debugging
    public ArrayList<Image> getSprites() {
        return sprites;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getBounds(){
        return (new Rectangle(
                xPos,
                yPos,
                width,
                height
        ));
    }

    public String getId() {
        return id;
    }

    public void setTextArray(ArrayList<String> textArray) {
        this.textArray = textArray;
    }

    public ArrayList<String> getTextArray() {
        return textArray;
    }

    public int getDuplicateID() {
        return duplicateID;
    }

    public void setDuplicateID(int duplicateID) {
        this.duplicateID = duplicateID;
    }

    public ImageView getImageOfObject() {
        return imageOfObject;
    }

    public void setImageOfObject(ImageView imageOfObject) {
        this.imageOfObject = imageOfObject;
    }
}
