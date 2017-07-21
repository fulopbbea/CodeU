package assignment6;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    
    private final int[] lotMap;
    private final int[] carMap;
    
    /**
     * Initializes the map of the parking lot.
     * Copies the array to preserve the initial map.
     * Generates the parking slot to car map as well.
     * @param initialMap initial layout of the cars in the parking lot
     */
    public ParkingLot(int[] initialMap) {
        this.lotMap = new int[initialMap.length];
        this.carMap = new int[initialMap.length];
        for (int i = 0; i < initialMap.length; i++) {
            this.lotMap[i] = initialMap[i];
            this.carMap[initialMap[i]] = i;
        }
    }
    
    /**
     * Executes the movement of a car on the car to slot map.
     */
    private void moveLot(Movement m) {
        this.lotMap[m.getTo()] = m.getCar();
        this.lotMap[m.getFrom()] = 0;
    }
    
    /**
     * Executes the movement of a car on the slot to car map.
     */
    private void moveCar(Movement m) {
        this.carMap[m.getCar()] = m.getTo();
        this.carMap[0] = m.getFrom();
    }
    
    /**
     * Moves one car to the empty space both on the car to slot and
     * the slot to car maps.
     * @param car number of the car to be moved
     * @return information about the movement
     */
    private Movement move(int car) {
        Movement m = new Movement(car, this.carMap[car], this.carMap[0]);
        this.moveLot(m);
        this.moveCar(m);
        return m;
    }
    
    /**
     * Generates the plan to move the cars around the parking lot to obtain
     * the desired arrangement.
     * Iteratively places the correct car in its desired slot.
     * @param desiredMap desired arrangement of the cars in the parking lot
     * @return plan (list of required movements) to rearrange the car
     */
    public List<Movement> rearrange(int[] desiredMap) {
        ArrayList<Movement> plan = new ArrayList<>();
        for (int i = 0; i < this.lotMap.length; i++) {
            //correct car in the slot or the place of the empty slot
            //NOTHING TO BE DONE
            if (this.lotMap[i] == desiredMap[i] || desiredMap[i] == 0) {
                continue;
            }
            //current slot already empty
            //MOVE THE CORRECT CAR INTO THE SLOT
            if (this.lotMap[i] == 0) {
                plan.add(this.move(desiredMap[i]));
                continue;
            }
            //current slot not empty, not holding the desired car
            //MOVE THE CURRENT CAR FROM THE SLOT,
            //MOVE THE CORRECT CAR INTO THE SLOT
            plan.add(this.move(this.lotMap[i]));
            plan.add(this.move(desiredMap[i]));
        }
        return plan;
    }
    
    /**
     * Executes a plan (list of movements) on an arrangements of cars.
     * @param lotMap current map of the parking lot
     * @param plan list of movements to be executed on the parking lot
     */
    public static void executePlan(int[] lotMap, List<Movement> plan) {
        plan.forEach((m) -> {
            lotMap[m.getTo()] = m.getCar();
            lotMap[m.getFrom()] = 0;
        });
    }
    
}
