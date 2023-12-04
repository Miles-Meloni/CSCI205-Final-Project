/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hunter Gehman
 * Section: 02 - 10 am
 * Date: 11/6/2023
 * Time: 9:28 AM
 *
 * Project: csci205_final_project
 * Package: org.cscigroup3project.MVC.model
 * Class: GameModel
 *
 * Description:
 * The Model representing our JavaFX application.
 * ****************************************
 */

package org.cscigroup3project.MVC.model;

import javafx.scene.image.Image;
import org.cscigroup3project.MVC.GameMain;
import org.cscigroup3project.MVC.model.gameObject.*;
import org.cscigroup3project.MVC.model.player.Player;
import org.cscigroup3project.MVC.model.room.Room;
import org.cscigroup3project.MVC.model.room.RoomManager;

import java.util.ArrayList;

/**
 * The model representing our JavaFX application.
 */
public class GameModel {

    /** Our player for the game */
    private Player player;

    /** Our array of all rooms for the game */
    private ArrayList<Room> rooms;

    /** Our {@link Wall} for the game */
    private Wall wall;

    /**Our {@link Disk} for testing music */
    private Disk disk;

    /** Our two {@link Key} objects for the key puzzle */
    private Key key1;
    private Key key2;

    /** Our lock for the key puzzle */
    private Lock lock;

    /** Our npcs and interactive items*/
    private Talker jazzNpc;
    private Talker hideNpc;
    private TalkerNoCollisions dogPicture;
    private Talker jukebox;
    private Talker shopGirl;
    private Talker tvBroken;
    private Talker glitterGirl;

    /** Decorative objects */
    private GameObject glitter;

    /** Collectible objects that don't do anything */
    private Collectible burger;


    /** The {@link RoomManager} for the game */
    private RoomManager roomManager;

    /** Array which keeps track of the room objects */
    private ArrayList<ArrayList<GameObject>> allObjectsArray;

    /**
     * Create a new GameModel object with any number of {@link Room} objects (and their contained
     * objects), a {@link Player} for the game, and {ADD ANYTHING ELSE HERE}
     */
    public GameModel(){

        // currently empty arraylist of rooms
        this.rooms = new ArrayList<>();

        // current Player for the game with position and name
        this.player = new Player(new int[]{0,0}, "DemoPlayer", "Sprites/PS");

        // Create the roomManager for the application
        this.roomManager = new RoomManager();

        //Create the objects array for the application
        this.allObjectsArray = new ArrayList<ArrayList<GameObject>>();

        //Add an array for the object array for each room
        for (int i = 0; i< roomManager.getRooms().size(); i++){
            allObjectsArray.add(new ArrayList<>());
        }

        // NOTE: separate key sprites used to avoid confusion in GameController image removal
        ArrayList<Image> keySprite = new ArrayList<Image>();
        keySprite.add(new Image(GameMain.class.getResourceAsStream("keyAndLockSprites/Key.png")));
        this.key1 = new Key(50,50, keySprite, "key1", 0);

        //add key to object array and room
        allObjectsArray.get(1).add(key1);
        this.roomManager.getRoom(1).addObject(key1);

        // Create the key for the application
        keySprite.add(0, new Image(GameMain.class.getResourceAsStream("keyAndLockSprites/Key.png")));
        this.key2 = new Key(-50,-50, keySprite, "key2",1);

        //add key to object array and room
        allObjectsArray.get(0).add(key2);
        this.roomManager.getRoom(0).addObject(key2);

        //create the lock in a similar manner to the keys
        ArrayList<Image> lockSprites = new ArrayList<Image>();
        lockSprites.add(new Image(GameMain.class.getResourceAsStream("keyAndLockSprites/Lock_0.png")));
        lockSprites.add(new Image(GameMain.class.getResourceAsStream("keyAndLockSprites/Lock_1.png")));
        lockSprites.add(new Image(GameMain.class.getResourceAsStream("keyAndLockSprites/Lock_2.png")));

        this.lock = new Lock(0, -120, lockSprites);

        //add lock to object array and room
        allObjectsArray.get(0).add(lock);
        this.roomManager.getRoom(0).addObject(lock);

        //create an npc
        ArrayList<Image> jazzNpcSprite = new ArrayList<Image>();
        jazzNpcSprite.add(new Image(GameMain.class.getResourceAsStream("npc_jazz.png")));
        ArrayList<String> jazzNpcText = new ArrayList<String>();
        jazzNpcText.add("\"It's important to stick to your routines when things get tough.\"");
        jazzNpcText.add("\"That's why I'm Jazzercising during armageddon.\"");
        jazzNpcText.add("\"Besides, if I don't my trainer will kill me.\"");
        jazzNpcText.add("\"You know how it is.\"");

        this.jazzNpc = new Talker(90, -100, 31, 42, jazzNpcSprite, jazzNpcText);

        //add npc to object array and room
        allObjectsArray.get(2).add(jazzNpc);
        this.roomManager.getRoom(2).addObject(jazzNpc);

        //create another npc
        ArrayList<Image> hideNpcSprite = new ArrayList<Image>();
        hideNpcSprite.add(new Image(GameMain.class.getResourceAsStream("npc_hide.png")));
        ArrayList<String> hideNpcText = new ArrayList<String>();
        hideNpcText.add("\"I lost my house. This is my new house.\"");
        hideNpcText.add("\"You can come inside if you want.\"");
        hideNpcText.add("\"If you can fit.\"");

        this.hideNpc = new Talker(-90, -100, 32, 32, hideNpcSprite, hideNpcText);

        //add npc to object array and room
        allObjectsArray.get(0).add(hideNpc);
        this.roomManager.getRoom(0).addObject(hideNpc);

        //create another npc
        ArrayList<Image> shopNpcSprite = new ArrayList<Image>();
        shopNpcSprite.add(new Image(GameMain.class.getResourceAsStream("Shopkeeper.png")));
        ArrayList<String> shopNpcText = new ArrayList<String>();
        shopNpcText.add("\"Welcome to my store.\"");
        shopNpcText.add("\"Everything's free, considering it's the end of the world, and money is gone.\"");
        shopNpcText.add("\"So you can just kinda take things.\"");

        this.shopGirl = new Talker(0, -115, 48, 51, shopNpcSprite, shopNpcText);

        //add npc to object array and room
        allObjectsArray.get(1).add(shopGirl);
        this.roomManager.getRoom(1).addObject(shopGirl);

        //create burger
        ArrayList<Image> burgerSprite = new ArrayList<Image>();
        burgerSprite.add(new Image(GameMain.class.getResourceAsStream("Burger.png")));
        ArrayList<String> burgerText = new ArrayList<String>();
        burgerText.add(">A Hamburger.");
        burgerText.add(">Well, an alien-equivalent- -of-a-cow-burger, technically.");
        burgerText.add(">You aren't hungry.");

        this.burger = new Collectible(16, -110, burgerSprite, burgerText);

        //add npc to object array and room
        allObjectsArray.get(1).add(burger);
        this.roomManager.getRoom(1).addObject(burger);

        //create dog picture
        ArrayList<Image> dogPicSprite = new ArrayList<Image>();
        dogPicSprite.add(new Image(GameMain.class.getResourceAsStream("dogPoster.png")));
        ArrayList<String> dogPicText = new ArrayList<String>();
        dogPicText.add(">Another propaganda poster.");
        dogPicText.add(">It appears to be of some sort of idyllic scene, meant to inspire the will to fight...");
        dogPicText.add(">Wait, this is just a picture of somebody's pet.");
        dogPicText.add(">How did this even get here?");

        this.dogPicture = new TalkerNoCollisions(-70, -130, 24, 28, dogPicSprite, dogPicText);

        //add npc to object array and room
        allObjectsArray.get(0).add(dogPicture);
        this.roomManager.getRoom(0).addObject(dogPicture);

        //create jukebox #TODO Replace with working storage item
        ArrayList<Image> jukeboxSprite = new ArrayList<Image>();
        jukeboxSprite.add(new Image(GameMain.class.getResourceAsStream("Jukebox.png")));
        ArrayList<String> jukeboxText = new ArrayList<String>();
        jukeboxText.add(">A jukebox.");
        jukeboxText.add(">It seems to be jammed; you can't change the song.");
        jukeboxText.add(">A shame, really.");
        jukeboxText.add(">You're getting sick of this one.");

        this.jukebox = new Talker(-90, -115, 32, 32, jukeboxSprite, jukeboxText);

        //add npc to object array and room
        allObjectsArray.get(2).add(jukebox);
        this.roomManager.getRoom(2).addObject(jukebox);

        //create broken TV
        ArrayList<Image> brokenTVSprite = new ArrayList<Image>();
        brokenTVSprite.add(new Image(GameMain.class.getResourceAsStream("brokenTV.png")));
        ArrayList<String> brokenTVText = new ArrayList<String>();
        brokenTVText.add(">A broken television.");
        brokenTVText.add(">You imagine that if it could work, it would be talking about the weather.");
        brokenTVText.add(">People love talking about the weather.");

        this.tvBroken = new Talker(70, -130, 48, 32, brokenTVSprite, brokenTVText);

        //add npc to object array and room
        allObjectsArray.get(0).add(tvBroken);
        this.roomManager.getRoom(0).addObject(tvBroken);

        //create glitter
        ArrayList<Image> glitterSprite = new ArrayList<Image>();
        glitterSprite.add(new Image(GameMain.class.getResourceAsStream("Glitter.png")));

        this.glitter = new GameObject(-64,-58,96,96,glitterSprite);

        //add glitter to object array and room
        allObjectsArray.get(1).add(glitter);
        this.roomManager.getRoom(1).addObject(glitter);

        //create another npc
        ArrayList<Image> glitterKidSprite = new ArrayList<Image>();
        glitterKidSprite.add(new Image(GameMain.class.getResourceAsStream("GlitterKid.png")));
        ArrayList<String> glitterKidText = new ArrayList<String>();
        glitterKidText.add("\"In a few seconds, I turned these boring green tiles....\"");
        glitterKidText.add("\"Into fancy sparkly tiles!\"");
        glitterKidText.add("\"Isn't glitter just amazing?\"");

        this.glitterGirl = new Talker(-80, -100, 48, 51, glitterKidSprite, glitterKidText);

        //add npc to object array and room
        allObjectsArray.get(1).add(glitterGirl);
        this.roomManager.getRoom(1).addObject(glitterGirl);

    }

    /** Getters for the {@link Player}, {@link RoomManager}, {@link Key} objects */
    public Player getPlayer() { return player; }

    public RoomManager getRoomManager(){
        return roomManager;
    }

    public ArrayList<ArrayList<GameObject>> getAllObjectsArray() {
        return allObjectsArray;
    }
}
