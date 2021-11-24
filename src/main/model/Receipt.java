package model;

import org.json.JSONObject;
import persistence.Writable;

// A receipt that contains amount of spending what the user bought
public class Receipt implements Writable {
    private double amount;
    private String item;

    public Receipt(double amount, String item) {
        this.amount = amount;
        this.item = item;
    }

    // EFFECTS: produce the amount of receipt
    public double getAmount() {
        return amount;
    }

    // EFFECTS: produce the item of receipt
    public String getItem() {
        return item;
    }

    // EFFECTS: change the amount of receipt
    public void changeAmount(double newAmount) {
        amount = newAmount;
        EventLog.getInstance().logEvent(new Event("Receipt amount changed"));
    }

    // EFFECTS: change the item of receipt
    public void changeItem(String newItem) {
        item = newItem;
        EventLog.getInstance().logEvent(new Event("Receipt item changed"));
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("amount", amount);
        json.put("item", item);
        return json;
    }
}
