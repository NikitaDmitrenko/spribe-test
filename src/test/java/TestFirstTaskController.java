import controllers.FirstTaskController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/DispatcherServlet-servlet.xml"})
public class TestFirstTaskController {

    private CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

    @Before
    public void setUp(){
        copyOnWriteArrayList.add("ß");
        copyOnWriteArrayList.add("ß");
        copyOnWriteArrayList.add("ß");
        copyOnWriteArrayList.add("ß");
        copyOnWriteArrayList.add("ß");
        copyOnWriteArrayList.add("ÿ");
        copyOnWriteArrayList.add("ÿ");
        copyOnWriteArrayList.add("ÿ");
        copyOnWriteArrayList.add("×");
        copyOnWriteArrayList.add("×");
        copyOnWriteArrayList.add("÷");
        copyOnWriteArrayList.add("÷");
        copyOnWriteArrayList.add("÷");
    }

    @Test
    public void testgetCountOfWords(){
        FirstTaskController firstTaskController = new FirstTaskController();
        firstTaskController.setCopyOnWriteArrayList(copyOnWriteArrayList);
        assertEquals(firstTaskController.getCountOfWords("ÿ"),8);
        assertEquals(firstTaskController.getCountOfWords("÷"), 5);
        assertEquals(firstTaskController.getCountOfWords("ôûâôûâ"),0);
        assertEquals(firstTaskController.getCountOfWords(null),0);
    }
}
