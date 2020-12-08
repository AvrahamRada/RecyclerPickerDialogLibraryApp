package com.example.recyclerpickerdialoglibrary.myRecycle;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerpickerdialoglibrary.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> listOfData;
    private int option;
    private int yourAnswer = -1;
    private ArrayList<Integer> yourAnswers;

    public MyAdapter(Context context, ArrayList<String> listOfData, int option) {
        this.context = context;
        this.listOfData = listOfData;
        this.option = option;
        yourAnswers = new ArrayList<>();
    }

    public int getLastCheckedPosition() {
        return yourAnswer;
    }

    // Initialize View Holder
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        switch (option) {
            case 0:
                view = inflater.inflate(R.layout.single_check_box_row, parent, false);
                break;
            case 1:
                view = inflater.inflate(R.layout.single_radio_button_row, parent, false);
                break;
            case 2:
                view = inflater.inflate(R.layout.single_switch_row, parent, false);
                break;
            default:
                view = null;
                break;
        }
        return new MyViewHolder(view, option);
    }

    // Bind data
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nameTxt.setText(listOfData.get(position));

        if (option == 1) {
            holder.myViewHolder_RBTN_radioBtn.setChecked(position == yourAnswer);

        }
        if (option == 2)
            holder.myViewHolder_SW_switch.setChecked(position == yourAnswer);
    }

    @Override
    public int getItemCount() {
        return listOfData.size();
    }

    public ArrayList<String> getListOfData() {
        return listOfData;
    }

    public ArrayList<Integer> getYourAnswers() {
        return yourAnswers;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTxt;
        CheckBox myViewHolder_CBX_checkBox;
        RadioButton myViewHolder_RBTN_radioBtn;
        Switch myViewHolder_SW_switch;

        public MyViewHolder(@NonNull View itemView, int option) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.nameTxt);
            myViewHolder_CBX_checkBox = itemView.findViewById(R.id.myViewHolder_CBX_checkBox);
            myViewHolder_RBTN_radioBtn = itemView.findViewById(R.id.myViewHolder_RDOBTN_radioBtn);
            myViewHolder_SW_switch = itemView.findViewById(R.id.myViewHolder_SW_switch);

            if (option == 0) {
                myViewHolder_CBX_checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(yourAnswers.contains(new Integer(getAdapterPosition()))){
                            yourAnswers.remove(new Integer(getAdapterPosition()));
                        }
                        else{
                            yourAnswers.add(new Integer(getAdapterPosition()));
                        }

//                        int copyOfLastCheckedPosition = yourAnswer;
//                        yourAnswer = getAdapterPosition();
//                        notifyItemChanged(copyOfLastCheckedPosition);
//                        notifyItemChanged(yourAnswer);
                    }
                });
            }

            if (option == 1) {
                myViewHolder_RBTN_radioBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int copyOfLastCheckedPosition = yourAnswer;
                        yourAnswer = getAdapterPosition();
                        notifyItemChanged(copyOfLastCheckedPosition);
                        notifyItemChanged(yourAnswer);
                    }
                });
            }
            if (option == 2) {
                myViewHolder_SW_switch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int copyOfLastCheckedPosition = yourAnswer;
                        yourAnswer = getAdapterPosition();
                        notifyItemChanged(copyOfLastCheckedPosition);
                        notifyItemChanged(yourAnswer);
//                        Log.d("pttt", "" + yourAnswer);
                    }
                });
            }
        }
    }
}