import Model.Word;
import controllers.FirstTaskController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/DispatcherServlet-servlet.xml"})
@WebAppConfiguration
public class TestFirstTaskController {

    private MockMvc mockMvc;

    private CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        copyOnWriteArrayList.add("�");
        copyOnWriteArrayList.add("�");
        copyOnWriteArrayList.add("�");
        copyOnWriteArrayList.add("�");
        copyOnWriteArrayList.add("�");
        copyOnWriteArrayList.add("�");
        copyOnWriteArrayList.add("�");
        copyOnWriteArrayList.add("�");
        copyOnWriteArrayList.add("�");
        copyOnWriteArrayList.add("�");
        copyOnWriteArrayList.add("�");
        copyOnWriteArrayList.add("�");
        copyOnWriteArrayList.add("�");
    }

    @Test
    public void testgetCountOfWords() {
        FirstTaskController firstTaskController = new FirstTaskController();
        firstTaskController.setCopyOnWriteArrayList(copyOnWriteArrayList);
        assertEquals(firstTaskController.getCountOfWords("�"),8);
        assertEquals(firstTaskController.getCountOfWords("�"), 5);
        assertEquals(firstTaskController.getCountOfWords("������"), 0);
        assertEquals(firstTaskController.getCountOfWords(null), 0);
    }

    @Test
    public void testClass() throws Exception {
        Word word = new Word();
        word.setKeyword("����");
        mockMvc.perform(post("/hello").contentType(MediaType.APPLICATION_JSON).content(word.toString())).andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(content().json("{\"word\": 1}"));

        word.setKeyword("����");
        mockMvc.perform(post("/hello").contentType(MediaType.APPLICATION_JSON).content(word.toString())).andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(content().json("{\"word\": 2}"));
        word.setKeyword("����2");
        mockMvc.perform(post("/hello").contentType(MediaType.APPLICATION_JSON).content(word.toString())).andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(content().json("{\"word\": 1}"));
        mockMvc.perform(post("/hello").contentType(MediaType.APPLICATION_JSON).content(word.toString())).andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(content().json("{\"word\": 2}"));
        mockMvc.perform(post("/hello").contentType(MediaType.APPLICATION_JSON).content(word.toString())).andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(content().json("{\"word\": 3}"));
    }

}
