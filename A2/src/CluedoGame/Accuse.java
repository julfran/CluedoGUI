package CluedoGame;

import Cards.*;

import java.util.*;

public class Accuse {
    public RoomType accusedRoom;
    public WeaponType accusedWeapon;
    public CharacterType accusedCharacter;
    public boolean isAccused = false;


    /** Constructor for Accuse*/
    public Accuse(RoomType room, WeaponType weapon, CharacterType character){
        this.accusedRoom = room;
        this.accusedWeapon = weapon;
        this.accusedCharacter = character;
    }

    /**
     * Allows player to make an accusation based on character, weapon and room
     * */
    public boolean doAccusation(Board b, List<Player> players, WeaponType w, RoomType r, CharacterType c,
                                WeaponCard murderWeapon, RoomCard murderRoom, CharacterCard murderCharacter){

        //iterate through list of players and find one that matches player's accusation
        //if iteration matches player input, check weapon type and room type
        //if all are true, print statement and then return true
        //if accusation is wrong,

        for(int i = 0; i < players.size(); i++){
            if(players.get(i).equals(murderCharacter)){
                Player accusedPlayer = players.get(i);
                ArrayList<Card> accusedPlayerHand = accusedPlayer.getHand();
                for(int j = 0; j < accusedPlayerHand.size(); j++){
                    //check player's hand for room and weapon


                }


            }

        }



        return false;
    }


    public String toString() {
        return null;
    }
}
