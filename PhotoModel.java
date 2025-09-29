import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class PhotoModel {
    private boolean flipped = false;
    private BufferedImage photo;
    private final List<StrokePath> strokeList = new ArrayList<>();
    private final List<TextBlock> textList = new ArrayList<>();

    public boolean isFlipped() {
        return flipped;
    }
    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    public BufferedImage getPhoto() {
        return photo;
    }
    public void setPhoto(BufferedImage photo) {
        this.photo = photo;
    }

    public List<StrokePath> getStrokeList() {
        return strokeList;
    }
    public List<TextBlock> getTextList() {
        return textList;
    }
}
