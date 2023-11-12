/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Keeler Thomas
 * Section: 01 - 9:00am
 * Date: 11/9/2023
 * Time: 10:44 AM
 *
 * Project: csci205_final_project
 * Package: org.cscigroup3project.MVC.model
 * Class: Key
 *
 * Description:
 * Class that represents keys that can be used by the Player.
 * ****************************************
 */
package org.cscigroup3project.MVC.model;

/**
 * Class that represents disks, containing a music file, that can be picked up, equipped, and used by the {@link Player}.
 */
public class Disk {

    /** Name of the disk object */
    private String name;

    /** File of the disk's music */
    private String file;

    /**
     * Constructs a key given a {@link String} name.
     *
     * @param name the String name of the disc
     * @param file the String file of the discs music
     */
    public Disk(String name, String file) {

        this.name = name;
        this.file = file;
    }

    public String getFile() {
        return file;
    }
}