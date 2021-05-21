package edu.cs.birzeit.android_group_assignment_1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity2 extends AppCompatActivity {

    // Array of strings...
    //String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
          //  "WebOS","Ubuntu","Windows7","Max OS X"};
      ListView lv;
      String address = "http://10.0.2.2/rest/get_all.php";
      InputStream is=null;
      String result = null;
    String line = null;
      String[]data;
      ArrayList<String> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.activity_listview,mobileArray);
        //ListView listView = (ListView) findViewById(R.id.mobile_list);
       // listView.setAdapter(adapter);
        lv= (ListView) findViewById(R.id.students_list);
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));

        getData();
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, data);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_listview, data);
//        lv.setAdapter(adapter);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.activity_listview,items);
         lv.setAdapter(adapter);
//        ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_expandable_list_item_1, items);
//        lv.setAdapter(mArrayAdapter);

    }

//    @Override
//    protected void onPostExecute(String s) {
//        super.onPostExecute(s);
//        if (!TextUtils.isEmpty(s) && s!=null) {
//            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
//            try {
//                loadIntoListView(s);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    private void loadIntoListView(String json) throws JSONException {
//        JSONArray jsonArray = new JSONArray(json);
//        try {
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject obj = jsonArray.getJSONObject(i);
//                data[i] = obj.getString("username");
//            }
//        } catch(JSONException e) {
//            //handler json exception
//        }
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
//        lv.setAdapter(arrayAdapter);
//    }
//
private void getData()  {

        try{
        URL url = new URL (address);
    HttpURLConnection con = (HttpURLConnection)url.openConnection();
    con.setRequestMethod("Get");
    is= new BufferedInputStream(con.getInputStream());

}catch (Exception e){
            e.printStackTrace();
        }

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb= new StringBuilder();

            while((line=br.readLine())!= null){
                sb.append(line+"\n");
            }
            is.close();
            result=sb.toString();
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
//            JSONArray ja = new JSONArray(result);
//            JSONObject jo =null;
//
//            data= new String [ja.length()];
//            for (int i=0;i<ja.length();i++){
//                jo =ja.getJSONObject(i);
//                data[i]=jo.getString("username");
//                System.out.println(data[i]);


            JSONArray jArray = new JSONArray(result);
              JSONObject json_data =null;
            items = new ArrayList<String>();
            for(int i=0; i < jArray.length() ; i++) {
                json_data = jArray.getJSONObject(i);
                int id=json_data.getInt("id");
                String name=json_data.getString("username");
                items.add(name);
                Log.d(name,"Output");
            }

        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }




}

}


