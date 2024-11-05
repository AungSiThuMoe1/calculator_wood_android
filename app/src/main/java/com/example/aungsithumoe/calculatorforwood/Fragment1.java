package com.example.aungsithumoe.calculatorforwood;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;

import com.example.aungsithumoe.calculatorforwood.fontcomponent.ZawgyiButton;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aungsithumoe on 11/7/17.
 */

public class Fragment1 extends Fragment {
    ListView list;
    Button btnCalculate;
    TextView feet, inch, qty, cufeet, cumt, totalvalue, mtvalue;
    DecimalFormat df = new DecimalFormat("0.00");
    static List<Round> rounds = new ArrayList<>();
    private AdView mBannerAd;
dbhelper dbh;
    int position;
    public Fragment1() {
        // Required empty public constructor
    }

    static double sumft = 0.0;
    static double summt = 0.0;
    static double value = 0.0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview = inflater.inflate(R.layout.fragement_one, container, false);
        dbh=new dbhelper(getActivity());
        list = myview.findViewById(R.id.list);
        feet = myview.findViewById(R.id.edlength);
        inch = myview.findViewById(R.id.edperimeter);
        qty = myview.findViewById(R.id.edquantity);
        cufeet = myview.findViewById(R.id.cufeet);
        cumt = myview.findViewById(R.id.cumt);
        mtvalue = myview.findViewById(R.id.mtvalue);
        totalvalue = myview.findViewById(R.id.totalvalue);
        btnCalculate = myview.findViewById(R.id.btncalculate);
        // Load the add into Admob banner view.
        mBannerAd = (AdView) myview.findViewById(R.id.banner_AdView);
        //Load BannerAd
        showBannerAd();
        try {
            list.setAdapter(new CustomAdapterRound(getActivity(), dbh.getAllRound()));
            cufeet.setText(df.format(dbh.totalrountft()));
            cumt.setText(df.format(dbh.totalround()));
        }
        catch (Exception e)
        {

        }
        mtvalue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    value = dbh.totalround() * Double.parseDouble(mtvalue.getText().toString());
                    totalvalue.setText("" + ((int)Math.round(value)));
                }catch (Exception e)
                {
                    totalvalue.setText("0");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double length = Double.parseDouble(feet.getText().toString());
                    double perimeter = Double.parseDouble(inch.getText().toString());
                    double quantity = Double.parseDouble(qty.getText().toString());
                    double cuft = 0.0;
                    double cumt1 = 0.0;
                    double inchs = perimeter;
                    double cuft1 = (inchs * inchs * length * quantity);
                    cuft = cuft1 / 2304.0;
                    cumt1 = cuft / 50;
                    dbh.insertRound(length,perimeter,quantity,Double.parseDouble(df.format(cuft)),Double.parseDouble(df.format(cumt1)));
//                    sumft += cuft;
//                    summt += cumt1;
                    cufeet.setText(df.format(dbh.totalrountft()));
                    cumt.setText(df.format(dbh.totalround()));
                   // Round round = new Round(length, perimeter, quantity);
                    //rounds.add(round);
                    list.setAdapter(new CustomAdapterRound(getActivity(), dbh.getAllRound()));
                    feet.setText("");
                    inch.setText("");
                    qty.setText("1");
                    feet.setHint("ပေ");
                    inch.setHint("လက်မ");
                    feet.requestFocus();
                }
                catch (Exception e)
                {

                }
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDialog(position);
            }
        });
        return myview;
    }
public void showDialog(int i)
{   position=(i+1);
    final Dialog dialog=new Dialog(getActivity());
    dialog.setContentView(R.layout.custom_round_dialog);
    final TextView length1=(TextView)dialog.findViewById(R.id.edlength);
    final TextView perimeter1=(TextView)dialog.findViewById(R.id.edperimeter);
    final TextView quantity1=(TextView)dialog.findViewById(R.id.edquantity);
    TextView showLenght=(TextView)dialog.findViewById(R.id.showlength);
    TextView showPerimeter=(TextView)dialog.findViewById(R.id.showperimeter);
    TextView showQuantity=(TextView)dialog.findViewById(R.id.showquantity);
    TextView btndelete=(TextView) dialog.findViewById(R.id.delete);
    TextView btncancel=(TextView) dialog.findViewById(R.id.cancel);
    TextView btnok=(TextView)dialog.findViewById(R.id.ok);
    List<Round> rounds=dbh.getAllRound();
    final Round round=rounds.get(i);
    showLenght.setText(round.getLength()+"");
    showPerimeter.setText(round.getPerimeter()+"");
    showQuantity.setText(round.getQuantity()+"");
    btndelete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dbh.deleteRound(round.getId());
            try {
                list.setAdapter(new CustomAdapterRound(getActivity(), dbh.getAllRound()));
                cufeet.setText(df.format(dbh.totalrountft()));
                cumt.setText(df.format(dbh.totalround()));
            }
            catch (Exception e)
            {

            }
            dialog.dismiss();

        }
    });
    btncancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog.dismiss();
        }
    });
    btnok.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            double length = Double.parseDouble(length1.getText().toString());
            double perimeter = Double.parseDouble(perimeter1.getText().toString());
            double quantity = Double.parseDouble(quantity1.getText().toString());
            double cuft = 0.0;
            double cumt1 = 0.0;
            double inchs = perimeter;
            double cuft1 = (inchs * inchs * length * quantity);
            cuft = cuft1 / 2304.0;
            cumt1 = cuft / 50.0;
            dbh.updateRound(round.getId(),length, perimeter, quantity,Double.parseDouble(df.format(cuft)),cumt1);
//                    sumft += cuft;
//                    summt += cumt1;
            try {
                list.setAdapter(new CustomAdapterRound(getActivity(), dbh.getAllRound()));
                cufeet.setText(df.format(dbh.totalrountft()));
                cumt.setText(df.format(dbh.totalround()));
            } catch (Exception e) {

            }
            dialog.dismiss();
        }
    });

    dialog.show();

}
    private void showBannerAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mBannerAd.loadAd(adRequest);

    }
//    static double sumft1=0.0;
//    static double summt1=0.0;
//    static List<Round> r=new ArrayList<>();
//    static double value1=0.0;

//@Override
//    public void onResume()
//{
//    super.onResume();
//    cufeet.setText(df.format(sumft));
//    cumt.setText(df.format(summt));
//    try {
//        list.setAdapter(new CustomAdapterRound(getActivity(), dbh.getAllRound()));
//    }
//    catch (Exception e)
//    {
//
//    }
//    totalvalue.setText("" + ((int)Math.round(value)));
//
//}

}
