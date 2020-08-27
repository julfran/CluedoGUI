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
     * Implements the accuse strategy where it checks if the accused
     * elements al match the murder set.
     * */
    public boolean doAccusation(Board b, List<Player> players, WeaponType w, RoomType r, CharacterType c,
                                WeaponCard murderWeapon, RoomCard murderRoom, CharacterCard murderCharacter){

      CharacterType p = b.getCurrentPlayer(); //get current players

        //If player successfully guesses murder weapons right
        if(murderCharacter.characters == c){
            if(murderWeapon.weapons == w){
                if(murderRoom.rooms == r){
                    return true;
                }
            }
        }
        //else game proceeds
        else {

        }
        return false;
    }
    
    
//     //If player successfully guesses murder weapons right
//         if(murderCharacter.characters != c && murderWeapon.getWeapon() != w && murderRoom.rooms != r){
//                 System.out.println("You have not found the murderer.");
//                 return false;

//         }

//         return true;
//     }


    public String toString() {
        return null;
    }
}
