import java.util.ArrayList;
import java.util.List;

public class blockCollection
{
    String name;
    List<Integer> options;

    public blockCollection(String name)
    {
        this.name = name;
        options = new ArrayList<>();
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
}