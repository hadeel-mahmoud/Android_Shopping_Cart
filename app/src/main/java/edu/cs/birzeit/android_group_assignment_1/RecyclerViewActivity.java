package edu.cs.birzeit.android_group_assignment_1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

public class RecyclerViewActivity extends AppCompatActivity {

    private final List<Item> studentsList = new ArrayList<>();
    RecyclerviewItemAdapter recyclerviewItemAdapter;
    RecyclerView recyclerView;
    EditText searchText;

    Item[]  itemsArray = new Item[3];

    List<Item> list = Arrays.asList(itemsArray);

    private RecyclerviewItemAdapter.ClickListener listener;


    SharedPreferences prefs ;
    SharedPreferences.Editor editor ;
    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
//
//        Pinterest
//        Nike Torba Torebka
//        eBay
//


        itemsArray[0] = new Item("Nike Sport Bag", "120", "4.5","3", "NIKE VARSITY GIRL MEDIUM BAG", "Bags",0);
        itemsArray[1] = new Item("Puma Water Bottle", "30", "3","1", "Puma Unisex Sports Water Bottle Water Bottle Workout Sport Classic", "Water Bottles", 1);
        itemsArray[2] = new Item("Dumbbells", "60", "4","6", "Yoga Mad Pair of 3Kg Neo Dumbbells - Orange", "Dumbbells", 2);

        prepareItems(false,"");
        

        prefs=PreferenceManager.getDefaultSharedPreferences(RecyclerViewActivity.this);
        editor= prefs.edit();
        String items = gson.toJson(itemsArray);
        editor.putString("allItems", items);
        editor.commit();

    }



    public void searchClick(View view) {
        searchText = (EditText) findViewById(R.id.searchEdt);
        prepareItems( true,searchText.getText().toString());

    }


    private void prepareItems( boolean search,String searchContent) {

        setOnClickListener();
        if (!search) {

            recyclerviewItemAdapter = new RecyclerviewItemAdapter(list,listener);
            recyclerView = findViewById(R.id.recycleView);
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
        }
        else {
            List<Item> searchStudentsList = new ArrayList<>();

            for (Item item : list) {
                if (item.getItemName().toLowerCase().contains(searchContent.toLowerCase())) {
                    Item searchedItem = new Item(item.getItemName(), item.getPrice(), item.getRating(), item.getItemsRemaining(), item.getDescription(), item.getCategory(), item.getImage());
                    searchStudentsList.add(searchedItem);
                    System.out.println(searchedItem + "ADDED");

                }
            }
            recyclerviewItemAdapter = new RecyclerviewItemAdapter(searchStudentsList,listener);
            recyclerView = findViewById(R.id.recycleView);
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);

        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerviewItemAdapter);


    }

    private void setOnClickListener() {

        listener=new RecyclerviewItemAdapter.ClickListener(){
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getApplicationContext(),"Position = "+position+"\n Item = "+"hi",Toast.LENGTH_SHORT).show();
                Intent displayDataIntent = new Intent(RecyclerViewActivity.this, ViewItemActivity.class);

                String cvString = gson.toJson(position);
                editor.putString("itemIndex", cvString);
                editor.commit();

                startActivity(displayDataIntent);

            }
        };
    }
}