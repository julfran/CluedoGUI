package CluedoGame;

import Cards.*;

import java.util.List;

public class Accuse {
    public RoomType accusedRoom;
    public WeaponType accusedWeapon;
    public CharacterType accusedCharacter;


    /** Constructor for Accuse*/
    public Accuse(RoomType room, WeaponType weapon, CharacterType character){
        this.accusedRoom = room;
        this.accusedWeapon = weapon;
        this.accusedCharacter = character;
    }

    /**
     * Implements the accuse strategy
     * */
    public boolean doAccusation(Board b, List<Player> players, WeaponType w, RoomType r, CharacterType c,
                                WeaponCard murderWeapon, RoomCard murderRoom, CharacterCard murderCharacter){

        //If player successfully guesses murder weapons right
        if(murderCharacter.characters == c){

        }

        return true;
    }


    public String toString() {
        return null;
    }
}
