package app.AppFunctions;

import java.util.ArrayList;

import app.Object.MenuItem;

public class CafeFunction {
    private ArrayList<MenuItem> listOfItem;

    public CafeFunction() {
        this.listOfItem = new ArrayList<>();
    }

    public ArrayList<MenuItem> getListOfItem() {
        return this.listOfItem;
    }

    public boolean addItem(MenuItem newItem) {  
        return listOfItem.add(newItem);
    }

    public boolean addAllItem(ArrayList<MenuItem> newList) {  
        return listOfItem.addAll(newList);
    }

    public boolean deleteItem(String id) {
        for (MenuItem i: listOfItem) {
            if (i.getId().equals(id)) {
                return listOfItem.remove(i);
            }
        }

        return false;
    }

    public MenuItem findItem(String id) {
        for (int i = 0 ; i < listOfItem.size() ; i++) {
            if (id.equals(listOfItem.get(i).getId())) {
                return listOfItem.get(i);
            }
        }
        
        return null;
    }
}
