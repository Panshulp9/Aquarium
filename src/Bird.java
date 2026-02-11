import java.awt.*;

public class Bird {
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width2;
    public int height2;
    public boolean isAlive;//a boolean to denote if the hero is alive or dead.
    public Rectangle perimeter;

    public Bird(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx = 3;
        dy =2;
        width2 = 50;
        height2 = 100;
        isAlive = true;
        perimeter = new Rectangle(xpos,ypos,width2,height2);

    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {
        if(xpos > 980) {
            dx=-dx;
        }
        if (xpos < 0){
            dx=-dx;
        }
        if(ypos < 0) {
            dy=-dy;
        }
        if(ypos > 500){
            dy=-dy;
        }
        //the cars and planes wrap when it hits the right and left walls

        xpos = xpos + dx;
        ypos = ypos + dy;
        perimeter = new Rectangle(xpos,ypos,width2,height2);

    }
}
