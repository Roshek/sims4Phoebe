
public class TempMain {

	public static void main(String[] args) {
		Engine engine=new Engine();
		engine.init(1);
		MainWindow window=new MainWindow(engine);
		engine.setWindow(window);
		window.setVisible(true);
	}

}
