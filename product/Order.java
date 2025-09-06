package product;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import person.Customer;

public class Order {
    private ArrayList<Serving> servings;
    private ArrayList<MixIn> mixin;
    private Customer customer;

    public Order(Customer customer) {
        this.customer = customer;
        servings = new ArrayList<>();
        mixin = new ArrayList<>();
    }

    public Order(BufferedReader in) throws IOException {
        int size = Integer.parseInt(in.readLine());
        while(size-- > 0) {
            servings.add(new Serving(in));
            mixin.add(new MixIn(in));
        }
    }

    public void save(BufferedWriter out) throws IOException {
        out.write("" + servings.size() + '\n');
        for(var s : servings) {
            s.save(out);
        }

        out.write("" + mixin.size() + '\n');
        for(var t : mixin) {
            t.save(out);
        }
    }

    public void addServing(Serving serving) {
        servings.add(serving);
    }

    public Customer getCustomer() {
        return customer;
    }

    public ArrayList<Serving> getServing() {
        return servings;
    }

    public Object[] servings() {
        return servings.toArray();
    }

    public int price() {
        int order = 0;
        for(Serving s : servings) {
            order += s.price();
        }
        return order;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String separator = "";
        if(servings.size() > 0) {
            for(Serving s : servings) {
                result.append(separator + s.toString());
                separator = "<br/>";
            }
        }
        return customer + ":" + "<br/>" + result.toString();
    }
}
