package com.example.owner.myproject;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter extends BaseAdapter{

    String [] myMenu;
    String [] myListCount;
    Context context;
    int [] images;

    // Setting listview background colors
    private int[] colors = new int[] {
            Color.parseColor("#4cc1bb"),
            Color.parseColor("#9ee59e"),
            Color.parseColor("#81d8d0"),
            Color.parseColor("#ffffb2"),
            Color.parseColor("#ffd27f")
    };

    private static LayoutInflater inflater=null;

    public CustomAdapter(MainActivity mainActivity, String[] menu, String[] listCount, int[] menuImages) {
        // TODO Auto-generated constructor stub
        myMenu=menu;
        myListCount = listCount;
        context=mainActivity;
        images=menuImages;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return myMenu.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView groceryTxt;
        TextView listCountTxt;
        ImageView groceryImg;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.custom_row, null);

        // get ID
        holder.groceryTxt=(TextView) rowView.findViewById(R.id.groceryTxt);
        holder.listCountTxt=(TextView) rowView.findViewById(R.id.listCountTxt);
        holder.groceryImg=(ImageView) rowView.findViewById(R.id.groceryImg);

        // set Text and Image
        holder.groceryTxt.setText(myMenu[position]);
        holder.listCountTxt.setText(myListCount[position]);
        holder.groceryImg.setImageResource(images[position]);


        rowView.setBackgroundColor(colors[position]);

        return rowView;
    }

}