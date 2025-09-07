
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Main {
    public static void main(String[] args) {
       var frame = new JFrame("Photo browser");
       frame.setPreferredSize(new Dimension(1000, 800));
  
       var menuBar = new JMenuBar();
       var fileMenu = new JMenu("File");
       menuBar.add(fileMenu);

      var viewMenu = new JMenu("View");
       menuBar.add(viewMenu);

       var importItem = new JMenuItem("Import");
       var deleteItem = new JMenuItem("Delete");
       var quitItem = new JMenuItem("Quit");

       fileMenu.add(importItem);
       fileMenu.add(deleteItem);
       fileMenu.add(quitItem);

       var viewRdItem = new JRadioButtonMenuItem("View photo");
 

       var browseRdItem = new JRadioButtonMenuItem("Browser");
       viewMenu.add(viewRdItem);
       viewMenu.add(browseRdItem);

       
       var viewGroup = new ButtonGroup();
       viewGroup.add(viewRdItem);
       viewGroup.add(browseRdItem);


       frame.setJMenuBar(menuBar);
       var label = new JLabel("Main panel");
       frame.add(label);
       frame.pack();
       frame.setVisible(true);


    //    var temperatureConverter = new Converter();
    //    temperatureConverter.setVisible(true);
    }


}

  