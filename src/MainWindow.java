import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MainWindow extends JFrame {
	
	private Engine engine=null;
	private Controller controller=null;
	private GamePanel gamePanel;
	
	public MainWindow(int jszam) {
		
		engine = new Engine();
		this.engine=engine;
		engine.init(jszam);
		engine.setWindow(this);
		this.controller=engine.getController();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		getContentPane().setLayout(null);
		
		gamePanel = new GamePanel(engine.view);
		gamePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(controller==null)
					System.out.println("Controller null ptr");
				controller.setArrow(arg0.getX(), arg0.getY());
			}
		});
		gamePanel.setBounds(0, 0, 806, 498);
		getContentPane().add(gamePanel);
		
		JLabel lblNewLabel = new JLabel("X.kor (30-X van hatra))");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(807, 11, 162, 31);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(817, 40, 152, 129);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblElsJtkos = new JLabel("Aktiv jatekos");
		lblElsJtkos.setBounds(10, 11, 71, 14);
		panel.add(lblElsJtkos);
		
		JButton btnNewButton = new JButton("Olaj lerak");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.putOil();
			}
		});
		btnNewButton.setBounds(10, 25, 132, 23);
		panel.add(btnNewButton);
		
		JButton btnRagacsLerak = new JButton("Ragacs lerak");
		btnRagacsLerak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.putSlime();
			}
		});
		btnRagacsLerak.setBounds(10, 59, 132, 23);
		panel.add(btnRagacsLerak);
		
		JButton btnKrVge = new JButton("Kor vege");
		btnKrVge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.turnPassed();
			}
		});
		btnKrVge.setBounds(10, 93, 132, 23);
		panel.add(btnKrVge);
	}
	
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
