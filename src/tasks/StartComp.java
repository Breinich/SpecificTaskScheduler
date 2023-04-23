package tasks;

import java.util.Comparator;

public class StartComp implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        return o1.getStart() - o2.getStart();
    }
}
