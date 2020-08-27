package Cards;

/**
 * Weapon cards
 * Contains weapon card methods
 */
public class WeaponCard implements Card {
    public WeaponType weapon;

    /** Constructor */
    public WeaponCard (WeaponType weapon){
        this.weapon = weapon;
    }


    /** To String */
    public String toString(){
        return weapon.toString();
    }

    public WeaponType getWeapon(){
        return weapon;
    }


}