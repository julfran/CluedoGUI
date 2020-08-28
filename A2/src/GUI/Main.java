import java.awt.*;
import javax.swing.*;


public class Main extends GUI{

	public static void main(String[] args) {
		new Main();
	}

	@Override
	protected void draw(Graphics g) {
		// TODO Auto-generated method stub
		 g.setColor(Color.YELLOW);
	        g.fillOval(500, 500, 2230, 2230);
	        g.setColor(Color.BLACK);
	        g.drawOval(0, 0, 50, 50);

	        g.drawLine(20, 10, 20, 20);
	        g.drawLine(30, 10, 30, 20);

	        g.drawArc(15, 15, 20, 20, 180, 180);


	        g.drawString("Drawing with swing!", 10, 100);
	        
	        System.out.println("drawing");
	}
	
	@Override
	protected void redraw(Graphics g) {
		draw(g);
	}

	@Override
	protected void redraw() {
		// TODO Auto-generated method stub
		
	}
	
}
