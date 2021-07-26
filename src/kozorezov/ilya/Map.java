package kozorezov.ilya;

public class Map {
    private static int[][] map;

    private static int xSize;
    private static int ySize;

    Map(int x, int y){
        map = new int[x][y];
        xSize = x;
        ySize = y;

    }

    public static void setPos(int x, int y, int n){
        map[x][y] = n;
    }

    public int getPos(int x, int y){
        return map[x][y];
    }

    public boolean move(int x, int y, Direction direction){
        int moveX = 0;
        int moveY = 0;
        switch(direction){
            case RIGHT:
                moveY = 1;
                if(y > ySize || map[x][y + 1] != 0) return false;
                break;
            case LEFT:
                moveY = -1;
                if(y <= 0 || map[x][y - 1] != 0) return false;
                break;
            case UP:
                moveX = -1;
                if(x <= 0 || map[x - 1][y] != 0) return false;
                break;
            case DOWN:
                moveX = 1;
                if(x > xSize || map[x + 1][y] != 0) return false;
                break;
            default:
                return false;
        }
        switch (map[x][y]) {
            case 0 -> System.out.println("invalid move");
            case 1, 10 -> map[x + moveX][y + moveY] = 10;
            case 2, 20 -> map[x + moveX][y + moveY] = 20;
            case 3, 30 -> map[x + moveX][y + moveY] = 30;
            case 4, 40 -> map[x + moveX][y + moveY] = 40;
            case 5, 50 -> map[x + moveX][y + moveY] = 50;
            case 6, 60 -> map[x + moveX][y + moveY] = 60;
            case 7, 70 -> map[x + moveX][y + moveY] = 70;
            case 8, 80 -> map[x + moveX][y + moveY] = 80;
            case 9, 90 -> map[x + moveX][y + 1] = 90;
        }
    return true;
    }

    public static void clearMap() {
        for(int i = 0; i<xSize;i++){
            for(int j =0;j<ySize;j++){
                if(map[i][j]>= 10)map[i][j] =0;
            }
        }
    }

    public static void clearColor(int color) {
        for(int i = 0; i<xSize;i++){
            for(int j =0;j<ySize;j++){
                if(map[i][j] == color) map[i][j] =0;
            }
        }
    }
    public static void printMap(){
        System.out.println("Map:");
        for(int[] i :map){
            for(int j :i){
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int getY() {
        return ySize;
    }
    public static int getX() {
        return xSize;
    }
    public static int[][] getMap(){
        return map;
    }
}
