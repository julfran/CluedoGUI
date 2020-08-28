package CluedoGame;

import Cards.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Cluedo {
    private static boolean isGameOver;
    public int numOfSteps;
    private static Board board;
    private Player player;
    private boolean gameWon = false;
    private ArrayList<Player> players;
    private ArrayList<Card> solutionSet = new ArrayList<>();

    RoomCard solutionRoom;
    WeaponCard solutionWeapon;
    CharacterCard solutionCharacter;


    /**
     * Deal out the cards when starting the game
     * dealCards shuffles the whole card deck, pick out a
     * murder set, and deals the rest of the cards to all the current players.
     * */
    public void dealCards(){

        //set up list for each type of cards
        ArrayList<WeaponCard> weaponCards = new ArrayList<>();
        ArrayList<CharacterCard> characterCards = new ArrayList<>();
        ArrayList<RoomCard> roomCards = new ArrayList<>();

        //get all cards and put them in their designated list
        for(WeaponType w: WeaponType.values()){
            weaponCards.add(new WeaponCard(w));
        }
        for(CharacterType c: CharacterType.values()){
            characterCards.add(new CharacterCard(c));
        }
        for(RoomType r: RoomType.values()){
            roomCards.add(new RoomCard(r));
        }

        //shuffle cards
        Collections.shuffle(weaponCards);
        Collections.shuffle(characterCards);
        Collections.shuffle(roomCards);

        //randomly select a murder set
        solutionRoom = roomCards.get(0);
        solutionCharacter = characterCards.get(0);
        solutionWeapon = weaponCards.get(0);

        //Remove those murder sets from its designated list
        roomCards.remove(0);
        characterCards.remove(0);
        weaponCards.remove(0);

        //add rest of cards after removing solution
        //Combine remaining cards and shuffle
        ArrayList <Card> netCards = new ArrayList<>();
        netCards.addAll(weaponCards);
        netCards.addAll(characterCards);
        netCards.addAll(roomCards);
        Collections.shuffle(netCards);

        //redistribute to other players
        for(Player p: players){
            if(netCards.isEmpty()){
                break;
            }
            p.addHand(netCards.remove(0));
        }
        
         /**
     * Make two die variable that randomly generates
     * numbers between 1-6 and add them together to get
     * sum total
     * @return dieSum
     * */
    public int rollDiceGenerator() {
        int die1 = (int) (Math.random()*6) +1;
        int die2 = (int) (Math.random()*6) +1;
        int dieSum = die1 + die2;
        return dieSum;
    }


    }

}
