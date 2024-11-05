package com.example.aungsithumoe.calculatorforwood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by aungsithumoe on 11/9/17.
 */

public class CustomAdapterRound extends BaseAdapter {
    Context context;
    List<Round> round;

    public CustomAdapterRound(Context context, List<Round> round) {
        this.context = context;
        this.round = round;
    }

    @Override
    public int getCount() {
        return round.size();
    }

    @Override
    public Object getItem(int position) {
        return round.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View myView=inflater.inflate(R.layout.customround_list,parent,false);
        TextView txtid=(TextView)myView.findViewById(R.id.length);
        TextView txtname=(TextView)myView.findViewById(R.id.perimeter);
        TextView txtaddress=(TextView)myView.findViewById(R.id.quantity);
        Round student=round.get(position);
        txtid.setText(""+student.getLength());
        txtname.setText(""+student.getPerimeter());
        txtaddress.setText(""+student.getQuantity());
        return myView;
    }
}
