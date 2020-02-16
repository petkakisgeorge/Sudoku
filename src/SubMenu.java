import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This Class Stores the SubMenu Panel and its components
 * From the SubMenu you can choose to play Classic or Killer Sudoku or Duidoku
 * If you are connected you can also see your Duidoku stats
 * Here is the Panel number 2
 */
public class SubMenu{

    public JPanel panel2 = new JPanel();
    private JLabel pic1 = new JLabel();
    private JLabel pic2 = new JLabel();
    private JLabel pic3 = new JLabel();
    public  JButton button5 = new JButton("Classic Sudoku");
    public JButton button6 = new JButton("Killer Sudoku");
    public JButton button7 = new JButton("Duidoku");
    public static JButton newGameClassic  = new JButton();
    public static JButton newGameKiller = new JButton();
    public static JButton newGameDuidoku = new JButton();
    public JButton button8 = new JButton();
    public JButton WordokuClas = new JButton("Classic Wordoku");
    public JButton WordokuDui = new JButton("Wordoku");
    public static JLabel wins = new JLabel();
    public static JLabel loses = new JLabel();
    public static JLabel wins_amount = new JLabel();
    public static JLabel loses_amount = new JLabel();

    /**
     * SubMenu constructor just contains all the buttons , labels, pics that makes the panel.
     * The action listeners for the buttons are all stored in class GUI
     * wins,loses,wins_amount and loses_amount being added in GUI class, only if the user is connected
     */
    public SubMenu()
    {
        pic1.setIcon(new javax.swing.ImageIcon(getClass().getResource("Classic.jpg")));
        pic1.setBounds(100, 153, 200, 200);
        pic2.setIcon(new javax.swing.ImageIcon(getClass().getResource("Killer.png")));
        pic2.setBounds(445, 156, 200, 200);
        pic3.setIcon(new javax.swing.ImageIcon(getClass().getResource("duidoku.png")));
        pic3.setBounds(152, 400, 200, 200);
        panel2.add(pic1);
        panel2.add(pic2);
        panel2.add(pic3);

        JLabel labelAmount = new JLabel();
        Font font1 = new Font("SansSerif",Font.BOLD, 25);
        labelAmount.setFont(font1);
        labelAmount.setBounds(150, 45, 450, 30);
        ResourceBundle bundle = ResourceBundle.getBundle("change", Locale.getDefault());
        labelAmount.setText(bundle.getString("label2"));
        button8.setText(bundle.getString("Stats"));
        wins.setText(bundle.getString("Wins"));
        loses.setText(bundle.getString("Loses"));

        Font font2 =  new Font("SansSerif",Font.BOLD,16);
        wins.setFont(font2);
        loses.setFont(font2);
        wins_amount.setFont(font2);
        loses_amount.setFont(font2);
        wins.setBounds(465, 470, 100, 30);
        loses.setBounds(540, 470, 100, 30);
        wins_amount.setBounds(485, 500, 50, 20);
        loses_amount.setBounds(555, 500, 50, 20);
        panel2.add(labelAmount);
        WordokuClas.setBounds(185,123,153,30);
        WordokuDui.setBounds(240, 380, 153, 30);
        panel2.add(WordokuClas);
        panel2.add(WordokuDui);
        newGameClassic.setText(bundle.getString("newGameClassicKiller"));
        newGameClassic.setBounds(220,577,160,30);
        newGameClassic.setFont(new Font("Arial",Font.BOLD,20));
        newGameClassic.setBackground(Color.decode("#90FFFF"));
        newGameClassic.setVisible(true);
        newGameKiller.setText(bundle.getString("newGameClassicKiller"));
        newGameKiller.setBounds(220,577,160,30);
        newGameKiller.setFont(new Font("Arial",Font.BOLD,20));
        newGameKiller.setBackground(Color.decode("#90FFFF"));
        newGameKiller.setVisible(true);
        newGameDuidoku.setText(bundle.getString("newGameClassicKiller"));
        newGameDuidoku.setBounds(230,480,130,30);
        newGameDuidoku.setVisible(true);


        button5.setBounds(25, 123, 153, 30);
        button6.setBounds(465, 123, 153, 30);
        button7.setBounds(80, 380, 153, 30);
        button8.setBounds(460, 430, 135, 30);
        panel2.add(button5);
        panel2.add(button6);
        panel2.add(button7);

        panel2.setLayout(null);
        panel2.setVisible(true);
        panel2.setBackground(Color.decode("#D4AF37"));
    }
}
