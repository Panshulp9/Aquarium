//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable {

   //Variable Definition Section
   //Declare the variables used in the program 
   //You can set their initial values too
   
   //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;
   
	public BufferStrategy bufferStrategy;
	public Image a380Pic;
    public Image b747Pic;
    public Image b777Pic;
    public Image a350Pic;
    public Image lambo;
    public Image car1;
    public Image backgroundPic;
    public Image bird;

   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
	private Bounce aircraft;
    public Bounce aircraft1;
    public Bounce aircraft2;
    public Bounce aircraft3;
    public Car car;
    public Car lambo1;
    public Bird bird1;




   // Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
	}


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
	public BasicGameApp() {
      setUpGraphics();
       
      //variable and objects
      //create (construct) the objects needed for the game and load up
        backgroundPic = Toolkit.getDefaultToolkit().getImage("sky+road.jpg");
		a380Pic = Toolkit.getDefaultToolkit().getImage("aqgame380.png"); //load the picture
		aircraft = new Bounce(500,50);
        a350Pic = Toolkit.getDefaultToolkit().getImage("aq350.png"); //load the picture
        aircraft1 = new Bounce(30,150);
        b747Pic = Toolkit.getDefaultToolkit().getImage("747.png"); //load the picture
        aircraft2 = new Bounce(110,300);
        b777Pic = Toolkit.getDefaultToolkit().getImage("2aq777.png"); //load the picture
        aircraft3 = new Bounce(700,200);
        car1 = Toolkit.getDefaultToolkit().getImage("car1.png"); //load the picture
        car = new Car(0,600);
        lambo = Toolkit.getDefaultToolkit().getImage("lambo.png"); //load the picture
        lambo1 = new Car(300,600);
        bird = Toolkit.getDefaultToolkit().getImage("bird.png");
        bird1 = new Bird(300,470);






	}// BasicGameApp()

   
//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
	public void run() {

      //for the moment we will loop things forever.
		while (true) {

         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(0); // sleep for 10 ms
		}
	}

	public void moveThings() {
      //calls the move( ) code in the objects
		aircraft.move();
        aircraft1.move();
        aircraft2.move();
        aircraft3.move();
        lambo1.move();
        car.move();
        crashing();
        bird1.move();

	}
    public void crashing(){
        if(car.hitbox.intersects(aircraft.bound)){
            System.out.println("crash");
            car.dx=-car.dx;
            car.dy=-car.dy;
        }
        if(lambo1.hitbox.intersects(aircraft2.bound)){
            System.out.println("KABOOM!");
            lambo1.ypos = lambo1.ypos-1;
            //lambo1.height1 = lambo1.height1-1;
            //lambo1.width1 = lambo1.width1 - 1;

        }

    }

	
   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
   
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();  
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
   
      panel.add(canvas);  // adds the canvas to the panel.
   
      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
      
      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
      System.out.println("DONE graphic setup");
   
   }


	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);

      //draw the image of the aircraft
        g.drawImage(backgroundPic, 0, 0, 1000, 700, null);
		g.drawImage(a380Pic, aircraft.xpos, aircraft.ypos, aircraft.width, aircraft.height, null);
        g.drawImage(a350Pic, aircraft1.xpos, aircraft1.ypos, aircraft1.width, aircraft1.height, null);
        g.drawImage(b777Pic, aircraft2.xpos, aircraft2.ypos, aircraft2.width, aircraft2.height, null);
        g.drawImage(b747Pic, aircraft3.xpos, aircraft3.ypos, aircraft3.width, aircraft3.height, null);
        g.drawImage(lambo, lambo1.xpos, lambo1.ypos, lambo1.width1, lambo1.height1, null);
        g.drawImage(car1, car.xpos, car.ypos, car.width1, car.height1, null);
        g.drawRect(car.hitbox.x,car.hitbox.y, car.hitbox.width,car.hitbox.height);
        g.drawRect(lambo1.hitbox.x,lambo1.hitbox.y, lambo1.hitbox.width,lambo1.hitbox.height);
        g.drawRect(aircraft.bound.x,aircraft.bound.y,aircraft.bound.width,aircraft.bound.height);
        g.drawRect(aircraft2.bound.x,aircraft2.bound.y, aircraft2.bound.width,aircraft2.bound.height);
        g.drawImage(bird, bird1.xpos, bird1.ypos, bird1.width2, bird1.height2, null);
        g.drawRect(bird1.perimeter.x,bird1.perimeter.y, bird1.perimeter.width,bird1.perimeter.height);
        g.dispose();

		bufferStrategy.show();
	}
}