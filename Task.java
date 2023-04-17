import java.util.ArrayList;
import java.util.Scanner;

public class Task {
    public String name;
    public int prio;
    public int start;
    public int burst;

    public int wait;

    public int hanyszorfutott;



    public Task(String n, String p, String s, String b) {
        name = n;
        prio = Integer.parseInt(p);
        start = Integer.parseInt(s);
        burst = Integer.parseInt(b);
        hanyszorfutott=0;
    }

    public int compareTo(Task t){
        return Integer.compare(start, t.start);
    }

    public void print() {
        System.out.println(name + " " + prio + " " + start + " " + burst);
    }

}
