import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class PhotoLib extends JFrame {
    private JLabel statusLabel;
    private final JPanel root = new JPanel(new BorderLayout(10,10)); // ROOT

    public PhotoLib() {
        super("Photo browser");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(root);                    
        setPreferredSize(new Dimension(1300, 900));
        // setMinimumSize(new Dimension(600, 300));

        setupMenuBar();
        setupMainPanel();   // root.adds CENTER
        setupToolbar();     // root.adds NORTH
        setupStatusBar();   // root.adds SOUTH

        pack();
    }

    private void setupMenuBar(){
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
     
       var viewRdItem = new JRadioButtonMenuItem("View photo", true);

       var browseRdItem = new JRadioButtonMenuItem("Browser");
       viewMenu.add(viewRdItem);
       viewMenu.add(browseRdItem);

       
       var viewGroup = new ButtonGroup();
       viewGroup.add(viewRdItem);
       viewGroup.add(browseRdItem);

        ActionListener importListener = e->importFile();
        ActionListener quitListener = e-> quitApp();
        ActionListener viewListener = e->photoViewer();
        ActionListener browseListener = e->photoBrowser();
        ActionListener deleteListener = e->deletePhoto();

        viewRdItem.addActionListener(viewListener);
        browseRdItem.addActionListener(browseListener);
        importItem.addActionListener(importListener);
        deleteItem.addActionListener(deleteListener);
        quitItem.addActionListener(quitListener);

       setJMenuBar(menuBar);
    
}
private void setupMainPanel() {
    PhotoComponent photo = new PhotoComponent();
    try {
    photo.loadPhoto("me.jpg"); 
} catch (Exception ex) {
    ex.printStackTrace();
}
    JScrollPane scrollPane = new JScrollPane(photo);
    root.add(scrollPane, BorderLayout.CENTER);  



}

private void setupStatusBar(){
  var statusPanel =  new JPanel();
  statusLabel = new JLabel("Status bar");
  statusPanel.setBackground(Color.decode("#ffffff"));
  statusPanel.setLayout(new BorderLayout());
  statusPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

  statusPanel.add(statusLabel, BorderLayout.WEST);
   
  root.add(statusPanel, BorderLayout.SOUTH);

}
private void setupToolbar(){
  var toolbarPanel = new JPanel();

  root.add(toolbarPanel, BorderLayout.NORTH);
  var label = new JLabel("Categories");
  toolbarPanel.add(label);
  ActionListener categListener = e->printCategory(e);

  var peopleToggle = new JToggleButton("People");
  peopleToggle.addActionListener(categListener);

  toolbarPanel.add(peopleToggle);
  var placesToggle = new JToggleButton("Places");
  placesToggle.addActionListener(categListener);
  toolbarPanel.add(placesToggle);
  var schoolToggle = new JToggleButton("School");
  schoolToggle.addActionListener(categListener);
  toolbarPanel.add(schoolToggle);
}

private void printCategory(ActionEvent e){
      statusLabel.setText("Selected category: "+ e.getActionCommand());

}
private void importFile(){

     //Got this code from Oracle documentation: https://docs.oracle.com/javase/8/docs/api/javax/swing/JFileChooser.html
     JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG & GIF Images", "jpg", "gif");
    chooser.setFileFilter(filter);
    int returnVal = chooser.showOpenDialog(this);
    if(returnVal == JFileChooser.APPROVE_OPTION) {
      statusLabel.setText("You chose to open this file: " +
            chooser.getSelectedFile().getName());
    }
}

private void quitApp(){
  System.exit(0);
}
private void deletePhoto(){
  statusLabel.setText("Deleting a photo...");
}
private void photoViewer(){
  statusLabel.setText("Photo viewer: shows one photo at a time");
}
private void photoBrowser(){
  statusLabel.setText("Browser: shows a grid of thumbnails");
}
}
        