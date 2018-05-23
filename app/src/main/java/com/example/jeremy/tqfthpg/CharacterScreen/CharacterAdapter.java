package com.example.jeremy.tqfthpg.CharacterScreen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jeremy.tqfthpg.CharacterScreen.Model.PCharacter;
import com.example.jeremy.tqfthpg.R;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharViewHolder>{

    private static final String TAG = "CharacterAdapter";
    //declare main list:
    private List<PCharacter> charlist;
    private Context mcontext;


    public CharacterAdapter(List<PCharacter> charlist, Context context) {
        this.charlist = charlist;
        this.mcontext = context;
    }

    //Declare needed attributes
    public class CharViewHolder extends RecyclerView.ViewHolder{

        public TextView Name;
        public TextView Race;
        public TextView Class;
        public TextView Weakness;
        public TextView Desc;

        //Set up ViewHolder:
        public CharViewHolder(View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.text_name);
            Race = itemView.findViewById(R.id.text_race);
            Class = itemView.findViewById(R.id.text_class);
            Weakness = itemView.findViewById(R.id.text_weakness);
            Desc = itemView.findViewById(R.id.text_description);
        }
    }


    @NonNull
    @Override
    public CharacterAdapter.CharViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewholder = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.char_recycler_view, parent, false);


        return new CharacterAdapter.CharViewHolder(viewholder);
    }

    @Override
    public void onBindViewHolder(@NonNull CharViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        PCharacter currentchar = charlist.get(position);

        holder.Name.setText(currentchar.getFullname());
        holder.Race.setText(currentchar.getRace());
        holder.Class.setText(currentchar.getCharclass());
        holder.Weakness.setText(currentchar.getWeakness());
        holder.Desc.setText(currentchar.getDescription());

    }

    //Get Item count if needed:
    @Override
    public int getItemCount() {
        return charlist.size();
    }
}




