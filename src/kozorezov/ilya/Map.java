package kozorezov.ilya;

/**
 * This is where the map will be created and represented in a 2D array, I thought it would be easier to understand
 * and less prone to errors if I can create the map in a 2D array and then use that to create the GUI
 */

public class Map {
    private static int[][] myMap;

    private static int myXSize;
    private static int myYSize;

    Map(int theX, int theY){                                                        // create an empty map
        myMap = new int[theX][theY];
        myXSize = theX;
        myYSize = theY;

    }

    public static void setPos(int theX, int theY, int theN){                         // Set a space a certain color
        myMap[theX][theY] = theN;
    }

    public boolean move(int theX, int theY, Direction theDirection){             // for when I try to create a solver for the map
        int moveX = 0;                                      // basically get a block and move in the direction specified
        int moveY = 0;
        switch(theDirection){
            case RIGHT:
                moveY = 1;
                if(theY > myYSize || myMap[theX][theY + 1] != 0) return false;
                break;
            case LEFT:
                moveY = -1;
                if(theY <= 0 || myMap[theX][theY - 1] != 0) return false;
                break;
            case UP:
                moveX = -1;
                if(theX <= 0 || myMap[theX - 1][theY] != 0) return false;
                break;
            case DOWN:
                moveX = 1;
                if(theX > myXSize || myMap[theX + 1][theY] != 0) return false;
                break;
            default:
                return false;
        }
        switch (myMap[theX][theY]) {
            case 0 -> System.out.println("invalid move");
            case 1, 10 -> myMap[theX + moveX][theY + moveY] = 10;
            case 2, 20 -> myMap[theX + moveX][theY + moveY] = 20;
            case 3, 30 -> myMap[theX + moveX][theY + moveY] = 30;
            case 4, 40 -> myMap[theX + moveX][theY + moveY] = 40;
            case 5, 50 -> myMap[theX + moveX][theY + moveY] = 50;
            case 6, 60 -> myMap[theX + moveX][theY + moveY] = 60;
            case 7, 70 -> myMap[theX + moveX][theY + moveY] = 70;
            case 8, 80 -> myMap[theX + moveX][theY + moveY] = 80;
            case 9, 90 -> myMap[theX + moveX][theY + 1] = 90;
        }
    return true;
    }

    public static void clearMap() {                                     // clear the map
        for(int i = 0; i<myXSize;i++){
            for(int j =0;j<myYSize;j++){
                if(myMap[i][j]>= 10)myMap[i][j] =0;
            }
        }
    }

    public static void clearColor(int theColor) {                         // clear a color
        for(int i = 0; i<myXSize;i++){
            for(int j =0;j<myYSize;j++){
                if(myMap[i][j] == theColor) myMap[i][j] =0;
            }
        }
    }

    public String toString(){                                           // convert the Map into a String
        StringBuilder string = new StringBuilder();
        string.append("Map:\n");
        for(int[] i :myMap){
            for(int j :i){
                string.append(j + " ");
            }
            string.append("\n");
        }
        return String.valueOf(string);
    }

    // all the getters
    public static int getY() {
        return myYSize;
    }
    public static int getX() {
        return myXSize;
    }
    public static int[][] getMap(){
        return myMap;
    }
}
