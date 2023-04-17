import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] data = new String[10][4];
        int db = 0;
        String line = null;

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.isEmpty()) break;
            String[] parts = line.split(",");
            for (int j = 0; j < 4; j++) {
                data[db][j] = parts[j];
            }
            db++;
        }
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < db; i++) {
            tasks.add(new Task(data[i][0], data[i][1], data[i][2], data[i][3]));
        }

        Scheduler sch = new Scheduler();
        sch.order(tasks, db);


//        System.out.println(sch.list);
//        for (int i = 0; i < db; i++) {
//            System.out.print(sch.name[i] + ":");
//            System.out.print(sch.wait[i]);
//            if (i < db - 1) {
//                System.out.print(",");
//            }
//        }
    }
}