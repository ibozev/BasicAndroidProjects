package com.example.examprep;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class ArticleListAdapter extends BaseAdapter {
    private static final String TAG = "ArticleListAdapter";
    private Context context;
    private Database db;
    private List<Article> articleList;

    public ArticleListAdapter(Context context) {
        this.context = context;
        db = new Database(this.context);
        articleList = db.getAllArticles();
    }

    @Override
    public int getCount() {
        return articleList.size();
    }

    @Override
    public Object getItem(int position) {
        return articleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public List<Article> getArticleList() {
        return articleList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_view_layout, null);
        }

        ImageView ivImage = (ImageView) convertView.findViewById(R.id.imageView);
        TextView tvName = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.textView2);

        int imageId = context.getResources().getIdentifier(articleList.get(position).getImageName(),
                "drawable", context.getPackageName());

        tvName.setText("Name: " + articleList.get(position).getName());
        tvPrice.setText("Price: " + String.valueOf(articleList.get(position).getPrice())+ "lv.");
        ivImage.setImageResource(imageId);

        return convertView;
    }
}
