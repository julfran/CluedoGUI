package Cards;

import CluedoGame.CharacterType;

/**
 * Character cards
 * Contains character card methods
 */
class CharacterCard implements Card {
    public CharacterType characters = null;

    /** Constructor */
    public CharacterCard (CharacterType c){
        characters = c;
    }

    /** To String */
    public String toString(){
        return characters.toString();
    }
}