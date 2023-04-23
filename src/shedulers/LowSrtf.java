package shedulers;

import tasks.Task;

import java.util.LinkedList;

public class LowSrtf {

    private final LinkedList<Task> tasks;
    private final LinkedList<Task> arrived;


    public LowSrtf(){
        tasks = new LinkedList<>();
        arrived = new LinkedList<>();
    }

    public void addTask(Task t){
        tasks.add(t);
    }


    //startTime az erkezesi utem, a koviben lehet inditani a task futtatast
    public void run(int startTime, int length){
        int t = startTime;
        boolean changed = true;
        boolean valid = false;

        while(t <= startTime+length){

            if(!valid && !arrived.isEmpty()){
                changed = isChanged(changed);
            }

            if(!arrived.isEmpty() && valid){
                if(changed){
                    System.out.print(arrived.getFirst().getName());
                    changed = false;
                }
                if(arrived.getFirst().decrease(1, t)){
                    arrived.removeFirst();
                    if(!arrived.isEmpty())
                        isChanged(changed);
                    changed = true;
                }
            }

            boolean newMember = false;

            while(!tasks.isEmpty()){
                if(tasks.getFirst().getStart() > t)
                    break;
                arrived.add(tasks.removeFirst());

               newMember = true;

            }

            if(newMember)
                changed = isChanged(changed);

            valid = true;
            t++;
        }

        if(!arrived.isEmpty()){
            arrived.add(arrived.removeFirst());
        }
    }

    private boolean isChanged(boolean changed) {
        int min = Integer.MAX_VALUE;
        Task mT = null;

        for (Task task: arrived) {
            if(task.getRemaining() < min){
                min = task.getRemaining();
                mT = task;
            }
        }

        if (arrived.getFirst() != mT)
            changed = true;
        while (arrived.getFirst() != mT) {
            arrived.add(arrived.removeFirst());
        }
        return changed;
    }
}
