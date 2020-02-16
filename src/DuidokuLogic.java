/**
 * This Class implements the Duidoku Logic
 * Builds an Array 4x4 .
 * Check the User Moves and if there are ok then,
 * add User Moves . Add Pc Moves , set the Blocked Areas and set the winner
 */

public class DuidokuLogic {
    private int[][] duidarr;
    public static final int SIZE = 4; //   size of Duidoku grids
    public static int I,J,VALUE=0;
    public int WinOrLose=0;  // if 1 means User Won, if 2 means Pc Won , 0 is the initialized number

    /**
     * Constructor of DuidokuLogic
     * Builds the array of Duidoku with 0 values
     */
    public DuidokuLogic()
    {
        this.duidarr= new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.duidarr[i][j] = 0;
            }
        }
    }
    /**
     * Set value function
     * @param row , the row
     * @param col , the column
     * @param num , the number we are adding
     */
    public void setDuidoku(int row,int col,int num)
    {
        duidarr[row][col]= num;
        SetBlockedAreas(); //we call this function to check and maybe set tha blocked cells after our add
    }

    /**
     * In this function we search for Cells that can't have a value and we give them -1 value make them unreachable
     */
    public void SetBlockedAreas()
    {
        for(int i=0;i<SIZE;i++)
        {
            for(int j=0;j<SIZE;j++)
            {
                if(duidarr[i][j]==0) { //if cell is free
                    int sum = 0;
                    for (int t = 1; t < 5; t++) {
                        if (isOk(i, j, t)) //trying every possible number
                            sum++;
                    }
                    if (sum == 0) //if no number was ok
                        duidarr[i][j] = -1;
                }
            }
        }
    }
    /**
     * This function Calls the PcMove function and also sets the 1 value for User Win and 2 value for Pc Win.
     */
    public void pc() {
        boolean t = PcMove();
        if (!t) {
            WinOrLose = 1;
        }
        else {
            int counter = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (duidarr[i][j] == 0)
                        counter++;
                }
            }
            if(counter==0)
                WinOrLose=2;
        }
        SetBlockedAreas();
    }
    /**
     * That's a simple function of Pc move that takes the first free cell that could be added a number and set it with this number
     * @return true if pc managed to make its move , false if didn't find any free cell.
     */
    public boolean PcMove()
    {
        for(int i=0;i<SIZE;i++)
        {
            for(int j=0;j<SIZE;j++)
            {
                if(duidarr[i][j]==0)
                {
                    for(int t=1;t<5;t++)
                    {
                        if(isOk(i,j,t)) {
                            duidarr[i][j] = t;
                            I=i;
                            J=j;
                            VALUE=t;
                            return true;
                        }
                    }
                }

            }
        }
        return false;
    }
    /**
     *
     * @param i , the coordinate i
     * @param j , the coordinate j
     * @return the value of duidoku array for these coordinates
     */
    public int get_duidar_value(int i,int j)
    {
        return duidarr[i][j];
    }
    /**
     * We check if a possible number is already in a row
     * @param row , the row
     * @param number , the number
     * @return true if it is, false either
     */
    public boolean isInRow(int row, int number) {
        for (int i = 0; i < SIZE; i++)
            if (duidarr[row][i] == number)
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
            if (duidarr[i][col] == number)
                return true;

        return false;
    }

    /**
     * We check if a possible number is in its 2x2 Cell
     * @param row ,number of row
     * @param col ,number of column
     * @param number ,the number
     * @return true if it is, false either
     */
    public boolean isInBox(int row, int col, int number) {
        int r = row - row % 2;
        int c = col - col % 2;

        for (int i = r; i < r + 2; i++)
            for (int j = c; j < c + 2; j++)
                if (duidarr[i][j] == number)
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
        return !isInRow(row, number)  &&  !isInCol(col, number)  &&  !isInBox(row, col, number);
    }
}