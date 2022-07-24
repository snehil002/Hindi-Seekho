package com.example.hindiseekho;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int colorID;

    private static final String LOG_TAG = WordAdapter.class.getSimpleName();

    public WordAdapter(Activity context, ArrayList<Word> formalWordArray, int formalColorID) {
        super(context, 0, formalWordArray);
        colorID = formalColorID;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);

        View textBox = listItemView.findViewById(R.id.text_box);
        int color = ContextCompat.getColor(getContext(), colorID);
        textBox.setBackgroundColor(color);

        TextView hindiTextView = listItemView.findViewById(R.id.hindi_text_view);
        assert currentWord != null;
        hindiTextView.setText(currentWord.getHindiTranslation());

        TextView defaultTextView = listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getDefaultTranslation());

        ImageView image = listItemView.findViewById(R.id.image_view);
        if(currentWord.hasImage()) {
            image.setImageResource(currentWord.getImageResourceId());
            image.setVisibility(View.VISIBLE);
        }else {
            image.setVisibility(View.GONE);
        }
        return listItemView;

        //       Filled by android
        //       return super.getView(position, convertView, parent);

        //       Find the ImageView in the list_item.xml layout with the ID list_item_icon
        //
        //       Get the image resource ID from the current AndroidFlavor object and
        //       set the image to iconView
        //

    }
}
