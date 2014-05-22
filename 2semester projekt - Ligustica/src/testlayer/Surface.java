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
    	
    	BufferedImage bi = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
    	
    	setQueenData(100);
    	
    	int rectX = 150;
    	int rectY = 350;

        //Graphics2D g2d = (Graphics2D) g;
    	Graphics2D g2d = bi.createGraphics();
    	g2d.setPaint(Color.black);
        g2d.setBackground(Color.white);
        g2d.drawRect(rectX, rectY, 200, 50);
        g2d.drawRect(100, 350, 200, 50);
        g2d.drawRect(350, 350, 200, 50);
        g2d.drawString("Navn: " + q.getName(), rectX + 5, rectY + 12);
        g2d.drawString("ID: " +Integer.toString(q.getQueenID()), rectX + 5, rectY + 24);
        g2d.drawString("Avler: " + q.getBreeder().getFname() + " " + q.getBreeder().getLname(), rectX + 5, rectY + 36);
        
        g2d.drawRect(175, 350, 200, 50);
        g2d.drawRect(325, 350, 200, 50);
        
        g2d.drawLine(175, 350, 100, 300);
        
        g2d.drawLine(325, 350, 400, 300);
        
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
}