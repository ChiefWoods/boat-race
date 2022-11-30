import java.util.*;
import java.lang.*;

public class Scoreboard
{
    private ArrayList<Player> topScores = new ArrayList<>(Player);
    private Scanner input;
    private Formatter output;

    public void loadScores()
    {
        input = openInputFile("topScores.txt");
        readScores();
        closeInputFile(input);
    }

    public void saveScores()
    {
        output = openOutputFile("topScores.txt");
        writeScores();
        closeOutputFile(output);
    }

    public Scanner openInputFile(String filename)
    {
        Scanner tempinput=null;
        try
        {
            tempinput = new Scanner(new File(filename));
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            System.err.println("Error opening file.");
            System.exit(1);
        }
        return tempinput;
    }

    public Formatter openOutputFile(String filename)
    {
        Formatter tempoutput=null;
        try
        {
            tempoutput = new Formatter(new File(filename));
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            System.err.println("Error opening file.");
            System.exit(1);
        }
        return tempoutput;
    }

    public void closeInputFile(Scanner input)
    {
        if (input!=null)
            input.close();
    }

    public void closeOutputFile(Formatter output)
    {
        if (output!=null)
            output.close();
    }

    public void readScores()
    {
        try {
            while(input.hasNext()){
                Player player = new Player();
                player.setName(input.next());
                player.setTopScores(input.nextInt());
                topScores.add(player);
            }
        }
        catch (NoSuchElementException elementException)
        {
            System.err.println("File improperly formed.");
            input.close();
            System.exit(1);
        }
        catch (IllegalStateException stateException)
        {
            System.err.println("Error reading from file.");
            System.exit(1);
        }
    }

    public void writeScores()
    {
        for ( int k = 0; k < topScores.size(); k++ )
        {
            output.format("%s %d\n", topScores.get(k).getName(), topScores.get(k).getTopScores());
        }
    }

    public void addPlayer(Player a)
    {
        topScores.add(a);
    }

    public void sortScores()
    {
        Collections.sort(topScores, Player.PlayerScore);
    }

    public String toString()
    {
        String a="";
        for(int i = 0; i<5; i++){
            a += String.format("%d. %s %d\n", i+1,topScores.get(i).getName(),topScores.get(i).getTopScores());
        }
        return a;
    }
}
