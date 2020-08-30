package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;

public abstract class GUI {
	
	private JFrame frame;
	private JPanel topLeftButtons;
	//private GraphicsPane rightGraphicsPane;
	//private JComponent drawing;
	private JComponent rightGraphicsPane;
	
	public GUI() {
		initialise();
	}
  
	private void initialise(){
		
	//=================================================================================
    //BUTTON METHODS
	//=================================================================================
		
    //---------------------------------------------------------------------------------
    //Start Game Button	  
    JButton start = new JButton("Start");
    start.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	    
    });
    //---------------------------------------------------------------------------------
    //Move Button	  
    JButton move = new JButton("Move");
    move.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	    
    });	  
    //---------------------------------------------------------------------------------	  
    //Suggest Button  
    JButton suggest = new JButton("Suggest");
    suggest.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	    
    });
    //---------------------------------------------------------------------------------  
    //Accuse Button	  
    JButton accuse = new JButton("Accuse");
    accuse.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	    
    });
    //---------------------------------------------------------------------------------
    //End Turn Button	  
    JButton end = new JButton("End");
    end.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	    
    });	  
    //---------------------------------------------------------------------------------	  
    //Quit Button	  
    JButton quit = new JButton("Quit");
    	quit.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.exit(0); //quit game
    		}
    });
    //---------------------------------------------------------------------------------
	  
    //=================================================================================
    //WINDOW LAYOUT
    //=================================================================================
    	
    //Create grid layout
    topLeftButtons = new JPanel(new GridBagLayout());
    //leftWindow = new JPanel(new GridBagLayout());
    GridBagConstraints c1 = new GridBagConstraints();
    GridBagConstraints c2 = new GridBagConstraints();
    
    //Set border
    Border border = BorderFactory.createEmptyBorder(5,5,5,5);
    topLeftButtons.setBorder(border);
    
    //---------------------------------------------------------------------------------
    //BUTTONS
    //---------------------------------------------------------------------------------
    
    //start & end button group
    JPanel globalButtons = new JPanel(new GridBagLayout());
	
	c1.fill = GridBagConstraints.HORIZONTAL;
	c1.gridx = 0;
	c1.gridy = 0;
	globalButtons.add(start,c1);
	
	c1.fill = GridBagConstraints.HORIZONTAL;
	c1.gridx = 0;
	c1.gridy = 1;
	globalButtons.add(quit,c1);
	
	topLeftButtons.add(globalButtons,c1);
	globalButtons.add(Box.createRigidArea(new Dimension(20, 0)));
	
	//move, end turn, suggest, accuse button group
	JPanel actionButtons = new JPanel(new GridBagLayout());
	
	c2.fill = GridBagConstraints.HORIZONTAL;
	c2.gridx = 0;
	c2.gridy = 0;
	actionButtons.add(move,c2);
	
	c2.fill = GridBagConstraints.HORIZONTAL;
	c2.gridx = 1;
	c2.gridy = 0;
	actionButtons.add(end,c2);
	
	c2.fill = GridBagConstraints.HORIZONTAL;
	c2.gridx = 0;
	c2.gridy = 1;
	actionButtons.add(suggest,c2);
	
	c2.fill = GridBagConstraints.HORIZONTAL;
	c2.gridx = 1;
	c2.gridy = 1;
	actionButtons.add(accuse,c2);
	topLeftButtons.add(actionButtons,c2);

	//---------------------------------------------------------------------------------
	//GRAPHICS AREA
	//---------------------------------------------------------------------------------
	
	rightGraphicsPane = new JComponent() {
		protected void paintComponent(Graphics g) {
			redraw(g);
		}
	};
	rightGraphicsPane.setPreferredSize(new Dimension(500,500));
	rightGraphicsPane.setVisible(true);

	//---------------------------------------------------------------------------------
	//FRAME
	//---------------------------------------------------------------------------------
	
	//Set up the content pane.
	frame = new JFrame("Window");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLocation(20, 20); //window creation location
	JSplitPane LeftSplitPanes = new JSplitPane(JSplitPane.VERTICAL_SPLIT,topLeftButtons, new TextPane()); //Split left pane into buttons and text area
	JSplitPane mainPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, LeftSplitPanes, rightGraphicsPane); //split left pane from right graphics area
	frame.add(mainPane,BorderLayout.NORTH);
    frame.pack();
    frame.setSize(new Dimension(870,660));
    frame.setVisible(true);
	  
    }

	protected abstract void redraw();

	protected void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	protected void redraw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
  
}
