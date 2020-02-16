import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This Class stores the MainMenu Panel and its components and extends the SubMenu Class
 * From the MainMenu you can choose to play as a single user, to connect as a user, to create a user, or to exit.
 * Here is the Panel number 1
 */

public class MainMenu extends SubMenu{

    public  JPanel panel1 = new JPanel();
    public  JLabel label = new JLabel();
    public  JButton button1 = new JButton();
    public  JButton button2 = new JButton();
    public  JButton button3 = new JButton();
    public  JButton button4 = new JButton();
    public static JPopupMenu pm;


    /**
     * MainMenu constructor is calling the submenu constructor with the super() command
     * Also contains all the buttons and labels are showing in panel3 , the panel of MainMenu
     *
     */
    public MainMenu()
    {
        super();
        MainMenuAction();
    }
    public void MainMenuAction(){
        JLabel j1 = new JLabel();
        j1.setIcon(new javax.swing.ImageIcon(getClass().getResource("sudoku.jpg")));
        j1.setBounds(140, 370, 600, 240);
        panel1.add(j1);

        Font font = new Font("SansSerif", Font.BOLD, 25);
        label.setFont(font);
        panel1.add(label);
        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);
        panel1.add(button4);

        button1.setBounds(240, 140, 220, 33);
        button2.setBounds(240, 190, 220, 33);
        button3.setBounds(240, 240, 220, 33);
        button4.setBounds(240, 290, 220, 33);
        label.setBounds(120, 60, 500, 50);

        button4.addActionListener(e->{
            System.exit(0);
        });
        ResourceBundle bundle = ResourceBundle.getBundle("change",Locale.getDefault());

        button1.setText(bundle.getString("button1"));
        button2.setText(bundle.getString("button2"));
        button3.setText(bundle.getString("button3"));
        button4.setText(bundle.getString("button4"));
        label.setText(bundle.getString("label1"));

        panel1.setVisible(true);
        panel1.setLayout(null);
        panel1.setBackground(Color.decode("#D4AF37"));
        panel1.setFocusable( true );
    }
}
