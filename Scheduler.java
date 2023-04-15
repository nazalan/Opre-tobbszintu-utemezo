import java.util.ArrayList;

public class Scheduler {

    public int[] wait=new int[10];
    public String list;
    public String[] name=new String[10] ;
    public void order(ArrayList<Task> tasks, int db){
        int futo=0;
        int ido=0;
        for(int i=0; i<db; i++){
            name[i]=tasks.get(i).name;
        }

        //hany utem lesz
        int utem=0;
        for(int i=0; i<db; i++){
            utem+=tasks.get(i).burst;
        }

        //utemezes kezdese
        for(int u=0; u<utem; u++) {

            //osszehasonlitas a tobbivel
            for(int i=0; i<db; i++){
                tasks.get(i).print();
                if(tasks.get(futo).burst==0 && tasks.get(i).start==ido){
                    futo=i;
                }
                if(tasks.get(i).start == ido && tasks.get(futo).prio<tasks.get(i).prio){
                    futo=i;
                }
                if(tasks.get(i).start == ido && tasks.get(futo).prio==tasks.get(i).prio && tasks.get(futo).name.compareTo(tasks.get(i).name)>0){
                    futo=i;
                }
            }

            //lepes
            ido++;
            tasks.get(futo).burst--;
            tasks.get(futo).start=ido;
            list = new StringBuilder(tasks.get(futo).name).append(list).toString();
            for(int i=0; i<db; i++){
                if(i!=futo && tasks.get(i).start<ido && tasks.get(i).burst!=0){
                    wait[i]++;
                    tasks.get(i).start++;
                }
            }
        }
    }

    public void print(){
        System.out.println(list);
    }
}
