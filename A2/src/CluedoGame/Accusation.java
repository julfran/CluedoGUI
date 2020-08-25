package CluedoGame;

import Cards.RoomType;
import Cards.WeaponType;

public class Accusation {
    public RoomType accusedRoom;
    public WeaponType accusedWeapon;
    public CharacterType accusedCharacter;


    /* Constructor for Accusation object */
    public Accusation(RoomType accusedRoom, WeaponType accusedWeapon, CharacterType accusedCharacter){
        this.accusedRoom = accusedRoom;
        this.accusedWeapon = accusedWeapon;
        this.accusedCharacter = accusedCharacter;
    }

    public String toString() {
        return null;
    }
}
