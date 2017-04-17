
import com.smash2k17.game.logic.*;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Pienie on 17-4-2017.
 */
public class StoreTest {

    private Store store;
    // Setting up the needed objects
    @Before
    public void setUp(){
        store = new Store();
    }
    // Testing all the methods that have to do with adding store items
    @Test
    public void testAdd(){
        boolean inList = false;
        store.addItems(new StoreItem(100, "Naam", new Image()));
        for (StoreItem s : store.getItems()) {
            if(s.getName() == "Naam"){
                inList = true;
            }
        }
        Assert.assertEquals(true, inList);
    }

}
