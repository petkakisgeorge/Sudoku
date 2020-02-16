import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * This Class is the Logic Class of Classic Sudoku and how is really working.
 * We build the array that we store the numbers by inserting into an ArrayList 10 different games we read from a file, names.txt.
 * Then we choose an array and start the game accepting or not numbers and making the right controls.
 */

public class ClassicSudokuLogic {


    private char[][] arrays = new char[10][81];  //10 games ,in each i we have each game in one big sentence of Characters
    private int[][] board;
    private String[] files= {"array0","array1","array2","array3","array4","array5","array6","array7","array8","array9"}; //the names of the array-files we use
    public static final int SIZE = 9; // size of our Sudoku grids
    public static int number_of_game_Chosed;  // We use it to take it with reference in Panel method and know which game is played


    /**
     * @param sudokugame if is 0 then we play for Classic, if is 1 then we play for Killer
     * @param connected if is 0 then we play unconnected, if is 1 then we play connected with a user
     * @param name_number is the i stored in the array of names containing the connected users name
     */
    public ClassicSudokuLogic(int sudokugame,int connected,int name_number) {
        if(sudokugame==0) {  //we play for Classic
            build();
            int t;
            if(connected==0) {   //we play unconnected
                Random r = new Random();
                t = r.nextInt(10);   //takes random game
            }
            else{
                UserStatistics tt = new UserStatistics();
                String UserName = tt.names.get(name_number);   //we take the player's name
                tt.addThePlayerFile(UserName);  //we load his file inside UserStatistics
                String[] files;
                files = tt.GetTheGamesWonOfPlayer();  //we get the file with the wins of Classic Sudoku
                Random r = new Random();
                boolean check;
                int checkFilledArray = 0;
                int check_if_array_is_filled;
                for (int num = 0; num < 10; num++) {
                    check_if_array_is_filled=0;
                    for (String s : files) {
                        if (num == Integer.parseInt(s))
                            check_if_array_is_filled++;  //it checks every number 1-9 if exists in files
                    }
                    if (check_if_array_is_filled!=0)
                        checkFilledArray++;
                }
                if(checkFilledArray==10)
                {
                    tt.delete_the_data(UserName);
                    t = r.nextInt(10);
                }
                else {
                    do {
                        t = r.nextInt(10);
                        check = false;
                        for (String file : files) {
                            if (t == Integer.parseInt(file))
                                check = true;
                        }
                    } while (check);  // It breaks when the random integer choose a number that's not inside the array of already played and won games.
                }
            }
            number_of_game_Chosed=t;
            board = new int[SIZE][SIZE];

            int i = 0, p = 0;
            while (i < SIZE) {   //We now build the chosen array
                int j = 0;
                while (j < SIZE) {
                    if (arrays[t][p] == '0')
                        board[i][j] = 0;  //We insert 0 if value is null
                    else
                        board[i][j] = (int) arrays[t][p]; //we insert the value if exists
                    j++;
                    p++;
                }
                i++;
            }
        }
        else  //this case is for Killer Sudoku
        {
            board = new int[SIZE][SIZE];
            for(int i=0;i<SIZE;i++)
            {
                for (int j = 0; j < SIZE; j++) {
                    board[i][j]=0;     //We fill the array with 0 because in the start every value is null
                }
            }
        }
    }
    /**
     * In this function we add all the Arrays we read from the files inside our 2d Array of [int][Char]
     */
    public void build(){
        int ii=0;
        for(int i=0;i<10;i++) { //we are doing it for 10 times , as the arrays-files are.
            try (BufferedReader in = new BufferedReader(
                    new FileReader("ClassicFiles/"+files[i]));  // we read every time the file with files[i] name
            ) {
                String l;
                int jj=0;
                while ((l = in.readLine()) != null) {

                    for (int j = 0; j < l.length(); j++) {

                        if (l.charAt(j) == '-') {  //if char is '-' , we make it '0' and add it
                            arrays[ii][jj]='0';

                        } else if (l.charAt(j) >= '1' && l.charAt(j) <= '9') {  //if is a number we transform it and then add it
                            int a = Character.getNumericValue(l.charAt(j));
                            arrays[ii][jj]=(char)a;
                        }
                        jj++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            ii++;
        }
    }
    /**
     * Accepts i and j values of the board and returns the value the board contains
     * @param i
     * @param j
     * @return the value
     */
    public int getBoard(int i,int j) {
        return board[i][j];
    }

    /**
     * Sets the board value
     * @param i
     * @param j
     * @param value
     */
    public void setBoard(int i, int j,int value){
        board[i][j]=value;
    }

    /**
     * We check if a possible number is already in a row
     * @param row , the row
     * @param number , the number
     * @return true if it is, false either
     */
    public boolean isInRow(int row, int number) {
        for (int i = 0; i < SIZE; i++)
            if (board[row][i] == number)
                return true;

        return false;
    }
    /**
     * We check if a possible number is already in a column
     * @param col ,the column
     * @param number ,the number
     * @return true if it is,false either
     */
    public boolean isInCol(int col, int number) {
        for (int i = 0; i < SIZE; i++)
            if (board[i][col] == number)
                return true;

        return false;
    }
    /**
     * We check if a possible number is in its 3x3 Cell
     * @param row ,number of row
     * @param col ,number of column
     * @param number ,the number
     * @return true if it is, false either
     */
    public boolean isInBox(int row, int col, int number) {
        int r = row - row % 3;
        int c = col - col % 3;

        for (int i = r; i < r + 3; i++)
            for (int j = c; j < c + 3; j++)
                if (board[i][j] == number)
                    return true;

        return false;
    }
    /**
     * Combined method to check if a number possible to a row,col position is ok
     * @param row , the row
     * @param col , the column
     * @param number , the number
     * @return true if all the checks are ok. For example if(!isInRow) means that the number doesn't exists in row and we can add it
     */
    public boolean isOk(int row, int col, int number) {
        return (!isInRow(row, number)  &&  !isInCol(col, number)  &&  !isInBox(row, col, number));
    }
}