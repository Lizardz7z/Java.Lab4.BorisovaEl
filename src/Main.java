import java.util.ArrayList;

import static java.lang.Math.abs;

public class Main {
    static int find_pos(ArrayList<Integer> arr, int cur_d, int target, int start){
        int prev= arr.get(0);
        int i=0;
        boolean SWF=false;
        for(int a:arr){
            if (a!=prev){
                if (SWF==true && (prev<a && target>a)||(prev>a && target<a))
                    return i+1;
            }
            if (start==a){
                SWF=true;
            }
            ++i;
        }
        return -1;
    }
    public static void main(String[] args) throws Exception{
        floors_stage[] floors=new floors_stage[15];
        for (int i=0; i<15;++i){
            floors[i]=new floors_stage();
        }

        int n=20;

        elevator el1=new elevator(1);
        elevator el2=new elevator(2);

        Thread t1, t2;
        t1=new Thread(el1);
        t2=new Thread(el2);

        t1.start();
        t2.start();

        for (int i=0;i<n;++i) {
            event a = new event();
            synchronized (el1.stage) {
                synchronized (el2.stage){
                    if (el1.stage.stops.isEmpty() && abs(el1.stage.cur_floor-a.floor)<=abs(el2.stage.cur_floor-a.floor)) {
                        el1.stage.stops.add(a.floor);
                        el1.stage.stops.add(a.destination);
                        System.out.println("Elevator1 get the task:");
                        a.print_event();
                    }
                    else if (el2.stage.stops.isEmpty() && abs(el2.stage.cur_floor-a.floor)<=abs(el1.stage.cur_floor-a.floor)) {
                        el2.stage.stops.add(a.floor);
                        el2.stage.stops.add(a.destination);
                        System.out.println("Elevator2 get the task:");
                        a.print_event();
                    }
                    else if ((el1.stage.direction==1 && a.floor>el1.stage.cur_floor && a.floor<el1.stage.stops.get(0))||(el1.stage.direction==0 && a.floor<el1.stage.cur_floor && a.floor>el1.stage.stops.get(0))) {
                        if (el1.stage.stops.contains(a.floor)) {
                            el1.stage.stops.remove(el1.stage.stops.indexOf(a.floor));
                        }
                        el1.stage.stops.add(0, a.floor);
                        if (!el1.stage.stops.contains(a.destination)){
                            int temp=find_pos(el1.stage.stops, el1.stage.direction, a.destination, a.floor);
                            if (temp==-1){
                                el1.stage.stops.add(a.destination);
                            }
                            else el1.stage.stops.add(temp, a.destination);
                        }
                        System.out.println("Elevator1 get the task:");
                        a.print_event();
                    }
                    else if ((el2.stage.direction==1 && a.floor>el2.stage.cur_floor && a.floor<el2.stage.stops.get(0))||(el2.stage.direction==0 && a.floor<el2.stage.cur_floor && a.floor>el2.stage.stops.get(0))) {
                        if (el2.stage.stops.contains(a.floor)) {
                            el2.stage.stops.remove(el2.stage.stops.indexOf(a.floor));
                        }
                        el2.stage.stops.add(0, a.floor);
                        if (!el2.stage.stops.contains(a.destination)){
                            int temp=find_pos(el2.stage.stops, el2.stage.direction, a.destination, a.floor);
                            if (temp==-1){
                                el2.stage.stops.add(a.destination);
                            }
                            else el2.stage.stops.add(temp, a.destination);
                        }
                        System.out.println("Elevator2 get the task:");
                        a.print_event();
                    }
                    else{
                        if (!el1.stage.stops.isEmpty() && !el2.stage.stops.isEmpty() && abs(el1.stage.stops.get(el1.stage.stops.size()-1)-a.floor)<=abs(el2.stage.stops.get(el2.stage.stops.size()-1)-a.floor)){
                            if (!el1.stage.stops.contains(a.floor))
                                el1.stage.stops.add(a.floor);
                            if (!el1.stage.stops.contains(a.destination))
                                el1.stage.stops.add(a.destination);
                            System.out.println("Elevator1 get the task:");
                            a.print_event();
                        }
                        else{
                            el2.stage.stops.add(a.floor);
                            el2.stage.stops.add(a.destination);
                            System.out.println("Elevator2 get the task:");
                            a.print_event();
                        }
                    }
                }
            }
            Thread.sleep(5000);
        }
        synchronized (el1) {
            el1.isWork = false;
        }
        synchronized (el2) {
            el2.isWork = false;
        }

    }
}