package product;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class MixIn {
    private MixInFlavor flavor;
    private MixInAmount amount;

public MixIn(MixInFlavor flavor, MixInAmount amount) {
    this.flavor = flavor;
    this.amount = amount;
}

@Override
public String toString() {
    if(amount == MixInAmount.Normal) {
        return "" + flavor;
    }else{
        return flavor + "(" + amount + ")";
    }
}

public MixIn(BufferedReader in) throws IOException {
    flavor = new MixInFlavor(in);
    amount.valueOf(in.readLine());
}

public void save(BufferedWriter out) throws IOException {
    out.write("" + flavor + '\n');
    out.write("" + amount + '\n');
}

public int price() {
    /*if(amount == MixInAmount.Light) {
        return flavor.price() * 0.8;
    }else if(amount == MixInAmount.Extra) {
        return flavor.price() * 1.2;
    }else if(amount == MixInAmount.Drenched) {
        return flavor.price() * 2;
    }*/
    return flavor.price();
}

} 
