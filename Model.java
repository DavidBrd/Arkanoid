package david_nour.arcanoid;

public class Model {
	private Brick[] bricks;
	private Ball ball;
	private Racket racket;
	private int score;
	private int ballNumber;
	private View view;
	
	public Model(Racket racket) {
		
	}
	
	public Model(int frameWidth, int frameHeight) {
		float racketWidth = (0.15f * frameWidth);
		float racketHeight = (0.02f * frameHeight);
		System.out.println((int) racketWidth+"  "+ (int) racketHeight);
		this.racket = new Racket((Main.WIDTH/2) - (Main.RACKET_WIDTH/2), (Main.HEIGHT - Main.RACKET_HEIGHT), Main.RACKET_WIDTH, Main.RACKET_HEIGHT);
		//this.racket = new Racket(440, 560, 40, 20);
	}
	
	public Racket getRacket() {return this.racket;}
	
	
	
	
	
}
