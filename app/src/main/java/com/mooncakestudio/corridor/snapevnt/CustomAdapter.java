package com.mooncakestudio.corridor.snapevnt;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
            Color.parseColor("#ffd27f"),
            Color.parseColor("#F02720"),
            /*Color.parseColor("#4cc1bb"),
            Color.parseColor("#9ee59e"),
            Color.parseColor("#81d8d0"),
            Color.parseColor("#ffffb2"),
            Color.parseColor("#ffd27f")*/
    };

    private static LayoutInflater inflater=null;

    public CustomAdapter(MainGroups mainGroup, String[] menu, String[] listCount, int[] menuImages) {
        // TODO Auto-generated constructor stub
        myMenu=menu;
        myListCount = listCount;
        context=mainGroup;
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
        TextView groupTxt;
        TextView msgCountTxt;
        ImageView groupImg;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.custom_row, null);

        // get ID
        holder.groupTxt=(TextView) rowView.findViewById(R.id.groupTxt);
        holder.msgCountTxt=(TextView) rowView.findViewById(R.id.msgCountTxt);
        holder.groupImg=(ImageView) rowView.findViewById(R.id.groupImg);

        // set Text and Image
        holder.groupTxt.setText(myMenu[position]);
        holder.msgCountTxt.setText(myListCount[position]);
        holder.groupImg.setImageResource(images[position]);


        rowView.setBackgroundColor(colors[position]);

        return rowView;
    }

}