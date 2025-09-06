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

public class Emporium {

    public Emporium() {
    }

public void addMixInFlavor(MixInFlavor flavor) {
    mixinFlavors.add(flavor);
}

public void addIceCreamFlavor(IceCreamFlavor flavor) {
    iceCreamFlavor.add(flavor);
}

public void addOrder(Order order, Customer customer) {
    orders.add(order);
    order.getCustomer();
    for(Serving s : order.getServing()) {
        favoriteServings.put(customer, s);
    }
}

public void addContainer(Container container) {
    containers.add(container);
}

public void addCustomer(Customer customer) {
    customers.add(customer);
}

public Object[] mixinFlavors() {
    return mixinFlavors.toArray();
}

public Object[] iceCreamFlavor() {
    return iceCreamFlavor.toArray();
}

public Object[] containers() {
    return containers.toArray();
}

public Object[] orders() {
    return orders.toArray();
}

public Object[] customers() {
    return customers.toArray();
}

public Object[] favoriteServings(Customer customer) {
    return favoriteServings.get(customer);
}

public Emporium(BufferedReader in) throws IOException {
    int size = Integer.parseInt(in.readLine());
    while(size-- > 0) {
        iceCreamFlavor.add(new IceCreamFlavor(in));
        mixinFlavors.add(new MixInFlavor(in));
        containers.add(new Container(in));
        orders.add(new Order(in));
        customers.add(new Customer(in));
    }
}

public void save(BufferedWriter out) throws IOException {
    out.write("" + iceCreamFlavor.size() + '\n');
    for (var s : iceCreamFlavor) {
        s.save(out);
    }

    out.write("" + mixinFlavors.size() + '\n');
    for(var t : mixinFlavors) {
        t.save(out);
    }

    out.write("" + containers.size() + '\n');
    for(var v : containers) {
        v.save(out);
    }

    out.write("" + orders.size() + '\n');
    for(var w : orders) {
        w.save(out);
    }

    out.write("" + customers.size() + '\n');
    for(var x : customers) {
        x.save(out);
    }

}

    private ArrayList<MixInFlavor> mixinFlavors = new ArrayList<>();
    private ArrayList<IceCreamFlavor> iceCreamFlavor = new ArrayList<>();
    private ArrayList<Container> containers = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private MultiMap<Customer, Serving> favoriteServings = new MultiMap<>();
} 
