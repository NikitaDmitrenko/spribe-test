import Model.Player;
import Model.Word;
import controllers.SecondTaskController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.concurrent.CopyOnWriteArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/DispatcherServlet-servlet.xml"})
@WebAppConfiguration
public class TestSecondTaskController {

    Player player1;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    public CopyOnWriteArrayList<Player> concurrentList;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
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

        assertEquals(concurrentList.size(), 11);
        assertEquals(concurrentList.contains(opponent), false);
        for(int i = 0, j = concurrentList.size();i<concurrentList.size();i++,j--){
            assertEquals(concurrentList.size(), j);
            opponent = controller.getOpponent(player1.getRate());

            assertEquals(concurrentList.contains(opponent), false);
        }
    }

    @Test
    public void testaordCounter() throws Exception {

        mockMvc.perform(post("/find_opponent").contentType(MediaType.APPLICATION_JSON).content(player1.toString())).andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()));
    }

}
