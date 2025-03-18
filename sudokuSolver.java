import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class sudokuSolver
{
    static block[][] puzzle;
    static blockCollection[] rows;
    static blockCollection[] columns;
    static blockCollection[] grids;
    static Queue<block> queue = new LinkedList<>();

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

    //used whenever a value is entered into grid, updates all rows,columns,gridblocks and blocks effected
    public static void updateOptions(block newlyCompletedBlock)
    {
        int row = newlyCompletedBlock.getRow();
        int column = newlyCompletedBlock.getColumn();
        //System.out.print("Grid: ");
        //System.out.println(newlyCompletedBlock.getGrid());
        int gridStartColumn = 3*(column/3);
        int gridStartRow = 3*(row/3);
        //System.out.print("grid start column:");
        //System.out.println(gridStartColumn);
        //System.out.print("grid start row:");
        //System.out.println(gridStartRow);

        rows[newlyCompletedBlock.getRow()].removeOption(newlyCompletedBlock.getValue());
        columns[newlyCompletedBlock.getColumn()].removeOption(newlyCompletedBlock.getValue());
        grids[newlyCompletedBlock.getGrid()].removeOption(newlyCompletedBlock.getValue());

        for(int i =0; i <9; i++)
        {
            puzzle[row][i].removeOption(newlyCompletedBlock.getValue());
            if(puzzle[row][i].options.size()==1)
            {
                queue.offer(puzzle[row][i]);
            }
            puzzle[i][column].removeOption(newlyCompletedBlock.getValue());
            if(puzzle[i][column].options.size()==1)
            {
                queue.offer(puzzle[i][column]);
            }
        }

        //gets first column and row number for each grid
        for(int i = gridStartRow; i<gridStartRow+3;i++)
        {
            for(int j = gridStartColumn; j<gridStartColumn+3; j++)
            {
                puzzle[i][j].removeOption(newlyCompletedBlock.getValue());
                if(puzzle[i][j].options.size()==1)
                {
                    queue.offer(puzzle[i][j]);
                }
            }
        }
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
            columns[i] = new blockCollection("column " +i);
            grids[i] = new blockCollection("grid " + i);
        }

        //instantiates the blocks in the puzzle and gets initial values for all block collections
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
        
        //gets initial options for all individual blocks in the puzzle
        for (int row =0; row <9; row++)
        {
            for(int column = 0; column < 9; column++)
            {
                if(puzzle[row][column].getValue()==0)
                {
                    ArrayList<Integer> intersection = new ArrayList<>(columns[column].options);
                    intersection.retainAll(rows[row].options);
                    intersection.retainAll(grids[puzzle[row][column].getGrid()].options);
                    puzzle[row][column].options = intersection;
                    if(intersection.size()==1)
                    {
                        queue.offer(puzzle[row][column]);
                    }
                }
                System.out.println(puzzle[row][column].options);
            }    
        }
        while(!queue.isEmpty())
        {
            block toUpdate = queue.poll();
            if(toUpdate.options.size()==1)
            {
                toUpdate.setValue(toUpdate.options.get(0));
                toUpdate.removeOption(toUpdate.getValue());
                updateOptions(toUpdate);
                printGrid();
            }
        }
    }
}
