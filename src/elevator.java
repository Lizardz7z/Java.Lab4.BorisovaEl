import static java.lang.Math.abs;

public class elevator implements Runnable{
    public static int[] pos={1, 1};
    public int numb;
    public int destination=-1;
    public int adder=2;
    public el_stage stage;
    public boolean isWork=true;



    elevator(int n){
        numb=n;
        stage=new el_stage();
    }
    public static synchronized void print_elevators(){
        synchronized (pos) {
            System.out.println();
            for (int i = 15; i > 0; --i) {
                if (i<10){
                    System.out.print(" "+i);
                }
                else System.out.print(i);
                if (pos[0]==i){
                    System.out.print(" |1|");
                }
                else{
                    System.out.print(" | |");
                }
                if (pos[1]==i){
                    System.out.println("|2|");
                }
                else{
                    System.out.println("| |");
                }
            }
            System.out.println();
        }
    }

    public void run(){
        while(isWork || !stage.stops.isEmpty()){
            synchronized (stage) {
                if (!stage.stops.isEmpty()) {
                    //System.out.print("Elevator "+numb+" is now");
                    destination = stage.stops.get(0);
                    if (destination < stage.cur_floor) {
                        //System.out.print(" going down to "+ destination + ". Now is on ");
                        adder = -1;
                        stage.direction = 0;
                    } else if (destination > stage.cur_floor) {
                        //System.out.print(" going up to " + destination + ". Now is on ");
                        adder = 1;
                        stage.direction = 1;
                    } else if (destination == stage.cur_floor) {
                        //System.out.print(" standing on ");
                        adder = 0;
                        stage.stops.remove(0);
                        stage.direction = -1;
                    }
                    stage.cur_floor += adder;
                    //System.out.println(stage.cur_floor);
                    synchronized (pos) {
                        pos[numb - 1] = stage.cur_floor;
                        print_elevators();
                    }
                }
                try {
                    if (adder == 0) {
                        ;
                        Thread.sleep(3000);
                    } else if (abs(adder) == 1) {
                        Thread.sleep(1500);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Elevator was interrupted. Number is " + numb);
                }
            }
            try {
                if (adder==2 || (stage.stops.isEmpty() && adder==0)) {
                    Thread.sleep(100);
                }
            }catch (InterruptedException e){
                System.out.println("Elevator was interrupted. Number is "+ numb);
            }
        }
        System.out.println("Elevator "+numb+" finish");
    }
}
