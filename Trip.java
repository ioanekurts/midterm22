import java.util.ArrayList;
import java.util.List;

class Trip {
    private String destination;
    private int duration;
    private double cost;

    public Trip(String destination, int duration, double cost) {
        this.destination = destination;
        this.duration = duration;
        this.cost = cost;
    }

    // Getters and setters for the Trip class properties

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
