import java.util.LinkedList;
import java.util.List;

public class LaptopFilter {
    private List<Laptop> laptops;
    public LaptopFilter(List<Laptop> laptops) {
        this.laptops = laptops;
    }

    public List<Laptop> filterByModel(String model){

        List<Laptop> result = new LinkedList<>();
        for (Laptop lap : laptops) {
            if (lap.getModel().equals(model)){
                result.add(lap);
            }
        }
        return result;
    }
    public List<Laptop> filterByBrand(String brand){
        List<Laptop> result = new LinkedList<>();
        for (Laptop lap : laptops) {
            if (lap.getBrand().equals(brand)){
                result.add(lap);
            }
        }
        return result;
    }

    public List<Laptop> filterByProcessor(String processor){
        List<Laptop> result = new LinkedList<>();
        for (Laptop lap : laptops) {
            if (lap.getProcessorManufacturer().equals(processor)){
                result.add(lap);
            }
        }
        return result;
    }

    public List<Laptop> filterByColor(String color){
        List<Laptop> result = new LinkedList<>();
        for (Laptop lap : laptops) {
            if (lap.getColor().equals(color)){
                result.add(lap);
            }
        }
        return result;
    }

    public List<Laptop> filterByScreenDiagonal(double[] arr){
        List<Laptop> result = new LinkedList<>();
        for (Laptop lap : laptops) {
            if (arr[0] <= lap.getScreenDiagonal() && lap.getScreenDiagonal() <= arr[1]){
                result.add(lap);
            }
        }
        return result;
    }

    public List<Laptop> filterByNumberOfCores(int[] arr){
        List<Laptop> result = new LinkedList<>();
        for (Laptop lap : laptops) {
            if (arr[0] <= lap.getNumberOfCores() && lap.getNumberOfCores() <= arr[1]){
                result.add(lap);
            }
        }
        return result;
    }

    public List<Laptop> filterByRAM(int[] arr){
        List<Laptop> result = new LinkedList<>();
        for (Laptop lap : laptops) {
            if (arr[0] <= lap.getRam() && lap.getRam() <= arr[1]){
                result.add(lap);
            }
        }
        return result;
    }

}
