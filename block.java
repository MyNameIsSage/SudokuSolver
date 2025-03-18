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
        options = new ArrayList<>();
        this.grid = 3*(row/3)+(column/3);
    }

    public void setValue(int value)
    {
        this.value = value;
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