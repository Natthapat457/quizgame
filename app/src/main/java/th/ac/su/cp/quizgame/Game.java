package th.ac.su.cp.quizgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import th.ac.su.cp.quizgame.model.WordItem;

public class Game extends AppCompatActivity implements View.OnClickListener {
    ImageView mQuestionimageview;
    public int counter=0;
    public int score=0;
   // private Button mChoice1,mChoice2,mChoice3,mChoice4;
    Button[] mButtonlist = new Button[4];
    List<WordItem> itemlist =new ArrayList<>(Arrays.asList(wordlistactivity.items)) ;
    private String mAnswerword;
    Random rand ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mQuestionimageview = findViewById(R.id.image_qa);
        mButtonlist[0] = findViewById(R.id.c1);
        mButtonlist[1] = findViewById(R.id.c2);
        mButtonlist[2] = findViewById(R.id.c3);
        mButtonlist[3] = findViewById(R.id.c4);
        //System.out.println(findViewById(R.id.c1));

        mButtonlist[0].setOnClickListener(this);
        mButtonlist[1].setOnClickListener(this);
        mButtonlist[2].setOnClickListener(this);
        mButtonlist[3].setOnClickListener(this);
        rand =  new Random();

        newQuiz();





    }//----------------------ctrl +alt +m
    private void newQuiz(){
        if(counter==5){
            AlertDialog.Builder dialog = new AlertDialog.Builder(Game.this);
            dialog.setTitle("สรุปผล");
            dialog.setMessage("คุณได้"+score+"คะเเนน\nต้องการเล่นใหม่หรือไม่ ?");

            System.out.println(score+">>>>>>>>>>>>>"+counter);
            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    score=0;
                    counter=0;

                }
            });

            dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    score=0;
                    counter=0;
                    finish();

                }
            });
            dialog.show();
        }
        counter++;
        List<WordItem> mItemList = new ArrayList<>(Arrays.asList(wordlistactivity.items));
        int answerindex = rand.nextInt(itemlist.size());
        WordItem item = mItemList.get(answerindex);
        mQuestionimageview.setImageResource(item.imageResId);
        TextView nameshow = findViewById(R.id.nameshow);
        nameshow.setText(score+" คะเเนน");
        mAnswerword = item.word;

        // สุ่มตำแหน่งปุ่มที่จะแสดงคำตอบ
        int randomButton = rand.nextInt(4);
        // แสดงคำศัพท์ที่เป็นคำตอบ
        mButtonlist[randomButton].setText(item.word);
        // ลบ WordItem ที่เป็นคำตอบออกจาก list
        mItemList.remove(item);

        // เอา list ที่เหลือมา shuffle
        Collections.shuffle(mItemList);

        // เอาคำศัพท์จาก list ที่ตำแหน่ง 0 ถึง 3 มาแสดงที่ปุ่ม 3 ปุ่มที่เหลือ โดยข้ามปุ่มที่เป็นคำตอบ
        for (int i = 0; i < 4; i++) {
            if (i == randomButton) { // ถ้า i คือ index ของปุ่มคำตอบ ให้ข้ามไป
                continue;
            }
            mButtonlist[i].setText(mItemList.get(i).word);
        }
    }

    @Override
    public void onClick(View view) {
        Button b =findViewById(view.getId());
        String buttontext = b.getText().toString();
        if(buttontext.equalsIgnoreCase(mAnswerword)){
            Toast.makeText(Game.this,"correct",Toast.LENGTH_SHORT).show();
            score++;
        }else{
            Toast.makeText(Game.this,"wrong",Toast.LENGTH_SHORT).show();
        }
        newQuiz();
    }


}