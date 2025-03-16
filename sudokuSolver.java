public class sudokuSolver
{
    static block[][] puzzle;
    static blockCollection[] rows;
    static blockCollection[] columns;
    static blockCollection[] grids;

    //prints grid out simply for visualising
    public static void printGrid()
    {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                System.out.println("+-------+-------+-------+");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) {
                    System.out.print("| ");
                }
                System.out.print(puzzle[i][j]+ " "); // Example numbers
            }
            System.out.println("|");
        }
        System.out.println("+-------+-------+-------+");
    }
    public static void main(String[] args)
    {
        int[][] sudoku = {
            {1,8,6,0,0,0,0,0,5},
            {0,0,5,0,0,0,1,0,4},
            {0,9,0,0,0,1,0,0,0},
            {0,5,0,1,0,2,0,8,0},
            {0,0,1,4,0,0,7,0,0},
            {8,0,0,5,9,7,6,1,0},
            {2,1,0,0,4,0,0,0,6},
            {6,0,0,9,0,0,0,4,0},
            {5,0,0,7,1,0,0,3,8},
        };
        puzzle = new block[9][9];
        rows = new blockCollection[9];
        columns = new blockCollection[9];
        grids = new blockCollection[9];

        //creates instances of the blockCollections for puzzle
        for(int i = 0; i <9; i++)
        {
            rows[i] = new blockCollection("row "+i);
            columns[i] = new blockCollection("column" +i);
            grids[i] = new blockCollection("grid " + i);
        }

        for (int row =0; row <9; row++)
        {
            for(int column = 0; column < 9; column++)
            {
                int currentValue = sudoku[row][column];
                puzzle[row][column] = new block(currentValue, column, row);
                if(currentValue!=0)
                {
                    rows[row].removeOption(currentValue);
                    columns[column].removeOption(currentValue);
                    grids[puzzle[row][column].getGrid()].removeOption(currentValue);
                }
            }
        }
        printGrid();
        
        for (int row =0; row <9; row++)
        {
            for(int column = 0; column < 9; column++)
            {

            }    
        }
    }
}
