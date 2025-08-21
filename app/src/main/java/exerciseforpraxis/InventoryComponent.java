package exerciseforpraxis;

import java.util.ArrayList;
import java.util.List;

public abstract class InventoryComponent {
    protected String name;
    protected List<Item> items = new ArrayList<>();

    public InventoryComponent() { }

    public InventoryComponent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public abstract void print(String indent);
}