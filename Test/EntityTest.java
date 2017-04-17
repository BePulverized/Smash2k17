import com.smash2k17.game.logic.Map;
import com.smash2k17.game.logic.Player;
import com.smash2k17.game.logic.World;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by BePulverized on 17-4-2017.
 */
public class EntityTest {

    private Player player;
    // Setting up the needed objects
    @Before
    public void setUp(){
        World world = new World();
        Map map = new Map(world);
        player = new Player(map);
    }
    // Testing all the methods that have to do with changing the players health
    @Test
    public void testHealth(){
        player.addHealth(10);
        Assert.assertEquals(110, player.getHealth());
    }

}
