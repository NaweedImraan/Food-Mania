package com.example.naweed.foodmania.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;

import com.example.naweed.foodmania.ProductDetailActivity;
import com.example.naweed.foodmania.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CustomExpandableListAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> expandableListTitle;
    private List<String> checked = new ArrayList<>();
    private List<String> unchecked = new ArrayList<>();
    private Map<String, List<String>> expandableListDetail;
    private String assosClass;

    public CustomExpandableListAdapter(Context context, List<String> list, String assosClass) {
        super(context,-1,list);
        this.context = context;
        this.expandableListTitle = list;
        this.assosClass = assosClass;
    }

//Customizing the list adapter and reusing it for different list types
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_group, parent, false);
        final CheckedTextView textView =  rowView.findViewById(R.id.listTitle);
        textView.setText(expandableListTitle.get(position));

        // change the icon for Windows and iPhone
        ImageView imageView = rowView.findViewById(R.id.listImage);
        imageView.setImageResource(R.drawable.burger);

        if (assosClass.equalsIgnoreCase("avail")) { //For available list
            textView.setChecked(true);
        }

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = expandableListTitle.get(position);
                if (textView.isChecked()){
textView.setChecked(false);
    checked.remove(name);
                    unchecked.add(name);
                } else if (assosClass.equalsIgnoreCase("recipes")) { //For the recipe list
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                    context.startActivity(browserIntent);

                } else {
    textView.setChecked(true);
                    checked.add(name);
                    unchecked.remove(name);

}

            }
        });

        if (assosClass.equalsIgnoreCase("editProduct")) { //For the edit product list
            textView.setCheckMarkDrawable(null);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    intent.putExtra("product", expandableListTitle.get(position));
                    System.out.println(expandableListTitle.get(position));
                    context.startActivity(intent);

                }
            });
        } else if (assosClass.equalsIgnoreCase("search")) { //For the search list
            textView.setCheckMarkDrawable(null);

        }
        return rowView;
    }

    public List<String> getChecked() {
        return checked;
    }

    public void setChecked(List<String> checked) {
        this.checked = checked;
    }

    public List<String> getUnchecked() {
        return unchecked;
    }

    private String getImageLoc(int listPosition) {
        return this.expandableListDetail.get(listPosition).get(0);
    }



}
