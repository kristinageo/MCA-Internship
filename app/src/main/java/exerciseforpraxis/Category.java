package exerciseforpraxis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import exerciseforpraxis.InventoryComponent;


class Category extends InventoryComponent {
    private List<Category> subcategories = new ArrayList<>();
    private Category parent;
 

    public Category() 
    {
        super();
    }

    public Category(String name) {
        super(name);
    }

    public void addSubcategory(Category subcategory) {
        subcategory.parent = this; 
        subcategories.add(subcategory);
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Category> subcategories) {
        this.subcategories = subcategories;
    }

    public boolean isTopLevel() {
        return parent == null;
    }



    //print the category and subcategories with items
    @Override
    public void print(String indent) {

        String label = isTopLevel() ? "Category" : "SubCategory";
        System.out.println(indent + label + ": " + getName());
        List<Item> itemsList = getItems();

       Collections.sort(itemsList,Comparator.comparing(Item::getName));
       for (int i = 0; i < itemsList.size(); i++) {
            itemsList.get(i).print(indent + "  " + (i + 1) + ". ");
        }

        Collections.sort(subcategories,Comparator.comparing(Category::getName));
        for (Category subcategory : subcategories) {
            subcategory.print(indent + "  ");
        }

    }

}