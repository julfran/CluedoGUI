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
