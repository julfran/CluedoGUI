package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.io.*;

//---------------------------------------------------------------------------------
//Text Pane Class
//---------------------------------------------------------------------------------
public class TextPane extends JPanel{

	JTextArea textArea = new JTextArea();
	int vsbPolicy =  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS;
	int hsbPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER;
	JScrollPane scrollPane = new JScrollPane(textArea, vsbPolicy, hsbPolicy); 
	TextOutput textOutput = new TextOutput(textArea);
	
	public TextPane() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(0, 534));                 //text pane vertical size (horizontal is set by buttons)
		textArea.setLineWrap(true);				 //shift text to next line if full
		textArea.setWrapStyleWord(true); 		         //dont cut text in the middle of word during wrap
		textArea.setEditable(false);			         //can't edit already printed text
		DefaultCaret caret = (DefaultCaret) textArea.getCaret(); //automatically scroll down when bar is full
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		add(scrollPane);					 //add text area to panel
	        System.setOut(new PrintStream(textOutput));              //set new output stream
	}
	
}

//---------------------------------------------------------------------------------
//Text Output Class
//---------------------------------------------------------------------------------
class TextOutput extends OutputStream {

	   JTextArea textArea;
	   StringBuilder textLine = new StringBuilder();

	   public TextOutput(final JTextArea textArea) {
		   this.textArea = textArea;
	   }

	   @Override
	   public void write(int b) throws IOException {
		   
		   if (b == '\n') {
	    	  String text = textLine.toString() + "\n"; //when line break reached then break line
	    	  textArea.append(text);  
	    	  textLine.setLength(0);
	    	  return;
		   }
		   
		   textLine.append((char) b);
		   
	   }
	   
}
