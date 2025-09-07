import javax.swing.*;
import javax.swing.*;
import java.awt.*;
public class Converter extends JFrame {
    // private JLabel resultLabel;
    public Converter() {
        super("Photo browser");
        // var tempPanel = new JPanel();
        // add(tempPanel, BorderLayout.CENTER);

        // var tempLabel = new JLabel("Temperature");
        // tempPanel.add(tempLabel);

        // var input = new JTextField( 10);
        // tempPanel.add(input);
        // setPreferredSize(new Dimension(600, 600));


    setPreferredSize(new Dimension(1000, 800));
  
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


       setJMenuBar(menuBar);
       var label = new JLabel("Main panel");
      add(label);
      pack();
    //   setVisible(true);



}

// private void setupInputPanel(){
//      var tempPanel = new JPanel();
//         add(tempPanel, BorderLayout.NORTH);

//         var tempLabel = new JLabel("Temperature");
//         tempPanel.add(tempLabel);

//         var input = new JTextField( 10);
//         tempPanel.add(input);
//         setPreferredSize(new Dimension(600, 600));
//         // pack();


//     }
// private void setupButtonPanel(){
//     var btnPanel = new JPanel();
//     var convertToF = new JButton("Convert to F");   btnPanel.add(convertToF);
// convertToF.addActionListener(e->{
//     System.out.println("Convert to F button clicked");
//     resultLabel.setText("Result: 212 F");
// });
//     var convertToC = new JButton("Convert to C");    btnPanel.add(convertToC);

//     add(btnPanel, BorderLayout.SOUTH);

// }


// private void setupResultPanel(){
//     var resultPanel = new JPanel();

// resultLabel = new JLabel("Result:");
// resultPanel.add(resultLabel);
// add(resultPanel, BorderLayout.CENTER);

// }
}
        