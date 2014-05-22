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

class Surface extends JPanel {
	private Queen q = new modellayer.Queen();
	private BreederCtr breederCtr = new BreederCtr();

    private void doDrawing(Graphics g) {
    	
    	BufferedImage bi = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
    	
    	setQueenData(100);
    	
    	int rectX = 400;
    	int rectY = 700;

        //Graphics2D g2d = (Graphics2D) g;
    	Graphics2D g2d = bi.createGraphics();
    	g2d.setPaint(Color.black);
        g2d.setBackground(Color.white);
        g2d.drawRect(rectX, rectY, 200, 50);
        g2d.drawString("Navn: " + q.getName(), rectX + 5, rectY + 12);
        g2d.drawString("ID: " +Integer.toString(q.getQueenID()), rectX + 5, rectY + 24);
        g2d.drawString("Avler: " + q.getBreeder().getFname() + " " + q.getBreeder().getLname(), rectX + 5, rectY + 36);
        
        g2d.drawRect(300, 575, 200, 50);
        g2d.drawLine(500, 700, 400, 625);
        
        g2d.drawRect(500, 575, 200, 50);
        dottedLine(500, 700, 600, 625, g2d);
        
        g2d.drawLine(200, 575, 100, 500);
        dottedLine(200, 575, 300, 500, g2d);
        
        
        
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
    
    private void setQueenData(int queenID)
    {    	
    	Breeder b = breederCtr.getAllBreeders().get(1);
    	q.setQueenID(queenID);
    	q.setBreeder(b);
    	q.setName("XXX");
    	q.setYear(1998);
    }
    
    private void dottedLine(float xStart, float yStart, float xEnd, float yEnd, Graphics2D g)
    {
    	float deltaX, deltaY, length, x, y;
    	int i;
    	
    	deltaX = Math.abs(xEnd - xStart);
    	deltaY = Math.abs(yEnd - yStart);
    	
    	if (deltaX <= deltaY)
    	{
    		length = deltaX;
    	}
    	else
    		length = deltaY;
    	
    	deltaX = (xEnd - xStart)/length;
    	deltaY = (yEnd - yStart)/length;
    	
    	x = xStart + 0.5f;
    	y = yStart + 0.5f;
    	drawPixel(xStart, yStart, g);
    	drawPixel(xEnd, yEnd, g);
    	
    	for (i = 0; i <= length; i++)
    	{
    		if(i%9<2)
    		{
    			
    		}
    		else if (i%9<7)
    		{
    			drawPixel(x, y, g);
    		}
    		
    		x += deltaX;
    		y += deltaY;
    	}
    }
    
    private void drawPixel(float x, float y, Graphics2D g)
    {
    	x = Math.round(x);
    	y = Math.round(y);
    	
    	g.drawLine((int)x,(int)y, (int)x, (int)y);
    }
}