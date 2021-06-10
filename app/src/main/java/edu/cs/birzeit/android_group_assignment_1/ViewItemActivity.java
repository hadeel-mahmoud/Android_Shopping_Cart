package edu.cs.birzeit.android_group_assignment_1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ViewItemActivity extends AppCompatActivity {
    EditText  ItemName,Price,Rating,ItemsRemaining,Description,Category;

     Item currentItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String allItemsJson = prefs.getString("allItems", "");
        Type type = new TypeToken<List<Item>>() {}.getType();


        final List<Item> itemsList = gson.fromJson(allItemsJson, type);

        String itemIndexJson = prefs.getString("itemIndex", "");


        System.out.println(itemsList.get(0)+"JSONINDEX");


        currentItem=itemsList.get(Integer.parseInt(itemIndexJson));
        System.out.println(currentItem.getItemName()+"NAMEE");


//        ItemName.setText(currentItem.getItemName());
//        Description.setText(currentItem.getDescription());
//        ItemsRemaining.setText(currentItem.getItemsRemaining());
//        Rating.setText(currentItem.getRating());
//        Category.setText(currentItem.getCategory());
//        Price.setText(currentItem.getPrice());


        //Toast.makeText(getApplicationContext(),"Name: "+movie.getName(), Toast.LENGTH_LONG).show();



    }

    public void addToCart_OnClick(View view) {
        Toast.makeText(getApplicationContext(),"item "+"added to cart",Toast.LENGTH_SHORT).show();
    }
}