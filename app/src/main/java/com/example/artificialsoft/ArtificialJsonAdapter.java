package com.example.artificialsoft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ArtificialJsonAdapter extends RecyclerView.Adapter<ArtificialJsonAdapter.ArtificialJsonViewHolder>
{
    private Context context;
    private ArrayList<ArtificalJsonTest> artificalJsonTests;
    private OnItemClickListner listner;


    public interface OnItemClickListner
    {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListner mylistener)
    {
        listner = mylistener;
    }

    //constructor
    public ArtificialJsonAdapter(Context myContext, ArrayList<ArtificalJsonTest> myArtificialJsonTests)
    {
        context = myContext;
        artificalJsonTests = myArtificialJsonTests;
    }


    //creating viewholder to get the layout
    @NonNull
    @Override
    public ArtificialJsonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.items_show, parent, false);
        return new ArtificialJsonViewHolder(view);
    }


    //setting all the data
    @Override
    public void onBindViewHolder(@NonNull ArtificialJsonViewHolder holder, int position)
    {
        ArtificalJsonTest selectedItem = artificalJsonTests.get(position);

        String imageUrl = selectedItem.getImageURL();
        String id = selectedItem.getId();
        String user = selectedItem.getUser();
        String name = selectedItem.getName();
        String designation = selectedItem.getDesignation();

        holder.textViewId.setText("ID: "+id);
        holder.textViewUser.setText("User: "+user);
        holder.textViewName.setText("Name: "+name);
        holder.textViewWho.setText("Designation: "+designation);
        Glide.with(context).load(imageUrl).into(holder.circleImageView);
    }

    //item size
    @Override
    public int getItemCount()
    {
        return artificalJsonTests.size();
    }


    //calling all the references of items of layout
    public class ArtificialJsonViewHolder extends RecyclerView.ViewHolder
    {
        public CircleImageView circleImageView;
        public TextView textViewId, textViewUser, textViewName, textViewWho;

        public ArtificialJsonViewHolder(@NonNull View itemView)
        {
            super(itemView);

            circleImageView = itemView.findViewById(R.id.example_image);
            textViewId = itemView.findViewById(R.id.example_id);
            textViewUser = itemView.findViewById(R.id.example_user);
            textViewName = itemView.findViewById(R.id.example_name);
            textViewWho = itemView.findViewById(R.id.example_who);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    if (listner!= null) //checks if it has listener
                    {
                        int position = getAdapterPosition(); //storing position
                        if (position != RecyclerView.NO_POSITION) //if position is listed
                        {
                            listner.onItemClick(position); //interface created
                        }
                    }
                }
            });
        }
    }
}
