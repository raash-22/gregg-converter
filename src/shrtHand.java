import javafx.scene.image.Image;

import java.awt.*;
//shrthand
//has shrthand info and how they are displayed
public class shrtHand {
    private Image image;
    private Point start;
    private Point end;
    public shrtHand(String url, int x1, int y1, int x2, int y2){
        this(url, new Point(x1,y1), new Point(x2, y2));
    }
    public shrtHand(String url, Point start, Point end){
        this.image = new Image(url);
        this.start = start;
        this.end = end;
    }

    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
    public Point getStart() {
        return start;
    }
    public void setStart(Point start) {
        this.start.setLocation(start);
    }
    public void setStart(int x, int y){
        start.setLocation(x,y);
    }
    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end.setLocation(end);
    }

     void setEnd(int x, int y){
       this.end.setLocation(x, y);
    }

    public int getLength(){
        return end.x - start.x;
    }

}
