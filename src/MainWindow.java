import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
	JLabel putatrap=null;
	JButton putoil=null;
	JButton putslime=null;
	JLabel nextplayer=null;
	JButton end=null;
	JLabel inthebag=null;
	JLabel roundsleft=null;
	
	
	public MainWindow(int jszam) {
		
		engine = new Engine();
		this.engine=engine;
		engine.init(jszam);
		engine.setWindow(this);
		this.controller=engine.getController();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		setTitle("Phoebe");
		setSize(1324, 768);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
		init();
		setVisible(true);
	}
	
	private void init() {
		
		//PANELEK
		gameplace = new GamePanel(engine.view);
		//gameplace = new JPanel();
		gameplace.setBackground(Color.BLACK);
		gameplace.setPreferredSize(new Dimension(1024, 768));

		others = new JPanel();
		others.setBackground(Color.RED);
		others.setPreferredSize(new Dimension(300, 768));
		
		this.setLayout(new BorderLayout());
		add(gameplace, BorderLayout.WEST);
		add(others, BorderLayout.EAST);
		
		//JATEKTER
		gameplace.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(controller==null)
					System.out.println("Controller null ptr");
				controller.setArrow(arg0.getX(), arg0.getY());
			}
		});
		
		
		//FELSO PANEL
		title = new JLabel("Kezelõfelület");
		title.setFont(new Font("Tahoma", Font.PLAIN, 34));
		others.add(title);
		
		//KOZEPSO PANEL
		putatrap = new JLabel("Csapda elhelyezése:");
		putatrap.setFont(new Font("Tahoma", Font.PLAIN, 20));
		others.add(putatrap);
		
		putoil = new JButton("Olaj lerakása");
		putoil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		putoil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.putOil();
			}
		});
		
		others.add(putoil);
		
		putslime = new JButton("Ragacs lerakása");
		putslime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		putslime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.putSlime();
			}
		});
		
		others.add(putslime);
		
		nextplayer = new JLabel("Következõ játékos:");
		nextplayer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		others.add(nextplayer);
		
		end = new JButton("Kör vége");
		end.setFont(new Font("Tahoma", Font.PLAIN, 16));
		end.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.turnPassed();
			}
		});
		 
		others.add(end);
		
		//ALSO PANEL
		inthebag = new JLabel("Még " + "X" +" olajod és " + "Y" + " ragacsod van.");
		inthebag.setFont(new Font("Tahoma", Font.PLAIN, 16));
		others.add(inthebag);
		
		roundsleft = new JLabel("Hátralévõ körök száma: Z");
		roundsleft.setFont(new Font("Tahoma", Font.PLAIN, 16));
		others.add(roundsleft);
		
		
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
	 
	
}













