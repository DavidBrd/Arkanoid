package david_nour.arcanoid;

public class Model {
	private Brick[] bricks;
	private Ball ball;
	private Racket racket;
	private int score;
	private int ballNumber;
	
	public Model(Racket racket) {
		
	}
	
	public Model(int frameWidth, int frameHeight) {
		float racketWidth = (0.15f * frameWidth);
		float racketHeight = (0.02f * frameHeight);
		System.out.println((int) racketWidth+"  "+ (int) racketHeight);
		//this.racket = new Racket(frameWidth, frameHeight, (int) racketWidth, (int) racketHeight);
		this.racket = new Racket(frameWidth, frameHeight, 120, 150);
		
		//this.racket = new Racket(frameWidth, frameHeight, (int) (Math.floor(0.15*frameWidth)) ,(int) (Math.floor(0.02*frameHeight)));
		//this.racket = new Racket(frameWidth, frameHeight, (int)(0.15*800), (int)(0.02*600));
		//this.racket = new Racket(frameWidth, frameHeight, (int)(Math.floor(0)120), (int)(60));
		//this.racket = new Racket(400,300,150,50);
		System.out.println("Model.frameWidth = " + frameWidth+"BLABLA = " +(int) (0.15*frameWidth));
		System.out.println("Model.frameHeight = " + frameHeight);
	}
	
	public Racket getRacket() {return this.racket;}
	
	
	
	
	
}
