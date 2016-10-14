package controllers;

import Model.Word;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class FirstTaskController {

    private CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

    public CopyOnWriteArrayList<String> getCopyOnWriteArrayList() {
        return copyOnWriteArrayList;
    }

    public void setCopyOnWriteArrayList(CopyOnWriteArrayList<String> copyOnWriteArrayList) {
        this.copyOnWriteArrayList = copyOnWriteArrayList;
    }

    public FirstTaskController() {
    }

    @RequestMapping(value = "/hello",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Long> wordCounter( @RequestBody Word data){
        String keyword = data.getKeyword();
        copyOnWriteArrayList.add(keyword);
        Map<String,Long> countMap = new HashMap<>();
        countMap.put("word", getCountOfWords(keyword));
        return countMap;
    }

    public long getCountOfWords(String word){
       return copyOnWriteArrayList.stream().filter(word1 -> word1.equalsIgnoreCase(word) && word != null).count();
    }
}
