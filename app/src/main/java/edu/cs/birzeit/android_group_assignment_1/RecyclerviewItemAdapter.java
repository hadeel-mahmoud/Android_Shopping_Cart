package edu.cs.birzeit.android_group_assignment_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerviewItemAdapter extends RecyclerView.Adapter<RecyclerviewItemAdapter.MyViewHolder> {

    private List<Item> studentsList;
    private ClickListener listener;

    RecyclerviewItemAdapter(List<Item> item,ClickListener listener){
        this.studentsList = item;
        this.listener=listener;
    }

    @Override
    public RecyclerviewItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerviewItemAdapter.MyViewHolder holder, final int position) {
        final Item item = studentsList.get(position);
        holder.itemName.setText(item.getItemName());
        holder.price.setText(String.valueOf(item.getPrice()));
        holder.rating.setText(String.valueOf(item.getRating()));



    }


    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    public interface ClickListener<T> {

        void onClick(View view, int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView itemName,price,rating;
        private LinearLayout itemLayout;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.itemName);
            price = itemView.findViewById(R.id.price);
            rating = itemView.findViewById(R.id.rating);

            itemLayout =  itemView.findViewById(R.id.itemLayout);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {

            listener.onClick(view,getAdapterPosition());
        }
    }
}