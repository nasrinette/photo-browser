// PhotoView.java
import java.awt.*;


public final class PhotoView {
    public void paint(Graphics2D g2, Dimension size, PhotoModel model) {
        // clear / background
        // g2.setColor(new Color(245,245,245));
        // g2.fillRect(0, 0, size.width, size.height);

        if (!model.flipped && model.photo != null) {
            // draw the image top-left 
            g2.drawImage(model.photo, 0, 0, null);
        } else {
            // show the “back” same size as the photo if present
            int w = (model.photo != null) ? model.photo.getWidth() : size.width;
            int h = (model.photo != null) ? model.photo.getHeight() : size.height;
            g2.setColor(Color.white);
            g2.fillRect(0, 0, w, h);
            g2.drawRect(0, 0, w-1, h-1);

            if(model.strokeList.size()>0){
            g2.setColor(Color.black);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            for (StrokePath s : model.strokeList) {
                for (int i = 1; i < s.points.size(); i++) {
                    Point p0 = s.points.get(i-1);
                    Point p1 = s.points.get(i);
                    g2.setStroke(new BasicStroke(s.width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    g2.drawLine(p0.x, p0.y, p1.x, p1.y);
                }
            }}
            if (model.textList.size()>0){
                g2.setColor(Color.black);

                for (TextBlock tb: model.textList){
                    System.out.println("some text "+ tb.text);
                     g2.drawString(tb.text.toString(), tb.position.x, tb.position.y);
                }
                
            }
        }
    }
}
