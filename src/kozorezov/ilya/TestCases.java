package kozorezov.ilya;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCases {
    private static Map myMap;
    @BeforeEach
    void setUpMap(){
        myMap = new Map(5 , 5);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if ((i == 1 && j == 2) || (i==4 && j==4)) Map.setPos(i,j,1);
                else Map.setPos(i,j,0);
            }
        }

    }

    /**
     * Test for setting the position of a certain color
     */
    @Test
    void testSetPos(){
        Map.setPos(0,0,3);
        Map.setPos(1,0,4);
        assertEquals(myMap.toString(), "Map:\n3 0 0 0 0 \n4 0 1 0 0 \n0 0 0 0 0 \n0 0 0 0 0 \n0 0 0 0 1 \n");
    }

    /**
     * Test for clearing all the pipes (values > 9) on the map
     */
    @Test
    void testClearMap(){
        myMap.move(1,2,Direction.DOWN);
        myMap.move(2,2,Direction.DOWN);
        myMap.move(3,2,Direction.DOWN);
        Map.clearMap();
        assertEquals(myMap.toString(), "Map:\n0 0 0 0 0 \n0 0 1 0 0 \n0 0 0 0 0 \n0 0 0 0 0 \n0 0 0 0 1 \n");
    }

    /**
     * Test for clearing a specific color off the Map
     */
    @Test
    void testClearColor(){
        Map.clearColor(1);
        assertEquals(myMap.toString(), "Map:\n0 0 0 0 0 \n0 0 0 0 0 \n0 0 0 0 0 \n0 0 0 0 0 \n0 0 0 0 0 \n");
    }


}