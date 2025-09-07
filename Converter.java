import javax.swing.*;
import javax.swing.*;
import java.awt.*;
public class Converter extends JFrame {
    private JLabel resultLabel;
    public Converter() {
        super("Temperature Converter");
        setupInputPanel();
        // var tempPanel = new JPanel();
        // add(tempPanel, BorderLayout.CENTER);

        // var tempLabel = new JLabel("Temperature");
        // tempPanel.add(tempLabel);

        // var input = new JTextField( 10);
        // tempPanel.add(input);
        // setPreferredSize(new Dimension(600, 600));
setupButtonPanel();
setupResultPanel();
        pack();




}

private void setupInputPanel(){
     var tempPanel = new JPanel();
        add(tempPanel, BorderLayout.NORTH);

        var tempLabel = new JLabel("Temperature");
        tempPanel.add(tempLabel);

        var input = new JTextField( 10);
        tempPanel.add(input);
        setPreferredSize(new Dimension(600, 600));
        // pack();


    }
private void setupButtonPanel(){
    var btnPanel = new JPanel();
    var convertToF = new JButton("Convert to F");   btnPanel.add(convertToF);
convertToF.addActionListener(e->{
    System.out.println("Convert to F button clicked");
    resultLabel.setText("Result: 212 F");
});
    var convertToC = new JButton("Convert to C");    btnPanel.add(convertToC);

    add(btnPanel, BorderLayout.SOUTH);

}


private void setupResultPanel(){
    var resultPanel = new JPanel();

resultLabel = new JLabel("Result:");
resultPanel.add(resultLabel);
add(resultPanel, BorderLayout.CENTER);

}
}
        