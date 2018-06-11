package com.example.jonh.androidlogoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jonh.androidlogoquiz.Adapter.GridViewAnswerAdapter;
import com.example.jonh.androidlogoquiz.Adapter.GridViewSuggestAdapter;
import com.example.jonh.androidlogoquiz.Common.Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public List<String> suggestSource = new ArrayList<> ();

    public GridViewAnswerAdapter answerAdapter;
    public GridViewSuggestAdapter suggestAdapter;

    public Button btnSubmit;

    public GridView gridViewAnswer, gridViewSuggest;

    public ImageView imgViewQuestion;

    int [] image_list = {
            R.drawable.adidas,
            R.drawable.beats,
            R.drawable.blogger,
            R.drawable.burgerking,
            R.drawable.buzzfeed,
            R.drawable.chanel,
            R.drawable.corvette,
            R.drawable.deviantart,
            R.drawable.digg,
            R.drawable.dolby,
            R.drawable.dropbox,
            R.drawable.evernote,
            R.drawable.facebook,
            R.drawable.fanta,
            R.drawable.flickr,
            R.drawable.foodpanda,
            R.drawable.generalelectric,
            R.drawable.google,
            R.drawable.hyves,
            R.drawable.instagram,
            R.drawable.intel,
            R.drawable.jaguarpng,
            R.drawable.linkedin,
            R.drawable.lious,
            R.drawable.mangobaaz,
            R.drawable.michlen,
            R.drawable.myspace,
            R.drawable.nbc,
            R.drawable.origin,
            R.drawable.picasa,
            R.drawable.pinterest,
            R.drawable.pringles,
            R.drawable.reddit,
            R.drawable.rolex,
            R.drawable.roundrock,
            R.drawable.rss,
            R.drawable.skype,
            R.drawable.soundcloud,
            R.drawable.starbucks,
            R.drawable.steam,
            R.drawable.stumbleupon,
            R.drawable.torbrowser,
            R.drawable.pizzahut,
            R.drawable.torr,
            R.drawable.twitter,
            R.drawable.uber,
            R.drawable.ubisoft,
            R.drawable.unilever,
            R.drawable.vimeo,
            R.drawable.vodafone,
            R.drawable.volkswagen,
            R.drawable.walls,
            R.drawable.wella,
            R.drawable.western_union,
            R.drawable.whatsapp,
            R.drawable.wwf,
            R.drawable.yahoo,
            R.drawable.youtube
    };

    public char[] answer;
    String correct_answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        gridViewAnswer = (GridView)findViewById(R.id.gridViewAnswer);
        gridViewSuggest = (GridView)findViewById(R.id.gridViewSuggest);

        imgViewQuestion = (ImageView)findViewById(R.id.imgLogo);

        //add setuplist her
        setupList();

        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result="";
                for (int i=0; i< Common.user_submit_answer.length; i++)
                    result += String.valueOf(Common.user_submit_answer[i]);
                if (result.equals(correct_answer))
                {
                    Toast.makeText(getApplicationContext(),"Yayyy!! This is " +result,Toast.LENGTH_SHORT).show();
                    //Reset

                    Common.count = 0;
                    Common.user_submit_answer = new char[correct_answer.length()];

                    //set Adapter
                    GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(setupNullList(),getApplicationContext());
                    gridViewAnswer.setAdapter(answerAdapter);
                    answerAdapter.notifyDataSetChanged();

                    GridViewSuggestAdapter suggestAdapter = new GridViewSuggestAdapter(suggestSource, getApplicationContext(),MainActivity.this);
                    gridViewSuggest.setAdapter(suggestAdapter);
                    suggestAdapter.notifyDataSetChanged();

                    setupList();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Incorrect!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupList() {
        //Random logo
        Random random = new Random();
        int imageSelected = image_list[random.nextInt(image_list.length)];
        imgViewQuestion.setImageResource(imageSelected);

        correct_answer = getResources().getResourceName(imageSelected);
        correct_answer = correct_answer.substring(correct_answer.lastIndexOf("/")+1);
        answer = correct_answer.toCharArray();
        Common.user_submit_answer = new char[answer.length];

        //add answer character to list
        suggestSource.clear();
        for(char item:answer)
        {
            //add logo name to list
            suggestSource.add(String.valueOf(item));
        }

        //random add some character to list

for(int i=answer.length; i<answer.length*2;i++)
    suggestSource.add(Common.alphabet_character[random.nextInt(Common.alphabet_character.length)]);

        //sort random
        Collections.shuffle(suggestSource);

        //set fot GridView
        answerAdapter = new GridViewAnswerAdapter(setupNullList(),this);
        suggestAdapter = new GridViewSuggestAdapter(suggestSource,this,this);

        answerAdapter.notifyDataSetChanged();
        suggestAdapter.notifyDataSetChanged();

        gridViewSuggest.setAdapter(suggestAdapter);
        gridViewAnswer.setAdapter(answerAdapter);

    }

    private char[] setupNullList()
    {
        char  result [] = new char[answer.length];
        for(int i=0; i<answer.length;i++)
            result[i] = ' ';
        return result;


    }
}
