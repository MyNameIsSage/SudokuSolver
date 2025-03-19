import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
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

    public static void removeBlockCollectionOption(block toRemove)
    {
        rows[toRemove.getRow()].removeOption(toRemove.getValue());
        columns[toRemove.getColumn()].removeOption(toRemove.getValue());
        grids[toRemove.getGrid()].removeOption(toRemove.getValue());
    }

    //used whenever a value is entered into grid, updates all rows,columns,gridblocks and blocks effected
    public static void updateOptions(block newlyCompletedBlock)
    {
        //set the value and remove the set value as an option
        newlyCompletedBlock.setValue(newlyCompletedBlock.options.get(0));
        newlyCompletedBlock.removeOption(newlyCompletedBlock.getValue());

        int row = newlyCompletedBlock.getRow();
        int column = newlyCompletedBlock.getColumn();
        int numberEntered = newlyCompletedBlock.getValue();

        int gridStartColumn = 3*(column/3);
        int gridStartRow = 3*(row/3);

        //removes the filled in block as an option in each relevant row,column,grid
        removeBlockCollectionOption(newlyCompletedBlock);

        for(int i =0; i <9; i++)
        {
            puzzle[row][i].removeOption(numberEntered);
            if(puzzle[row][i].options.size()==1)
            {
                queue.offer(puzzle[row][i]);
            }
            puzzle[i][column].removeOption(numberEntered);
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
                puzzle[i][j].removeOption(numberEntered);
                if(puzzle[i][j].options.size()==1)
                {
                    queue.offer(puzzle[i][j]);
                }
            }
        }
    }

    public static void updateMissingBlocks(block toUpdate)
    {
        rows[toUpdate.getRow()].blocksMissingValues.remove(toUpdate);
        columns[toUpdate.getColumn()].blocksMissingValues.remove(toUpdate);
        grids[toUpdate.getGrid()].blocksMissingValues.remove(toUpdate);
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
                    removeBlockCollectionOption(puzzle[row][column]);
                }
                else
                {
                    rows[row].blocksMissingValues.add(puzzle[row][column]);
                    columns[column].blocksMissingValues.add(puzzle[row][column]);
                    grids[puzzle[row][column].getGrid()].blocksMissingValues.add(puzzle[row][column]);
                }
            }
        }
        printGrid();
        
        //gets initial options for all individual blocks in the puzzle by getting the common options from blocks row, column and grid
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
            }    
        }
        while(!queue.isEmpty())
        {
            block toUpdate = queue.poll();
            if(toUpdate.options.size()==1)
            {
                updateOptions(toUpdate);
                updateMissingBlocks(toUpdate);
            }
        }
        printGrid();

        //go through each blockCollection starting from the one with least options to try and solve
        PriorityQueue<blockCollection> pq = new PriorityQueue<>(Comparator.comparingInt(b -> b.options.size()));
        for (int i =0; i <9; i++)
        {
            if(rows[i].options.size()>0)
            {
                pq.add(rows[i]);
            }
            if(columns[i].options.size()>0)
            {
                pq.add(columns[i]);
            }
            if(grids[i].options.size()>0)
            {
                pq.add(grids[i]);
            }
        }

        while(!pq.isEmpty())
        {
            blockCollection toCheck = pq.poll();
            int[] optionsForEachBlock = new int[toCheck.options.size()];
            
        }
            
    }
}
