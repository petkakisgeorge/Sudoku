/**
 * This Class represent a Cell of 2 Coordinates(i,j), that contains a value.
 */
public class Cell
{
    private  int i,j; //coordinates
    private int value;  //Cell value

    public Cell(){}

    public Cell(int coi, int coj){
        this.i=coi;
        this.j=coj;
    }
    public void setbox(int bvalue)
    {
        value=bvalue;
    }
    public int getValue(){ return value; }
    public int getI(){return i;}
    public int getJ(){return j;}
}
