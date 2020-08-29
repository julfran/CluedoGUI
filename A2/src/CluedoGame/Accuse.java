package CluedoGame;

import Cards.*;

import java.util.List;

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
     * Implements the accuse strategy
     * */
    public boolean doAccusation(Board b, List<Player> players, WeaponType w, RoomType r, CharacterType c,
                                WeaponCard murderWeapon, RoomCard murderRoom, CharacterCard murderCharacter){


        /* Placeholder */
//        //If player successfully guesses murder weapons right
//        if(murderCharacter.characters != c && murderWeapon.getWeapon() != w && murderRoom.rooms != r){
//                System.out.println("You have not found the murderer.");
//                return false;
//
//        }

        //iterate through list of players and find one that matches player's accusation
        //if iteration matches player input, check weapon type
        //if all are true, print statement and then return true

        for(Player p: players){
//            if(p.)
        }



        return false;
    }


    public String toString() {
        return null;
    }
}
