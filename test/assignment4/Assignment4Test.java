package assignment4;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class Assignment4Test {
    
    private static final String IMAGE_DIR = "pics";
    private static final String[] IMAGE_LIST =
        {"crosses.bmp", "diagonal.bmp", "disks.bmp", "letters.bmp", "shapes.bmp", "text_binary.bmp", "empty.bmp"};
    private static final int[] NO_OF_ISLANDS = {9, 88, 22, 25, 145, 87, 0};
    private static final List<Map> MAPS = new ArrayList<>();
    
    private static class Map {
        
        private final int height;
        private final int width;
        private final boolean[][] map;
        
        /**
         * Reads a binary image, stores its width and height and the images as a boolean[][].
         * 
         * @param filename image's file name with path to it included
         * @throws IOException image not found
         */
        public Map(String filename) throws IOException {
            BufferedImage img = ImageIO.read(new File(filename));
            this.height = img.getHeight();
            this.width = img.getWidth();
            this.map = new boolean[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    this.map[i][j] = ((char) img.getRGB(j, i) == 0);
                }
            }
        }
        
        public int getHeight() {
            return this.height;
        }
        
        public int getWidth() {
            return this.width;
        }
        
        public boolean[][] getMap() {
            return this.map;
        }
    }
    
    /**
     * Creates a list of maps from the list of image names.
     */
    @BeforeClass
    public static void setUpClass() {
        for (String img : IMAGE_LIST) {
            try {
                MAPS.add(new Map(Paths.get(IMAGE_DIR, img).toString()));
            } catch (IOException e) {
                System.err.println(e.getLocalizedMessage());
            }
        }
    }

    /**
     * Tests the 2 countIsland functions on all images.
     */
    @Test
    public void testCountIslands() {
        int n = MAPS.size();
        for (int i = 0; i < n; i++) {
            Map m = MAPS.get(i);
            System.out.println("countIslandsFloodFill: " + IMAGE_LIST[i]);
            assertEquals("FloodFill " + IMAGE_LIST[i], NO_OF_ISLANDS[i],
                    Assignment4.countIslandsFloodFill(m.getMap(), m.getHeight(), m.getWidth()));
            System.out.println("countIslandsEquivalenceGraph: " + IMAGE_LIST[i]);
            assertEquals("EquivalenceGraph " + IMAGE_LIST[i], NO_OF_ISLANDS[i],
                    Assignment4.countIslandsEquivalenceGraph(m.getMap(), m.getHeight(), m.getWidth()));
        }
    }
}
