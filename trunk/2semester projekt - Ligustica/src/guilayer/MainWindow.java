package guilayer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;

import modellayer.Settings;

/**
 * @author Gruppe 3
 */
public class MainWindow extends JFrame {

	private Settings settings;
	private String title = "Ligustica ";
	private int widht = 850;
	private int height = 525;
	
	private static final long serialVersionUID = 7596122900802093283L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() throws SQLException {
		settings = Settings.getInstance();
		initFrame();
		initContentPane();
		initMenuBar();
		initLoginPane();
		//initTabbedPane();
	}
	

	/**
	 * initialize the frame
	 */
	private void initFrame() {
		ImageIcon img = new ImageIcon("/resources/logo.png");
		setIconImage(img.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)d.getWidth()/4, (int)d.getHeight()/5);
		setTitle(settings.getTitle());
		setSize(settings.getWidth(), settings.getHeight());
		setResizable(false);
		setVisible(true);
	}

	/**
	 * initialize the content
	 */
	private void initContentPane() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	/**
	 * initialize the MenuBar
	 */
	private void initMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
	}
	
	private void initLoginPane() {
		JPanel loginPanel = new LoginPanel();
		contentPane.add(loginPanel);
		
	}

	/**
	 * initialize the Tab
	 * @throws SQLException 
	 */
	private void initTabbedPane() throws SQLException {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel chartPanel = new ChartPanel();
		tabbedPane.addTab("Indberetning af skema", null, chartPanel, null);

		JPanel queenPanel = new QueenPanel();
		tabbedPane.addTab("Dronninger", null, queenPanel, null);
		
		JPanel breederPanel = new BreederPanel();
		tabbedPane.addTab("Avler", null, breederPanel, null);
	}
	
	public boolean equals(Object o) {
		return true;
	}

}
