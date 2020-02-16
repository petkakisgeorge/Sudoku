import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This Class implements Duidoku Panel.
 * Its a game against computer where user puts first a number.
 * The winner  is the one who makes the last move.
 */
public class DuidokuPanel extends SubMenu{

    JPanel panel4 = new JPanel();
    public  JButton help = new JButton();
    public  JButton Game_stats = new JButton();
    public  JLabel Wins = new JLabel();
    public  JLabel Loses = new JLabel();
    public  JLabel Wins_amount = new JLabel();
    public  JLabel Loses_amount = new JLabel();
    public JLabel printResult = new JLabel();
    public  JButton GoBack = new JButton();
    public JTextField[][] field2;
    private JLabel title;
    public JLabel title2;
    public static int name_number=0,User=0;
    public UserStatistics user = new UserStatistics();
    public int[] ArrayOfHelp = new int[5];
    public int[] ArrayOfHelp2 = new int[2];
    private int WriteCounter=0;
    DuidokuLogic t = new DuidokuLogic();


    /**
     * This Class is used for accepting only 1 digit for input for every jTextField.
     */
    class JTextFieldLimit extends PlainDocument {
        private int limit;
        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null)
                return;
            if ((getLength() + str.length()) <= limit ){
                super.insertString(offset, str, attr);
            }
        }

    }

    /**
     * DuidokuPanel's Constructor is setting the name_number and the User and calls DuidokuPanelRun Function.
     * @param name is the i stored in the array of names containing the connected users name
     * @param user 1 if user playing connected , 0 if playing disconnected
     */
    public DuidokuPanel(int name,int user)
    {
        User=user;
        name_number=name;
        DuidokuPanelRun();
    }
    /**
     * This function is used for creating the contents and adding them to Duidoku Panel.
     */
    void DuidokuPanelRun()
    {
        ResourceBundle bundle = ResourceBundle.getBundle("change", Locale.getDefault());
        Font fontPrint = new Font("Arial", Font.BOLD, 50);
        printResult.setForeground(Color.decode("#168B78"));
        printResult.setFont(fontPrint);
        printResult.setBounds(210,205,300,200);
        help.setText(bundle.getString("helpClassic"));
        GoBack.setText(bundle.getString("GoBack"));
        Game_stats.setText(bundle.getString("Stats"));
        Wins.setText(bundle.getString("Wins"));
        Loses.setText(bundle.getString("Loses"));
        WriteCounter=0;
        GoBack.setBounds(10,580,80,30);
        GoBack.setFont(new Font("Arial",Font.BOLD,15));
        GoBack.setBackground(Color.decode("#FF4B4B"));
        help.setBounds(520,110,100,25);
        Game_stats.setBounds(510,430,110,25);
        Font font2 =  new Font("SansSerif",Font.BOLD,15);
        Wins.setFont(font2);
        Loses.setFont(font2);
        Wins_amount.setFont(font2);
        Loses_amount.setFont(font2);
        Loses.setVisible(true);
        Wins.setBounds(516, 470, 100, 30);
        Loses.setBounds(574, 470, 100, 30);
        Wins_amount.setBounds(528, 500, 50, 20);
        Loses_amount.setBounds(588, 500, 50, 20);
        GoBack.setVisible(true);

        panel4.add(newGameDuidoku);
        if(User==1)  //if user is connected , then show stats
           panel4.add(Game_stats);
        panel4.add(help);
        panel4.add(GoBack);
        title = new JLabel("Duidoku");
        title2 = new JLabel("Wordoku");
        Font font = new Font("Sanserif", Font.BOLD, 30);
        title.setFont(font);
        title2.setFont(font);
        title.setBounds(230,20,170,70);
        title2.setBounds(230,20,170,70);
        Font font1 = new Font("TimesRoman",Font.BOLD, 30);

        //build all the jTextFields
        field2=new JTextField[4][4];
        for(int  i=0;i<4;i++) {
            for (int j = 0; j < 4; j++){
                field2[i][j]=new JTextField();
                field2[i][j].setBounds((115+j*90),(95 +i*90),90,90);
                field2[i][j].setDocument(new JTextFieldLimit(1)); //set the limit calling the class JTextFieldLimit
                field2[i][j].setHorizontalAlignment(JTextField.CENTER);
                field2[i][j].setFont(font1);
                if(j==1|| j==3) {
                    if (i == 1 )
                        field2[i][j].setBorder(BorderFactory.createMatteBorder(1, 1 , 4, 4, Color.BLACK));
                    else if(i==0)
                        field2[i][j].setBorder(BorderFactory.createMatteBorder(4, 1, 1, 4, Color.BLACK));
                    else {
                        if(i==3)
                            field2[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 4, 4, Color.BLACK));
                        else
                            field2[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 4, Color.BLACK));
                    }
                }
                else if(j==0) {
                    if(i==0)
                        field2[i][j].setBorder(BorderFactory.createMatteBorder(4, 4, 1, 1, Color.BLACK));
                    else if( i == 1 || i==3)
                        field2[i][j].setBorder(BorderFactory.createMatteBorder(1, 4, 4, 1, Color.BLACK));
                    else {
                        field2[i][j].setBorder(BorderFactory.createMatteBorder(1, 4, 1, 1, Color.BLACK));
                    }
                }
                else
                {
                    if(i==1)
                        field2[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 4, 1, Color.BLACK));
                    else if(i==0)
                        field2[i][j].setBorder(BorderFactory.createMatteBorder(4, 1, 1, 1, Color.BLACK));
                    else if(i==3)
                        field2[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 4, 1, Color.BLACK));
                    else
                        field2[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

                }
                panel4.add(field2[i][j]);
            }
        }
        panel4.setBackground(Color.decode("#DCAB24"));
        panel4.setLayout(null);
        panel4.setVisible(true);
        panel4.revalidate();
        panel4.repaint();
    }
    /**
     * This function contains all the listeners for every jTextField ,listener for help and focus.
     * Calls the Duidoku Logic.
     * Checks every move if is ok ,set or unset values by calling Duidoku's function.
     * Makes a move for the pc.
     */
    void Duidoku()
    {
        panel4.add(title);
        help.addActionListener(e->{ // This listener creates a popup Menu with items - every number that could be added.
                int finalI= ArrayOfHelp2[0]; // the previously focused i
                int finalJ = ArrayOfHelp2[1]; // the previously focused j
                JPopupMenu dui = new JPopupMenu();

                for(int iv=0;iv<5;iv++)
                {
                    if(ArrayOfHelp[iv]!=0) {
                        JMenuItem miv = new JMenuItem(" "+ iv +" ");
                        dui.add(miv);
                        miv.setFont(new Font("SanSerif",Font.CENTER_BASELINE,23));
                        int finalValue = iv;
                        miv.addActionListener(e1 -> { //This listener takes the item with number that has been chosen.
                            miv.requestFocus();
                            t.setDuidoku(finalI, finalJ, finalValue);
                            field2[finalI][finalJ].setText(String.valueOf(finalValue));  //set the value to the TextField
                            field2[finalI][finalJ].setEnabled(false);  //makes it disabled
                            field2[finalI][finalJ].setDisabledTextColor(Color.black); //with color black
                            field2[finalI][finalJ].setBackground(Color.decode("#86DFF0"));
                            t.pc();
                            field2[DuidokuLogic.I][DuidokuLogic.J].setText(String.valueOf(DuidokuLogic.VALUE)); //set the value of pc
                            field2[DuidokuLogic.I][DuidokuLogic.J].setEnabled(false);   //makes it disabled
                            field2[DuidokuLogic.I][DuidokuLogic.J].setDisabledTextColor(Color.black); //with color black
                            field2[DuidokuLogic.I][DuidokuLogic.J].setBackground(Color.decode("#86DFF0"));
                            //we are setting all the blocked cells with value -1
                            for (int i1 = 0; i1 < 4; i1++) {
                                for (int j1 = 0; j1 < 4; j1++) {
                                    if (t.get_duidar_value(i1, j1) == -1) {
                                        field2[i1][j1].setBackground(Color.decode("#7A6969"));
                                        field2[i1][j1].setEnabled(false);
                                    }
                                }
                            }
                            //Checking if the game is finished, so can write the result
                            int Counter=0;
                            for(int v=0;v<4;v++) {
                                for (int vj = 0; vj < 4; vj++) {
                                    if (t.get_duidar_value(v, vj) != 0)
                                        Counter++;
                                }
                            }
                            if(Counter==16) //also checks if the user is connected
                            {
                                if(User==1)
                                    write_in_file();
                                for (int ii = 0; ii < 4; ii++) {
                                    for (int jj = 0; jj < 4; jj++) {
                                        panel4.remove(field2[ii][jj]);
                                    }
                                }
                                ResourceBundle bundle = ResourceBundle.getBundle("change", Locale.getDefault());
                                if(t.WinOrLose==1)
                                    printResult.setText(bundle.getString("WonMessage"));
                                else
                                    printResult.setText(bundle.getString("LostMessage"));
                                panel4.remove(wins);
                                panel4.remove(loses);
                                panel4.remove(wins_amount);
                                panel4.remove(loses_amount);
                                panel4.remove(Game_stats);
                                panel4.remove(help);
                                panel4.remove(title);
                                panel4.add(printResult);
                                printResult.setVisible(true);
                                panel4.repaint();
                            }
                        });
                    }
                }
                dui.show(panel4, 553, 150);
        });
        // Listener about showing in real time the User's Statistics ,wins and loses
        Game_stats.addActionListener(e->{
            user.addThePlayerFile(user.names.get(name_number));
            Wins_amount.setText(String.valueOf(user.fileData.get(0)));
            Loses_amount.setText(String.valueOf(user.fileData.get(1)));
            panel4.add(Wins);
            panel4.add(Loses);
            panel4.add(Wins_amount);
            panel4.add(Loses_amount);
            panel4.repaint();
            panel4.revalidate();
        });
        //we run the action listeners for all the jTextFields
        for(int  i=0;i<4;i++) {
            for (int j = 0; j < 4; j++) {
                int finalJ = j;
                int finalI = i;
                field2[i][j].addKeyListener(
                        new KeyListener() {
                            @Override
                            public void keyTyped(KeyEvent keyEvent) {
                                if (keyEvent.getKeyChar() >= '1' && keyEvent.getKeyChar() <= '4') { //if the number the User has input is between 1-4 accept it

                                    if(!t.isOk(finalI,finalJ,Character.getNumericValue(keyEvent.getKeyChar()))) //if number is not ok
                                    {
                                        keyEvent.consume(); //reject the User's input
                                    }
                                    else
                                    {
                                        t.setDuidoku(finalI,finalJ,Character.getNumericValue(keyEvent.getKeyChar()));
                                        field2[finalI][finalJ].setText(String.valueOf(Character.getNumericValue(keyEvent.getKeyChar()))); //set the value to the TextField
                                        field2[finalI][finalJ].setEnabled(false); //makes it disable
                                        field2[finalI][finalJ].setDisabledTextColor(Color.black); //with color black
                                        field2[finalI][finalJ].setBackground(Color.decode("#86DFF0"));
                                        t.pc();
                                        field2[DuidokuLogic.I][DuidokuLogic.J].setText(String.valueOf(DuidokuLogic.VALUE)); //set the pc value to the TextField
                                        field2[DuidokuLogic.I][DuidokuLogic.J].setEnabled(false);  //makes it disable
                                        field2[DuidokuLogic.I][DuidokuLogic.J].setDisabledTextColor(Color.black); //with color black
                                        field2[DuidokuLogic.I][DuidokuLogic.J].setBackground(Color.decode("#86DFF0"));
                                        for(int i1=0;i1<4;i1++)
                                        {
                                            for(int j1=0;j1<4;j1++)
                                            {
                                                if(t.get_duidar_value(i1,j1)==-1)  //means the cell is blocked
                                                {
                                                    field2[i1][j1].setBackground(Color.decode("#7A6969"));
                                                    field2[i1][j1].setEnabled(false);
                                                }
                                            }
                                        }
                                    }
                                }
                                else{
                                    keyEvent.consume();}
                                //Checking if the game is finished, so can write the result
                                int Counter=0;
                                for(int v=0;v<4;v++) {
                                    for (int vj = 0; vj < 4; vj++) {
                                        if (t.get_duidar_value(v, vj) != 0)
                                            Counter++;
                                    }
                                }
                                if(Counter==16) //also checks if the user is connected
                                {
                                    if(User==1)
                                       write_in_file();
                                    for (int ii = 0; ii < 4; ii++) {
                                        for (int jj = 0; jj < 4; jj++) {
                                            panel4.remove(field2[ii][jj]);
                                        }
                                    }
                                    ResourceBundle bundle = ResourceBundle.getBundle("change", Locale.getDefault());
                                    if(t.WinOrLose==1)
                                        printResult.setText(bundle.getString("WonMessage"));
                                    else
                                        printResult.setText(bundle.getString("LostMessage"));
                                    panel4.remove(wins);
                                    panel4.remove(loses);
                                    panel4.remove(wins_amount);
                                    panel4.remove(loses_amount);
                                    panel4.remove(Game_stats);
                                    panel4.remove(help);
                                    panel4.remove(title);
                                    panel4.add(printResult);
                                    printResult.setVisible(true);
                                    panel4.repaint();
                                }
                            }
                            @Override
                            public void keyPressed(KeyEvent keyEvent) {}
                            @Override
                            public void keyReleased(KeyEvent keyEvent){}
                        }
                );
                //This listener is about Keeping the i and j of clicked jTextField and filling the ArrayOfHelp
                field2[finalI][finalJ].addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent focusEvent) {
                        for(int ii=1;ii<5;ii++)
                        {
                            if(t.isOk(finalI,finalJ,ii))
                            {
                                ArrayOfHelp[ii]=ii; //we fill the Array only with values that is ok been added
                            }
                            else
                                ArrayOfHelp[ii]=0; //else we fill the value 0 for those where can't been added
                        }
                        ArrayOfHelp2[0]=finalI; //This Array contains in the first place the focused i
                        ArrayOfHelp2[1]=finalJ; //and in the second place the focused j
                    }
                    @Override
                    public void focusLost(FocusEvent focusEvent){}
                });
            }
        }
        panel4.revalidate();
        panel4.repaint();
    }
    /**
     * This function is used for writing in Users folder, if he won or lose.
     */
    void write_in_file() {
        if(t.WinOrLose!=0) {  // User =0 means User isn't connected so we are not writing
            if(WriteCounter==0) {  //check for mistakes to write only one time the result to the file.
                String UserName = user.names.get(name_number);
                user.addWinDuidoku(UserName, t.WinOrLose);
                user = new UserStatistics();
                WriteCounter++;
            }
        }
    }
}