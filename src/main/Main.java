package main;

import shedulers.Global;
import shedulers.HighRobin;
import shedulers.LowSrtf;
import tasks.Task;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        HighRobin h = new HighRobin(2);
        LowSrtf l = new LowSrtf();

        Global global = new Global(h, l);

        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()){
            String input = sc.nextLine();
            String[] in = input.split(",");
            if(in.length == 4)
                global.addTask(new Task(in[0].charAt(0), Integer.parseInt(in[1]), Integer.parseInt(in[2]), Integer.parseInt(in[3])));
        }

        sc.close();

        global.run();
    }
}
