package shedulers;

import shedulers.HighRobin;
import shedulers.LowSrtf;
import tasks.NameComp;
import tasks.PriorComp;
import tasks.StartComp;
import tasks.Task;

import java.util.LinkedList;

public class Global {

    private final HighRobin hr;
    private final LowSrtf ls;
    private final LinkedList<Task> tasks;
    private final LinkedList<Task> alltask;

    public Global(HighRobin h, LowSrtf l){
        hr = h;
        ls = l;
        tasks = new LinkedList<>();
        alltask = new LinkedList<>();
    }

    public void addTask(Task t){
        tasks.add(t);
        alltask.add(t);
    }

    public void run(){
        PriorComp pcomp = new PriorComp();
        StartComp scomp = new StartComp();
        NameComp ncomp = new NameComp();
        tasks.sort(pcomp);
        alltask.sort(ncomp);
        tasks.sort(scomp);
        alltask.sort(scomp);

        int time = 0;
        //start ütem, ebben érkeztetendõ a task a megfelelõ ütemezõbe, a köviben indulhat a task futtatása
        int robinRange = 0;
        int robinStart = 0;

        int srtfStart = 0;
        int srtfRange = 0;

        do {
            while (!tasks.isEmpty()) {
                if(tasks.getFirst().getStart() != time)
                    break;
                if (tasks.getFirst().getPriority() == 1) {

                    if (robinRange == 0) {
                        robinStart = time;
                        robinRange = tasks.getFirst().getLast();
                    } else {
                        robinRange += tasks.getFirst().getLast();
                    }
                    hr.addTask(tasks.removeFirst());
                }
                else if (tasks.getFirst().getPriority() == 0) {

                    if (srtfRange == 0) {
                        srtfRange = tasks.getFirst().getLast();
                        srtfStart = time;
                    } else {
                        srtfRange += tasks.getFirst().getLast();
                    }
                    ls.addTask(tasks.removeFirst());
                }
            }

            if (srtfRange > 0 && robinRange > 0) {
                if (srtfStart < robinStart) {
                    if (srtfStart + srtfRange <= robinStart) {
                        ls.run(srtfStart, srtfRange);
                        srtfRange = 0;
                    } else {
                        ls.run(srtfStart, robinStart - srtfStart);
                        srtfRange -= robinStart - srtfStart;
                        srtfStart = robinStart + robinRange;
                    }
                } else if (robinStart + robinRange <= time ) {
                    hr.run(robinStart);
                    srtfStart = robinStart + robinRange;
                    robinRange = 0;

                }
            } else if (robinRange > 0) {
                if (robinStart + robinRange <= time ) {
                    hr.run(robinStart);
                    robinRange = 0;
                }
            } else if (srtfRange > 0) {
                if (srtfStart + srtfRange <= time) {
                    ls.run(srtfStart, srtfRange);
                    srtfRange = 0;
                }
            }

            time++;
        } while (!tasks.isEmpty() || robinRange != 0 || srtfRange != 0);

        System.out.print("\n"+alltask.get(0).getName()+":"+alltask.get(0).getLatency());
        for(int i = 1; i < alltask.size(); i++){
            Task t = alltask.get(i);
            System.out.print(","+t.getName()+":"+t.getLatency());
        }
    }
}
