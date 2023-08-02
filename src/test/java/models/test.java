package models;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) {

        JsonObject recipientAddress  = new JsonObject();
        recipientAddress.addProperty("name","Minh");
        recipientAddress.addProperty("phone","1231232131");


        JsonObject orderItems1 = new JsonObject();
        orderItems1.addProperty("itemID","1");
        orderItems1.addProperty("itemSKU","1");
        orderItems1.addProperty("itemName","name1");

        JsonObject orderItems2 = new JsonObject();
        orderItems2.addProperty("itemID","2");
        orderItems2.addProperty("itemSKU","2");
        orderItems2.addProperty("itemName","name2");


        List<JsonObject> json = new ArrayList<>();
        json.add(orderItems1);
        json.add(orderItems2);

        JsonObject requestBody = new JsonObject();
        requestBody.add("recipientAdress",recipientAddress);
        System.out.println(requestBody);
    }


}
