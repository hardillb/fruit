package uk.me.hardill.bbc.fruit.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by hardillb on 22/04/17.
 */

public class Fruit {
    private String type;
    private int price;
    private int weight;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Fruit(String type, int price, int weight){
        this.type = type;
        this.price = price;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return this.type + " " + this.price + " " + this.weight;
    }
}
