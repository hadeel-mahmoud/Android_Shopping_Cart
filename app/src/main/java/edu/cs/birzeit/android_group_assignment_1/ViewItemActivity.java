package edu.cs.birzeit.android_group_assignment_1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ViewItemActivity extends AppCompatActivity {
    TextView ItemName, Price, Rating, ItemsRemaining, Description, Category;
    Item currentItem;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String allItemsJson = prefs.getString("allItems", "");
        Type type = new TypeToken<List<Item>>() {
        }.getType();


        final List<Item> itemsList = gson.fromJson(allItemsJson, type);
        String itemIndexJson = prefs.getString("itemIndex", "");
        currentItem = itemsList.get(Integer.parseInt(itemIndexJson));


        ItemName = findViewById(R.id.itemName);
        Price = findViewById(R.id.price);
        Rating = findViewById(R.id.Rating);
        ItemsRemaining = findViewById(R.id.ItemsRemaining);
        Description = findViewById(R.id.Description);
        Category = findViewById(R.id.Category);
        image = findViewById(R.id.imageView);


        ItemName.setText(currentItem.getItemName());
        System.out.println(currentItem.getPrice()+"PRICE");
        Price.setText(Integer.toString(currentItem.getPrice()));
        Rating.setText(currentItem.getRating());
        ItemsRemaining.setText(currentItem.getItemName());
        Description.setText(currentItem.getDescription());
        Category.setText(currentItem.getCategory());

        image.setImageResource(currentItem.getImage());

//        Description.setText(currentItem.getDescription());
//        ItemsRemaining.setText(currentItem.getItemsRemaining());
//        Rating.setText(currentItem.getRating());
//        Category.setText(currentItem.getCategory());
//        Price.setText(currentItem.getPrice());


        //Toast.makeText(getApplicationContext(),"Name: "+movie.getName(), Toast.LENGTH_LONG).show();


    }

    public void addToCart_OnClick(View view) {
        Toast.makeText(getApplicationContext(), "item " + "added to cart", Toast.LENGTH_SHORT).show();
    }
}