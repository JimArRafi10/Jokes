package com.example.jokesapp.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jokesapp.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    List<String>cats;
    int positionOfCard;

    public  CategoryAdapter(List<String>cats){
        this.cats = cats;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_view,parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         holder.catName.setText(cats.get(position));
         if(positionOfCard == position){
             //set card background green
             holder.mCardView.setCardBackgroundColor(Color.CYAN);

         } else{
             //set white
             holder.mCardView.setCardBackgroundColor(Color.WHITE);

         }

    }

    @Override
    public int getItemCount() {
        return cats.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        TextView catName;
        CardView mCardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            catName= itemView.findViewById(R.id.catName);
            mCardView= itemView.findViewById(R.id.mCardView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(v.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
        notifyItemChanged(positionOfCard);
        positionOfCard = getAdapterPosition();
        notifyItemChanged(positionOfCard);

        }
    }
}
