package com.mp.tshepo.mobile.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mp.tshepo.mobile.R;
import com.mp.tshepo.mobile.function.TwoVariable;
import com.mp.tshepo.mobile.realm.Feed;
import com.mp.tshepo.mobile.realm.RealmHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;

/**
 * Created by Tshepo on 24/08/2017.
 */

public class FrgTwoVariable extends Fragment {

    private RecyclerView funcitonRecyclerView;
    private RelatedAdapter funcitonAdapter;
    private List<Feed> items;
    private SnapHelper snapHelper;
    private View view;
    private Realm realm;
    private RealmHelper helper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frg_function, container, false);
        setupControls(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.two_variable);
        funcitonRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_related_function);
        funcitonRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), OrientationHelper.HORIZONTAL, false));
        items = new ArrayList<>();
        helper = new RealmHelper();
        realm = helper.initialize("feedsDB");

        funcitonAdapter = new RelatedAdapter(getContext(), items);
        funcitonRecyclerView.setAdapter(funcitonAdapter);

        snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(funcitonRecyclerView);
    }

    private TextView helperName;
    private ImageView previous;
    private ImageView next;
    private String[] functionList = {"Greatest Common Divisor", "Lowest Common Multiple", "Permutations", "Combinations", "Modulus", "Relatively Prime"};
    private int count = -1;

    private EditText variableX = null;
    private EditText variableY = null;
    private TextView twoOutput = null;
    private TextView twoFunction = null;
    private Button twoButton = null;
    private CardView twoVariableCard = null;
    private String twoInputX = "";
    private String twoInputY = "";
    private String twoFunctionName = "";
    private String twoOutputHolder = "";


    private void setupControls(View view) {
        helperName = view.findViewById(R.id.two_function_name);
        previous = (ImageView) view.findViewById(R.id.function_previous);
        next = (ImageView) view.findViewById(R.id.function_next);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count - 1 > 0) {
                    count -= 1;
                    helperName.setText(functionList[count]);
                } else if (count - 1 == 0) {
                    count -= 1;
                    helperName.setText(functionList[count]);
                    previous.setVisibility(View.INVISIBLE);
                    next.setVisibility(View.VISIBLE);
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count + 1 < (functionList.length - 1)) {
                    count += 1;
                    helperName.setText(functionList[count]);
                } else if (count + 1 == (functionList.length - 1)) {
                    count += 1;
                    helperName.setText(functionList[count]);
                    previous.setVisibility(View.VISIBLE);
                    next.setVisibility(View.INVISIBLE);
                }
            }
        });
        variableX = (EditText) view.findViewById(R.id.x_value);
        variableY = (EditText) view.findViewById(R.id.y_value);
        twoButton = (Button) view.findViewById(R.id.calculate);
        twoVariableCard = (CardView) view.findViewById(R.id.card_two_output);
        twoFunction = (TextView) view.findViewById(R.id.two_function_name);
        twoOutput = (TextView) view.findViewById(R.id.two_output_text);

        twoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                twoInputX = variableX.getText().toString();
                twoInputY = variableY.getText().toString();
                twoFunctionName = twoFunction.getText().toString();
                performCalculation(twoInputX, twoInputY, twoFunctionName);
            }
        });
        twoVariableCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                saveToRealm(twoOutputHolder);
                return true;
            }
        });
    }

    private void performCalculation(String x, String y, String operation) {
        if (x.length() > 0 && y.length() > 0) {
            if (operation.toUpperCase(Locale.ENGLISH).contains("GREATEST")) {
                long result = TwoVariable.greatestCommonDivisor(Long.valueOf(x), Long.valueOf(y));
                twoOutputHolder = result + "";
                twoOutput.setText(twoOutputHolder);
            } else if (operation.toUpperCase(Locale.ENGLISH).contains("LOWEST")) {
                long result = TwoVariable.leastCommonMultiple(Long.valueOf(x), Long.valueOf(y));
                twoOutputHolder = result + "";
                twoOutput.setText(twoOutputHolder);
            } else if (operation.toUpperCase(Locale.ENGLISH).contains("COMB")) {
                if (Long.valueOf(x) > Long.valueOf(y)) {
                    double result = TwoVariable.combination(Long.valueOf(x), Long.valueOf(y));
                    twoOutputHolder = String.format("%.0f", result);
                    twoOutput.setText(twoOutputHolder);
                } else {
                    Toast.makeText(getContext(), "You just killed a puppy! :(", Toast.LENGTH_LONG).show();
                }
            } else if (operation.toUpperCase(Locale.ENGLISH).contains("PERM")) {
                if (Long.valueOf(x) > Long.valueOf(y)) {
                    double result = TwoVariable.permutation(Long.valueOf(x), Long.valueOf(y));
                    twoOutputHolder = String.format("%.0f", result);
                    twoOutput.setText(twoOutputHolder);
                } else {
                    Toast.makeText(getContext(), "You just killed a puppy! :(", Toast.LENGTH_LONG).show();
                }
            } else if (operation.toUpperCase(Locale.ENGLISH).contains("MOD")) {
                long result = TwoVariable.modulo(Long.valueOf(x), Long.valueOf(y));
                twoOutputHolder = result + "";
                twoOutput.setText(twoOutputHolder);
            } else if (operation.toUpperCase(Locale.ENGLISH).contains("RELATIVE")) {
                boolean result = TwoVariable.relativelyPrime(Long.valueOf(x), Long.valueOf(y));

                if (result) {
                    twoOutputHolder = x + ", " + y + " are relatively prime";
                } else {
                    twoOutputHolder = x + ", " + y + " are not relatively prime";
                }
                twoOutput.setText(twoOutputHolder);
            }
        } else {
            Toast.makeText(getContext(), "Invalid input", Toast.LENGTH_LONG).show();
        }
    }

    public void saveToRealm(String output) {
        if (output.length() > 0) {
            Feed feed = new Feed();
            String title = twoFunctionName;
            String calculation = "IN: " + twoInputX + ", " + twoInputY + "\nOUT: " + output;
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm");
            Date date = new Date(System.currentTimeMillis());

            String timeStamp = sdf.format(date);

            feed.setId(System.currentTimeMillis());
            feed.setTitle(title);
            feed.setOutput(calculation);
            feed.setTimeStamp(timeStamp);

            helper.create(realm, feed, System.currentTimeMillis());
        } else {
            Toast.makeText(getContext(), "nothing saved", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}


