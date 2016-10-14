import Model.Player;
import controllers.SecondTaskController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.concurrent.CopyOnWriteArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/DispatcherServlet-servlet.xml"})
public class TestSecondTaskController {

    Player player1;

    @Autowired
    public CopyOnWriteArrayList<Player> concurrentList;

    @Before
    public void setUp(){
        player1 = new Player();
        player1.setName("Anatoliy");
        player1.setRate(0.549);
    }

    @Test
    public void testgetOponnent(){
        SecondTaskController controller = new SecondTaskController();
        controller.setConcurrentList(concurrentList);
        assertEquals(concurrentList.size(), 12);
        Player opponent = controller.getOpponent(player1.getRate());
        assertEquals(concurrentList.size(),11);
        assertEquals(concurrentList.contains(opponent), false);
        for(int i = 0, j = concurrentList.size();i<concurrentList.size();i++,j--){
            assertEquals(concurrentList.size(), j);
            opponent = controller.getOpponent(player1.getRate());

            assertEquals(concurrentList.contains(opponent), false);
        }
    }

}
