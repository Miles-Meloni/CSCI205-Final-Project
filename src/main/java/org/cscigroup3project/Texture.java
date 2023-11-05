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
package org.cscigroup3project;

import java.io.File;

public class Texture {
    private File textureFile;
    public Texture(File textureFile){
        this.textureFile = textureFile;
    }

    public void setTextureFile(File textureFile) {
        this.textureFile = textureFile;
    }

    public File getTextureFile() {
        return textureFile;
    }
}
