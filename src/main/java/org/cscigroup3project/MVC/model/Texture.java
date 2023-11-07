/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hunter Gehman
 * Section: 02 - 10 am
 * Date: 11/4/2023
 * Time: 10:12 PM
 *
 * Project: csci205_final_project
 * Package: org.cscigroup3project
 * Class: Texture
 *
 * Description: Class to store the texture of an object
 *
 * ****************************************
 */
package org.cscigroup3project.MVC.model;

import javafx.scene.image.Image;
//#TODO make embed work
import javafx.embed.swing.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Texture {

    /** File containing the data for the texture */
    private Image textureFile;

    /**
     * Create the new texture
     * @param textureFile the data for the new texture
     */
    public Texture(Image textureFile){
        this.textureFile = textureFile;
    }

    /**
     * Create the new texture after converting to image
     * @param textureFileBuffered the data for the new texture
     */
    public Texture(BufferedImage textureFileBuffered){

        Image textureFile = SwingFXUtils.toFXImage(textureFileBuffered, null);
        this.textureFile = textureFile;
    }

    public void setTextureFile(Image textureFile) {
        this.textureFile = textureFile;
    }

    public Image getTextureFile() {
        return textureFile;
    }
}
