import java.util.ArrayList;

public class Scheduler {

    public int[] wait=new int[10];
    public String list="";
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
            //RR
            //osszehasonlitas a tobbivel
            for(int i=0; i<db; i++){
                tasks.get(i).print();
                if( tasks.get(i).start==ido && tasks.get(futo).burst==0){
                    futo=i;
                }
                if(tasks.get(i).start == ido && tasks.get(futo).prio<tasks.get(i).prio){
                    futo=i;
                }
                if(tasks.get(i).start == ido /*&& tasks.get(futo).prio==tasks.get(i).prio*/ && tasks.get(futo).name.compareTo(tasks.get(i).name)>0){
                    futo=i;
                }
                if(tasks.get(i).start == ido /* && tasks.get(futo).prio==tasks.get(i).prio*/ && tasks.get(i).burst<tasks.get(futo).burst){
                    futo=i;
                }

            }
            //lepes
            ido++;
            tasks.get(futo).burst--;

            list+=tasks.get(futo).name;
            for(int i=0; i<db; i++){
                if(tasks.get(i).start<ido && tasks.get(i).burst!=0){
                    tasks.get(i).start=ido;
                    if(i!=futo){
                        wait[i]++;
                    }
                }
            }
        }
    }

    public void print(){
        System.out.println(list);
    }
}
