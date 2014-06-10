package testlayer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import modellayer.Breeder;
import modellayer.Queen;
import controllayer.*;
import dblayer.DBConnection;

/**
 * A class to draw and save a pedigree for a Queen.
 * @author Gruppe 3
 */
class Surface extends JPanel {
	private QueenCtr queenCtr = new QueenCtr();
	
	private int queenID = 1;

    private void doDrawing(Graphics g) {
    	
    	/*DBConnection dbCon = dblayer.DBConnection.getInstance();
    	
    	try {
			dbCon.insertDatabaseData();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
    	
    	try{
	    	//Create a buffered image with size 1000x1000 and the type of an RGB image
	    	BufferedImage bi = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
	    	
	    	Queen q = queenCtr.getAllQueens().get(queenID);
	    	
	    	//the coordinates for the main queen
	    	int rectX = 400;
	    	int rectY = 700;
	    	
	    	//creates a canvas surface to draw on
	    	Graphics2D g2d = bi.createGraphics();
	    	
	    	//Sets the pixels to black
	    	g2d.setPaint(Color.black);
	    	//sets the background to white
	        g2d.setBackground(Color.white);
	        
	        //Draws a rectangle at rectX, rectY with the size 200x50
	        g2d.drawRect(rectX, rectY, 200, 50);
	        //Draws strings inside the rectangle, using data from the main queen
	        g2d.drawString("Navn: " + q.getName(), rectX + 5, rectY + 12);
	        g2d.drawString("ID: " +Integer.toString(q.getQueenID()), rectX + 5, rectY + 24);
	        g2d.drawString("Avler: " + q.getBreeder().getFname() + " " + q.getBreeder().getLname(), rectX + 5, rectY + 36);
	        
	        System.out.println(q.getMother().getBreeder().getFname());
	        
	        //Draws a rectangle for the mother and draws a line between the main queen and the mother
	        int motherRectX = 300;
	        int motherRectY = 575;
	        
	        g2d.drawRect(motherRectX, motherRectY, 200, 50);
	        
	        //draws the strings for info about the mother
	        
	        g2d.drawString("Navn: " + q.getMother().getName(), motherRectX + 5, motherRectY + 12);
	        g2d.drawString("ID: " + Integer.toString(q.getMother().getQueenID()), motherRectX + 5, motherRectY + 24);
	        g2d.drawString("Avler: " + q.getMother().getBreeder().getFname() + " " + q.getMother().getBreeder().getLname(), motherRectX + 5, motherRectY + 36);
	        
	        g2d.drawLine(500, 700, 400, 625);
	        
	        //draw the rectangle for the mother's mother, and the line to show relations
	        int grandMotherRectX = 100;
	        int grandMotherRectY = 450;
	        g2d.drawRect(grandMotherRectX, grandMotherRectY, 200, 50);
	        
	        g2d.drawString("Navn: " + q.getMother().getMother().getName(), grandMotherRectX + 5, grandMotherRectY + 12);
	        g2d.drawString("ID: " + Integer.toString(q.getMother().getMother().getQueenID()), grandMotherRectX + 5, grandMotherRectY + 24);
	        g2d.drawString("Avler: " + q.getMother().getMother().getBreeder().getFname() + " " + q.getMother().getMother().getBreeder().getLname(), grandMotherRectX + 5, grandMotherRectY + 36);
	        
	        
	        g2d.drawLine(300, 575, 200, 500);
	        
	        //draw the rectangle for the mother's father's mother, and the dotted line to show the relation
	        int motherFatherMotherRectX = 300;
	        int motherFatherMotherRectY = 450;
	        g2d.drawRect(motherFatherMotherRectX, motherFatherMotherRectY, 200, 50);
	        
	        g2d.drawString("Navn: " + q.getMother().getFathersMother().getName(), motherFatherMotherRectX + 5, motherFatherMotherRectY + 12);
	        g2d.drawString("ID: " + Integer.toString(q.getMother().getFathersMother().getQueenID()), motherFatherMotherRectX + 5, motherFatherMotherRectY + 24);
	        g2d.drawString("Avler: " + q.getMother().getFathersMother().getBreeder().getFname() + " " + q.getMother().getFathersMother().getBreeder().getLname(), motherFatherMotherRectX + 5, motherFatherMotherRectY + 36);
	        
	        
	        dottedLine(300, 575, 400, 500, g2d);
	        
	        //Draws a rectangle for the father's mother and draws a dotted line (to denote that it's a drone line)
	        //between the main queen and the fathers mother
	        int fatherRectX = 500;
	        int fatherRectY = 575;
	        g2d.drawRect(fatherRectX, fatherRectY, 200, 50);
	        
	        g2d.drawString("Navn: " + q.getFathersMother().getName(), fatherRectX + 5, fatherRectY + 12);
	        g2d.drawString("ID: " + Integer.toString(q.getFathersMother().getQueenID()), fatherRectX + 5, fatherRectY + 24);
	        g2d.drawString("Avler: " + q.getFathersMother().getBreeder().getFname() + " " + q.getFathersMother().getBreeder().getLname(), fatherRectX + 5, fatherRectY + 36);
	        
	        dottedLine(500, 700, 600, 625, g2d);
	        
	        
	        //the father's father's mother
	        int fatherGrandMotherRectX = 500;
	        int fatherGrandMotherRectY = 450;
	        g2d.drawRect(fatherGrandMotherRectX, fatherGrandMotherRectY, 200, 50);
	        
	        /*g2d.drawString("Navn: " + q.getFathersMother().getMother().getName(), fatherGrandMotherRectX + 5, fatherGrandMotherRectY + 12);
	        g2d.drawString("ID: " + Integer.toString(q.getFathersMother().getMother().getQueenID()), fatherGrandMotherRectX + 5, fatherGrandMotherRectY + 24);
	        g2d.drawString("Avler: " + q.getFathersMother().getMother().getBreeder().getFname() + " " + q.getFathersMother().getMother().getBreeder().getLname(), fatherGrandMotherRectX + 5, fatherGrandMotherRectY + 36);
	        */
	        
	        g2d.drawLine(700, 575, 600, 500);
	        
	       
	        //The father's mother's mother
	        int fatherFatherMotherRectX = 700;
	        int fatherFatherMotherRectY = 450;
	        g2d.drawRect(700, 450, 200, 50);
	        
	        /*g2d.drawString("Navn: " + q.getFathersMother().getFathersMother().getName(), fatherFatherMotherRectX + 5, fatherFatherMotherRectY + 12);
	        g2d.drawString("ID: " + Integer.toString(q.getFathersMother().getFathersMother().getQueenID()), fatherFatherMotherRectX + 5, fatherFatherMotherRectY + 24);
	        g2d.drawString("Avler: " + q.getFathersMother().getFathersMother().getBreeder().getFname() + " " + q.getFathersMother().getFathersMother().getBreeder().getLname(), fatherFatherMotherRectX + 5, fatherFatherMotherRectY + 36);
	        */
	        
	        dottedLine(700, 575, 800, 500, g2d);
	        
	        //Tries to save the image in the "PNG" format
	        try {
				ImageIO.write(bi, "PNG", new File("TestImage.PNG"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	} catch (NullPointerException e) {
    		e.printStackTrace();
    	}
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
    
    public void setQueenID(int ID) {
    	queenID = ID;
    }
    
    
    
    /**
     * A method to create a dotted line
     * 
     * @param xStart The start of the line on the X axis
     * @param yStart The start of the line on the Y axis
     * @param xEnd The end of the line on the X axis
     * @param yEnd The end of the line on the Y axis
     * @param g The 2D canvas to paint on
     */
    private void dottedLine(float xStart, float yStart, float xEnd, float yEnd, Graphics2D g)
    {
    	float deltaX, deltaY, length, x, y;
    	int i;
    	
    	//Calculates the difference between start and end, and gets the absolute value (only numbers above zero)
    	deltaX = Math.abs(xEnd - xStart);
    	deltaY = Math.abs(yEnd - yStart);
    	
    	//sets the length to the biggest difference
    	if (deltaX <= deltaY)
    	{
    		length = deltaX;
    	}
    	else
    	{
    		length = deltaY;
    	}
    	
    	//Divides the difference between start and end and divides it by the length to
    	//get an incremental ratio
    	deltaX = (xEnd - xStart)/length;
    	deltaY = (yEnd - yStart)/length;
    	
    	//adds 0.5 to the start of the line
    	x = xStart + 0.5f;
    	y = yStart + 0.5f;
    	//draws a pixel at the beginning and end of the line
    	drawPixel(xStart, yStart, g);
    	drawPixel(xEnd, yEnd, g);
    	
    	//loops until the end of the line
    	for (i = 0; i <= length; i++)
    	{
    		//draws nothing if i mod 9 is less than 2
    		if(i%9<2)
    		{
    			
    		}
    		//draws a pixel if i mod 9 is less than 7
    		else if (i%9<7)
    		{
    			drawPixel(x, y, g);
    		}
    		
    		//increases the x and y coordinate with the incremental ratio
    		x += deltaX;
    		y += deltaY;
    	}
    }
    
    /**
     * A method to draw a pixel with given floats
     * @param x x-coordinate
     * @param y y-coordinate
     * @param g 2D canvas to draw on
     */
    private void drawPixel(float x, float y, Graphics2D g)
    {
    	//Rounds X and Y so they can be used as integers
    	x = Math.round(x);
    	y = Math.round(y);
    	
    	//draws a line which is 1 high and 1 wide (so a pixel)
    	g.drawLine((int)x,(int)y, (int)x, (int)y);
    }
}