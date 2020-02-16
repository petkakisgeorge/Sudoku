import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserStatisticsTest {

    @Test
    void addWinClassic() {
        UserStatistics t = new UserStatistics();
        t.addWinClassic("george",2);
        assertFalse(t.ThrowWriteInFileException);
    }

    @Test
    void addWinKiller() {
        UserStatistics t = new UserStatistics();
        t.addWinClassic("george",30);
        assertFalse(t.ThrowWriteInFileException);
    }

    @Test
    void addWinDuidoku() {
        UserStatistics t = new UserStatistics();
        t.addWinDuidoku("george",2);  //for lose
        assertFalse(t.ThrowWriteInFileException);
        t.addWinDuidoku("george",1);  //for win
        assertFalse(t.ThrowWriteInFileException);
    }

    @Test
    void createAuserName() {
        UserStatistics t = new UserStatistics();
        t.CreateAuserName("john");
        assertFalse(t.ThrowWriteInFileException);
        assertEquals(t.names.get(t.names.size()-1),"john");
    }

    @Test
    void getUsernamePlace() {
        UserStatistics t = new UserStatistics();
        int a = t.names.size()-1;
        assertEquals(a,t.getUsernamePlace());
    }

    @Test
    void addThePlayerFile() {
        UserStatistics t = new UserStatistics();
        t.addThePlayerFile("george");
        assertFalse(t.ThrowWriteInFileException);
    }

    @Test
    void getTheGamesWonOfPlayer() {
        UserStatistics t = new UserStatistics();
        t.addThePlayerFile("george");
        int a = Integer.parseInt(t.fileData.get(2)); //we take the game number in place 2 , 0 and 1 are for duidoku
        String[] File = t.GetTheGamesWonOfPlayer();  //we take the array with all the games.
        int b = Integer.parseInt(File[0]);
        assertEquals(a,b);

    }
}