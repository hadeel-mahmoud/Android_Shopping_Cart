package edu.cs.birzeit.android_group_assignment_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewCartAdapter extends RecyclerView.Adapter<RecyclerViewCartAdapter.MyViewHolder> {

    private List<Item> studentsList;
    private ClickListener listener;

    RecyclerViewCartAdapter(List<Item> item){
        this.studentsList = item;
    }

    @Override
    public RecyclerViewCartAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewCartAdapter.MyViewHolder holder, final int position) {
        final Item item = studentsList.get(position);
        holder.itemName.setText(item.getItemName());
        holder.price.setText(String.valueOf(item.getPrice()));
        holder.rating.setText(String.valueOf(item.getRating()));



        holder.image.setImageResource(item.getImage());


    }


    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    public interface ClickListener<T> {

        void onClick(View view, int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
//        private String ItemName;
//        private int Price ;
//        private String Rating;
//        private String ItemsRemaining;
//        private String Description;
//        private String Category;
//        private int Image;

        public TextView itemName,price,rating;
        private LinearLayout itemLayout;
        public ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.itemName);
            price = itemView.findViewById(R.id.price);
            rating = itemView.findViewById(R.id.rating);
            image=itemView.findViewById(R.id.itemPhoto);
            itemLayout =  itemView.findViewById(R.id.itemLayout);


        }

//        @Override
//        public void onClick(View view) {
//
//            listener.onClick(view,getAdapterPosition());
//        }
    }
}