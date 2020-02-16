import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * This Class is the logic Class of Killer Sudoku and extends the logic class of Classic Sudoku.
 * Reads the files of Killer Sudoku and add them into an Array of ArrayLists of Characters.
 * Gets the board from Classic with zero values and also gets the check methods of Classic.
 * Add the game that has been chosen inside the board and inside an ArrayList of ColorAreas
 * so that we can check the rules of Classic and the sum of every ColorArea(rules of Killer).
 * Implements the check method for ColorAreas .
 */
public class KillerSudokuLogic extends ClassicSudokuLogic {

    public ArrayList<ColorArea> Colorareas; // creating an ArrayList from Class ColorArea.
    public String[][] ColorArrayGui = new String[9][9]; //Array we will store the colors of the game we will play for all the coordinates
    private ArrayList<Character>[] AllArraysStored = new ArrayList[10];
    private String[] filesKiller = {"Array0", "Array1", "Array2", "Array3", "Array4", "Array5", "Array6", "Array7", "Array8", "Array9"};
    public static int number_of_game_chosed;


    /**
     * This is our Constructor that extend ClassicLogic's Constructor
     * @param connected ,if is 0 then we play unconnected, if is 1 then we play connected with a user
     * @param name ,is the i stored in the array of names containing the connected users name
     */
    public KillerSudokuLogic(int connected, int name) {
        super(1,connected,name); // 1 means we play for Killer
        Colorareas = new ArrayList<>(); //Initialize
        buildKiller(); //adds the 10 files of Killer
        loadGame(connected,name); //Choose a game and add it
    }

    /**
     * In this function we add all the Arrays we read from the files inside our Array of ArrayLists of Chars
     */
    public void buildKiller(){
        for(int i=0;i<10;i++) {
            AllArraysStored[i]= new ArrayList<>(); //Initialize
            try (BufferedReader in = new BufferedReader(
                    new FileReader("KillerFiles/"+filesKiller[i]));
            ) {
                String l;
                while ((l = in.readLine()) != null) {
                    int j=0;
                    while(j<l.length())
                    {
                        AllArraysStored[i].add(l.charAt(j));
                        j++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * In this function if we are connected we choose randomly a game from our Array of 10 ArrayLists.
     * If not , we choose randomly from games that player hasn't won yet.
     * @param connected ,if is 0 then we play unconnected, if is 1 then we play connected with a user
     * @param name_number ,is the i stored in the array of names containing the connected users name
     * In the file of won games of user, number 10 means 1,20 means 2....etc , number 100 means 0.
     * We do that to separate the number games of Classic,where are 1,2,3..etc.
     */
    public void loadGame(int connected,int name_number)
    {
        int tt;
        if(connected==0) {
            Random r = new Random();
            tt = r.nextInt(10);
        }
        else{
            UserStatistics ttt = new UserStatistics();
            String UserName = ttt.names.get(name_number); //we take the player's name
            ttt.addThePlayerFile(UserName);//we load his file inside UserStatistics
            String[] files;
            files = ttt.GetTheGamesWonOfPlayer();   //we get the file with the wins of Killer Sudoku
            Random r = new Random();
            boolean check;
            int checkFilledArray = 0;
            int check_if_array_is_filled;
            for (int num = 0; num < 10; num++) {
                check_if_array_is_filled=0;
                for (String s : files) {
                    if (num!=0) {
                        if (num * 10 == Integer.parseInt(s))
                            check_if_array_is_filled++;  //it checks every number 1-9 if exists in files
                    }
                    else
                    {
                        if (Integer.parseInt(s)==100)
                            check_if_array_is_filled++;  //it checks every number 1-9 if exists in files
                    }
                }
                if (check_if_array_is_filled!=0)
                    checkFilledArray++;
            }
            if(checkFilledArray==10)
            {
                ttt.delete_the_data(UserName);
                tt = r.nextInt(10);
            }
            else {
                do {
                    tt = r.nextInt(10);
                    check = false;
                    for (String file : files) {
                        if (Integer.parseInt(file) != 100) {
                            if (tt * 10 == Integer.parseInt(file)) { //we multiply with 10 every number so we can compare only the games of killer
                                check = true;  //if game tt exists
                            }
                        } else   //means that value 100 is value 0 for killer
                            if (tt == 0) {
                                check = true;
                            }
                    }
                } while (check);  //check is true, while we choose a number that's not included in the player's won games
            }
        }
        number_of_game_chosed=tt;
        int i=0;
        //now we build the ColorAreas
        while(i<AllArraysStored[tt].size()) {
            ColorArea t = new ColorArea(); //We create a new ColorArea
            StringBuilder g = new StringBuilder(); //we use it to build the Color for the ColorArea
            while (AllArraysStored[tt].get(i) != ' ') {
                g.append(AllArraysStored[tt].get(i));
                i++;
            }
            i++;
            while(AllArraysStored[tt].get(i)!='#'){  //when is '#' starts a new ColorArea
                if (AllArraysStored[tt].get(i) == '.') {
                    i++;
                    if (AllArraysStored[tt].get(i + 1) == '#' || AllArraysStored[tt].get(i+1)=='!') { //if is '!' then we reached the end
                        int ar = Character.getNumericValue(AllArraysStored[tt].get(i));
                        t.setSum((ar)); //we add the Sum inside the ColorArea
                    }
                    else   //if sum is bigger than 9 and has 2 digits
                    {
                        int ar = Character.getNumericValue( AllArraysStored[tt].get(i) )* 10 + Character.getNumericValue( AllArraysStored[tt].get(i + 1));
                        t.setSum(ar);
                        i++;
                    }
                } else {
                    int a = Character.getNumericValue(AllArraysStored[tt].get(i));
                    int b = Character.getNumericValue(AllArraysStored[tt].get(i + 1));
                    t.AddColorAreaBox(a, b);
                    ColorArrayGui[a][b] = g.toString();  //converting StringBuilder back to String and adding a color for each Coordinates
                    i++;
                }
                i++;
                if(AllArraysStored[tt].get(i)=='!')
                    break;
            }
            Colorareas.add(t); //we add the ColorArea we have created,inside the Colorareas, the ArrayList of ColorAreas
            if(AllArraysStored[tt].get(i)=='!') //we reached the end and we break
                break;
        }
    }

    /**
     * In this function we are inserting a number in specific coordinates
     * @param row , the row
     * @param col , the column
     * @param number , the number we are adding
     */
    public void SetBoxNumber(int row, int col, int number) {
        super.setBoard(row,col,number); //we add the number inside the board
        //We Search every ColorArea ,every cell and when we find the wright coordinates we add the value
        for (int i = 0; i < Colorareas.size(); i++) {
            for (int j = 0; j < Colorareas.get(i).ColorAreaa.size(); j++) {
                if (Colorareas.get(i).ColorAreaa.get(j).getI() == row && Colorareas.get(i).ColorAreaa.get(j).getJ() == col) {
                    Colorareas.get(i).ColorAreaa.get(j).setbox(number);
                }
            }
        }
    }

    /**
     * @param row ,the row
     * @param col ,the column
     * @return the value inside board for this row and this column
     */
    public int GetBoard(int row,int col)
    {
        return super.getBoard(row,col);
    }

    /**
     * Checks if a number can be added Correctly or Not
     * @param row , the row
     * @param col , the column
     * @param number ,the number we want to add
     * @return false if number is not ok to being add, true if is ok
     */
    public boolean check(int row,int col, int number)
    {
        for (int i = 0; i < Colorareas.size(); i++) {
            for (int j = 0; j < Colorareas.get(i).ColorAreaa.size(); j++) {
                if (Colorareas.get(i).ColorAreaa.get(j).getI() == row && Colorareas.get(i).ColorAreaa.get(j).getJ() == col) {
                    if((super.isOk(row, col, number) && Colorareas.get(i).checksum(number))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
