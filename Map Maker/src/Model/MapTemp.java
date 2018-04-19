package Model;

/*
* Template for all maps
* Handles addition of new segments
* Handles expanding and clearing a map
 */
public class MapTemp {
    private static final int MAP_PIXLES_LENGTH = 600;
    private static final int MAP_PIXELS_HEIGHT = 480;
    private int mapPixelsX;
    private int mapPixelsY;
    private int[][] map;

    public MapTemp() {
        this.mapPixelsX = MAP_PIXLES_LENGTH;
        this.mapPixelsY = MAP_PIXELS_HEIGHT;
        this.map = new int[mapPixelsX][mapPixelsY];
    }

    public int getCols() {return mapPixelsX;}
    public int getRows() {return mapPixelsY;}
    public int getVal(int x, int y) {return map[x][y];}

    /*
    Effect: Checks if a segment exits in the map
     */
    public boolean checkSegExist(RoadState r) {
        for(int i = 0; i < mapPixelsX; ++i) {
            for(int j = 0; j < mapPixelsY; ++j) {
                if(map[i][j] == r.stateVal) return true;
            }
        }
        return false;
    }

    /*
    Modifies: this
    Effects: Increases the length and height of the feature map
     */
    public void extendPixelsX() {
        int[][] temp = new int[mapPixelsX + 1][mapPixelsY];
        for(int i = 0; i < mapPixelsX; ++i) {
            for(int j = 0; j < mapPixelsY; ++j) {
                temp[i][j] = this.map[i][j];
            }
        }
        this.map = temp;
        ++mapPixelsX;
    }
    public void extendPixelsY() {
        int[][] temp = new int[mapPixelsX][mapPixelsY + 1];
        for(int i = 0; i < mapPixelsX; ++i) {
            for(int j = 0; j < mapPixelsY; ++j) {
                temp[i][j] = this.map[i][j];
            }
        }
        this.map = temp;
        ++mapPixelsY;
    }


    /*
    Effect: checks if a pixel is out of bound of the feature map
    Parameters: possible x or y values on the feature map
     */
    public boolean outOfBoundPixelX(int x) {
        return x < 0 || x >= mapPixelsX;
    }
    public boolean outOfBoundPixelY(int y) {
        return y < 0 || y >= mapPixelsY;
    }

    /*
    Modifies: this
    Effect: Adds a road segment to the feature map. Extends map if
            the user wants to place the segment outside the map bounds
     */
    public void addSeg(SegTemp s) {
        while(outOfBoundPixelX(s.getXBound())) extendPixelsX();
        while(outOfBoundPixelY(s.getYBound())) extendPixelsY();
        for(int i = 0; i < s.getPixelsY(); ++i) {
            for (int j = 0; j < s.getPixelsX(); ++j) {
                map[s.getPosX() + j][s.getPosY() + i] = s.getVal();
            }
        }
    }

    /*
    Modifies: this
    Effect: Resets the map, all values set to 0, map is set back to original size
     */
    public void clearMap() {
        this.mapPixelsX = MAP_PIXLES_LENGTH;
        this.mapPixelsY = MAP_PIXELS_HEIGHT;
        this.map = new int[mapPixelsX][mapPixelsY];
    }

    public void compactMap() {
        //Code for compacting map
    }
}












