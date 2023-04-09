public class Laptop {
    private String model;
    private String brand;
    private int numberOfCores;
    private double screenDiagonal;
    private String processorManufacturer;
    private int ram;
    private String color;

    public Laptop(String model, String brand, int numberOfCores, double screenDiagonal,
                  String processorManufacturer, int ram, String color){
        this.model = model;
        this.brand = brand;
        this.numberOfCores = numberOfCores;
        this.screenDiagonal = screenDiagonal;
        this.processorManufacturer = processorManufacturer;
        this.ram = ram;
        this.color = color;
    }

    public String toString(){
        return String.format("Model = %s; Brand = %s; Number of cores = %d;" + System.lineSeparator() +
                        "Screen diagonal = %2.1f; Processor manufacturer = %s; RAM = %d; Color = %s",
                model, brand, numberOfCores, screenDiagonal, processorManufacturer, ram, color);
    }

    public String getModel() {
        return model;
    }

    public void setId(String id) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getNumberOfCores() {
        return numberOfCores;
    }

    public void setNumberOfCores(int numberOfCores) {
        this.numberOfCores = numberOfCores;
    }

    public double getScreenDiagonal() {
        return screenDiagonal;
    }

    public void setScreenDiagonal(double screenDiagonal) {
        this.screenDiagonal = screenDiagonal;
    }

    public String getProcessorManufacturer() {
        return processorManufacturer;
    }

    public void setProcessorManufacturer(String processorManufacturer) {
        this.processorManufacturer = processorManufacturer;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
