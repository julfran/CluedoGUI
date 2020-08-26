package Cards;

import CluedoGame.CharacterType;

/**
 * Character cards
 * Contains character card methods
 */
public class CharacterCard implements Card {
    public CharacterType characters;

    /** Constructor */
    public CharacterCard (CharacterType c){
        characters = c;
    }

    /** To String */
    public String toString(){
        return characters.toString();
    }
}