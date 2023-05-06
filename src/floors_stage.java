import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class floors_stage {
    public ArrayList<event> events_up;
    public ArrayList<event> events_down;
    public int cur_events=0;
    public int up=0;
    public int down=0;
    floors_stage(){
        events_up = new ArrayList<event>();
        events_down = new ArrayList<event>();
    }
    int find_pos(int dir, event b){
        if (dir==1){
            int i=0;
            for (event a:events_up){
                if (b.destination<a.destination){
                    return i+1;
                }
                ++i;
            }
            return -1;
        }
        else{
            int i=0;
            for (event a:events_down){
                if (b.destination>a.destination){
                    return i+1;
                }
                ++i;
            }
            return -1;
        }
    }
    void add_event(event a){
        if (a.direction==1){
            int i=find_pos(1, a);
            if (i!=-1) {
                events_up.add(i, a);
            }
            else {
                events_up.add(a);
            }
            up++;
        }
        else{
            int i=find_pos(0, a);
            if (i!=-1){
                events_down.add(i, a);
            }
            else{
                events_down.add(a);
            }
            down++;
        }
        ++cur_events;
    }
}
