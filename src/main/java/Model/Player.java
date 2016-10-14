package Model;

import org.springframework.stereotype.Component;

@Component
public class Player {


    private String name;

    private double rate;

    public Player() {
        this.name = "";
        this.rate = 0.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "{\"name\":\"" + name + "\"" +
                ", \"rate\":" + rate +
                '}';
    }
}
