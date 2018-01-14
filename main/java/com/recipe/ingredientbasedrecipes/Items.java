package com.recipe.ingredientbasedrecipes;

public class Items {

    private String item;
    private boolean selected;

    public Items(String items)
    {
        this.item=items;
    }

    public String getItems()
    {
        return item;
    }

    public void setItemName(String name)
    {
        this.item=name;
    }
    public boolean getSelected()
    {
        return selected;
    }
    public void setSelected(Boolean selected)
    {
        this.selected=selected;
    }
}
