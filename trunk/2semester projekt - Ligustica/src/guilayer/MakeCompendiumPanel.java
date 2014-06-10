package guilayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;
import controllayer.CompendiumCtr;
import dblayer.*;
import modellayer.*;

public class MakeCompendiumPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public MakeCompendiumPanel() {
		setLayout(null);
		
		JButton btnLavKompendie = new JButton("Lav kompendie");
		btnLavKompendie.setBounds(91, 91, 120, 23);
		btnLavKompendie.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CompendiumCtr compCtr = new CompendiumCtr();
				//IFDBCompendium
				//compCtr.createPDF(compendium);
			}
			
		});
		add(btnLavKompendie);

	}
}
