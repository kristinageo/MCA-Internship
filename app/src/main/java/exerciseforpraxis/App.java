package exerciseforpraxis;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exerciseforpraxis.Item;
import exerciseforpraxis.InventoryType;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class App {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        URL url = new URL("https://interview-task-api.mca.dev/warehouse-inventory-1/data.json");

        List<InventoryType> types = mapper.readValue(url, new TypeReference<>() {});
        List<InventoryType> inventoryTypes = new ArrayList<>();

        for (InventoryType type : types) {
            List<Category> categories = new ArrayList<>();
            for (Category cat : type.getCategories()) {
                categories.add(buildCategoryTree(cat, type.getType()));
            }
            inventoryTypes.add(new InventoryType(type.getType(), categories));
        }
        

        // Print Inventory
        for (InventoryType type : inventoryTypes) {
            type.print();
            System.out.println();
        }


         double perishableTotalValue = 0.0;
         int perishableTotalWeight = 0;
         double nonPerishableTotalValue = 0.0;
         int nonPerishableTotalWeight = 0;


         for (InventoryType inventoryType : inventoryTypes) {
            if ("perishable".equalsIgnoreCase(inventoryType.getType())) {
                    for (Category category : inventoryType.getCategories()) {
                        perishableTotalValue += sumValueRecursive(category);
                        perishableTotalWeight += sumWeightRecursive(category);
                    }
            } else if ("non-perishable".equalsIgnoreCase(inventoryType.getType())) {
                   for (Category category : inventoryType.getCategories()) {
                       nonPerishableTotalValue += sumValueRecursive(category);
                       nonPerishableTotalWeight += sumWeightRecursive(category);    
                    }
            }
         }

    
        System.out.println("\n");
        System.out.println("Perishable Total Value: $" + (String.format("%.2f",perishableTotalValue))); // Total value of perishable items is $212.00 why $167.00 in the document??
        System.out.println("Non-Perishable Total Value: $" + (String.format("%.2f",nonPerishableTotalValue))); 
        System.out.println("Perishable Total Weight: " + perishableTotalWeight + " kg");
        System.out.println("Non-Perishable Total Weight: " + nonPerishableTotalWeight + " kg");
    }

    private static Category buildCategoryTree(Category cat, String categoryType) {
        if(cat == null) return null;
        Category category = new Category(cat.name);
        for (Item item : cat.getItems()) {
            category.addItem(item);
        }
        for (Category sub : cat.getSubcategories()) {
            category.addSubcategory(buildCategoryTree(sub, categoryType));
        }
        return category;
    }



    //function for total value of items in a category and its subcategories
    private static double sumValueRecursive(Category category) {
        double localSum = 0;
        if (category.getItems() != null) {
            localSum = category.getItems().stream()
                .mapToDouble(item -> item.getValue() != null ? item.getValue() : 0)
                .sum();
        }
        if (category.getSubcategories() != null) {
            for (Category sub : category.getSubcategories()) {
                localSum += sumValueRecursive(sub);
            }
        }
        return localSum;
    }


    //function for total weight of items in a category and its subcategories
    private static int sumWeightRecursive(Category category) {
    
        int localSum = 0;
        if (category.getItems() != null) {
            localSum = category.getItems().stream()
                .mapToInt(item -> item.getWeight() != null ? item.getWeight() : 0)
                .sum();
        }
        if (category.getSubcategories() != null) {
            for (Category sub : category.getSubcategories()) {
                localSum += sumWeightRecursive(sub);
            }
        }
        return localSum;
    }

}

