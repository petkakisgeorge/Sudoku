import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Starting GUI Class is our manipulating Class, creates an object of GUI and reloads the MainMenu object.
 * We make a Menu , with items so the player can go Back to the Main Menu, Exit, or see how the game is played
 * We set the frames defaults.
 * We also stop the Statics newGames ActionListeners ,so we can play with other user or unconnected ,without
 * keeping the Listeners of the static Buttons.
 */
public class Starting_GUI {

    private JMenuItem item2 = new JMenuItem();
    private JMenuItem item3 = new JMenuItem();
    private JMenuItem item4 = new JMenuItem();
    private JFrame tg;
    public  GUI t = new GUI();

    /**
     * This is our Constructor where we create the Menu and add it to the frame.
     */
    public Starting_GUI()
    {
        tg=t.frame;
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu();
        JMenu menu2 = new JMenu();
        menu1.add(item4);
        menu2.add(item2);
        menu1.add(item3);
        menuBar.add(menu1);
        menuBar.add(menu2);
        ResourceBundle bundle = ResourceBundle.getBundle("change", Locale.getDefault());
        item2.setText(bundle.getString("item2"));
        item3.setText(bundle.getString("item3"));
        item4.setText(bundle.getString("item4"));
        menu1.setText(bundle.getString("menu1"));
        menu2.setText(bundle.getString("menu2"));

        item3.addActionListener(this::actionPerformed);
        item2.addActionListener(this::actionPerformed);
        item4.addActionListener(this::actionPerformed);
        t.frame.setJMenuBar(menuBar);
        t.frame.setSize(680,680);
        t.frame.setLocationRelativeTo(null);
        t.frame.setResizable(false);
        t.frame.setVisible(true);
        t.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * This is a function of ActionEvent E,that listens the item is chosen from Menu
     * and do some actions.
     * @param E ,the ActionEvent for Menu items.
     */
    public void actionPerformed(ActionEvent E) {
        if (E.getSource() == item2) {
            JDialog d = new JDialog(t.frame, "How to play Sudoku", Dialog.ModalityType.APPLICATION_MODAL);
            d.setSize(400, 150);
            Font font = new Font("SanSerif", Font.PLAIN, 20);
            Font font2 = new Font("Courier",Font.PLAIN,15);
            ResourceBundle bundle = ResourceBundle.getBundle("change", Locale.getDefault());
            JLabel l = new JLabel(bundle.getString("HelpingMessage3"));
            JLabel t = new JLabel("<html>"+bundle.getString("HelpingMessage1")+"<br>"+bundle.getString("HelpingMessage2")+"</html>");
            l.setFont(font);
            t.setFont(font2);
            d.add(l);
            d.add(t);
            d.setLayout(new FlowLayout());
            d.setLocationRelativeTo(tg);
            d.setVisible(true);
        }
        if (E.getSource() == item3) //if item3 , then exit
            System.exit(0);
        if (E.getSource() == item4) { //if item4 then remove panels and add the MainMenu
            if (t.if_menu_exists) {
                if (t.PanelCopy.isVisible()) {
                    t.PanelCopy.removeAll();
                    t.PanelCopy.setVisible(false);
                    t.frame.remove(t.PanelCopy);
                }
                t.m.panel2.remove(t.m.button8);

                for (ActionListener conf : SubMenu.newGameClassic.getActionListeners()) {
                    SubMenu.newGameClassic.removeActionListener(conf);
                }
                for (ActionListener conf : SubMenu.newGameKiller.getActionListeners()) {
                    SubMenu.newGameKiller.removeActionListener(conf);
                }
                for (ActionListener conf : SubMenu.newGameDuidoku.getActionListeners()) {
                    SubMenu.newGameDuidoku.removeActionListener(conf);
                }

                t.hide();
                t.PanelCopy = new JPanel();
                t.PanelCopy.setVisible(false);
                t.m = null;
                t.m = new MainMenu();
                t.frame.add(t.m.panel1);
                t.m.panel1.setVisible(true);
                t.ButtonsListenerMainMenu();
                t.if_menu_exists=false;
            }
        }

    }
}
