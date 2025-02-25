import java.util.Random;

import javax.swing.*;
import java.awt.*;

public class Main{
    private static int rows = 10;
    private static int cols = 10;
    private static int cellSize = 40;

    private static int[][] grid;
    private static int population;
    private static int generation = 0;

    private static Random random = new Random();

    private static JFrame jframe;
    private static JPanel jpanel;

    private static void setupWindow(){
        jpanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        int colorValue = (int) (grid[j][i] * 255);
                        g.setColor(new Color(colorValue, colorValue, colorValue));
                        g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                        g.setColor(Color.BLACK);
                        g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
                    }
                }
            }
        };

        jframe = new JFrame("Lock's Conway's Game of Life");
        jframe.setSize(new Dimension((rows + 1) * cellSize, (cols + 1) * cellSize));
        jframe.setLayout(new BorderLayout());
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(jpanel, BorderLayout.CENTER);
        jframe.setVisible(true);
    }

    private static int[][] generateGrid(){
        int[][] newGrid = new int[cols][rows];

        for(int i = 0; i < newGrid.length; i++){
            for(int j = 0; j < newGrid[0].length; j++){
                newGrid[i][j] = random.nextInt(2);
            }
        }

        return newGrid;
    }

    private static int getPopulation(){
        int currentPopulation = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    currentPopulation++;
                }
            }
        }
        return currentPopulation;
    }

    private static void drawgrid(){
        for(int i = 0; i < grid.length; i++){
            String line = "";
            for(int j = 0; j < grid[0].length; j++){
                line += grid[j][i] + " ";
            }
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        grid = generateGrid();

        setupWindow();

        population = getPopulation();
        while(population > 0){
            drawgrid();

            //grid = Automation.simulate(grid);

            generation++;
            population = getPopulation();
        }
    }
}