package com.example.jokesapp.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jokesapp.R;
import com.example.jokesapp.fragments.Main;

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
        
        if(cats.get(positionOfCard).equals("Any")){
            loadFragment(v, new Main(v.getContext().getResources().getString(R.string.Url)+"Any?amount=10"));
        }
        if(cats.get(positionOfCard).equals("Programming")){
            loadFragment(v, new Main(v.getContext().getResources().getString(R.string.Url)+"Programming?amount=10"));
        }
        if(cats.get(positionOfCard).equals("Misc")){
            loadFragment(v, new Main(v.getContext().getResources().getString(R.string.Url)+"Misc?amount=10"));
        }
        if(cats.get(positionOfCard).equals("Dark")){
            loadFragment(v, new Main(v.getContext().getResources().getString(R.string.Url)+"Dark?amount=10"));
        }
        if(cats.get(positionOfCard).equals("Pun")){
            loadFragment(v, new Main(v.getContext().getResources().getString(R.string.Url)+"Pun?amount=10"));
        }
        if(cats.get(positionOfCard).equals("Spooky")){
            loadFragment(v, new Main(v.getContext().getResources().getString(R.string.Url)+"Spooky?amount=10"));
        }
        if(cats.get(positionOfCard).equals("Christmas")){
            loadFragment(v, new Main(v.getContext().getResources().getString(R.string.Url)+"Christmas?amount=10"));
        }


        }
    }

    public void loadFragment(View v, Fragment fragment){
        AppCompatActivity activity = (AppCompatActivity) v.getContext();
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction().replace(R.id.fragment_frame, new Main(activity.getResources().getString(R.string.Url)+"Any"));
        transaction.commit();

    }
}
