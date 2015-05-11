import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Engine engine=null;
	private Controller controller=null;
	private GamePanel gamePanel;

	JPanel gameplace=null;
	JPanel others=null;
	JPanel up=null;
	JPanel center=null;
	JPanel down=null;
	
	JLabel title=null;
	JLabel activeplayer=null;
	JLabel putatrap=null;
	JButton putoil=null;
	JButton putslime=null;
	JLabel nextplayer=null;
	JButton end=null;
	JLabel inthebag=null;
	JLabel roundsleft=null;
	
	
	public MainWindow(int jszam) {
		
		engine = new Engine();

		engine.init(jszam);
		engine.setWindow(this);
		this.controller=engine.getController();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		setTitle("Phoebe");
		setSize(1324, 807);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
		init();
		setVisible(true);
	}
	
	private void init() {
		
		//FO PANELEK
		gameplace = new GamePanel(engine.view);
		//gameplace = new JPanel();
		gameplace.setBackground(Color.BLACK);
		gameplace.setPreferredSize(new Dimension(1024, 807));

		others = new JPanel();
		others.setBackground(new Color(189, 15, 23));
		others.setPreferredSize(new Dimension(300, 807));
		others.setLayout(new BorderLayout());
		
		this.setLayout(new BorderLayout());
		add(gameplace, BorderLayout.WEST);
		add(others, BorderLayout.EAST);
		
		//MELLEK PANELEK		
		up = new JPanel();
		up.setBackground(new Color(189, 15, 23));
		others.add(up, BorderLayout.NORTH);
		
		center= new JPanel();
		center.setBackground(new Color(189, 15, 23));
		others.add(center, BorderLayout.CENTER);
		
		down= new JPanel();
		down.setBackground(new Color(189, 15, 23));
		others.add(down, BorderLayout.SOUTH);
		
		
		//JATEKTER
		gameplace.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(controller==null)
					System.out.println("Controller null ptr");
				controller.setArrow(arg0.getX(), arg0.getY());
				System.out.println("Mouse clicked on gamepanel");
			}
		});
		
		
		//FELSO PANEL
		title = new JLabel("Kezelofelulet");
		title.setFont(new Font("Tahoma", Font.PLAIN, 34));
		up.add(title);
		
		//KOZEPSO PANEL
		activeplayer = new JLabel(engine.activePlayer.ID + ". Jatekos");
		activeplayer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		center.add(activeplayer);
		
		putatrap = new JLabel("  Csapda elhelyezese:   ");
		putatrap.setFont(new Font("Tahoma", Font.PLAIN, 20));
		center.add(putatrap);
		
		putoil = new JButton("Olaj lerakasa");
		putoil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		putoil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.putOil();
				updateLabels();
			}
		});
		center.add(putoil);
		
		putslime = new JButton("Ragacs lerakasa");
		putslime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		putslime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.putSlime();
				updateLabels();
			}
		});
		center.add(putslime);
		
		nextplayer = new JLabel("Kovetkezo jatekos:");
		nextplayer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		center.add(nextplayer);
		
		end = new JButton("Kor vege");
		end.setFont(new Font("Tahoma", Font.PLAIN, 16));
		end.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.turnPassed();
				updateLabels();
			}
		});
		center.add(end);
		
		inthebag = new JLabel("Meg " + engine.activePlayer.getOil_num() +" olajod es " + engine.activePlayer.getSlime_num() + " ragacsod van.");
		inthebag.setFont(new Font("Tahoma", Font.PLAIN, 16));
		center.add(inthebag);
		
		//ALSO PANEL
		roundsleft = new JLabel("Hatralevo korok szama: " + engine.getRound_num());
		roundsleft.setFont(new Font("Tahoma", Font.PLAIN, 16));
		down.add(roundsleft);
		
		
	}//init vege
	
	
	
	 public void setController(Controller x){
		controller=x;
	}
	
	public Engine getEngine(){
		return engine;
	}
	
	public GamePanel getGamePanel(){
		return gamePanel;
	}
	
	public void init(Engine engine){
		this.engine=engine;
		this.controller=engine.getController();
	}
	
	public void updateLabels(){
		activeplayer.setText(engine.activePlayer.ID + ". Jatekos");
		inthebag.setText("Meg " + engine.activePlayer.getOil_num() +" olajod es " + engine.activePlayer.getSlime_num() + " ragacsod van.");
		roundsleft.setText("Hatralevo korok szama: " + engine.getRound_num());
	}
	 
	
}













