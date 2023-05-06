import java.util.Random;

import static java.lang.Math.abs;

public class event{
    static Random rand=null;
    public int floor;
    public int destination;
    public int direction; // 1 - up, 0 - down
    event(){
        if (rand==null){
            rand= new Random();
        }
        floor= rand.nextInt(15)+1;
        destination= rand.nextInt(15)+1;
        if (destination==floor){
            if (floor<15/2){
                destination=14;
            }
            else{
                destination=1;
            }
        }
        if (destination>floor)
            direction=1;
        else direction=0;

    }
    void print_event(){
        System.out.println(floor+"-->"+destination);
    }
}
