package exerciseforpraxis;

import java.util.List;
import exerciseforpraxis.Category;

class InventoryType {
    private String type;
    private List<Category> categories;

    public InventoryType() {}

    public InventoryType(String type, List<Category> categories) {
        this.type = type;
        this.categories = categories;
    }

    public String getType() {
        return type;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories() {
        this.categories = categories;
    }

    public void print() {
        System.out.println(type.substring(0,1).toUpperCase() + type.substring(1) + " Items: ");
        for (Category category : categories) {
            category.print("  ");
        }
    }
}