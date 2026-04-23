public class CircularTourProblem {

    static class PetrolPump {
        int petrol;
        int distance;

        PetrolPump(int petrol, int distance) {
            this.petrol = petrol;
            this.distance = distance;
        }
    }

    public static int findStartingPoint(PetrolPump[] pumps) {
        int n = pumps.length;
        int totalSurplus = 0;
        int currentSurplus = 0;
        int start = 0;

        for (int i = 0; i < n; i++) {
            int diff = pumps[i].petrol - pumps[i].distance;
            totalSurplus += diff;
            currentSurplus += diff;

            if (currentSurplus < 0) {
                start = i + 1;
                currentSurplus = 0;
            }
        }

        return totalSurplus >= 0 ? start : -1;
    }

    public static void main(String[] args) {
        PetrolPump[] pumps = {
            new PetrolPump(4, 6),
            new PetrolPump(6, 5),
            new PetrolPump(7, 3),
            new PetrolPump(4, 5)
        };

        int start = findStartingPoint(pumps);

        if (start == -1) {
            System.out.println("No valid starting point exists.");
        } else {
            System.out.println("Starting point index: " + start);
        }
    }
}
