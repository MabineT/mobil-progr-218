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
import com.mp.tshepo.mobile.function.OneVariable;
import com.mp.tshepo.mobile.realm.Feed;
import com.mp.tshepo.mobile.realm.RealmHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;

/**
 * Created by Tshepo on 07/08/2017.
 */

public class FrgOneVariable extends Fragment {

    private RecyclerView helperRecyclerView;
    private RelatedAdapter helperAdapter;
    private SnapHelper snapHelper;
    private View view;
    private Realm realm;
    private RealmHelper helper;
    private List<Feed> items;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frg_helper, container, false);
        setupControls(view);
        setupInputs(view);
        return view;
    }
    private  EditText variableN = null;
    private TextView oneOutput = null;
    private TextView oneFunction = null;
    private Button oneButton = null;
    private CardView oneVariableCard = null;
    private String oneInputHolder = "";
    private String oneFunctionName = "";
    private String oneOutputHolder = "";

    private void setupInputs(View view) {
        variableN = (EditText) view.findViewById(R.id.n_value_fib);
        oneButton = (Button) view.findViewById(R.id.calculate_fib);
        oneVariableCard = (CardView) view.findViewById(R.id.card_one_output);
        oneOutput = (TextView) view.findViewById(R.id.one_output_text);
        oneFunction = (TextView) view.findViewById(R.id.one_function_name);
        oneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oneInputHolder = variableN.getText().toString();
                oneFunctionName = oneFunction.getText().toString();
                performCalculation(oneInputHolder, oneFunctionName);
            }
        });
        oneVariableCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                saveToRealm(oneOutputHolder);
                return true;
            }
        });
    }

    public void saveToRealm(String output)
    {
        if(output.length() > 0)
        {
            Feed feed = new Feed();
            String title = oneFunctionName;
            String calculation = "IN: " + oneInputHolder + "\nOUT: " + output;
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm");
            Date date = new Date(System.currentTimeMillis());
            String timeStamp = sdf.format(date);
            feed.setId(System.currentTimeMillis());
            feed.setTitle(title);
            feed.setOutput(calculation);
            feed.setTimeStamp(timeStamp);
            helper.create(realm, feed, System.currentTimeMillis());
        }
        else
        {
            Toast.makeText(getContext(), "nothing saved", Toast.LENGTH_LONG).show();
        }
    }
    private void performCalculation(String value, String function)
    {
        if(value.length() > 0)
        {
            if(function.toUpperCase(Locale.ENGLISH).contains("FACTORIAL"))
            {
                oneOutputHolder = String.format("%.0f", OneVariable.Factorial(Long.valueOf(value)));
                oneOutput.setText(oneOutputHolder);
            }
            else if(function.toUpperCase(Locale.ENGLISH).contains("MODULO"))
            {
                ArrayList<Long> result = OneVariable.unitsModuloN(Integer.valueOf(value));
                oneOutputHolder = "U(" +value+ ")"+ " = {";

                for (int i = 0; i < result.size() - 1; i++)
                {
                    oneOutputHolder += result.get(i) + ", ";
                }
                oneOutputHolder += result.get(result.size() - 1) +"}";

                if(OneVariable.isAbelian(result, Integer.valueOf(value)))
                {
                    oneOutputHolder += "\n\nU(" +value+ ") is an Abelian group";
                }
                else
                {
                    oneOutputHolder += "\n\nU(" +value+ ") is not Abelian";
                }

                oneOutput.setText(oneOutputHolder);

                items = new ArrayList<>();
                Feed  tempItem = new Feed();
                tempItem.setTitle("Abelian Groups:");
                tempItem.setOutput("obey the axiom of commutativity\n x * y = y * x\n x * x = identity\n\nwhere * is any operation");
                items.add(tempItem);
                Feed relatedItem = new Feed();
                relatedItem.setTitle("U(" +value+ ") < " + "Z(" +value+ ")");

                String subofString = "{";

                for (int i = 0; i < OneVariable.subOf.size() - 1; i++)
                {
                    subofString += OneVariable.subOf.get(i) + ", ";
                }
                subofString += result.get(result.size() - 1) +"}";
                subofString += "\n\nU(" +value+ ") is a proper subgroup of " + "Z(" +value+ ")";
                relatedItem.setOutput(subofString);
                items.add(relatedItem);
                helperAdapter = new RelatedAdapter(getContext(),items);
                helperRecyclerView.setAdapter(helperAdapter);

            }
            else if(function.toUpperCase(Locale.ENGLISH).contains("FIBO"))
            {
                OneVariable.seq = "{0, 1,";
                OneVariable.Fibonacci(Integer.valueOf(value) - 2, 0, 1);
                oneOutputHolder = OneVariable.seq + "}";

                oneOutput.setText(oneOutputHolder);
            }
        }
        else
        {
            Toast.makeText(getContext(), "Invalid input", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.one_variable);
        helperRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view_related_helper);
        helperRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), OrientationHelper.HORIZONTAL, false));

        items = new ArrayList<>();
        helper = new RealmHelper();
        realm = helper.initialize("feedsDB");
        helperAdapter = new RelatedAdapter(getContext(),items);
        helperRecyclerView.setAdapter(helperAdapter);
        snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(helperRecyclerView);
    }


    private TextView functionName;
    private ImageView previous;
    private ImageView next;
    private String[] oneVariableList = {"Factorial", "Units modulo n", "Fibonacci Sequence"};
    private int count = 0;

    private void setupControls(View view) {
        functionName = view.findViewById(R.id.one_function_name);
        previous = (ImageView) view.findViewById(R.id.helper_previous);
        next = (ImageView) view.findViewById(R.id.helper_next);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count - 1 > 0) {
                    count -= 1;
                    functionName.setText(oneVariableList[count]);
                } else if (count - 1 == 0) {
                    count -= 1;
                    functionName.setText(oneVariableList[count]);
                    previous.setVisibility(View.INVISIBLE);
                    next.setVisibility(View.VISIBLE);
                }


            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count + 1 < (oneVariableList.length - 1)) {
                    count += 1;
                    functionName.setText(oneVariableList[count]);
                } else if (count + 1 == (oneVariableList.length - 1)) {
                    count += 1;
                    functionName.setText(oneVariableList[count]);
                    previous.setVisibility(View.VISIBLE);
                    next.setVisibility(View.INVISIBLE);
                }
            }
        });
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


