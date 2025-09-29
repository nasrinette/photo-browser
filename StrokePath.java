import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class StrokePath {
    public List<Point> points =  new ArrayList<>();
    public float width = 3f;
    public void addPoint(Point p){
        points.add(p);
    }

}
