package CluedoGame;

import Cards.Card;
import Cards.RoomType;
import Cards.WeaponType;

import java.util.ArrayList;
import java.util.Scanner;

public class Suggest {
	private Player suggestingPlayer;
	
    public RoomType suggestedRoom;
    public WeaponType suggestedWeapon;
    public CharacterType suggestedCharacter;

    /* Constructor for Suggestion object */
    public Suggest(RoomType suggestedRoom, WeaponType suggestedWeapon, CharacterType suggestedCharacter, Player player){
        this.suggestedRoom = suggestedRoom;
        this.suggestedWeapon = suggestedWeapon;
        this.suggestedCharacter = suggestedCharacter;
        this.suggestingPlayer = player;
    }

    public boolean testSuggestion(Player player) {
    	// Find the invalid cards
        ArrayList<Card> invalidCards = new ArrayList<Card>();
        for (Card c : player.getHand()) {
            if (c.getClass() == RoomCard.class) {
                if (((RoomCard) c).getRoom() == suggestedRoom) {
                    invalidCards.add(c);
                }
            }
            if (c.getClass() == WeaponCard.class) {
            	if (((WeaponCard) c).getWeapon() == suggestedWeapon) {
            		invalidCards.add(c);
            	}
            }
            if (c.getClass() == CharacterCard.class) {
            	if (((CharacterCard) c).getCharacter() == suggestedCharacter) {
            		invalidCards.add(c);
            	}
            }
        }
        // If invalid cards were found, those cards owner chooses one to show
        if (!invalidCards.isEmpty()) {
        	boolean choiceMade = false;
        	while (!choiceMade) {
            	System.out.println(player.toString() + " choose a card to show " + suggestingPlayer.toString());
            	int x = 1;
        		for (Card c : invalidCards) {
        			System.out.println(x + " " + invalidCards.get(x - 1).toString());
        		}
        		Scanner input = new Scanner(System.in);
        		if (input.hasNextInt()) {
        			int inputNum = input.nextInt();
        			if (inputNum <= invalidCards.size()) {
        				System.out.println(suggestingPlayer.toString() + ", " + player.toString() + " shows you " + invalidCards.get(inputNum).toString() + "");
        				choiceMade = true;
        				input.close();
        				return true;
        			} else {
        				System.out.println("Please enter a number between 1 and " + invalidCards.size());
        			}
        		} else {
        			System.out.println("Please enter a number between 1 and " + invalidCards.size());
        		}
        	}
        }
        // If no invalid cards were found, return false
        System.out.println("None of the suggested cards were in " + player + "'s hand.");
        return false;
    }

    public String toString() {
        return null;
    }


}
