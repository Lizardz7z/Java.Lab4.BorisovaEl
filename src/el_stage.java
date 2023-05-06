import java.util.ArrayList;

public class el_stage {
    public int cur_floor;
    public int direction;
    public ArrayList<Integer> stops;
    el_stage(){
        cur_floor=1;
        direction=-1;
        stops=new ArrayList<Integer>();
    }
}
