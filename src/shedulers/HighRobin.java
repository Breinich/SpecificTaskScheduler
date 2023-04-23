package shedulers;

import tasks.Task;

import java.util.LinkedList;

public class HighRobin {

    private final LinkedList<Task> tasks;
    private final LinkedList<Task> arrived;
    private final int slice;


    public HighRobin(int s){
        slice = s;
        tasks = new LinkedList<>();
        arrived = new LinkedList<>();
    }

    public void addTask(Task t){
        tasks.add(t);
    }

    public void run(int time){
        int t = time;
        int ctDown = slice;
        boolean changed = true;


        while(!tasks.isEmpty() || !arrived.isEmpty()){

            if(!arrived.isEmpty()){
                ctDown--;

                if(changed) {
                    System.out.print(arrived.getFirst().getName());
                    changed = false;
                }

                if(arrived.getFirst().decrease(1, t)){
                    arrived.removeFirst();
                    ctDown = 2;
                    changed = true;
                }
            }

            while(!tasks.isEmpty()){
                if(tasks.getFirst().getStart() != t)
                    break;
                arrived.add(tasks.removeFirst());
            }

            if(ctDown == 0){
                if(arrived.size()>1) {
                    arrived.add(arrived.removeFirst());
                    changed = true;
                }
                ctDown = 2;
            }

            t++;
        }
    }
}
