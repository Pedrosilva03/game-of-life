import java.util.Random;

import javax.swing.*;
import java.awt.*;

public class Main{
    private static int rows = 100;
    private static int cols = 100;
    private static int cellSize;

    private static int[][] grid;
    private static int population = 0;
    private static int generation = 0;

    private static Random random = new Random();

    private static JFrame jframe;
    private static JPanel jpanel;
    private static JPanel statsPanel;

    private static void setupWindow(){
        jpanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                cellSize = Math.min(getWidth() / cols, (getHeight() - 60) / rows);

                int xOffset = (getWidth() - (cols * cellSize)) / 2;
                int yOffset = (getHeight() - (rows * cellSize)) / 2;

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        int colorValue = (grid[j][i] == 1) ? 0 : 255;
                        g.setColor(new Color(colorValue, colorValue, colorValue));
                        g.fillRect(xOffset + j * cellSize, yOffset + i * cellSize, cellSize, cellSize);
                        g.drawRect(xOffset + j * cellSize, yOffset + i * cellSize, cellSize, cellSize);
                    }
                }
            }
        };

        statsPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial", Font.BOLD, 16));
                g.drawString("Generation = " + generation, 10, 20);
                g.drawString("Population = " + population, 10, 40);
            }
        };

        jframe = new JFrame("My Conway's Game of Life");
        jframe.setSize(new Dimension(600, 600));
        jframe.setLayout(new BorderLayout());
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        statsPanel.setPreferredSize(new Dimension(600, 60));

        jframe.add(jpanel, BorderLayout.CENTER);
        jframe.add(statsPanel, BorderLayout.SOUTH);

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
        jpanel.repaint();
        statsPanel.repaint();
    }

    public static void main(String[] args) {
        grid = generateGrid();

        population = getPopulation();

        setupWindow();
        while(population > 0){
            drawgrid();

            grid = Automation.simulate(grid);

            generation++;
            population = getPopulation();

            try{
                Thread.sleep(100);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        System.exit(0);
    }
}