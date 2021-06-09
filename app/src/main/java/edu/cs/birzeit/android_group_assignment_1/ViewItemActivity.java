package edu.cs.birzeit.android_group_assignment_1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.SparseIntArray;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ViewItemActivity extends AppCompatActivity {

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


        }
    }