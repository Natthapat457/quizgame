package th.ac.su.cp.quizgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import th.ac.su.cp.quizgame.model.WordItem;

public class wordlistactivity extends AppCompatActivity {
     static public WordItem [] items={
            new WordItem(R.drawable.cat,"CAT"),
            new WordItem(R.drawable.dog,"DOG"),
            new WordItem(R.drawable.pig,"PIG"),
            new WordItem(R.drawable.rabbit,"RABBIT"),
            new WordItem(R.drawable.tiger,"TIGER"),
            new WordItem(R.drawable.owl,"OWL"),
             new WordItem(R.drawable.dolphin,"Dolphin"),
             new WordItem(R.drawable.koala,"KOALA"),
             new WordItem(R.drawable.penguin,"PENGUIN"),

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordlistactivity);
        List<WordItem> wordlist = Arrays.asList(items);
        MyAdapter adapter = new MyAdapter(wordlistactivity.this,wordlist);
        LinearLayoutManager layout = new LinearLayoutManager(wordlistactivity.this);
        RecyclerView rv = findViewById(R.id.word_list_recycler_view);
        rv.setLayoutManager(layout);
        rv.setAdapter(adapter);



    }

}
class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    final Context mcontext ;
    final List <WordItem> mwordlist ;

    //WordItem item,item2;


    public MyAdapter(Context context, List<WordItem> wordlist){
        this.mcontext =context;
        mwordlist = wordlist;
        //item = new WordItem(R.drawable.cat,"CAT");
        //item2 = new WordItem(R.drawable.dog,"DOG");

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_word, parent, false);
        MyViewHolder vh1 = new MyViewHolder(mcontext,v);
        return vh1;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
          /* if(position ==0){
                holder.imageview.setImageResource(item.imageResId);
                holder.wordtextview.setText(item.word);}
            else if(position ==1){
                holder.imageview.setImageResource(item2.imageResId);
                holder.wordtextview.setText(item2.word);}
            }*/
        holder.imageview.setImageResource(mwordlist.get(position).imageResId);
        holder.wordtextview.setText(mwordlist.get(position).word);
        holder.item = mwordlist.get(position);


    }



    @Override
    public int getItemCount() {
        System.out.println("-----------"+mwordlist.size());
        return  mwordlist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        View rootview ;
        ImageView imageview;
        TextView wordtextview;
        WordItem item;

        public MyViewHolder(final Context context ,@NonNull View itemView) {

            super(itemView);
            rootview = itemView;
            imageview = itemView.findViewById(R.id.imageview);
            wordtextview = itemView.findViewById(R.id.wordtextview);
            rootview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*Toast.makeText(mcontext,item.word,Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                    dialog.setTitle("My Dialog");
                    dialog.setMessage(item.word);
                    dialog.setPositiveButton("OK",null);
                    dialog.show();*/
                    Intent intent = new Intent(context,Word_detail_activity.class);
                    //intent.putExtra("word",item.word);
                    //intent.putExtra("image",item.imageResId);
                    String itemjson = new Gson().toJson(item);
                    intent.putExtra("item",itemjson);
                    context.startActivity(intent);
                }
            });
        }
    }

}