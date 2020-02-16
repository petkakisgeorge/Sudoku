import java.util.ArrayList;

/**
 * This Class is used for storing a ColorArea and all of her cells.
 */
public class ColorArea {
    public ArrayList<Cell> ColorAreaa; //Contains all the cells of each ColorArea
    public int sum; //the sum of each ColorArea

    /**
     * Constructor ,initialize the ArrayList for cells.
     */
    public ColorArea()
    {
        ColorAreaa= new ArrayList<>();

    }
    /**
     * Creating a Cell and adding it into the ColorArea.
     * @param i ,the coordinate i
     * @param j ,the coordinate j
     */
    public void AddColorAreaBox(int i,int j)
    {
        Cell b= new Cell(i,j);
        ColorAreaa.add(b);
    }
    /**
     * Setting the sum for the ColorArea
     * @param summ , the sum
     */
    public void setSum(int summ)
    {
        sum=summ;
    }
    /**
     * Checks all the cells' values and counts them if there are >0.
     * After we increase counter for the values that exists and are acceptable(>0),
     * we increase the counter for 1 ,because we want to see if with the number
     * we want to add, we will fill all the ColorArea's Cells.
     * Then compare them with the size of cells the ColorArea has.
     * If there are equals means that ColorArea is filled up for every cell.
     * @return true if every cell of ColorArea is filled up, false if not.
     */
    public boolean checkIfAllCellsAreFilled()
    {
        int counter=0;
        for(int i=0; i < ColorAreaa.size(); i++)
        {
            if (ColorAreaa.get(i).getValue()>0)
                counter++;
        }
        counter++; //increasing the counter for 1
        if(counter==ColorAreaa.size()) {
            return true;
        }
        return false;
    }
    /**
     * This function checks if we can add a number, based in the sum.
     * If with adding the number ,the sum of cells get over the sum of ColorArea,
     * or we fill every cell but sum of cells isn't equal with sum of ColorArea,
     * then we can't add the number.
     * @param num , the number the user wants to add
     * @return true if we can add it , false if we can't
     */
    public boolean checksum(int num)
    {
        int sumofcells = 0;
        for (int i = 0; i < ColorAreaa.size(); i++) {
            if(ColorAreaa.get(i).getValue()>0) {
                sumofcells += ColorAreaa.get(i).getValue();
            }
        }
        sumofcells+=num;
        if (sumofcells < sum) {
            if (checkIfAllCellsAreFilled()) {   //if true means all the cells are filled up
                if (sumofcells != sum)
                    return false;
            }
            else
                return true;
        } else if (sumofcells == sum) {    //if sum of cells is equals with sum of ColorArea
            if (checkIfAllCellsAreFilled()) //check if all cells are filled up
                return true;
        }
        return false;
    }
}