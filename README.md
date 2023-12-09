# CSCI 205 - Software Engineering and Design
Bucknell University
Lewisburg, PA
### Course Info
Instructor: Brian King and Joshua Stough
Semester: Fall, 2023
## Team Information
* Abigail Motter, Scrum Master, 2025, Animal Behavior
* Hunter Gehman, Developer, 2026, Computer Engineering
* Keeler Thomas, Project Owner, 2026, Biomedical Engineering and Computer Science
* Miles Meloni, Developer, 2026, Computer Science and Engineering

## Project Information
A simple 2D game, featuring a curious character, Ticoo, who enters 
into a strange series of rooms and explores his new location. Along
the way, he must solve a few puzzles by interacting with a series
of items that litter the floor of the rooms, including peculiar 
looking keys and disks that can be used to unlock various doors as
the player moves through the maze of rooms. The game ends when he
has successfully left the final room and escaped the maze.


## How to run it
First, if you are using GitBash or another Git-based terminal,
make sure to clone the project onto your local machine using:
    "git clone csci205_final_project"
Alternatively, from our GitLab page you can simply click the "Clone" button.
After the project has been loaded onto your device, run it using:
    "./gradlew run"
and you should be straight to playing!

## 3rd Party Libraries
JavaFX version 21, id 'org.openjfx.javafxplugin' version '0.0.13'

## Package Structure
For the creation of this game, strict MVC was followed to keep the 
JavaFX simple and easy to understand for all developers. The 
GameMain class runs the game. The Model is broken up more into several
packages, the first holds all game objects, such as keys and walls,
as well as the three interfaces that the game objects might implement.
The second package contains everything pertaining to the player,
including an Enum that controls direction, the inventory that holds
the player's items, and the player itself. The third package contains
the puzzles and the interface for the puzzle, which determine how
a puzzle can be solved and what happens when the puzzle is solved.
The final package is room which contains the room class, which creates
a 2D array of sprites to populate a room, the room manager, which 
actually populates the room and determines which room is being visualized,
and an Enum that holds the types of sprites to be displayed in the 
room.

## Link to Video
https://mediaspace.bucknell.edu/media/CSCI205+FA23+Team03+Project+Video/1_alvzwzqe