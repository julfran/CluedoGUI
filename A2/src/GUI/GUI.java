package GUI;

import javax.swing.*;
import java.awt.event.ActionListener;


public abstract class GUI {

    public GUI() {
        initialise();
    }

    private void initialise(){

        //BUTTONS
        //---------------------------------------------------------------------------------
        //Start Game Button
        JButton start = new JButton("Start");
        start.addActionListener(new ActionListener(){

        });
        //---------------------------------------------------------------------------------
        //Move Button
        JButton start = new JButton("Move");
        move.addActionListener(new ActionListener(){

        });
        //---------------------------------------------------------------------------------
        //Suggest Button
        JButton suggest = new JButton("Suggest");
        suggest.addActionListener(new ActionListener(){

        });
        //---------------------------------------------------------------------------------
        //Accuse Button
        JButton accuse = new JButton("Accuse");
        accuse.addActionListener(new ActionListener(){

        });
        //---------------------------------------------------------------------------------
        //End Turn Button
        JButton end = new JButton("End");
        end.addActionListener(new ActionListener(){

        });
        //---------------------------------------------------------------------------------
        //Quit Button
        JButton quit = new JButton("Quit");
        quit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                System.exit(0);
            }
        });
        //---------------------------------------------------------------------------------

        //LAYOUT




    }

}
