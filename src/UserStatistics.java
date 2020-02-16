import java.io.*;
import java.util.ArrayList;

/**
 * UserStatistics Class is used for writing and reading data for and from User files.
 */
public class UserStatistics {
    public ArrayList<String> names = new ArrayList<>(); //contains the names of every created User
    public ArrayList<String> fileData = new ArrayList<>(); //contains the data of the connected User
    private static int wins,loses;
    public boolean ThrowWriteInFileException=false;


    /**
     * Constructor ,when we Create an object of the UserStatistics Class ,reads the names of all the users
     * from the file with names , and adds them into an ArrayList.
     */
    public UserStatistics()
    {
        try (BufferedReader in = new BufferedReader(
                new FileReader("UserStatistics/names.txt"));
        ) {
            String l;
            while ((l = in.readLine()) != null) {
                names.add(l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Adding the game of Classic ,the user won.
     * @param name ,the file we open with the name of user
     * @param addingGame ,the game he won
     */
    void addWinClassic(String name,int addingGame)
    {
        try (FileWriter w = new FileWriter("UserStatistics/names/"+name,true)) {
            w.write('\n');
            w.write(String.valueOf(addingGame));
        } catch (IOException e) {
            ThrowWriteInFileException=true;
            e.printStackTrace();
        }
    }
    /**
     * Adding the game of Killer, the user won.
     * We add 10 for 1,20 for 2,30 for 3...etc and 100 for 0,
     * because we want to separate them from Classic's store form.
     * @param name ,the file we open with the name of user
     * @param addingGame ,the game he won
     */
    void addWinKiller(String name,int addingGame)
    {
        if(addingGame!=0) {
            addingGame = addingGame * 10;  //multiplying them with 10 to separate them from Classic's
        }
        else
        {
            addingGame = 100; //means is 0
        }
        try (FileWriter w = new FileWriter("UserStatistics/names/"+name,true)) {
            w.write('\n');
            w.write(String.valueOf(addingGame));
        } catch (IOException e) {
            ThrowWriteInFileException=true;
            e.printStackTrace();
        }
    }
    /**
     * Adding the game of Duidoku, the user won.
     * @param name ,the file we open with the name of user
     * @param state ,the number of win (1) or lose (2)
     */
    void addWinDuidoku(String name,int state)
    {
        try (BufferedReader in = new BufferedReader(
                new FileReader("UserStatistics/names/"+name));
        ) {
            String l;
            fileData.clear();
            while ((l = in.readLine()) != null) {
                fileData.add(l);  //we add every line we read from the file, in the ArrayList of Strings (fileData).
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        wins = Integer.parseInt(fileData.get(0));
        loses = Integer.parseInt(fileData.get(1));
        if(state==2)
            loses++;
        else if (state==1)
            wins++;
        try (FileWriter w = new FileWriter("UserStatistics/names/"+name,false)) { //overwrites the file
            w.write(String.valueOf(wins));
            w.write("\n");
            w.write(String.valueOf(loses));
            if(fileData.size()>2) {
                for (int i = 2; i < fileData.size(); i++) {
                    w.write("\n");
                    w.write(fileData.get(i));
                }
            }
        } catch (IOException e) {
            ThrowWriteInFileException=true;
            e.printStackTrace();
        }

    }
    /**
     * Adds to the file of names the userName we want to create.
     * @param name ,the userName
     */
    void CreateAuserName(String name)
    {

        try (FileWriter w = new FileWriter("UserStatistics/names.txt",true)) {
            w.write(name);
            names.add(name);
            w.write("\n"); //we change the line ,so the next name would'nt be overwrite in the previous name.
        } catch (IOException e) {
            ThrowWriteInFileException=true;
            e.printStackTrace();
        }

        try (FileWriter ww = new FileWriter("UserStatistics/names/"+name)) {
            ww.write("0\n0");  //after we Create a User's file, we add the default values for Duidoku Wins or Loses.
        } catch (IOException e) {
            ThrowWriteInFileException=true;
            e.printStackTrace();
        }
    }
    /**
     * @return the i of the last added name ,in the array of names.
     */
    int getUsernamePlace()
    {
        return names.size()-1;
    }

    /**
     * This function takes a User's name and load the data from his file,
     * so we can use them while he is playing.
     * @param name ,the User's name
     */
    void addThePlayerFile(String name)
    {
        try (BufferedReader in = new BufferedReader(
                new FileReader("UserStatistics/names/"+name));
        ) {
            String l;
            fileData.clear();
            while ((l = in.readLine()) != null) {
                    fileData.add(l); //we add every line we read into the ArrayList of Data
            }
        } catch (IOException e) {
            ThrowWriteInFileException=true;
            e.printStackTrace();
        }
    }
    /**
     * Creates a String Array and adds only the wins of Classic And Killer Sudoku.
     * @return the String Array we created
     */
    String[] GetTheGamesWonOfPlayer()
    {
        String[] file = new String[fileData.size()-2];
        for(int i=2;i<fileData.size();i++)
        {
            file[i-2]=fileData.get(i);
        }
        return file;
    }

    /**
     * rewrites the data file of a player
     * ,after he finished all the games,
     * so he can play from begin all the games
     * @param name, the name of the connected User
     */
    void delete_the_data(String name)
    {
        try (FileWriter ww = new FileWriter("UserStatistics/names/"+name,false)) {
            ww.write("0\n0");  //after we Create a User's file, we add the default values for Duidoku Wins or Loses.
        } catch (IOException e) {
            ThrowWriteInFileException=true;
            e.printStackTrace();
        }
    }
}