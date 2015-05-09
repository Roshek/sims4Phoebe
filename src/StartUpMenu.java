import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class StartUpMenu extends JFrame {
	

	private static final long serialVersionUID = 1L;
	JPanel panel;
	JButton indit;
	JButton kilep;
	JComboBox jatekosszam;
	
		
		
	public StartUpMenu() {
		setTitle("Phoebe");
		setSize(500, 200);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		init();
		setVisible(true);
	}
	
	
	private void init() {
		panel = new JPanel();
		panel.setBackground(Color.RED);
		panel.setLayout(new FlowLayout());
		this.add(panel);
		
		String[] str = {"2", "3", "4"};
		jatekosszam = new JComboBox(str);
		jatekosszam.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//TODO
					}});
		panel.add(jatekosszam);
		
		indit = new JButton("Start");
		indit.setPreferredSize(new Dimension(200, 150));
		indit.setBorderPainted(false);
		indit.setBackground(Color.RED);
		indit.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					newgame();
				}});
		panel.add(indit);
		
		
		kilep = new JButton("Exit");
		kilep.setPreferredSize(new Dimension(200, 150));
		kilep.setBorderPainted(false);
		kilep.setBackground(Color.RED);
		kilep.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}});
		panel.add(kilep);	
		
		
	}

	
	private void newgame() {
		
		
	}
	
}