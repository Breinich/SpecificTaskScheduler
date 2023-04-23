package tasks;

public class Task {

    private final char name;
    private final int priority;
    private final int start;
    private final int last;
    private int remaining;
    private int latency;

    public Task(char c, int p, int s, int l){

        name = c;
        priority = p;
        start = s;
        last = l;
        remaining = l;
        latency = 0;

    }


    public char getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public int getStart() {
        return start;
    }

    public int getLast() {
        return last;
    }

    public boolean decrease(int amount, int time){
        remaining -= amount;
        if(remaining == 0){
            latency = time - start - last;
            return true;
        }
        return false;
    }

    public int getLatency() {
        return latency;
    }

    public int getRemaining(){
        return remaining;
    }
}
