package com.example.learnjapanwords;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Words> words;

    WordsAdapter(Context context, List<Words> words) {
        this.words = words;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public WordsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WordsAdapter.ViewHolder holder, int position) {
        Words word = words.get(position);
        holder.nameView.setText(word.getJapan());
        holder.turnWord.setText(word.getFlip());
    }

    @Override
    public int getItemCount() {
        return words.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView, turnWord;
        ViewHolder(View view) {
            super(view);
            nameView = (TextView)view.findViewById(R.id.name);
            turnWord = (TextView)view.findViewById(R.id.flip);
        }

    }
}



