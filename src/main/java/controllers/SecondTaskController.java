package controllers;

import Model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class SecondTaskController {
    @Autowired
    private Random rand;

    @Autowired
    private CopyOnWriteArrayList<Player> concurrentList;

    @RequestMapping(method = RequestMethod.POST,path = "/find_opponent", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String,Object> findOpponent(@RequestBody Player player){
        Map<String,Object> opponentMap = new HashMap<>();
        Player player1 = getOpponent(player.getRate());
        opponentMap.put("opponent",player1);
        return opponentMap;
    }

    public Player getOpponent(double rate) {
        Player player;
        double rateDifference;
        if(!concurrentList.isEmpty()){
            double player2Rate = concurrentList.get(0).getRate();
            player = concurrentList.get(0);
            rateDifference = Math.abs(rate-player2Rate);
            for(Player player1 : concurrentList){
                double playersDifference = Math.abs(rate-player1.getRate());
                if(playersDifference<rateDifference){
                    player = player1;
                }
            }
            concurrentList.remove(player);
            return player;
        }
        return new Player();
    }

    @Bean
    @Scope("prototype")
    public double getRandomRate(){
        return rand.nextDouble();
    }

    @Bean
    public Random random(){
        return new java.util.Random();
    }

    @Bean
    @Scope("prototype")
    public String randomName(){
        return String.valueOf(UUID.randomUUID());
    }


    public CopyOnWriteArrayList<Model.Player> getConcurrentList() {
        return concurrentList;
    }

    public void setConcurrentList(CopyOnWriteArrayList<Model.Player> concurrentList) {
        this.concurrentList = concurrentList;
    }


}
