package edu.cs.birzeit.android_group_assignment_1;

public class Item {


    private String ItemName;
    private String Price ;
    private String Rating;
    private String ItemsRemaining;
    private String Description;
    private String Category;
    private String Size;


    public Item(String itemName, String price, String rating, String itemsRemaining, String description, String category, String size) {
        ItemName = itemName;
        Price = price;
        Rating = rating;
        ItemsRemaining = itemsRemaining;
        Description = description;
        Size = size;
        Category = category;

    }

    public String getItemName() {
        return ItemName;
    }
    public String getPrice() {
        return Price;
    }

    public String getRating() {
        return Rating;
    }

    public String getItemsRemaining() {
        return ItemsRemaining;
    }
    public String getDescription() {
        return Description;
    }

    public String getCategory() {
        return Category;
    }

    public String getSize() {
        return Size;
    }



    public String toString() {
        return ItemName + " " + Price + " " + Rating + " " + ItemsRemaining + " " + Description + " " + Category + ""+ Size + "";
    }
}
