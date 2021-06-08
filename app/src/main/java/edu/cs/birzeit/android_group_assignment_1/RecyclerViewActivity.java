package edu.cs.birzeit.android_group_assignment_1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private final List<Item> studentsList = new ArrayList<>();
    RecyclerviewItemAdapter recyclerviewItemAdapter;
    RecyclerView recyclerView;
    EditText searchText;

    Item[]  studentsArray = new Item[2];

    List<Item> list = Arrays.asList(studentsArray);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        studentsArray[0] = new Item("HADEEL", "NEZ", "@gmail","DOB", "ADDRESS", "20", "FEMALE");
        studentsArray[1] = new Item("LENA", "NEZ", "@gmail","DOB", "ADDRESS", "20", "FEMALE");

        prepareItems(false,"");

    }

//    public void onClickAdd(View view) {
//
//        Intent intent = new Intent(this, addStudent.class);
//        startActivity(intent);
//
//
//    }

    public void searchClick(View view) {
        searchText = (EditText) findViewById(R.id.searchEdt);
//        System.out.println(searchText + "SENT");
        prepareItems( true,searchText.getText().toString());
    }

    private void prepareItems( boolean search,String searchContent) {
        int i;

        if (!search) {

            recyclerviewItemAdapter = new RecyclerviewItemAdapter(list);
            recyclerView = findViewById(R.id.recycleView);
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
        }
        else {
            List<Item> searchStudentsList = new ArrayList<>();

            for (Item item : list) {
                if (item.getItemName().toLowerCase().contains(searchContent.toLowerCase())) {
                    Item searchedItem = new Item(item.getItemName(), item.getPrice(), item.getRating(), item.getItemsRemaining(), item.getDescription(), item.getCategory(), item.getSize());
                    searchStudentsList.add(searchedItem);
                    System.out.println(searchedItem + "ADDED");

                }
            }
            recyclerviewItemAdapter = new RecyclerviewItemAdapter(searchStudentsList);
            recyclerView = findViewById(R.id.recycleView);
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);

        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerviewItemAdapter);


    }
}