package david_nour.arcanoid;

public class FpsCounter extends Thread{
	private double previousTime;
	private double fps;
	
	public void run() {
		while(!Model.gameOver) {
			while(!Model.paused) {
				previousTime = System.nanoTime();
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					// TODO: handle exception
				}
				fps = (1_000_000_000/ (System.nanoTime() - previousTime));
				previousTime = System.nanoTime();
			}
		}
		
		
	}
	
	public int getFps() {
		return (int) fps;
	}
}
