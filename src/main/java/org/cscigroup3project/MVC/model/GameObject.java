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

public class GameObject {

    //TODO change to enum??
    private String id;

    public GameObject (){
        this.id = "";
    }
    public GameObject (String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
