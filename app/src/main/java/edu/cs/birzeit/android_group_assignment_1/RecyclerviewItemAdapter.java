package edu.cs.birzeit.android_group_assignment_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerviewItemAdapter extends RecyclerView.Adapter<RecyclerviewItemAdapter.MyViewHolder> {

    private List<Student> studentsList;
    private ClickListener clickListener;

    RecyclerviewItemAdapter(List<Student> student){
        this.studentsList = student;
    }

    @Override
    public RecyclerviewItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerviewItemAdapter.MyViewHolder holder, final int position) {
        final Student student = studentsList.get(position);
        holder.firstName.setText(student.getFirstName());
        holder.lastName.setText(String.valueOf(student.getLastName()));

    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView firstName,lastName;
        private LinearLayout itemLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            lastName = itemView.findViewById(R.id.lastName);
            firstName = itemView.findViewById(R.id.firstName);
            itemLayout =  itemView.findViewById(R.id.itemLayout);
        }
    }
}