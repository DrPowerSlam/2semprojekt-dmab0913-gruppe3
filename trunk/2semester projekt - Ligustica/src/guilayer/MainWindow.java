package guilayer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;

import dblayer.DBCities;

/**
 * @author Gruppe 3
 */
public class MainWindow extends JFrame {

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
		initFrame();
		initContentPane();
		initMenuBar();
		initTabbedPane();
	}
	

	/**
	 * initialize the frame
	 */
	private void initFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)d.getWidth()/4, (int)d.getHeight()/5);
		setTitle(title);
		setSize(widht, height);
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

	/**
	 * initialize the Tab
	 * @throws SQLException 
	 */
	private void initTabbedPane() throws SQLException {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
//		JPanel orderPanel = new ViewAllOrdersPanel();
//		tabbedPane.addTab("Orders", null, orderPanel, null);
//		
//		
//		JPanel newOrderPanel = new NewOrderPanel();
//		tabbedPane.addTab("New Order", null, newOrderPanel, null);
//		
//		JPanel supplierPanel = SupplierPanel.getInstance();
//		tabbedPane.addTab("Suppliers", null, supplierPanel, null);
		
	}

}
