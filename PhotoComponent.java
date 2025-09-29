// PhotoComponent.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;

import java.awt.event.MouseEvent;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class PhotoComponent extends JComponent {
    private final PhotoModel model = new PhotoModel();
    private final PhotoView view = new PhotoView();
    StrokePath currentStroke;
    TextBlock currentText;
    public PhotoComponent() {

        addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    model.setFlipped(!model.isFlipped());
                   
                    repaint();
                }
                if (model.isFlipped() && e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
                    currentText = new TextBlock(e.getPoint());
                    model.getTextList().add(currentText);
                    requestFocusInWindow();  // so typing goes here
                    setFocusable(true); 
                    repaint();
                 }
         
            }
          
              @Override public void mousePressed(MouseEvent e) {
                if (model.isFlipped()){
                   currentStroke = new StrokePath();
                    currentStroke.addPoint(e.getPoint());
                    model.getStrokeList().add(currentStroke);
                    
                   
                    // repaint();
                    System.out.println("mouse pressed" + e.getPoint());
                }
         
            }   
       
            @Override public void mouseReleased(MouseEvent e){
                currentStroke=null;
                System.out.println("mouse released" + e.getPoint());
            }
          
        });
          addKeyListener(new KeyAdapter() {
              @Override public void keyTyped(KeyEvent e){
                 if (currentText != null && model.isFlipped()) {
                    
                char c = e.getKeyChar();
                if (!Character.isISOControl(c)) {   // skip backspace, enter, etc.
                    currentText.text.append(c);
                    System.out.println(currentText.text);
                    repaint();
                        }
                    }
                }

            @Override
            public void keyPressed(KeyEvent e) {
                if (currentText != null && model.isFlipped()) {
                    if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                        StringBuilder text = currentText.text;
                        if (text.length() > 0) {
                            text.deleteCharAt(text.length() - 1);
                            repaint();
                        }
                    }
                }
            }

          });
            addMouseMotionListener(new MouseMotionAdapter() {
            @Override public void mouseDragged(MouseEvent e){
                            if(model.isFlipped()&&currentStroke!=null){
                                currentStroke.addPoint(e.getPoint());
                            System.out.println("mouse dragged" + e.getPoint());
                                repaint();
                            }

                        }


            });
     
    }

    // Load an image file and show it
    public void loadPhoto(String path) throws IOException {
        BufferedImage img = ImageIO.read(new File(path));
        model.setPhoto(img);
        revalidate();
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        if (model.getPhoto() != null) {
            // Match the real image size when a photo is present
            return new Dimension(model.getPhoto().getWidth(), model.getPhoto().getHeight());
        }
        // Default size before any photo is loaded
        return new Dimension(800, 600);
    }

    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        view.paint((Graphics2D) g, getSize(), model);
    }

    public PhotoModel getModel() { return model; }
}
