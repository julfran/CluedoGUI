package GUI;

import CluedoGame.CharacterType;
import CluedoGame.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OpeningFrame extends GUI {

    private JTextField textField;
    private JFrame openingFrame;
    private JPanel opening;		//for adding of players
    public ArrayList<Player> players = new ArrayList<Player>();

    //-----------------------------------------------
    //Opening Frame- setup panel
    //Choose Characters and username for each player
    //POPUP WINDOW

    public OpeningFrame(GUI gui){
        opening = new JPanel();
        opening.setBounds(50,80,200,30);
        openingFrame.getContentPane().add(opening);
        openingFrame.pack();
        String[] choices = {"MISSSCARLETT", "COLONELMUSTARD", "MRSWHITE",
                "MRGREEN", "MRSPEACOCK", "PROFESSORPLUM"};
        JLabel heading = new JLabel("CLUEDO GAME: ENTER YOUR NAME AND CHARACTER.");
        opening.add(heading);
       // opening.setLayout(null);
        opening.setVisible(true);


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
                                            openingFrame.getRootPane().requestFocus();
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
                openingFrame.getRootPane().requestFocus();
            }
        });
    }









    @Override
    protected void redraw() {

    }
}
