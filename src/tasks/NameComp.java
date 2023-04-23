package tasks;

import java.util.Comparator;

public class NameComp implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        return o1.getName()-o2.getName();
    }
}
