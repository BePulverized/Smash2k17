
import com.smash2k17.game.logic.*;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Pienie on 17-4-2017.
 */
public class StoreItemTest {

    private StoreItem storeItem;
    // Setting up the needed objects
    @Before
    public void setUp(){
        Image image = new Image();
        storeItem = new StoreItem(100, "Naam", image);
    }
    // Testing all getters
    @Test
    public void testGetPrice(){
        Assert.assertEquals(100, storeItem.getPrice());
    }

    @Test
    public void testGetName(){
        Assert.assertEquals("Naam", storeItem.getName());
    }

}
