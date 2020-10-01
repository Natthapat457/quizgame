package th.ac.su.cp.quizgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import th.ac.su.cp.quizgame.model.WordItem;

public class wordlistactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordlistactivity);
        MyAdapter adapter = new MyAdapter();
        LinearLayoutManager layout = new LinearLayoutManager(wordlistactivity.this);
        RecyclerView rv = findViewById(R.id.word_list_recycler_view);
        rv.setLayoutManager(layout);
        rv.setAdapter(adapter);



    }
     class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
        //WordItem item,item2;
        WordItem [] items={
                new WordItem(R.drawable.cat,"CAT"),
                new WordItem(R.drawable.dog,"DOG"),
                new WordItem(R.drawable.pig,"PIG"),
                new WordItem(R.drawable.rabbit,"RABBIT"),
                new WordItem(R.drawable.tiger,"TIGER")
        };

        public MyAdapter(){
            //item = new WordItem(R.drawable.cat,"CAT");
            //item2 = new WordItem(R.drawable.dog,"DOG");

        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_word, parent, false);
            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
          /* if(position ==0){
                holder.imageview.setImageResource(item.imageResId);
                holder.wordtextview.setText(item.word);}
            else if(position ==1){
                holder.imageview.setImageResource(item2.imageResId);
                holder.wordtextview.setText(item2.word);}
            }*/
            holder.imageview.setImageResource(items[position].imageResId);
            holder.wordtextview.setText(items[position].word);}



        @Override
        public int getItemCount() {
            return items.length;
        }

         class MyViewHolder extends RecyclerView.ViewHolder{

            ImageView imageview;
            TextView wordtextview;


            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                imageview = itemView.findViewById(R.id.imageview);
                wordtextview = itemView.findViewById(R.id.wordtextview);
            }
        }

    }
}