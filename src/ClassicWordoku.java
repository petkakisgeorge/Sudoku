import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This Class extends ClassicPanel Class and its methods.
 * Its the same thing with Classic but we have two functions that takes letters and make them numbers,
 * or take numbers and make them letters, so that we can print only letters but work with numbers in ClassicLogic.
 */
public class ClassicWordoku extends ClassicPanel {

    public int[] Integers;
    public char[] Letters;

    /**
     * @param number is the i stored in the array of names containing the connected users name
     * @param user 1 if user playing connected , 0 if playing disconnected
     */
    public ClassicWordoku(int number,int user)
    {
        super(number,user);
    }
    /**
     * This is a function we use to reverse an integer into a character
     * @param val the integer
     * @return the character
     */
    Character getWordokuCharacter(int val)
    {
        for(int i=0;i<9;i++)
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
        for(int i=0;i<9;i++)
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
     * Calls the Classic Logic and loads the Array that has been chosen.
     * Checks every move if is ok ,set or unset values by calling ClassicLogic's function.
     * Use letters in setting the jTextFields instead of numbers
     */
    void ClassicWordo()
    {
        panel3.add(newGameClassic);
        Integers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        Letters = new char[]{'A','B','C','D','E','F','G','H','I'};
        ClassicSudokuLogic t = new ClassicSudokuLogic(0,User,name_number);
        help.addActionListener(e-> { // This listener creates a popup Menu with items every number that could be added.
            int finalI= ArrayOfHelp2[0]; // the previously focused i
            int finalJ = ArrayOfHelp2[1]; // the previously focused j
                JPopupMenu dui = new JPopupMenu();

                for(int iv=0;iv<10;iv++)
                {
                    if(ArrayOfHelp[iv]!=0) {
                        JMenuItem miv = new JMenuItem(" "+ getWordokuCharacter(iv) +" ");
                        dui.add(miv);
                        miv.setFont(new Font("SanSerif",Font.CENTER_BASELINE,23));
                        int finalValue = iv;
                        miv.addActionListener(e1 -> { //This listener takes the item with number that has been chosen.
                            char a = getWordokuCharacter(finalValue); //we transform the integer to char
                            field1[finalI][finalJ].setText(String.valueOf(a)); //and set the value to the TextField
                            field1[finalI][finalJ].setForeground(Color.black); //set it black
                            t.setBoard(finalI, finalJ,finalValue); //set it inside the board
                            //Checking if the game is finished, so can write the result
                            int Counter=0;
                            for(int ii=0;ii<9;ii++)
                            {
                                for(int jj=0;jj<9;jj++)
                                {
                                    if(t.getBoard(ii,jj)<=0)
                                        Counter++;
                                }
                            }
                            if(Counter==0) { //also checks if the user is connected
                                if(User==1)
                                    write_in_file();
                                for (int ii = 0; ii < 9; ii++) {
                                    for (int jj = 0; jj < 9; jj++){
                                        panel3.remove(field1[ii][jj]);
                                    }
                                }
                                panel3.remove(help);
                                panel3.remove(title3);
                                panel3.add(printResult);
                                printResult.setVisible(true);
                                panel3.repaint();
                            }
                        });
                    }
                }
                dui.show(panel3, 600, 180);
        });
        panel3.add(title3);
        number_of_game_won= ClassicSudokuLogic.number_of_game_Chosed;
        //we set the numbers that Classic's Array contains at first
        for(int i=0;i<9;i++) {
            for (int j = 0; j < 9; j++) {
                int tt = t.getBoard(i, j);
                if (tt != 0) {
                    char a = getWordokuCharacter(tt);
                    field1[i][j].setText(String.valueOf(a));
                    field1[i][j].setEditable(false);
                }
            }
        }
        //we run the action listeners for all the jTextFields
        for(int  i=0;i<9;i++) {
            for (int j = 0; j < 9; j++) {
                int finalJ = j;
                int finalI = i;
                field1[i][j].addKeyListener(
                        new KeyListener() {
                            @Override
                            public void keyTyped(KeyEvent keyEvent) {

                                if (keyEvent.getKeyChar() >= 'A' && keyEvent.getKeyChar() <= 'I') { //if the number the User has input is between A-I accept it
                                    int a = getWordokuInteger(keyEvent.getKeyChar()); //transform letter to integer
                                    if (t.getBoard(finalI, finalJ) == 0) {  // if is 0 then the cell is empty and we can add a value
                                        if (!t.isOk(finalI, finalJ, a)) { //if number is not ok
                                            field1[finalI][finalJ].setForeground(Color.red); //set the color to red
                                            t.setBoard(finalI, finalJ, -a); //sets the negative value of number
                                        } else { //if is ok
                                            field1[finalI][finalJ].setForeground(Color.black); //sets the color to black
                                            t.setBoard(finalI, finalJ, a); //sets the number
                                        }
                                    }
                                    else // if cell already has a value
                                        keyEvent.consume(); //reject the User's input
                                }
                                else if (keyEvent.getKeyChar() == KeyEvent.VK_BACK_SPACE) {//if user pressed backSpace
                                    t.setBoard(finalI, finalJ, 0); //delete the value by setting it with default value 0
                                } //what ever else is typed
                                else
                                    keyEvent.consume(); //reject it
                            }
                            @Override
                            public void keyPressed(KeyEvent keyEvent){}
                            @Override
                            public void keyReleased(KeyEvent keyEvent){
                                //Every time we release a key we check if a red value is now ok and can be transform to black
                                for(int i=0;i<9;i++)
                                {
                                    for(int j=0;j<9;j++)
                                    {
                                        if(t.getBoard(i,j)<0) //if we find a red ,with negative value
                                        {
                                            if(t.isOk(i,j,t.getBoard(i,j)*(-1))) //we check if it is ok to been add multiplying with -1 to take it's positive value
                                            {
                                                field1[i][j].setForeground(Color.black); //setting the color black
                                                t.setBoard(i, j, t.getBoard(i,j)*(-1)); //if is ok we add it multiplying with -1
                                            }
                                        }
                                    }
                                }
                                //Checking if the game is finished, so can write the result
                                int Counter=0;
                                for(int ii=0;ii<9;ii++)
                                {
                                    for(int jj=0;jj<9;jj++)
                                    {
                                        if(t.getBoard(ii,jj)<=0)
                                            Counter++;
                                    }
                                }
                                if(Counter==0) { //also checks if the user is connected
                                    if(User==1)
                                        write_in_file();
                                    for (int ii = 0; ii < 9; ii++) {
                                        for (int jj = 0; jj < 9; jj++){
                                            panel3.remove(field1[ii][jj]);
                                        }
                                    }
                                    panel3.remove(help);
                                    panel3.remove(title3);
                                    panel3.add(printResult);
                                    printResult.setVisible(true);
                                    panel3.repaint();
                                }
                            }
                        }
                );
                //This listener is about Keeping the i and j of clicked jTextField and filling the ArrayOfHelp
                field1[finalI][finalJ].addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent focusEvent) {
                        for(int ii=1;ii<10;ii++)
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
        panel3.revalidate();
        panel3.repaint();
    }
}