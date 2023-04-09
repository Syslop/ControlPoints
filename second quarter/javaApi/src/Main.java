import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Laptop> stock = new ArrayList<>();

        stock.add(new Laptop("HYM-W56","Honor", 6, 16.1, "AMD", 16, "grey"));
        stock.add(new Laptop("FX506HCB", "ASUS", 6, 15.6,"Intel", 8, "black"));
        stock.add(new Laptop("M1/8/256", "Apple", 8, 13.3, "Apple", 8, "grey"));
        stock.add(new Laptop("JYU4525RU","Xiaomi", 2, 16.1, "Intel", 8, "black"));
        stock.add(new Laptop("JYU4526RU","Xiaomi", 2, 16.1, "Intel", 8, "black"));
        stock.add(new Laptop("HDE-W36","Honor", 8, 14.1, "AMD", 12, "red"));
        stock.add(new Laptop("M1/16/256", "Apple", 16, 17.8, "Apple", 16, "white"));
        stock.add(new Laptop("82FG0165US", "Lenovo", 4, 15.6, "Intel", 8, "black"));
        stock.add(new Laptop("RLEF-Xi5", "HUAWEI", 12, 16.0, "Intel", 16, "white"));
        stock.add(new Laptop("JYU4532RU","Xiaomi", 2, 15.6, "Intel", 8, "grey"));

        Menu.start(stock);
    }
}
