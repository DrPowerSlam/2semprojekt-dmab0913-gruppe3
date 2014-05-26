package testlayer;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * A simple skeleton to show the picture, might get deleted in the final version of the program
 * @author Gruppe 3
 */
public class Skeleton extends JFrame {

    public Skeleton() {

        initUI();
    }

    private void initUI() {

        setTitle("Simple Java 2D example");

        add(new Surface());

        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                Skeleton sk = new Skeleton();
                sk.setVisible(true);
            }
        });
    }
}