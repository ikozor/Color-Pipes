package kozorezov.ilya;
/**
 * Get the information from the game file and then put the map into a 2D array that represents the game map.
 * Then the game will be put into the GUI
 *
 * Some methods here are to get information to build a solver, so they have not been used in this project yet
 */

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class inputReader {
    // Create a 2D array that will rep the map
    private static Map map;
    private static final int[] check = new int[9];

    public static double[] getDistances() {
        return distances;
    }

    private static final double[] distances = new double[9];
    private static final boolean[] distanceCheck = new boolean[9];
    private static final Point[] points = new Point[9];


    inputReader() throws FileNotFoundException{
        //find the file and make sure it is correct
       new fileGUI();
        while(!fileGUI.getGotFile()){                           //Wait until the program finds the file
            System.out.print("");
        }
        //Get the file and fill in the 2D array with the contents of the file if everything is correct
        File input = fileGUI.getMapFile();
        System.out.println(input);

        Scanner reader = new Scanner(input);
        Arrays.fill(distances, -1);
        //try{
            int x = reader.nextInt();
            int y = reader.nextInt();
            map = new Map(x,y);
            int i = 0;
            while(reader.hasNextInt()){
                int n = reader.nextInt();
                int X;
                if(x==y) X = i/x;
                else if(x>y) X = (i+(i/x) +1 )/x;
                else X = (i-1)/x;

                if(X>= x) X = x-1;

                int Y = i%y;

                Map.setPos(X,Y, n);

                //If there are more than 2 dots of 1 color, throw error
                if(n!=0 && ++check[n-1] > 2){
                    System.out.println("Error: More than 2 of one color, please fix and rerun program");
                    System.exit(-1);
                }

                //To get the distance between each pair of dots
                if(n!= 0 && distanceCheck[n-1]){
                    Point second = new Point(X,Y);

                    if(second.getX() == points[n-1].getX()){
                        distances[n-1] = 0;
                    }
                    else if(second.getY() == points[n-1].getY()){
                        distances[n-1] = 0;
                    }
                    else{
                        distances[n-1] = Math.min(Math.abs(second.getX()-points[n-1].getX()) /
                                Math.abs(second.getY()-points[n-1].getY()) ,
                                Math.abs(second.getY()-points[n-1].getY())) / Math.abs(second.getX()-points[n-1].getX());
                    }
                }
                else if(n!=0){
                    distanceCheck[n-1] = true;

                    points[n-1] = new Point(X,Y);
                }
                i++;
            }
            for (int j : check) {
                if (j == 1) {
                    System.out.println("Error: There is one 1 node of one color, please fix and rerun program");
                    System.exit(-1);
                }
            }
            //Throw exception of the file was not correct
       }
    public static Map getMap() {
        return map;
    }
}
