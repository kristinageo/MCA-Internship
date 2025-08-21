package exerciseforpraxis;

public class Item {
    private String name;
    private Double value;
    private Integer weight;

    public Item(){}

    public String getName() 
    {
        return name;
    }

    public Double getValue() 
    {
        return value;
    }

    public Integer getWeight() 
    {
        return weight;
    }

    public void print(String prefix) 
    {
        System.out.println(prefix + "  " + ((name.length() >= 10) ?  name.substring(0,9) + "..." : name )
         + " - Value:  $" + String.format("%.2f", value) + ", Weight: " + (weight == null ? "N/A" : weight) + " kg");
    }
} 