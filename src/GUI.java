import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * GUI  creates an object of MainMenu Class witch is extending the SubMenu Class.
 * We create the frame and we store all the panels ,removing and adding them into the frame.
 * Also we store the most of the button listeners.
 * We do that so we can hide and remove or add and show the panels , considering the player's choice.
 */
public class GUI{

    JFrame frame;
    public  JPanel PanelCopy = new JPanel() ;
    JTextField Usernamefill =  new JTextField();
    JLabel fill = new JLabel();
    MainMenu m = new MainMenu();
    boolean if_menu_exists;


    /**
     * In GUIs constructor we build our basic, one and only frame
     * and we also add a helping MenuBar for more choices for the user
     */
    public GUI() {
        super();
        frame = new JFrame("Sudoku");
        frame.add(m.panel1);
        PanelCopy.setVisible(false);
        ButtonsListenerMainMenu();
    }

    /**
     * This function contains all the buttons listeners we store inside the MainMenu Class
     * It also contains a PopUp Menu in button2 ,for choosing a user
     * and a jTextField in button3 , for creating a user
     */
    public void ButtonsListenerMainMenu()
    {
        m.button1.addActionListener(e->{     // button1 is used for setting the right panels and calling SubMenu with zero values
            m.panel1.setVisible(false);
            frame.remove(m.panel1);
            frame.add(m.panel2);
            m.panel2.setVisible(true);
            ButtonsListenerSubMenu(0,0);   // user is starting the game unconnected
            Usernamefill.setVisible(false);
            fill.setVisible(false);
        });
        m.button2.addActionListener(e -> {  //button 2 is used for playing connected ,choosing a users name from the pop up menu we create
            Usernamefill.setVisible(false);
            fill.setVisible(false);
            UserStatistics see = new UserStatistics();
            MainMenu.pm = new JPopupMenu("Pick your nickname");

            for(int i=0;i<see.names.size();i++)
            {
                JMenuItem mi = new JMenuItem(see.names.get(i)); //creating an item with the name of user for every user
                MainMenu.pm.add(mi); //adding it in the pop up menu
                int finalI = i;
                mi.addActionListener(e1 -> {  //listen for witch item is being pushed from User
                    m.panel2.add(m.button8);
                    see.addThePlayerFile(see.names.get(finalI)); //load the player's Statistics
                    m.panel1.setVisible(false);
                    frame.remove(m.panel1);
                    frame.add(m.panel2);
                    m.panel2.setVisible(true);
                    ButtonsListenerSubMenu(finalI,1);  //finalI is the players i , user = 1 is that he plays connected
                });
            }
            MainMenu.pm.show(m.panel1, 470, 200);
        });
        m.button3.addActionListener(e -> {  //button 3 is used for Creating a User
            m.panel2.add(m.button8);
            ResourceBundle bundle = ResourceBundle.getBundle("change",Locale.getDefault());
            fill.setText(bundle.getString("Username"));
            fill.setBounds(475,235,100,25);
            Usernamefill.setBounds(475,260,100,25);
            Usernamefill.setVisible(true);
            fill.setVisible(true);
            Usernamefill.addActionListener(actionEvent -> {//listen and Create the name he typed
                UserStatistics tg = new UserStatistics();
                boolean b=false;
                for(int ii=0;ii<tg.names.size();ii++) {
                    if (Usernamefill.getText().isBlank() || tg.names.get(ii).equals(Usernamefill.getText()))
                        b=true;
                }
                if(!b){
                    tg.CreateAuserName(Usernamefill.getText()); //add new name into the file of names
                    int finalI = tg.getUsernamePlace(); //taking the Users i for the file of names
                    m.panel1.setVisible(false);
                    frame.remove(m.panel1);
                    frame.add(m.panel2);
                    m.panel2.setVisible(true);
                    tg.addThePlayerFile(tg.names.get(finalI)); //load the players file
                    ButtonsListenerSubMenu(finalI, 1);   //finalI is the players i , user = 1 is that he plays connected
                }
            });
            m.panel1.add(fill);
            m.panel1.add(Usernamefill);
            m.panel1.repaint();
        });
    }
    /**
     * This function contains all the buttons listeners we store inside the SubMenu Class
     * @param number is a variable that stores the place number of the name that is connected
     * @param user is a variable that stores the value 0 if user is playing without logging in and the value 1 if he is playing logged.
     */
    public void ButtonsListenerSubMenu(int number,int user)
    {
        if_menu_exists=true;
        //counter for new_Game Button
        final int[] count ={0}; //if count = 1 then we play numbers ,if count =2 we play letters
        m.button5.addActionListener(e -> { //button 5 is used for Creating an object of Classic Panel
            count[0] =1;
            hide();
            ClassicPanel d = new ClassicPanel(number,user);
            PanelCopy = d.panel3;
            frame.add(PanelCopy);
            PanelCopy.setVisible(true);
            d.Classic();
            d.GoBack.addActionListener(ee->{ //This listener is used for going back , changing panels
                PanelCopy.setVisible(false);
                frame.remove(PanelCopy);
                frame.add(m.panel2);
                m.panel2.setVisible(true);
            });
        });
        m.WordokuClas.addActionListener(e->{ //WordokuClass is the same with button 5 but is used for creating an object of Classic Wordoku
            count[0]=2;
            hide();
            ClassicWordoku d = new ClassicWordoku(number,user);
            PanelCopy = d.panel3;
            frame.add(PanelCopy);
            PanelCopy.setVisible(true);
            d.ClassicWordo();
            d.GoBack.addActionListener(ee->{ //This listener is used for going back , changing panels
                PanelCopy.setVisible(false);
                frame.remove(PanelCopy);
                frame.add(m.panel2);
                m.panel2.setVisible(true);
            });
        });
        SubMenu.newGameClassic.addActionListener(e->{ //This listener is used for Creating New Game in Classic or Wordoku Classic
            PanelCopy.removeAll();
            PanelCopy.setVisible(false);
            frame.remove(PanelCopy);
            if(count[0]==1) {
                ClassicPanel d = new ClassicPanel(number, user);
                PanelCopy = d.panel3;
                frame.add(PanelCopy);
                PanelCopy.setVisible(true);
                d.Classic();
                d.GoBack.addActionListener(ee -> { //This listener is used for going back , changing panels
                    PanelCopy.setVisible(false);
                    frame.remove(PanelCopy);
                    frame.add(m.panel2);
                    m.panel2.setVisible(true);
                });
            }
            else
            {
                ClassicWordoku d = new ClassicWordoku(number,user);
                PanelCopy = d.panel3;
                frame.add(PanelCopy);
                PanelCopy.setVisible(true);
                d.ClassicWordo();
                d.GoBack.addActionListener(ee->{ //This listener is used for going back , changing panels
                    PanelCopy.setVisible(false);
                    frame.remove(PanelCopy);
                    frame.add(m.panel2);
                    m.panel2.setVisible(true);
                });
            }
            PanelCopy.repaint();
            PanelCopy.revalidate();
        });
        m.button6.addActionListener(e -> { //button 6 is used for Creating an object of Killer Panel
            hide();
            KillerPanel d = new KillerPanel(number,user);
            PanelCopy = d.panel3;
            frame.add(PanelCopy);
            PanelCopy.setVisible(true);
            d.Killer();
            d.GoBack.addActionListener(ee->{ //This listener is used for going back , changing panels
                PanelCopy.setVisible(false);
                frame.remove(PanelCopy);
                frame.add(m.panel2);
                m.panel2.setVisible(true);
            });
        });
        SubMenu.newGameKiller.addActionListener(e->{ //This listener is used for Creating New Game in Killer
            PanelCopy.removeAll();
            PanelCopy.setVisible(false);
            frame.remove(PanelCopy);
            KillerPanel d = new KillerPanel(number,user);
            PanelCopy = d.panel3;
            frame.add(PanelCopy);
            PanelCopy.setVisible(true);
            d.Killer();
            d.GoBack.addActionListener(ee->{ //This listener is used for going back , changing panels
                PanelCopy.setVisible(false);
                frame.remove(PanelCopy);
                frame.add(m.panel2);
                m.panel2.setVisible(true);
            });
            PanelCopy.repaint();
            PanelCopy.revalidate();
        });
        m.button7.addActionListener(e -> { //button 7 is used for Creating an object of Duidoku Panel
            count[0]=1;
            hide();
            DuidokuPanel v = new DuidokuPanel(number, user);
            PanelCopy = v.panel4;
            frame.add(PanelCopy);
            PanelCopy.setVisible(true);
            v.Duidoku();
            v.GoBack.addActionListener(ee->{ //This listener is used for going back , changing panels
                v.panel4.setVisible(false);
                frame.remove(v.panel4);
                frame.add(m.panel2);
                m.panel2.setVisible(true);
            });
        });
        m.WordokuDui.addActionListener(e->{ //WordokuDui is used for Creating an object of Wordoku Panel
            count[0]=2;
            hide();
            Wordoku v = new Wordoku(number, user);
            PanelCopy = v.panel4;
            frame.add(PanelCopy);
            PanelCopy.setVisible(true);
            v.wordoku();
            v.GoBack.addActionListener(ee->{ //This listener is used for going back , changing panels
                v.panel4.setVisible(false);
                frame.remove(v.panel4);
                frame.add(m.panel2);
                m.panel2.setVisible(true);
            });
        });
        SubMenu.newGameDuidoku.addActionListener(e-> //This listener is used for Creating New Game in Duidoku or Wordoku
        {
            PanelCopy.removeAll();
            PanelCopy.setVisible(false);
            frame.remove(PanelCopy);
            if(count[0]==1) {
                DuidokuPanel v = new DuidokuPanel(number, user);
                PanelCopy = v.panel4;
                frame.add(PanelCopy);
                PanelCopy.setVisible(true);
                v.Duidoku();
                v.GoBack.addActionListener(ee -> { //This listener is used for going back , changing panels
                    v.panel4.setVisible(false);
                    frame.remove(v.panel4);
                    frame.add(m.panel2);
                    m.panel2.setVisible(true);
                });
            }
            else
            {
                Wordoku v = new Wordoku(number, user);
                PanelCopy = v.panel4;
                frame.add(PanelCopy);
                PanelCopy.setVisible(true);
                v.wordoku();
                v.GoBack.addActionListener(ee->{ //This listener is used for going back , changing panels
                    v.panel4.setVisible(false);
                    frame.remove(v.panel4);
                    frame.add(m.panel2);
                    m.panel2.setVisible(true);
                });
            }
            PanelCopy.repaint();
            PanelCopy.revalidate();
        });
        m.button8.addActionListener(e->{ //button 8 is used for showing the Statistics in Duidoku for the user is playing
            UserStatistics g = new UserStatistics();
            g.addThePlayerFile(g.names.get(number)); //loads the player file
            SubMenu.wins_amount.setText(String.valueOf(g.fileData.get(0)));
            SubMenu.loses_amount.setText(String.valueOf(g.fileData.get(1)));
            m.panel2.add(SubMenu.wins);
            m.panel2.add(SubMenu.loses);
            m.panel2.add(SubMenu.wins_amount);
            m.panel2.add(SubMenu.loses_amount);
            m.panel2.repaint();
        });
    }
    public void hide()
    {
        m.panel2.remove(SubMenu.wins);
        m.panel2.remove(SubMenu.loses);
        m.panel2.remove(SubMenu.wins_amount);
        m.panel2.remove(SubMenu.loses_amount);
        m.panel2.setVisible(false);
        frame.remove(m.panel2);
    }
}