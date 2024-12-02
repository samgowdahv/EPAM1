package ProjectPractise.src.main.java;

public class Car implements Comparable<Car>{

    private String model;
    private int Id;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Car(String model, int id) {
        this.model = model;
        this.Id = id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }


    @Override
    public int compareTo(Car o) {
        if(this.getId() == o.getId()) return 0;
        else if (this.getId() > o.getId()) return -1;
        else return 1;

    }
}
