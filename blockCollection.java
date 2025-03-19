import java.util.ArrayList;
import java.util.List;

public class blockCollection
{
    String name;
    List<Integer> options;
    List<block> blocksMissingValues;

    public blockCollection(String name)
    {
        this.name = name;
        options = new ArrayList<>();
        blocksMissingValues = new ArrayList<>();
        for (int i = 1; i < 10; i++)
        {
            options.add(i);
        }
    }

    public void removeOption(int toRemove)
    {
        options.remove(Integer.valueOf(toRemove));
    }

    public String toString()
    {
        return name+ ": " +options.toString();
    }

    public void missingValueXY()
    {
        System.out.print(name+": ");
        for(int i =0; i<blocksMissingValues.size();i++)
        {
            System.out.print("("+blocksMissingValues.get(i).getRow()+", "+ blocksMissingValues.get(i).getColumn()+"), ");
        }
        System.out.println();
    }
}