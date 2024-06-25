package views;

/**
 * This class represents a view for Dream Tiles.
 * It provides methods to display information about a tile.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class TileView {

    /**
     * Displays information about the given tile.
     *
     * @param  name name of tile to display information about
     * @param  abilityDescription ability description of tile to display information about
     * @param  ZzzCount count of Zzz on tile to display information about
     * @param  isInfinite infinity value of tile to display information about
     */
    public void displayInfo(String name, String abilityDescription, int ZzzCount, boolean isInfinite) {
        System.out.println("Tile name: " + name);
        System.out.println("Tile ability: " + abilityDescription);
        System.out.println("Placement bonus: " + ZzzCount + (isInfinite ? " infinite" : " regular") + " zzzs");
    }
    
}
