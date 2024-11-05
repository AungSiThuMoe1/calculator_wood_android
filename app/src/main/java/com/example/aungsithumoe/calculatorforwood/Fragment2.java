package com.example.aungsithumoe.calculatorforwood;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aungsithumoe on 11/7/17.
 */

public class Fragment2 extends Fragment {
    ListView list;
    Button btnCalculate;
    TextView feet, inch,inch1, qty, cufeet, cumt, totalvalue, mtvalue;
    dbhelper dbh;
    DecimalFormat df = new DecimalFormat("0.00");
    private InterstitialAd mInterstitialAd;
    static List<Cutsize> rounds = new ArrayList<>();
    public Fragment2() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview =inflater.inflate(R.layout.fragment_two, container, false);
        dbh=new dbhelper(getActivity());
        list = myview.findViewById(R.id.list);
        feet = myview.findViewById(R.id.edthick);
        inch = myview.findViewById(R.id.edwidth);
        inch1 = myview.findViewById(R.id.edlength);
        qty = myview.findViewById(R.id.edquantity);
        cufeet = myview.findViewById(R.id.cufeet);
        cumt = myview.findViewById(R.id.cumt);
        mtvalue = myview.findViewById(R.id.mtvalue);
        totalvalue = myview.findViewById(R.id.totalvalue);
        btnCalculate = myview.findViewById(R.id.btncalculate);
        // Creating and load a  new InterstitialAd .
        mInterstitialAd = createNewIntAd();
        loadIntAdd();
        try {
            list.setAdapter(new CustomAdapterCutsize(getActivity(), dbh.getAllCut()));
            cufeet.setText(df.format(dbh.totalcutft()));
            cumt.setText(df.format(dbh.totalcut()));
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
                    value = dbh.totalcut() * Double.parseDouble(mtvalue.getText().toString());
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
                showIntAdd();
                try {
                    double thick = Double.parseDouble(feet.getText().toString());
                    double width = Double.parseDouble(inch.getText().toString());
                    double length = Double.parseDouble(inch1.getText().toString());
                    double quantity = Double.parseDouble(qty.getText().toString());
                    double cuft = 0.0;
                    double cumt1 = 0.0;
                    double cuft1 = (thick * width * length * quantity);
                    cuft = cuft1 / 144.0;
                    cumt1 = cuft / 50.0;
                    dbh.insertCut(thick,width,length,quantity,Double.parseDouble(df.format(cuft)),cumt1);
                    cufeet.setText(df.format(dbh.totalcutft()));
                    cumt.setText(df.format(dbh.totalcut()));
                    System.out.println("cuft="+ cuft);
                    System.out.println("cumt1="+ cumt1);
                    System.out.println("cufeet="+ dbh.totalcutft());
                    System.out.println("cumt="+ dbh.totalcut());
                    //Cutsize round = new Cutsize(thick, width, length, quantity);
                    //rounds.add(round);
                    list.setAdapter(new CustomAdapterCutsize(getActivity(), dbh.getAllCut()));
                    feet.setText("");
                    inch.setText("");
                    inch1.setText("");
                    qty.setText("1");
                    feet.setHint("လက်မ");
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
    {
       final Dialog dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_cut_dialog);
        final TextView thick1=(TextView)dialog.findViewById(R.id.edthick);
        final TextView width1=(TextView)dialog.findViewById(R.id.edwidth);
        final TextView length1=(TextView)dialog.findViewById(R.id.edlength);
        final TextView quantity1=(TextView)dialog.findViewById(R.id.edquantity);
        TextView showLenght=(TextView)dialog.findViewById(R.id.showlength);
        TextView showThick=(TextView)dialog.findViewById(R.id.showthick);
        TextView showWidth=(TextView)dialog.findViewById(R.id.showwidth);
        TextView showQuantity=(TextView)dialog.findViewById(R.id.showquantity);
        TextView btndelete=(TextView) dialog.findViewById(R.id.delete);
        TextView btncancel=(TextView) dialog.findViewById(R.id.cancel);
        TextView btnok=(TextView)dialog.findViewById(R.id.ok);
        List<Cutsize> rounds=dbh.getAllCut();
        final Cutsize round=rounds.get(i);
        showThick.setText(round.getThick()+"");
        showWidth.setText(round.getWidth()+"");
        showLenght.setText(round.getLength()+"");
        showQuantity.setText(round.getQuantity()+"");
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbh.deleteCut(round.getId());
                try {
                    list.setAdapter(new CustomAdapterCutsize(getActivity(), dbh.getAllCut()));
                    cufeet.setText(df.format(dbh.totalcutft()));
                    cumt.setText(df.format(dbh.totalcut()));
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
                double thick = Double.parseDouble(thick1.getText().toString());
                double width = Double.parseDouble(width1.getText().toString());
                double length = Double.parseDouble(length1.getText().toString());
                double quantity = Double.parseDouble(quantity1.getText().toString());
                double cuft = 0.0;
                double cumt1 = 0.0;
                double cuft1 = (thick * width * length * quantity);
                cuft = cuft1 / 144.0;
                cumt1 = cuft / 50;
                dbh.updateCut(round.getId(),thick,width,length,quantity,Double.parseDouble(df.format(cuft)),Double.parseDouble(df.format(cumt1)));
//                    sumft += cuft;
//                    summt += cumt1;
                try {
                    list.setAdapter(new CustomAdapterCutsize(getActivity(), dbh.getAllCut()));
                    cufeet.setText(df.format(dbh.totalcutft()));
                    cumt.setText(df.format(dbh.totalcut()));
                } catch (Exception e) {

                }
                dialog.dismiss();
            }
        });

        dialog.show();

    }
    private InterstitialAd createNewIntAd() {
        InterstitialAd intAd = new InterstitialAd(getContext());
        // set the adUnitId (defined in values/strings.xml)
        intAd.setAdUnitId(getString(R.string.ad_id_interstitial));
        intAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
               // mLevelTwoButton.setEnabled(true);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
              //  mLevelTwoButton.setEnabled(true);
            }

            @Override
            public void onAdClosed() {
                // Proceed to the next level.
              //  levelTwo();
            }
        });
        return intAd;
    }
    private void showIntAdd() {

// Show the ad if it's ready. Otherwise, toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
    private void loadIntAdd() {
        // Disable the  level two button and load the ad.
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        // .addTestDevice("754DB6521943676637AE86202C5ACE52")
        mInterstitialAd.loadAd(adRequest);
    }

//    static double sumft1=0.0;
//    static double summt1=0.0;
//    static List<Round> r=new ArrayList<>();
//    static double value1=0.0;

//    @Override
//    public void onResume()
//    {
//        super.onResume();
//        cufeet.setText(df.format(sumft));
//        cumt.setText(df.format(summt));
//        try {
//            list.setAdapter(new CustomAdapterCutsize(getActivity(), dbh.getAllCut()));
//        }
//        catch (Exception e)
//        {
//
//        }
//        totalvalue.setText("" + ((int)Math.round(value)));
//
//    }
}
