import java.util.ArrayList;

public class Scheduler {

    public int[] wait = new int[10];
    public String list = "";
    public String[] name = new String[10];

    public void order(ArrayList<Task> tasks, int db) {
        int futo = 0;
        int ido = 0;
        boolean futprio=true;
        for (int i = 0; i < db; i++) {
            name[i] = tasks.get(i).name;
        }

        //hany utem lesz
        int utem = 0;
        for (int i = 0; i < db; i++) {
            utem += tasks.get(i).burst;
        }
        //futo=11;
        //utemezes kezdese


        for (int u = 0; u < utem; u++) {
            if(tasks.get(futo).prio==1 && tasks.get(futo).burst==0){
                futprio=false;
            }
            for(int i=0; i<db; i++){
                if(tasks.get(futo).prio==0){
                    futprio=false;
                }
                if(tasks.get(i).prio==0){
                    tasks.get(i).hanyszorfutott=0;
                }
            }

            //RR
            //if(futprio){
            for(int i=0; i<db; i++){
                tasks.get(i).print();
                System.out.println("RR");
                if(tasks.get(i).prio==1 && tasks.get(futo).prio==0 && tasks.get(i).burst!=0){
                    futo=i;
                    futprio=true;
                }
                if(tasks.get(futo).hanyszorfutott==2 && i!=futo &&  tasks.get(i).prio==1 ){
                    futo=i;
                    futprio=true;
                }
                if(tasks.get(i).start==ido && tasks.get(futo).burst==0 && tasks.get(i).hanyszorfutott!=2 && tasks.get(i).prio==1){
                    futo=i;
                    futprio=true;
                }
            }
            System.out.println(futo);
            //}

            //SRTF
            //osszehasonlitas a tobbivel
            if(!futprio){
                System.out.println(futo);
                for(int i=0; i<db; i++){
                    tasks.get(i).print();
                    if(tasks.get(i).start==ido && tasks.get(i).prio==0){
                        futo=i;
                    }
                    if(tasks.get(i).start==ido && tasks.get(futo).burst==0 && tasks.get(i).prio==0){
                        futo=i;
                    }
                    if(tasks.get(i).start == ido && tasks.get(futo).name.compareTo(tasks.get(i).name)>0 && tasks.get(i).prio==0){
                        futo=i;
                    }
                    if(tasks.get(i).start == ido  && tasks.get(i).burst<tasks.get(futo).burst && tasks.get(i).prio==0){
                        futo=i;
                    }
                }
            }

            //lepes
            ido++;
            if(futo!=11){
                tasks.get(futo).burst--;
                tasks.get(futo).hanyszorfutott++;
                if(list.isEmpty()){
                    list+=tasks.get(futo).name;
                }
                else{
                    if(list.charAt(list.length()-1)!=tasks.get(futo).name.charAt(tasks.get(futo).name.length() - 1)){
                        list+=tasks.get(futo).name;
                    }
                }
                for(int i=0; i<db; i++){
                    if(tasks.get(i).start<ido && tasks.get(i).burst!=0){
                        tasks.get(i).start=ido;
                        if(i!=futo){
                            wait[i]++;
                        }
                    }
                    if(i!=futo){
                        tasks.get(i).hanyszorfutott=0;
                    }
                }
            }
            if(futo==11){
                u--;
            }
        }
    }


}


