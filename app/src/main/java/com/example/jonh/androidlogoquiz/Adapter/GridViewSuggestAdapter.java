package com.example.jonh.androidlogoquiz.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.example.jonh.androidlogoquiz.Common.Common;
import com.example.jonh.androidlogoquiz.MainActivity;

import java.util.List;


public class GridViewSuggestAdapter extends BaseAdapter{

    private List<String > suggestSource;
    private Context context;
    private MainActivity mainActivity;

    public GridViewSuggestAdapter (List<String > suggestSource, Context context,MainActivity mainActivity){
        this.suggestSource = suggestSource;
        this.context = context;
        this.mainActivity = mainActivity;

    }

    @Override
    public int getCount() {
        return suggestSource.size();
    }

    @Override
    public Object getItem(int position) {
        return suggestSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
       Button button;

       if(convertView == null)
       {
           if(suggestSource.get(position).equals("null"))
           {
              button = new Button(context);
              button.setLayoutParams(new GridView.LayoutParams(85,85));
              button.setPadding(8,8,8,8);
              button.setBackgroundColor(Color.DKGRAY);
           }
           else
           {
               button = new Button(context);
               button.setLayoutParams(new GridView.LayoutParams(85,85));
               button.setPadding(8,8,8,8);
               button.setBackgroundColor(Color.DKGRAY);
               button.setTextColor(Color.YELLOW);
               button.setText(suggestSource.get(position));
               button.setOnClickListener(new View.OnClickListener() {

                   @Override
                   public void onClick(View v) {
                       // if correct answer contains characters user selected
                       if(String.valueOf(mainActivity.answer).contains(suggestSource.get(position)))
                       {
                            char compare = suggestSource.get(position).charAt(0);

                            for(int i=0; i<mainActivity.answer.length;i++) {
                                if (compare == mainActivity.answer[i])
                                    Common.user_submit_answer[i] = compare;
                            }

                            //Update UI
                           GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(Common.user_submit_answer,context);
                            mainActivity.gridViewAnswer.setAdapter(answerAdapter);
                            answerAdapter.notifyDataSetChanged();

                            //remove from suggest source
                           mainActivity.suggestSource.set(position,"null");
                           mainActivity.suggestAdapter = new GridViewSuggestAdapter(mainActivity.suggestSource,context,mainActivity);
                           mainActivity.gridViewSuggest.setAdapter(mainActivity.suggestAdapter);
                           mainActivity.suggestAdapter.notifyDataSetChanged();
                       }
                       else
                       {
                           //remove from suggest source
                           mainActivity.suggestSource.set(position,"null");
                           mainActivity.suggestAdapter = new GridViewSuggestAdapter(mainActivity.suggestSource,context,mainActivity);
                           mainActivity.gridViewSuggest.setAdapter(mainActivity.suggestAdapter);
                           mainActivity.suggestAdapter.notifyDataSetChanged();

                       }
                   }
               });
           }
       }
        else
            button = (Button) convertView;
            return button;
    }
}
