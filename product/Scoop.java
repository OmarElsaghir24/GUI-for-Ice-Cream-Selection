package product;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Scoop {
    private IceCreamFlavor flavor;
    private ArrayList<MixIn> mixins;

public Scoop(IceCreamFlavor flavor) {
    this.flavor = flavor;
    mixins = new ArrayList<>();
}

public void addMixIn(MixIn mixin) {
    mixins.add(mixin);
}

@Override
public String toString() {
    String result = "";
    if(mixins.size() > 0) {
    String separator = " with ";
    for(MixIn m : mixins) {
        result += separator + m.toString();
        separator = ", ";
    }
    }
    return flavor + result;
}

public Scoop(BufferedReader in) throws IOException {
    flavor = new IceCreamFlavor(in);
    int size = Integer.parseInt(in.readLine());
    while(size-- > 0) {
        mixins.add(new MixIn(in));
    }
}

public void save(BufferedWriter out) throws IOException {
    out.write("" + flavor + '\n');
    out.write("" + mixins.size() + '\n');
    for(var s : mixins) {
        s.save(out);
    }
}

public int price() {
    int mix = flavor.price();
    for(MixIn m : mixins) {
       mix += m.price();
    }
    return mix;
}

} 
