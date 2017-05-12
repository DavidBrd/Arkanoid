package david_nour.arcanoid;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
	
public class Controller2 implements KeyListener {
	private Model model;
	private View view;
	private final float acceleration = 8;
	
	public Controller2(Model model, View view) {
		this.model = model;
		this.view = view;
		this.view.getFrame().addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
//		if(e.getKeyChar() == 'p') {
//			if(!Model.paused2) {
//				Model.paused2 = true;
//			} else {
//				State state = new State();
//				state.start();
//				Model.paused2 = false;
//			}
//		}
				
		if(e.getKeyChar() == 'm') {				
			model.getRacket2().moveRight();
		}

		if(e.getKeyChar() == 'k') {
		model.getRacket2().moveLeft();
		}
				
	}
			
	
	@Override
	public void keyReleased(KeyEvent e) {
					
		
		if(e.getKeyChar() == 'm' && (model.getRacket2().getSpeed() != 0)) {
			model.getRacket2().setSpeed(0);			
		}
		
		if(e.getKeyChar() == 'k' && (model.getRacket2().getSpeed() != 0)) {
			model.getRacket2().setSpeed(0);
		}
		
	}

	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	
}
