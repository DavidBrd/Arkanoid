package david_nour.arcanoid;

public class Main {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final int RACKET_WIDTH = (int) (0.15f * WIDTH);
	public static final int RACKET_HEIGHT = (int) (0.025f * HEIGHT);
	public static final int OFFSET = 50;
	
	public static void main(String[] args) {
		
		
		Model model = new Model(WIDTH, HEIGHT);
		View view = new View(model, WIDTH, HEIGHT);
		Controller controller = new Controller(model, view);
		
		//Debug&Test
		Racket racket = model.getRacket();
		view.start();
		racket.start();
		System.out.println("Position Y = "+racket.getPositionY());
	}

}
