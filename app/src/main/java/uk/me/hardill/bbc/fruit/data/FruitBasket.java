package uk.me.hardill.bbc.fruit.data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import uk.me.hardill.bbc.fruit.logging.Logger;

/**
 * Created by hardillb on 22/04/17.
 *
 * Place to stash the all the fruits
 */

public class FruitBasket {

    private static HashMap<String, Fruit> basket = new HashMap<String, Fruit>();

    private static Logger logger = Logger.init(null);

    /*
     * Gets the details of a given fruit
     * Returns null if not found
     */
    public static Fruit getDetails(String type) {
        return basket.get(type);
    }

    /*
     * Converts a JSON string into the current Fruit Basket
     */
    public static void parseData(String data) {
        Log.i("Basket","new basket - " + data);
        HashMap newBasket = new HashMap<String, Fruit>();
        try {
            JSONObject json = new JSONObject(data);
            JSONArray fruits = json.getJSONArray("fruit");
            for (int i=0; i< fruits.length(); i++) {
                JSONObject fruitData = fruits.getJSONObject(i);
                Fruit fruit = new Fruit(fruitData.getString("type"), fruitData.getInt("price"), fruitData.getInt("weight"));
                newBasket.put(fruit.getType(), fruit);
            }
            basket = newBasket;
        } catch (JSONException exp) {
            Log.i("Basket", "Broken - " + exp.getMessage());
            logger.logError(exp.toString());
        }
    }

    /*
     * Returns a list of fruits
     */
    public static ArrayList<String> getTypes() {
        return new ArrayList<String>(basket.keySet());
    }
}
