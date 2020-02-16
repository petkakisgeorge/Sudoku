import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This Class is Classic Panel and from here we create the Panel,the Classic's Logic and playing the Classic Game.
 * Contains all the Buttons ,textFields, Labels we use when we play Classic Game .
 * Also contains all the function we use , creating new game, implement listeners, writing statistics in the file...etc.
 */

public class ClassicPanel extends SubMenu{


    public JTextField[][] field1;
    private JLabel title; //title of Classic Sudoku
    public JLabel title2; //title of Killer Sudoku
    public JLabel title3; //title of Classic Wordoku
    public JLabel printResult = new JLabel();
    JPanel panel3 = new JPanel();
    public static int name_number=0,User=0,number_of_game_won;
    public UserStatistics user = new UserStatistics();
    int g=1;
    public  JButton help = new JButton();
    public  JButton GoBack = new JButton();
    public int[] ArrayOfHelp = new int[10];
    public int[] ArrayOfHelp2 = new int[2];

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
     * ClassicPanel's Constructor is setting the name_number and the User and starts new game .
     * @param name is the i stored in the array of names containing the connected users name
     * @param user 1 if user playing connected , 0 if playing disconnected
     */
    public ClassicPanel(int name,int user){
        name_number=name;
        User=user;
        ResourceBundle bundle = ResourceBundle.getBundle("change", Locale.getDefault());
        printResult.setText(bundle.getString("WonMessage"));
        help.setText(bundle.getString("helpClassic"));
        GoBack.setText(bundle.getString("GoBack"));
        new_game();
    }
    /**
     * This function starts a new game,Creates the contents of Panel and adds them.
     */
    public void new_game()
    {
        Font fontPrint = new Font("Arial", Font.BOLD, 50);
        printResult.setForeground(Color.decode("#51DCC5"));
        printResult.setFont(fontPrint);
        printResult.setBounds(210,205,300,200);
        title = new JLabel("Classic");
        title2 = new JLabel("Killer");
        title3 = new JLabel("Classic Wordoku");
        Font font = new Font("Courier", Font.BOLD, 30);
        title.setFont(font);
        title2.setFont(font);
        title3.setFont(font);
        title.setForeground(Color.white);
        title2.setForeground(Color.white);
        title3.setForeground(Color.white);
        title.setBounds(240,5,170,30);
        title2.setBounds(250,5,170,30);
        title3.setBounds(160,5,280,30);
        GoBack.setBounds(10,580,80,30);
        GoBack.setFont(new Font("Arial",Font.BOLD,15));
        GoBack.setBackground(Color.decode("#FF4B4B"));
        panel3.add(GoBack);
        GoBack.setVisible(true);
        help.setBounds(577,130, 90,25);
        panel3.add(help);

        field1=new JTextField[9][9];
        Font font1 = new Font("TimesRoman",Font.BOLD, 23);

        for(int  i=0;i<9;i++) {
            for (int j = 0; j < 9; j++){
                field1[i][j]=new JTextField(); //Initialize
                field1[i][j].setBounds((30+j*60),(32 +i*60),60,60);
                field1[i][j].setDocument(new JTextFieldLimit(1)); //set the limit calling the class JTextFieldLimit
                field1[i][j].setHorizontalAlignment(JTextField.CENTER);
                field1[i][j].setFont(font1);
                if(j==2|| j==5 || j==8) {
                    if (i == 2 || i == 5 || i==8)
                        field1[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 4, 4, Color.BLACK));
                    else if(i==0)
                        field1[i][j].setBorder(BorderFactory.createMatteBorder(4, 1, 1, 4, Color.BLACK));

                    else {
                        field1[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 4, Color.BLACK));
                    }
                }
                else if(j==0) {
                    if(i==0)
                        field1[i][j].setBorder(BorderFactory.createMatteBorder(4, 4, 1, 1, Color.BLACK));
                    else if( i == 2 || i == 5 || i == 8)
                        field1[i][j].setBorder(BorderFactory.createMatteBorder(1, 4, 4, 1, Color.BLACK));
                    else {
                        field1[i][j].setBorder(BorderFactory.createMatteBorder(1, 4, 1, 1, Color.BLACK));
                    }
                }
                else
                {
                    if(i==2 || i==5 || i==8)
                        field1[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 4, 1, Color.BLACK));
                    else if(i==0)
                        field1[i][j].setBorder(BorderFactory.createMatteBorder(4, 1, 1, 1, Color.BLACK));
                    else
                        field1[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

                }
                panel3.add(field1[i][j]);
            }
        }
        panel3.setBackground(Color.decode("#503A00"));
        panel3.setLayout(null);
        panel3.setVisible(true);
        panel3.revalidate();
        panel3.repaint();
    }
    /**
     * This function contains all the listeners for every jTextField ,listener for help and focus.
     * Calls the Classic Logic and loads the Array that has been chosen.
     * Checks every move if is ok ,set or unset values by calling ClassicLogic's function.
     */
    void Classic()
    {
        panel3.add(newGameClassic);
        ClassicSudokuLogic t = new ClassicSudokuLogic(0,User,name_number); //0 - Classic Game
        help.addActionListener(e-> { // This listener creates a popup Menu with items every number that could be added.
                int finalI= ArrayOfHelp2[0]; // the previously focused i
                int finalJ = ArrayOfHelp2[1]; // the previously focused j
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
                                panel3.remove(title);
                                panel3.add(printResult);
                                printResult.setVisible(true);
                                panel3.repaint();
                            }
                        });
                    }
                }
                dui.show(panel3, 600, 180);
        });
        panel3.add(title);
        number_of_game_won= ClassicSudokuLogic.number_of_game_Chosed;
        //we set the numbers that Classic's Array contains at first
        for(int i=0;i<9;i++) {
            for (int j = 0; j < 9; j++) {
                int tt = t.getBoard(i, j);
                if (tt != 0) {
                    field1[i][j].setText(Integer.toString(tt));
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

                                if (keyEvent.getKeyChar() >= '1' && keyEvent.getKeyChar() <= '9') { //if the number the User has input is between 1-9 accept it
                                    if (t.getBoard(finalI, finalJ) == 0) {  // if is 0 then the cell is empty and we can add a value

                                        if (!t.isOk(finalI, finalJ, Character.getNumericValue(keyEvent.getKeyChar()))) { //if number is not ok
                                            field1[finalI][finalJ].setForeground(Color.red); //set the color to red
                                            t.setBoard(finalI, finalJ, -Character.getNumericValue(keyEvent.getKeyChar())); //sets the negative value of number
                                        } else { //if is ok
                                            field1[finalI][finalJ].setForeground(Color.black); //sets the color to black
                                            t.setBoard(finalI, finalJ, Character.getNumericValue(keyEvent.getKeyChar())); //sets the number
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
                            public void keyReleased(KeyEvent keyEvent) {
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
                                    panel3.remove(title);
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
    /**
     * This function is used for writing in Users folder, the game he won.
     */
    void write_in_file() {
        if(User!=0) {    // User =0 means User isn't connected so we are not writing
            String UserName = user.names.get(name_number);
            user.addWinClassic(UserName,number_of_game_won);
            user = new UserStatistics();
        }
    }
}