import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class TravelAgency {
    private List<Trip> trips;

    public TravelAgency() {
        trips = new ArrayList<>();
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public void removeTrip(Trip trip) {
        trips.remove(trip);
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void saveState() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("state.csv"))) {
            for (Trip trip : trips) {
                writer.write(String.format("%s,%d,%.2f\n", trip.getDestination(), trip.getDuration(), trip.getCost()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void restoreState() {
        trips.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("state.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String destination = parts[0];
                    int duration = Integer.parseInt(parts[1]);
                    double cost = Double.parseDouble(parts[2]);
                    Trip trip = new Trip(destination, duration, cost);
                    trips.add(trip);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}