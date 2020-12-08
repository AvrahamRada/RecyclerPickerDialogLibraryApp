package com.example.recyclerpickerdialoglibrary.myRecycle;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerpickerdialoglibrary.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MyOwnCustomDialog extends DialogFragment {
    private String title; // titel of DialogFragment
    private int option; // 0 - CheckBox, 1 - RadioButton, 2 - switch
    private int numOfRows; // Number of rows to display in RecyclerView
    ArrayList<String> listOfData = new ArrayList<>(); // data
    private int color;

    TextView fragment_dialog_TXTVIEW_title;
    RecyclerView fragment_dialog_RCVIEW_myRecycle;
    MaterialButton fragment_dialog_BTN_ok;
    MyAdapter myAdapter;

    public MyOwnCustomDialog() {
        // Required empty public constructor
    }

    public MyOwnCustomDialog(String title, int option, Object data, int color) {
        setTitle(title);
        setOption(option);
        convertDataToRelevantCollection(data);
        setNumOfRows(numOfRows);
        this.color = color;
    }

    private void convertDataToRelevantCollection(Object data) {

        if (data instanceof List) {
            for (String value : (List<String>) data) {
                listOfData.add(value);
            }
        } else if (data instanceof String[]) {
            String[] temp = (String[]) data;
            for (int i = 0; i < temp.length; i++) {
                listOfData.add(temp[i]);
            }
        } else {
            Log.d("pttt", "Transfered collection is not good");
            listOfData.add("ERROR");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dialog, container, false);
        CardView cardView = rootView.findViewById(R.id.fragment_dialog_BTN_CRDVIEW_cv);


        // Text View of title
        fragment_dialog_TXTVIEW_title = rootView.findViewById(R.id.fragment_dialog_TXTVIEW_title);
        fragment_dialog_TXTVIEW_title.setText(title);
        // OK button
        fragment_dialog_BTN_ok = rootView.findViewById(R.id.fragment_dialog_BTN_ok);
        fragment_dialog_BTN_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (option != 0) {
                    if (myAdapter.getLastCheckedPosition() != -1)
                        saveBtnClicked();
                } else
                    saveBtnClicked();
            }
        });
        // Recycle
        fragment_dialog_RCVIEW_myRecycle = rootView.findViewById(R.id.fragment_dialog_RCVIEW_myRecycle);
        fragment_dialog_RCVIEW_myRecycle.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        // Adapter
        myAdapter = new MyAdapter(this.getActivity(), listOfData, option);
        fragment_dialog_RCVIEW_myRecycle.setAdapter(myAdapter);
        Log.d("pttt", "" + this.color);
        cardView.setCardBackgroundColor(color);

        this.getDialog().setCanceledOnTouchOutside(false);
        this.getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return rootView;
    }

    private void layoutAnimation(RecyclerView recyclerView) {
        Context context = recyclerView.getContext();
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down);

        recyclerView.setLayoutAnimation(layoutAnimationController);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();

    }

    public void saveBtnClicked() {
        this.getDialog().dismiss();
    }

    public String getTitle() {
        return this.title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    public int getOption() {
        return this.option;
    }

    private void setOption(int option) {
        this.option = option;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    private void setNumOfRows(int numOfRows) {
        this.numOfRows = this.listOfData.size();
    }

    public MyAdapter getMyAdapter() {
        return myAdapter;
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
        final Activity activity = getActivity();
        if (activity instanceof DialogInterface.OnDismissListener) {
            ((DialogInterface.OnDismissListener) activity).onDismiss(dialog);
        }
    }
}