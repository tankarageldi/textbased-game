package TileTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import models.Player;
import models.tiles.CoolKidsClubTile;

public class CoolKidsClubTileTest {

    private CoolKidsClubTile tile;
    private Player player;
    @BeforeEach
    public void setUp() {
        tile = new CoolKidsClubTile();
        player = new Player("Adil", "blue");
    }
    @Test
    public void testActivateEffectPlayerHasNoZzzs() {
    
        tile.activateEffect(player, null, null);

        assertEquals(40, player.getPillowPosition(), "Pillow position should not move");
    }

    @Test
    public void testActivateEffectPlayerHasZZZs() {
        tile.placeZzzs(1, false);
        tile.activateEffect(player, null, null);
        assertEquals(39, player.getPillowPosition(), "Pillow position should move back by 1");
    }

}