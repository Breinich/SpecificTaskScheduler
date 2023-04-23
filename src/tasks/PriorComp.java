package tasks;

import java.util.Comparator;

public class PriorComp implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        return o2.getPriority() - o1.getPriority();
    }
}
