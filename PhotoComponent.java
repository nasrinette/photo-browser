// PhotoComponent.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class PhotoComponent extends JComponent {
    private final PhotoModel model = new PhotoModel();
    private final PhotoView view = new PhotoView();

    public PhotoComponent() {
        setPreferredSize(new Dimension(800, 600));

        addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    model.flipped = !model.flipped;
                    repaint();
                }
            }
        });
    }

    // Load an image file and show it
    public void loadPhoto(String path) throws IOException {
        BufferedImage img = ImageIO.read(new File(path));
        model.photo = img;
        revalidate(); 
        repaint();
    }

    @Override public Dimension getPreferredSize() {
        if (model.photo != null) {
            return new Dimension(model.photo.getWidth(), model.photo.getHeight());
        }
        return super.getPreferredSize(); // fallback 800x600
    }

    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        view.paint((Graphics2D) g, getSize(), model);
    }

    public PhotoModel getModel() { return model; }
}
