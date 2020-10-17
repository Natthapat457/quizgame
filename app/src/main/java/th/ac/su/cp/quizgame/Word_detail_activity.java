package th.ac.su.cp.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import th.ac.su.cp.quizgame.model.WordItem;

public class Word_detail_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail_activity);
        Intent intent = getIntent();
        String itemjson =intent.getStringExtra("item");
        WordItem item =  new Gson().fromJson(itemjson, WordItem.class);

        /*String word = intent.getStringExtra("word");
        int img = intent.getIntExtra("image",0);*/
        ImageView iv = findViewById(R.id.imageViewDetail);
        TextView tv = findViewById(R.id.textViewDetail);
        iv.setImageResource(item.imageResId);
        tv.setText(item.word);

    }
}