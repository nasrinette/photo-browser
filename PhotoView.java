// PhotoView.java
import java.awt.*;

public final class PhotoView {

    public void paint(Graphics2D g2, Dimension size, PhotoModel model) {
       // clear / background
        // g2.setColor(new Color(245,245,245));
        // g2.fillRect(0, 0, size.width, size.height);

        if (!model.flipped && model.photo != null) {
            //draw the photo full size
            g2.drawImage(model.photo, 0, 0, null);
            return;
        }
        //  white card same size as photo (or component if no photo yet)
            int w = (model.photo != null) ? model.photo.getWidth() : size.width;
            int h = (model.photo != null) ? model.photo.getHeight() : size.height;
            Rectangle back = new Rectangle(0, 0, w, h);

        // draw white back + thin border
        g2.setColor(Color.WHITE);
        g2.fillRect(back.x, back.y, back.width, back.height);
        g2.setColor(new Color(0,0,0,40));
        g2.drawRect(back.x, back.y, back.width - 1, back.height - 1);

        // strokes
        if (model.strokeList != null && !model.strokeList.isEmpty()) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // clip so ink can't spill outside the back
            Shape oldClip = g2.getClip();
            g2.setClip(back);
            g2.setColor(Color.BLACK);
            for (StrokePath s : model.strokeList) {
                if (s.points.size() < 2) continue;
                g2.setStroke(new BasicStroke(s.width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                for (int i = 1; i < s.points.size(); i++) {
                    Point p0 = s.points.get(i - 1);
                    Point p1 = s.points.get(i);
                    g2.drawLine(p0.x, p0.y, p1.x, p1.y);
                }
            }
            g2.setClip(oldClip);
        }

        // text
        if (model.textList != null && !model.textList.isEmpty()) {
            // text rendering
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setColor(Color.BLACK); // text color 
            for (TextBlock tb : model.textList) {
                drawWrappedTextInBack(g2, tb, back);
            }
        }
    }

   
    //   Draw a TextBlock starting at tb.position, wrapping at the right edge of 'back'.
    //   Wrap at spaces when possible; if a single word is too long, break by characters.
    //   Stops when reaching the bottom of 'back'.
    
    private void drawWrappedTextInBack(Graphics2D g2, TextBlock tb, Rectangle back) {
        if (tb == null || tb.text == null || tb.position == null) return;

        FontMetrics fm = g2.getFontMetrics();
        int ascent  = fm.getAscent();
        int descent = fm.getDescent();
        int leading = fm.getLeading();
        int lineHeight = ascent + descent + leading;

        // starting baseline and available width from the insertion x to the back's right edge
        int x = tb.position.x;
        int baselineY = tb.position.y;
        int maxRight = back.x + back.width;
        int maxWidth = Math.max(0, maxRight - x);

        // If insertion point is outside the back or width is zero, nothing to draw
        if (!back.contains(x, baselineY) || maxWidth <= 0) return;

        // Split into paragraphs on explicit newlines (preserve empty lines)
        String[] paragraphs = tb.text.toString().split("\n", -1);

        for (int pi = 0; pi < paragraphs.length; pi++) {
            String para = paragraphs[pi];

            // blank line: just move down
            if (para.isEmpty()) {
                baselineY += lineHeight;
                if (baselineY + descent > back.y + back.height) return; // no more vertical space
                continue;
            }

            String[] words = para.split(" ");
            StringBuilder line = new StringBuilder();

            for (String w : words) {
                String candidate = (line.length() == 0) ? w : line + " " + w;

                if (fm.stringWidth(candidate) <= maxWidth) {
                    // still fits -> keep building this line
                    line.setLength(0);
                    line.append(candidate);
                } else {
                    // doesn't fit -> draw current line if it has content
                    if (line.length() > 0) {
                        g2.drawString(line.toString(), x, baselineY);
                        baselineY += lineHeight;
                        if (baselineY + descent > back.y + back.height) return; // ran out of space
                        line.setLength(0);
                    }

                    //  try to place the word 'w'. If too long for an empty line, hard-break it.
                    String word = w;
                    while (!word.isEmpty() && fm.stringWidth(word) > maxWidth) {
                        int cut = word.length();
                        // shrink cut until the left chunk fits
                        while (cut > 0 && fm.stringWidth(word.substring(0, cut)) > maxWidth) {
                            cut--;
                        }
                        if (cut == 0) break; // nothing fits, bail to avoid infinite loop
                        String chunk = word.substring(0, cut);
                        g2.drawString(chunk, x, baselineY);
                        baselineY += lineHeight;
                        if (baselineY + descent > back.y + back.height) return; // no more space
                        word = word.substring(cut);
                    }
                    // any leftover of the word starts the next line
                    if (!word.isEmpty()) line.append(word);
                }
            }

            // draw the last partial line of this paragraph
            if (line.length() > 0) {
                g2.drawString(line.toString(), x, baselineY);
                baselineY += lineHeight;
                if (baselineY + descent > back.y + back.height) return;
            } 
            
        }
    }
}
