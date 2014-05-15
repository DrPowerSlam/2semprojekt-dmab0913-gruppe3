package guilayer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import controllayer.BreederCtr;
import modellayer.Settings;

/**
 * @author Gruppe 3
 */
public class MainWindow extends JFrame {

	private Settings settings;
	
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
		if(settings.getBreeder() == null) {
			initLoginPane();
		} else {
			initTabbedPane();
		}
	}
	
	public void refreshMainWindow() throws SQLException {
		initFrame();
		initContentPane();
		initMenuBar();
		if(settings.getBreeder() == null) {
			initLoginPane();
		} else {
			initTabbedPane();
		}
		repaint();
	}
	

	/**
	 * initialize the frame
	 */
	private void initFrame() {
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
		if(settings.getBreeder() != null) {
			//JMenu menu = new JMenu("Logget ind som " + settings.getBreeder().getFname() + " " + settings.getBreeder().getLname());
			BreederCtr breederCtr = new BreederCtr(); 
			JMenu menu = new JMenu("Logget ind som " + breederCtr.getInitials(settings.getBreeder()));
			menuBar.add(menu);
			
			JMenuItem menuItem = new JMenuItem(new AbstractAction("Logud") {
			    public void actionPerformed(ActionEvent e) {
			        BreederCtr breederCtr = new BreederCtr();
			        breederCtr.logOut();
			        try {
						refreshMainWindow();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    }
			});
			menu.add(menuItem);
		}
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
		
		if(settings.getBreeder() != null && settings.getBreeder().isAdmin()) {
			JPanel breederPanel = new BreederPanel();
			tabbedPane.addTab("Avlere", null, breederPanel, null);
			
			JPanel makeCompendiumPanel = new MakeCompendiumPanel();
			tabbedPane.addTab("Saml kompendie", null, makeCompendiumPanel, null);
		} else {
			JPanel breederPanel = new BreederPanel();
			tabbedPane.addTab("Avler", null, breederPanel, null);
		}
	}
	
	public boolean equals(Object o) {
		return true;
	}

}
