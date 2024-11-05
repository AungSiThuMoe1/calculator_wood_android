package com.example.aungsithumoe.calculatorforwood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by aungsithumoe on 11/22/17.
 */

public class CustomAdapterCutsize extends BaseAdapter{
    Context context;
    List<Cutsize> cutsizes;

    public CustomAdapterCutsize(Context context, List<Cutsize> cutsizes) {
        this.context = context;
        this.cutsizes = cutsizes;
    }

    @Override
    public int getCount() {
        return cutsizes.size();
    }

    @Override
    public Object getItem(int position) {
        return cutsizes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View myView=inflater.inflate(R.layout.customcutsize_list,parent,false);
        TextView txtthick=(TextView)myView.findViewById(R.id.thick);
        TextView txtinch=(TextView)myView.findViewById(R.id.width);
        TextView txtinch1=(TextView)myView.findViewById(R.id.length);
        TextView txtquantity=(TextView)myView.findViewById(R.id.quantity);
        Cutsize student=cutsizes.get(position);
       txtthick.setText(""+student.getThick());
        txtinch.setText(""+student.getWidth());
        txtinch1.setText(""+student.getLength());
        txtquantity.setText(""+student.getQuantity());
        return myView;
    }
}
