package Cards;

/**
 * Room cards
 * Contains room card methods
 */
public class RoomCard implements Card {
    public RoomType rooms = null;

    /** Constructor */
    public RoomCard(RoomType rt){
        rooms = rt;
    }

    /** To String */
    public String toString(){
        return rooms.toString();
    }
}