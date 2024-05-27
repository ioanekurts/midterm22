import java.io.*;
import java.util.ArrayList;
import java.util.List;

    class Organization {
        private List<TravelAgency> agencies;

        public Organization() {
            agencies = new ArrayList<>();
        }

        public void addAgency(TravelAgency agency) {
            agencies.add(agency);
        }

        public void removeAgency(TravelAgency agency) {
            agencies.remove(agency);
        }

        public List<TravelAgency> getAgencies() {
            return agencies;
        }

        public void saveState() {
            try {
                FileWriter fileWriter = new FileWriter("organization_state.csv");
                BufferedWriter writer = new BufferedWriter(fileWriter);
                for (TravelAgency agency : agencies) {
                    List<Trip> trips = agency.getTrips();
                    for (Trip trip : trips) {
                        writer.write(agency.hashCode() + "," + trip.getDestination() + "," + trip.getDuration() + "," + trip.getCost());
                        writer.newLine();
                    }
                }
                writer.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void restoreState() {
            for (TravelAgency agency : agencies) {
                agency.getTrips().clear();
            }
            try {
                FileReader fileReader = new FileReader("organization_state.csv");
                BufferedReader reader = new BufferedReader(fileReader);
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        int agencyHashCode = Integer.parseInt(parts[0]);
                        String destination = parts[1];
                        int duration = Integer.parseInt(parts[2]);
                        double cost = Double.parseDouble(parts[3]);
                        Trip trip = new Trip(destination, duration, cost);
                        for (TravelAgency agency : agencies) {
                            if (agency.hashCode() == agencyHashCode) {
                                agency.addTrip(trip);
                                break;
                            }
                        }
                    }
                }
                reader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

