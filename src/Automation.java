public class Automation {
    private static int countNeighbours(int currentGeneration[][], int cellX, int cellY){
        int neighbourCount = 0;
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= -1; j++){
                if(currentGeneration[cellX + i][cellY + j] == 1 && !(i == 0 && j == 0)){
                    neighbourCount++;
                }
            }
        }
        return neighbourCount;
    }

    private static int checkRules(int currentCell, int numNeighbours){
        if(numNeighbours < 2 && currentCell == 1){
            return 0;
        }
        if((numNeighbours == 2 || numNeighbours == 3) && currentCell == 1){
            return 1;
        }
        if(numNeighbours > 3 && currentCell == 1){
            return 0;
        }
        if(numNeighbours == 3 && currentCell == 0){
            return 1;
        }
        return 0;
    }

    public static int[][] simulate(int currentGeneration[][]){
        int newGeneration[][] = new int[currentGeneration.length][currentGeneration[0].length];

        for(int i = 0; i < currentGeneration.length; i++){
            for(int j = 0; j < currentGeneration[0].length; j++){
                newGeneration[i][j] = checkRules(currentGeneration[i][j], countNeighbours(currentGeneration, i, j));
            }
        }

        return newGeneration;
    }
}
