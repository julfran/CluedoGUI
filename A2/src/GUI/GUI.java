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

public abstract class GUI extends Canvas {
	
	private JFrame frame;
	private JPanel topLeftButtons;
	private JTextArea bottomLeftTextArea;
	//private GraphicsPane rightGraphicsPane;
	private JComponent drawing;
	
	private JTextField textField;
	private JPanel opening;		//for adding of players
	public ArrayList <Player> players = new ArrayList<Player>()
	
	public GUI() {
		initialise();
	}
  
	private void initialise(){
		
	//=================================================================================
    //BUTTONS	  
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
	  
    //LAYOUT
    	
    //Create grid layout
    topLeftButtons = new JPanel(new GridBagLayout());
    //leftWindow = new JPanel(new GridBagLayout());
    GridBagConstraints c1 = new GridBagConstraints();
    GridBagConstraints c2 = new GridBagConstraints();
    
    //Set border
    Border border = BorderFactory.createEmptyBorder(5,5,5,5);
    topLeftButtons.setBorder(border);

    //BUTTONS
    
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
	globalButtons.add(Box.createRigidArea(new Dimension(50, 0)));
	
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
	
	//TEXT AREA
	bottomLeftTextArea = new JTextArea(80, 80);
	bottomLeftTextArea.setLineWrap(true);
	bottomLeftTextArea.setWrapStyleWord(true); // pretty line wrap.
	bottomLeftTextArea.setEditable(false);
	JScrollPane scroll = new JScrollPane(bottomLeftTextArea);
	bottomLeftTextArea.setPreferredSize(new Dimension(250, 250));
	// these two lines make the JScrollPane always scroll to the bottom when
	// text is appended to the JTextArea.
	DefaultCaret caret = (DefaultCaret) bottomLeftTextArea.getCaret();
	caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

	/*
	 * finally, make the outer JFrame and put it all together. this is more
	 * complicated than it could be, as we put the drawing and text output
	 * components inside a JSplitPane so they can be resized by the user.
	 * the JScrollPane and the top bar are then added to the frame.
	 */

	/*JSplitPane leftSplitPanes = new JSplitPane(JSplitPane.VERTICAL_SPLIT,topLeftButtons,bottomLeftTextArea);
	leftSplitPanes.setDividerSize(5); // make the selectable area smaller
	leftSplitPanes.setContinuousLayout(true); // make the panes resize nicely
	leftSplitPanes.setResizeWeight(1); // always give extra space to drawings
	// JSplitPanes have a default border that makes an ugly row of pixels at
	// the top, remove it.
	leftSplitPanes.setBorder(BorderFactory.createEmptyBorder());
	leftSplitPanes.setTopComponent(drawing);
	leftSplitPanes.setBottomComponent(scroll);*/
	//GRAPHICS PANE
	//rightGraphicsPane = new GraphicsPane();
	//rightGraphicsPane.setBackground(new Color(255,255,255));
	//rightGraphicsPane.setBorder(border);
	//rightGraphicsPane.setSize(200, 200);
	//JPanel win = new GridLayout(2,2);
	
	drawing = new JComponent() {
		protected void paintComponent(Graphics g) {
			redraw(g);
		}
	};
	drawing.setPreferredSize(new Dimension(500,500));
	// this prevents a bug where the component won't be
	// drawn until it is resized.
	drawing.setVisible(true);

	drawing.addMouseListener(new MouseAdapter() {
		public void mouseReleased(MouseEvent e) {
			//onClick(e);
			redraw();
		}
	});

	drawing.addMouseWheelListener(new MouseAdapter() {
		public void mouseWheelMoved(MouseWheelEvent e) {
		}
	});
	
	
	//Set up the content pane.
	frame = new JFrame("Window");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLocation(50, 50); //window creation location
	//frame.setSize(200,200);
    
    //frame.add(split,2,0);
    //window.add(leftWindow,BorderLayout.WEST);
    //frame.add(leftWindow,BorderLayout.NORTH);
	JSplitPane LeftSplitPanes = new JSplitPane(JSplitPane.VERTICAL_SPLIT,topLeftButtons,bottomLeftTextArea);
	//LeftSplitPanes.add(topLeftButtons,BorderLayout.NORTH);
	//LeftSplitPanes.add(bottomLeftTextArea,BorderLayout.CENTER);
	JSplitPane mainPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, LeftSplitPanes, drawing);
	
    //frame.add(topLeftButtons,BorderLayout.NORTH);
    //frame.add(bottomLeftTextArea,BorderLayout.CENTER);
  	//frame.add(rightGraphicsPane,BorderLayout.SOUTH);
	frame.add(mainPane,BorderLayout.NORTH);
    frame.pack();
    frame.setSize(800, 600);
    frame.setMinimumSize(new Dimension(500,500));
    frame.setMaximumSize(new Dimension(500,500));
    frame.setVisible(true);
    
    //rightGraphicsPane.draw();
    
	  
  }
	
	//----------------------------------
		//Opening Frame- setup panel
		//Choose Characters and username for each player
		opening = new JPanel();
		opening.setBounds(10,80,200,30);
		frame.getContentPane().add(opening);
		String[] choices = {"MISSSCARLETT", "COLONELMUSTARD", "MRSWHITE",
								"MRGREEN", "MRSPEACOCK", "PROFESSORPLUM"};
		JLabel heading = new JLabel("CLUEDO GAME: ENTER YOUR NAME AND CHARACTER.");
		opening.add(heading);

		//Selection list of players
		//miss Scarlett radio button
		JRadioButton missScarlett = new JRadioButton("MISS SCARLETT");
		missScarlett.setBounds(70,50, 200, 200);
		opening.add(missScarlett);

		//Colonel mustard radio button
		JRadioButton colonelMustard = new JRadioButton("COLONELMUSTARD");
		colonelMustard.setBounds(70, 70,200, 30);
		opening.add(colonelMustard);

		//Mrs White radio button
		JRadioButton mrsWhite = new JRadioButton("MRSWHITE");
		mrsWhite.setBounds(70, 90, 200, 30);
		opening.add(mrsWhite);

		//Mr Green radio button
		JRadioButton mrGreen = new JRadioButton("MRGREEN");
		mrGreen.setBounds(70, 110, 200, 30);
		opening.add(mrGreen);

		//mrs Peacock radio button
		JRadioButton mrsPeacock = new JRadioButton("MRSPEACOCK");
		mrsPeacock.setBounds(70,130, 200,30);
		opening.add(mrsPeacock);

		//professor plum radio button
		JRadioButton professorPlum = new JRadioButton("PROFESSORPLUM");
		professorPlum.setBounds(70,150, 200, 30);
		opening.add(professorPlum);


		//textfield to enter username
		textField = new JTextField(20);
		textField.setBounds(10,50,80,20);
		opening.add(textField);
		textField.setText("ENTER USERNAME HERE");

		//press start game button when players finish enter their username
		JButton startGame = new JButton("Start Game");
		startGame.setBounds(10,180,280,20);
		opening.add(startGame);
		startGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getRootPane().requestFocus();
				if(startGame.isEnabled()){
					opening.setVisible(false);
					//need to repaint/show board
					//set the players to board game
					//setup players sequence turns
				}
			}
		}
		);

		//add player button to create players
		JButton addPlayer = new JButton("ADD PLAYER");
		addPlayer.setBounds(10,180,280,20);
		opening.add(addPlayer);
		addPlayer.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				if(username.equals("")){
					JOptionPane.showMessageDialog(addPlayer, "NO NAME ENTERED.PLEASE ENTER A NAME:");
					addPlayer.setEnabled(false);
					return;
				}
				for(Player p: players) {
					if (p.getPlayerName().equals(textField.getText())) {
						JOptionPane.showMessageDialog(addPlayer, "USERNAME ALREADY EXIST. " +
								"PLEASE ENTER ANOTHER NAME");
						return;
					}
				}

				if(missScarlett.isEnabled() && missScarlett.isSelected()){
					missScarlett.setEnabled(false);
					players.add(new Player(CharacterType.MISSSCARLETT, username));
				}
				if(colonelMustard.isEnabled() && colonelMustard.isSelected()){
					colonelMustard.setEnabled(false);
					players.add(new Player(CharacterType.COLONELMUSTARD, username));
				}
				if(mrsWhite.isEnabled() && mrsWhite.isSelected()){
					mrsWhite.setEnabled(false);
					players.add(new Player(CharacterType.MRSWHITE, username));
				}
				if(mrsPeacock.isEnabled() && mrsPeacock.isSelected()){
					mrsPeacock.setEnabled(false);
					players.add(new Player(CharacterType.MRSPEACOCK, username));
				}
				if(professorPlum.isEnabled() && professorPlum.isSelected()){
					professorPlum.setEnabled(false);
					players.add(new Player(CharacterType.PROFESSORPLUM, username));
				}
				if(mrGreen.isEnabled() && mrGreen.isSelected()){
					mrGreen.setEnabled(false);
					players.add(new Player(CharacterType.MRGREEN, username));
				}

				if(players.size() >6 || players.size() == 0){
					startGame.setEnabled(false);
				}
				else{
					startGame.setEnabled(true);
				}
				frame.getRootPane().requestFocus();
			}

		});


	}

	protected abstract void redraw();

	protected void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	protected void redraw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
  
}
