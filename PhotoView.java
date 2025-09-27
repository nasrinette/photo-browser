// PhotoView.java
import java.awt.*;


public final class PhotoView {
    public void paint(Graphics2D g2, Dimension size, PhotoModel model) {
        // clear / background
        g2.setColor(new Color(245,245,245));
        g2.fillRect(0, 0, size.width, size.height);

        if (!model.flipped && model.photo != null) {
            // draw the image at 1:1 in the top-left 
            g2.drawImage(model.photo, 0, 0, null);
        } else {
            // show the “back” same size as the photo if present
            int w = (model.photo != null) ? model.photo.getWidth() : size.width;
            int h = (model.photo != null) ? model.photo.getHeight() : size.height;
            g2.setColor(Color.white);
            g2.fillRect(0, 0, w, h);
            g2.setColor(new Color(0,0,0,40));
            g2.drawRect(0, 0, w-1, h-1);
        }
    }
}
