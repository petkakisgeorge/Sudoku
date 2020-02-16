import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This Class extends DuidokuPanel Class and its methods.
 * Its the same thing with Duidoku but we have two functions that takes letters and make them numbers,
 * or take numbers and make them letters, so that we can print only letters but work with numbers in DuidokuLogic.
 */
public class Wordoku extends DuidokuPanel{

    public int[] Integers;
    public char[] Letters;

    /**
     * @param name is the i stored in the array of names containing the connected users name
     * @param user 1 if user playing connected , 0 if playing disconnected
     */
    public Wordoku(int name,int user)
    {
        super(name,user);
    }

    /**
     * This is a function we use to reverse an integer into a character
     * @param val the integer
     * @return the character
     */
    Character getWordokuCharacter(int val)
    {
        for(int i=0;i<4;i++)
        {
            if(Integers[i]==val)
            {
                return Letters[i];
            }
        }
        return '0';
    }
    /**
     * This is a function we use to reverse a character into an integer
     * @param a the character
     * @return the integer
     */
    Integer getWordokuInteger(char a)
    {
        for(int i=0;i<4;i++)
        {
            if(Letters[i]==a)
            {
                return Integers[i];
            }
        }
        return 0;
    }
    /**
     * This function contains all the listeners for every jTextField ,listener for help and focus.
     * Calls the Duidoku Logic.
     * Checks every move if is ok ,set or unset values by calling Duidoku's function.
     * Makes a move for the pc.
     * Use letters in setting the jTextFields instead of numbers
     */
    void wordoku()
    {
        panel4.add(title2);
        Integers = new int[]{1, 2, 3, 4};   //every number is in the same place as every letter
        Letters = new char[]{'A','B','C','D'};  //for example A=1, B=2, C=3, D=4
        help.addActionListener(e-> {
                int finalI= ArrayOfHelp2[0];
                int finalJ = ArrayOfHelp2[1];
                JPopupMenu dui = new JPopupMenu();

                for(int iv=0;iv<5;iv++)
                {
                    if(ArrayOfHelp[iv]!=0) {
                        JMenuItem miv = new JMenuItem(" "+ getWordokuCharacter(iv) +" ");
                        dui.add(miv);
                        miv.setFont(new Font("SanSerif",Font.CENTER_BASELINE,23));
                        int finalValue = iv;
                        miv.addActionListener(e1 -> { //This listener takes the item with number that has been chosen.
                            t.setDuidoku(finalI, finalJ, finalValue);
                            field2[finalI][finalJ].setText(String.valueOf(getWordokuCharacter(finalValue))); //set the value to the TextField
                            field2[finalI][finalJ].setEnabled(false);  //makes it disabled
                            field2[finalI][finalJ].setDisabledTextColor(Color.black); //with color black
                            field2[finalI][finalJ].setBackground(Color.decode("#86DFF0"));
                            t.pc();
                            field2[DuidokuLogic.I][DuidokuLogic.J].setText(String.valueOf(getWordokuCharacter(DuidokuLogic.VALUE)));  //set the value of pc
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
                            if(Counter==16)
                            {
                                if(User==1) //also checks if the user is connected
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
                                panel4.remove(title2);
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
            Wins.setVisible(true);
            Loses.setVisible(true);
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
                                if (keyEvent.getKeyChar() >= 'A' && keyEvent.getKeyChar() <= 'D') { //if the number the User has input is between A-D accept it
                                    int a = getWordokuInteger(keyEvent.getKeyChar()); //transform letter to integer
                                    if(!t.isOk(finalI,finalJ,a))  //if number is not ok
                                    {
                                        keyEvent.consume(); //reject the User's input
                                    }
                                    else
                                    {
                                        t.setDuidoku(finalI,finalJ,a);
                                        field2[finalI][finalJ].setEnabled(false);
                                        field2[finalI][finalJ].setText(String.valueOf(keyEvent.getKeyChar())); //set the value to the TextField
                                        field2[finalI][finalJ].setEnabled(false); //makes it disable
                                        field2[finalI][finalJ].setDisabledTextColor(Color.black); //with color black
                                        field2[finalI][finalJ].setBackground(Color.decode("#86DFF0"));
                                        t.pc();
                                        char aa = getWordokuCharacter(DuidokuLogic.VALUE); //transform integer to letter
                                        field2[DuidokuLogic.I][DuidokuLogic.J].setText(String.valueOf(aa)); //set the value to the TextField
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
                                    panel4.remove(title2);
                                    panel4.add(printResult);
                                    printResult.setVisible(true);
                                    panel4.repaint();
                                }
                            }
                            @Override
                            public void keyPressed(KeyEvent keyEvent) {}
                            @Override
                            public void keyReleased(KeyEvent keyEvent) {}
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
}