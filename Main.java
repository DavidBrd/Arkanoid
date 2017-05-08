package david_nour.arcanoid;

public class Main {
		
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
		
	public static final int RACKET_WIDTH = 110;//(int) (0.15f * WIDTH);
	public static final int RACKET_HEIGHT = 15;//(int) (0.025f * HEIGHT);
	public static final int OFFSET = 50;
	
	public static final int BALL_RADIUS = 15;
	public static final double BALL_SPEED = 2f;
	public static final double BRICK_HEIGHT = 20;
	
	public static final double BRICK_WIDTH = 50;
	
	public static FpsCounter fpscounter;
	
	public static void main(String[] args) {
			
		Model model = new Model();
		View view = new View(model, WIDTH, HEIGHT);
		Controller controller = new Controller(model, view);
		Collision collisionThread = new Collision(model);
		
		fpscounter = new FpsCounter();
		
		fpscounter.start();		
		
		view.start();
		for (Ball ball : model.getBalls()) {
			ball.start();
		}	
		
		model.getRacket().start();
		collisionThread.start();		
	}

}
