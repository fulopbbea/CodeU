package assignment6;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

public class ParkingLotTest {
    
    @Test
    public void testRearrangementPlan0() {
        System.out.println("RearrangementPlan0");
        assertEquals(new ParkingLot(new int[]{1, 2, 0, 3}).rearrange(new int[]{3, 1, 2, 0}),
                Arrays.asList(
                        new Movement(1, 0, 2),
                        new Movement(3, 3, 0),
                        new Movement(2, 1, 3),
                        new Movement(1, 2, 1),
                        new Movement(2, 3, 2)));
    }
    
    @Test
    public void testRearrangementPlan1() {
        System.out.println("RearrangementPlan1");
        assertEquals(new ParkingLot(new int[]{1, 2, 0, 3}).rearrange(new int[]{0, 1, 3, 2}),
                Arrays.asList(
                        new Movement(2, 1, 2),
                        new Movement(1, 0, 1),
                        new Movement(2, 2, 0),
                        new Movement(3, 3, 2),
                        new Movement(2, 0, 3)));
    }
    
    @Test
    public void testRearrangementPlan2() {
        System.out.println("RearrangementPlan2");
        assertEquals(new ParkingLot(new int[]{0, 1, 2, 3}).rearrange(new int[]{1, 2, 3, 0}),
                Arrays.asList(
                        new Movement(1, 1, 0),
                        new Movement(2, 2, 1),
                        new Movement(3, 3, 2)));
    }
    
    @Test
    public void testRearrangementPlan3() {
        System.out.println("RearrangementPlan3");
        assertEquals(new ParkingLot(new int[]{3, 1, 0, 2}).rearrange(new int[]{0, 1, 3, 2}),
                Arrays.asList(new Movement(3, 0, 2)));
    }
    
    @Test
    public void testRearrangementPlanIdenticalMaps() {
        System.out.println("RearrangementPlanIdenticalMaps");
        assertEquals(new ParkingLot(new int[]{3, 0, 2, 1}).rearrange(new int[]{3, 0, 2, 1}),
                new ArrayList<>());
    }
    
    @Test
    public void testRearrangementPlanEmpty() {
        System.out.println("RearrangementPlanEmpty");
        assertEquals(new ParkingLot(new int[]{}).rearrange(new int[]{}),
                new ArrayList<>());
    }
    
    @Test
    public void testExecutePlan0() {
        System.out.println("ExecutePlan0");
        int[] initialMap = {1, 2, 0, 3};
        int[] desiredMap = {3, 1, 2, 0};
        ParkingLot.executePlan(initialMap,
                new ParkingLot(initialMap).rearrange(desiredMap));
        assertArrayEquals(initialMap, desiredMap);
    }
    
    @Test
    public void testExecutePlan1() {
        System.out.println("ExecutePlan1");
        int[] initialMap = {1, 2, 0, 3};
        int[] desiredMap = {0, 1, 3, 2};
        ParkingLot.executePlan(initialMap,
                new ParkingLot(initialMap).rearrange(desiredMap));
        assertArrayEquals(initialMap, desiredMap);
    }
    
    @Test
    public void testExecutePlan2() {
        System.out.println("ExecutePlan2");
        int[] initialMap = {0, 1, 2, 3};
        int[] desiredMap = {1, 2, 3, 0};
        ParkingLot.executePlan(initialMap,
                new ParkingLot(initialMap).rearrange(desiredMap));
        assertArrayEquals(initialMap, desiredMap);
    }
    
    @Test
    public void testExecutePlan3() {
        System.out.println("ExecutePlan3");
        int[] initialMap = {3, 1, 0, 2};
        int[] desiredMap = {0, 1, 3, 2};
        ParkingLot.executePlan(initialMap,
                new ParkingLot(initialMap).rearrange(desiredMap));
        assertArrayEquals(initialMap, desiredMap);
    }
    
    @Test
    public void testExecutePlanIdenticalMaps() {
        System.out.println("ExecutePlanIdenticalMaps");
        int[] initialMap = {3, 0, 2, 1};
        int[] desiredMap = {3, 0, 2, 1};
        ParkingLot.executePlan(initialMap,
                new ParkingLot(initialMap).rearrange(desiredMap));
        assertArrayEquals(initialMap, desiredMap);
    }
    
    @Test
    public void testExecutePlanEmpty() {
        System.out.println("ExecutePlanEmpty");
        int[] initialMap = {};
        int[] desiredMap = {};
        ParkingLot.executePlan(initialMap,
                new ParkingLot(initialMap).rearrange(desiredMap));
        assertArrayEquals(initialMap, desiredMap);
    }
    
    @Test
    public void testExecutePlanRandom() {
        System.out.println("ExecutePlanRandom");
        ArrayList<Integer> initial = new ArrayList<>();
        ArrayList<Integer> desired = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            initial.add(i);
            desired.add(i);
        }
        java.util.Collections.shuffle(initial);
        java.util.Collections.shuffle(desired);
        int[] initialMap = initial.stream().mapToInt(i->i).toArray();
        int[] desiredMap = desired.stream().mapToInt(i->i).toArray();
        ParkingLot.executePlan(initialMap,
                new ParkingLot(initialMap).rearrange(desiredMap));
        assertArrayEquals(initialMap, desiredMap);
    }
}
