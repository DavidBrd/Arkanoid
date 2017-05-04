package david_nour.arcanoid;

public class Main {
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	
	public static void main(String[] args) {
		
		Model model = new Model(WIDTH, HEIGHT);
		View view = new View(model, WIDTH, HEIGHT);
		
		//Debug&Test
		Racket racket = model.getRacket();
		System.out.println("Raquette ");
		System.out.println("x :"+racket.getPositionX()+" y :"+(racket.getPositionY()) );
		
	}

}
