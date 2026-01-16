import java.awt.*;

public class Car {

        //VARIABLE DECLARATION SECTION
        //Here's where you state which variables you are going to use.
        public int xpos;                //the x position
        public int ypos;                //the y position
        public int dx;                    //the speed of the hero in the x direction
        public int dy;                    //the speed of the hero in the y direction
        public int width1;
        public int height1;
        public boolean isAlive;//a boolean to denote if the hero is alive or dead.
        public Rectangle hitbox;



        // METHOD DEFINITION SECTION

        // Constructor Definition
        // A constructor builds the object when called and sets variable values.


        //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
        // if you put in a String, an int and an int the program will use this constructor instead of the one above.
        public Car(int pXpos, int pYpos) {
            xpos = pXpos;
            ypos = pYpos;
            dx = 4;
            dy =0;
            width1 = 150;
            height1 = 60;
            isAlive = true;
            hitbox = new Rectangle(xpos,ypos,width1,height1);

        } // constructor

        //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
        public void move() {
            if(ypos>700){//wrap when hitting the bottom wall
                ypos = 0;
            }
            if(ypos<0){//wrap when hitting the top
                ypos=700;
            }
            if(xpos>1000){
                xpos = 0;
            }
            if(xpos<0){
                xpos = 1000;
            }
            //the cars and planes wrap when it hits the right and left walls

            xpos = xpos + dx;
            ypos = ypos + dy;
            hitbox = new Rectangle(xpos,ypos,width1,height1);

        }
    }








