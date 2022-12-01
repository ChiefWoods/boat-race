package com.assignment.object;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Scoreboard
{
    private ArrayList<Player> topScores = new ArrayList<Player>();
    private Scanner input;
    private Formatter output;
    private String filepath = "src/com/assignment/score.txt";

    public void loadScores()
    {
        input = openInputFile(filepath);
        readScores();
        closeInputFile(input);
    }

    public void saveScores()
    {
        output = openOutputFile(filepath);
        writeScores();
        closeOutputFile(output);
    }

    public Scanner openInputFile(String filename)
    {
        Scanner tempinput = null;
        try
        {
            tempinput = new Scanner(new File(filename));
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            System.err.println(fileNotFoundException);
            System.exit(1);
        }
        return tempinput;
    }

    public Formatter openOutputFile(String filename)
    {
        Formatter tempoutput = null;
        try
        {
            tempoutput = new Formatter(filename);
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            System.err.println(fileNotFoundException);
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
                player.setScore(input.nextInt());
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
            output.format("%s %d\n", topScores.get(k).getName(), topScores.get(k).getScore());
        }
    }

    public void addPlayer(Player a)
    {
        topScores.add(a);
    }


    public void sortTopScore() {
        Collections.sort(topScores, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return Integer.valueOf(o2.getScore()).compareTo(o1.getScore());
            }
        });
        Collections.reverse(topScores);
    }
    
    

    public String toString()
    {
        String board = "Score Board: \n";
        for(int i = 0; i<5; i++){
            board += String.format("%d. %s %d rounds\n", i+1,topScores.get(i).getName(),topScores.get(i).getScore());
        }
        return board;
    }
}
