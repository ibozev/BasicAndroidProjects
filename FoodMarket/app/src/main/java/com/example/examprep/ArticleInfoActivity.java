package com.example.examprep;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ArticleInfoActivity extends AppCompatActivity {

    private int position = 0;
    private List<Article> articles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_info);

        position = getIntent().getExtras().getInt("position");
        ArticleListAdapter adapter = new ArticleListAdapter(this);
        articles = adapter.getArticleList();

        ImageView ivImage = (ImageView) findViewById(R.id.imageView2);
        TextView tvName = (TextView) findViewById(R.id.textView3);
        TextView tvPrice = (TextView) findViewById(R.id.textView4);
        Button btnCheckout = (Button) findViewById(R.id.btnCheckout);
        Button btnBack = (Button) findViewById(R.id.btnBack);
        final EditText quantity = (EditText) findViewById(R.id.editText);

        int imageId = this.getResources().getIdentifier(articles.get(position).getImageName(),
                "drawable", this.getPackageName());

        ivImage.setImageResource(imageId);
        tvName.setText("Name: " + articles.get(position).getName());
        tvPrice.setText("Price: " + (articles.get(position).getPrice()) + "lv.");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener() {

            AlertDialog.Builder builder = new AlertDialog.Builder(ArticleInfoActivity.this);

            @Override
            public void onClick(View v) {
                double price = 0.0;

                try {
                    price = articles.get(position).getPrice() * Double.parseDouble(quantity.getText().toString());
                    alertDialog("Your checkout is: " + price + "lv.");
                } catch (Exception exc) {
                    alertDialog("Incorrect input!");
                }
            }

            private void alertDialog(String msg) {
                builder.setTitle("CHECKOUT!");
                builder.setCancelable(false);
                builder.setMessage(msg);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}
