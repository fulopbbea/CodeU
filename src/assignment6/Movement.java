package assignment6;

/**
 * Data structure holding the information about the moving of one car in the parking lot.
 */
public class Movement {
    
    private final int from;
    private final int to;
    private final int car;
    
    public Movement(int car, int from, int to) {
        this.car = car;
        this.from = from;
        this.to = to;
    }
    
    public int getCar() {
        return this.car;
    }
    
    public int getFrom() {
        return this.from;
    }
    
    public int getTo() {
        return this.to;
    }
    
    @Override
    public String toString() {
        return ("Car no. " + this.car + " moved from parking space " + this.from + " to " + this.to + ".");
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Movement) {
        Movement m = (Movement)o;
            return (this.getCar() == m.getCar() &&
                    this.getFrom() == m.getFrom() &&
                    this.getTo() == m.getTo());
        }
        return false;
    }
    
}
