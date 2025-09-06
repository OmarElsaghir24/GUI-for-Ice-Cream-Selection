package emporium;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import product.Container;
import product.Serving;
import product.Order;
import product.MixInFlavor;
import product.IceCreamFlavor;
import product.MixInAmount;
import product.MixIn;
import product.Item;
import person.Customer;

public class MultiMap<K, V> {
    private HashMap<K, HashSet<V>> map = new HashMap<>();

    public void put(K key, V value) {
        if(map.get(key) == null) {
            map.put(key, new HashSet<V>());
        }
        map.get(key).add(value);
    }

    public Object[] get(K key) {
        if(map.get(key) == null) {
            return new Object[0];
        }else {
            return map.get(key).toArray();
        }
    }
}
