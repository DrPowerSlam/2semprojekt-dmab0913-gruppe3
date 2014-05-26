package testlayer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import modellayer.Breeder;
import modellayer.Queen;
import controllayer.*;

/**
 * A class to draw and save a pedigree for a Queen.
 * @author Gruppe 3
 */
class Surface extends JPanel {
	private Queen q = new modellayer.Queen();
	private BreederCtr breederCtr = new BreederCtr();

    private void doDrawing(Graphics g) {
    	
    	//Create a buffered image with size 1000x1000 and the type of an RGB image
    	BufferedImage bi = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
    	
    	//sets an arbitrary queen with ID 100.
    	setQueenData(100);
    	
    	//the coordinates for the main queen
    	int rectX = 400;
    	int rectY = 700;

        //Graphics2D g2d = (Graphics2D) g;
    	
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
        
        //Draws a rectangle for the mother and draws a line between the main queen and the mother
        g2d.drawRect(300, 575, 200, 50);
        g2d.drawLine(500, 700, 400, 625);
        
        //Draws a rectangle for the fathers mother and draws a dotted line (to denote that it's a drone line)
        //between the main queen and the fathers mother
        g2d.drawRect(500, 575, 200, 50);
        dottedLine(500, 700, 600, 625, g2d);
        
        g2d.drawLine(200, 575, 100, 500);
        dottedLine(200, 575, 300, 500, g2d);
        
        
        //Tries to save the image in the "PNG" format
        try {
			ImageIO.write(bi, "PNG", new File("TestImage.PNG"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
    
    /**
     * Creates an arbitrary queen with a given ID.
     * This method will be unnecessary when the database has test data
     * @param queenID The ID for the queen
     */
    private void setQueenData(int queenID)
    {    	
    	Breeder b = breederCtr.getAllBreeders().get(1);
    	q.setQueenID(queenID);
    	q.setBreeder(b);
    	q.setName("XXX");
    	q.setYear(1998);
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