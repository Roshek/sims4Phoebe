
public class TempMain {

	public static void main(String[] args) {
		Engine engine=new Engine();
		engine.init(1);
		MainWindow window=new MainWindow(engine);
		window.setVisible(true);
	}

}
