package com.example.dynamicui;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomerAdapter extends BaseAdapter {

    private Context context;
    private String[] fruitnames;
    private int[] images;

    private LayoutInflater inflater;

    public  CustomerAdapter(Context context, String[] fruitnames, int[] images){
        this.context = context;
        this.fruitnames = fruitnames;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return fruitnames.length;
    }


    @Override
    public Object getItem(int position) {
        return fruitnames[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.listview, null);
        TextView text = (TextView) convertView.findViewById(R.id.textView);
        ImageView image = (ImageView) convertView.findViewById(R.id.imageView);
        text.setText(fruitnames[position]);
        image.setImageResource(images[position]);

        return convertView;
    }
}
