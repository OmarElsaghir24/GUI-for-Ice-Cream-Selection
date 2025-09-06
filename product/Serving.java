package product;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Serving {
    private Container container;
    private ArrayList<Scoop> scoops;
    private ArrayList<MixIn> toppings;
    private Scoop scoop;

    public Serving(Container container) {
        this.container = container;
        scoops = new ArrayList<>();
        toppings = new ArrayList<>();
    }

    public Serving(BufferedReader in) throws IOException {
        container = new Container(in);
        int size = Integer.parseInt(in.readLine());
        while(size-- > 0) {
            scoops.add(new Scoop(in));
            toppings.add(new MixIn(in));
        }
    }

    public void save(BufferedWriter out) throws IOException {
        out.write("" + container + '\n');
        out.write("" + scoops.size() + '\n');
        for(var s : scoops) {
            s.save(out);
        }

        out.write("" + toppings.size() + '\n');
        for(var t : toppings) {
            t.save(out);
        }
    }

    public void addScoop(Scoop scoop) {
        scoops.add(scoop);
    }

    public void addTopping(MixIn topping) {
        toppings.add(topping);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(container.toString());
        if(scoops.size() > 0) {
            String separator = (scoops.size() == 1) ? " with a scoop of " : " with scoops of ";
            for(Scoop s : scoops) {
                result.append(separator + s.toString());
                separator = ", ";
            }
        }
        if(toppings.size() > 0) {
            String separator = (toppings.size() == 1) ? " and topped with " : " and toppings of ";
            for(MixIn t : toppings) {
                result.append(separator + t.toString());
                separator = ", ";
            }
        }
        return result.toString();
    }

    public int numScoops() {
        return scoops.size();
    }
    
    public int price() {
        int serving = 0;
        for(Scoop s : scoops) {
            serving += s.price();
            for(MixIn m : toppings) {
               serving += m.price();
            }
        }
        return serving;
    }
}
