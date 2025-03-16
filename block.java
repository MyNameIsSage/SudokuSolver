import java.util.ArrayList;
import java.util.List;

public class block
{
    private int value;
    List<Integer> options;
    private int column;
    private int row;
    private int grid;

    public block(int value, int column, int row)
    {
        this.value = value;
        this.column = column;
        this.row = row;
        if(value==0)
        {
            options = new ArrayList<>();
            for (int i = 1; i < 10; i++)
            {
                options.add(i);
            }
        }

        if(column<=2)
        {
            if(row<=2)
            {
                grid = 0;
            }
            else if(row<=5)
            {
                grid = 3;
            }
            else
            {
                grid = 6;
            }
        }
        else if (column<=5)
        {
            if(row<=2)
            {
                grid = 1;
            }
            else if(row<=5)
            {
                grid = 4;
            }
            else
            {
                grid = 7;
            }
        }
        else
        {
            if(row<=2)
            {
                grid = 2;
            }
            else if(row<=5)
            {
                grid = 5;
            }
            else
            {
                grid = 8;
            }
        }
    }

    public int getValue()
    {
        return this.value;
    }

    public int getRow()
    {
        return this.row;
    }

    public int getColumn()
    {
        return this.column;
    }

    public int getGrid()
    {
        return this.grid;
    }

    public String toString()
    {
        return Integer.toString(value);
    }

    public void removeOption(int toRemove)
    {
        options.remove(Integer.valueOf(toRemove));
    }
}