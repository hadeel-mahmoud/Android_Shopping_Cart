package edu.cs.birzeit.android_group_assignment_1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cart extends AppCompatActivity {

    private final List<Item> studentsList = new ArrayList<>();
    RecyclerViewCartAdapter recyclerviewItemAdapter;
    RecyclerView recyclerView;

    Item[] itemsArray = new Item[3];

    List<Item> list = Arrays.asList(itemsArray);


    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        itemsArray[0] = new Item("Nike Sport Bag", 120, "4.5", "3", "NIKE VARSITY GIRL MEDIUM BAG", "Bags", R.drawable.img1);
        itemsArray[1] = new Item("Puma Water Bottle", 30, "3", "1", "Puma Unisex Sports Water Bottle Water Bottle Workout Sport Classic", "Water Bottles", R.drawable.img2);
        itemsArray[2] = new Item("Dumbbells", 60, "4", "6", "Yoga Mad Pair of 3Kg Neo Dumbbells - Orange", "Dumbbells", R.drawable.img3);

        prepareItems();


        prefs = PreferenceManager.getDefaultSharedPreferences(Cart.this);
        editor = prefs.edit();
        String items = gson.toJson(itemsArray);
        editor.putString("allItems", items);
        editor.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ui, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item1) {
            Intent displayDataIntent = new Intent(Cart.this, Cart.class);

            startActivity(displayDataIntent);
        }
        return super.onOptionsItemSelected(item);
    }




    private void prepareItems() {

        recyclerviewItemAdapter = new RecyclerViewCartAdapter(list);
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerviewItemAdapter);


    }


    public void Checkout(View view) {
        Toast.makeText(getApplicationContext(),"checked out ",Toast.LENGTH_SHORT).show();

    }
}