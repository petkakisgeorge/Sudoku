import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This Class implements Killer's Panel and extends ClassicPanel Class.
 *  We create the Panel,the Killer's Logic and playing the Killer Game.
 *  Contains all the Buttons ,textFields, Labels we use when we play Killer Game,inheriting the most of them from Classic Panel.
 */
public class KillerPanel extends ClassicPanel{

    public static int number_of_Puzzle_Played,Counter; //When Counter is 81 , all the jTextFields are filled


    /**
     * KillerPanel's Constructor is calling the Classic's Panel Constructor with the name_number and the user.
     * @param name is the i stored in the array of names containing the connected users name
     * @param user 1 if user playing connected , 0 if playing disconnected
     */
    public KillerPanel(int name,int user)
    {
        super(name,user);
    }

    /**
     *  This function contains all the listeners for every jTextField ,listener for help and focus.
     *  Calls the Killer Logic and loads the Array that has been chosen.
     *  Checks every move if is ok ,set or unset values by calling Killer's Logic function.
     */
    void Killer()
    {
        panel3.add(newGameKiller);
        KillerSudokuLogic g= new KillerSudokuLogic(User,name_number);
        panel3.add(title2);
        //We set the number of sums for every ColorArea
        JLabel tbi;
        Font fontB = new Font("Sanserif", Font.BOLD, 12);
        for (int i = 0; i < g.Colorareas.size(); i++) {
            int I= g.Colorareas.get(i).ColorAreaa.get(0).getI(); //gets the first's cell's I
            int J= g.Colorareas.get(i).ColorAreaa.get(0).getJ(); //and the first's cell's J
            int SUM = g.Colorareas.get(i).sum; //and the sum of every ColorArea
            tbi = new JLabel(String.valueOf(SUM));
            tbi.setFont(fontB);
            field1[I][J].add(tbi);
            field1[I][J].setLayout(new FlowLayout(FlowLayout.LEFT)); //Sets the number's view, up and left
        }
        number_of_Puzzle_Played = KillerSudokuLogic.number_of_game_chosed;
        //We set the background Color for every Cell from the Array of Colors in KillerLogic
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                field1[i][j].setBackground(Color.decode(g.ColorArrayGui[i][j])); //we decode the Color and set it
            }
        }
        help.addActionListener(e->{ // This listener creates a popup Menu with items every number that could be added.
            int finalI= ArrayOfHelp2[0]; // the previously focused i
            int finalJ = ArrayOfHelp2[1];  // the previously focused j
            JPopupMenu dui = new JPopupMenu();

            for(int iv=0;iv<10;iv++)
            {
                if(ArrayOfHelp[iv]!=0) {
                    JMenuItem miv = new JMenuItem(" "+ iv +" ");
                    dui.add(miv);
                    miv.setFont(new Font("SanSerif",Font.CENTER_BASELINE,23));
                    int finalValue = iv;
                    miv.addActionListener(e1 -> { //This listener takes the item with number that has been chosen.
                        field1[finalI][finalJ].setText(String.valueOf(finalValue)); //and set the value to the TextField
                        field1[finalI][finalJ].setForeground(Color.black);  //set it black
                        g.SetBoxNumber(finalI, finalJ, finalValue); //set it inside the board
                        //Checking if the game is finished, so can write the result
                        int Counter=0;
                        for(int ii=0;ii<9;ii++)
                        {
                            for(int jj=0;jj<9;jj++)
                            {
                                if(g.getBoard(ii,jj)<=0)
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
                            panel3.remove(title2);
                            panel3.add(printResult);
                            printResult.setVisible(true);
                            panel3.repaint();
                        }
                    });
                }
            }
            dui.show(panel3, 600, 180);
        });
        //we run the action listeners for all the jTextFields
        for(int  i=0;i<9;i++) {
            for (int j = 0; j < 9; j++) {
                int finalJ = j;
                int finalI = i;
                field1[i][j].addKeyListener(
                        new KeyListener() {
                            @Override
                            public void keyTyped(KeyEvent keyEvent) {
                                if (keyEvent.getKeyChar() >= '1' && keyEvent.getKeyChar() <= '9') { //if the number the User has input is between 1-9 accept it
                                    if(g.GetBoard(finalI,finalJ)==0) {  // if is 0 then the cell is empty and we can add a value

                                        if (!g.check(finalI, finalJ, Character.getNumericValue(keyEvent.getKeyChar()))) {  //if number is not ok
                                            field1[finalI][finalJ].setForeground(Color.red); //set the color to red
                                            g.SetBoxNumber(finalI, finalJ, -Character.getNumericValue(keyEvent.getKeyChar())); //sets the negative value of number

                                        } else {
                                            field1[finalI][finalJ].setForeground(Color.black); //sets the color to black
                                            g.SetBoxNumber(finalI, finalJ, Character.getNumericValue(keyEvent.getKeyChar())); //sets the number
                                        }
                                    }
                                    else  // if cell already has a value
                                        keyEvent.consume(); //reject the User's input
                                }
                                else if(keyEvent.getKeyChar()==KeyEvent.VK_BACK_SPACE)  //if user pressed backSpace
                                {
                                    g.SetBoxNumber(finalI,finalJ,0); //delete the value by setting it with default value 0
                                }
                                else //what ever else is typed
                                    keyEvent.consume(); //reject it
                            }
                            @Override
                            public void keyPressed(KeyEvent keyEvent){}
                            @Override
                            public void keyReleased(KeyEvent keyEvent) {
                                //Every time we release a key we check if a red value is now ok and can be transform to black
                                for(int i=0;i<9;i++)
                                {
                                    for(int j=0;j<9;j++)
                                    {
                                        if(g.GetBoard(i,j)<0) //if we find a red ,with negative value
                                        {
                                            if(g.check(i,j,g.GetBoard(i,j)*(-1))) //we check if it is ok to been add multiplying with -1 to take it's positive value
                                            {
                                                field1[i][j].setForeground(Color.black); //setting the color black
                                                g.SetBoxNumber(i, j, g.GetBoard(i,j)*(-1)); //if is ok we add it multiplying with -1
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
                                        if(g.getBoard(ii,jj)<=0)
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
                                    panel3.remove(title2);
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
                            if(g.check(finalI,finalJ,ii))
                            {
                                ArrayOfHelp[ii]=ii; //we fill the Array only with values that is ok been added
                            }
                            else
                                ArrayOfHelp[ii]=0;//else we fill the value 0 for those where can't been added
                        }
                        ArrayOfHelp2[0]=finalI; //This Array contains in the first place the focused i
                        ArrayOfHelp2[1]=finalJ; //and in the second place the focused j
                    }
                    @Override
                    public void focusLost(FocusEvent focusEvent){}
                });
            }
        }
    }
    /**
     * This function is used for writing in Users folder, the game he won.
     */
    void write_in_file() {
        if(User!=0) {     // User =0 means User isn't connected so we are not writing
            String UserName = user.names.get(name_number);
            user.addWinKiller(UserName,number_of_Puzzle_Played);
            user = new UserStatistics();
        }
    }
}