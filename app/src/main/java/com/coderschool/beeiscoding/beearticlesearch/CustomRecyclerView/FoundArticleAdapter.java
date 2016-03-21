package com.coderschool.beeiscoding.beearticlesearch.CustomRecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coderschool.beeiscoding.beearticlesearch.FilterResult.Article;
import com.coderschool.beeiscoding.beearticlesearch.GlobalVariable;
import com.coderschool.beeiscoding.beearticlesearch.R;
import com.coderschool.beeiscoding.beearticlesearch.ShowTopNewsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by beeiscoding on 20/03/2016.
 */
public class FoundArticleAdapter extends RecyclerView.Adapter<FoundArticleAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Article> articles;

    public FoundArticleAdapter(Context context, ArrayList<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.found_article, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Article article = articles.get(position);
        holder.title.setText(article.getTitle());
        int imageSquareLenght = GlobalVariable.getImageWidth((Activity) context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(imageSquareLenght, imageSquareLenght);
        holder.image.setLayoutParams(params);
        Glide.with(context).load(article.getImageURL()).placeholder(R.drawable.placeholderpicasso).into(holder.image);

        if (!article.getSection().equals("") && article.getSection() != null) {
            holder.section.setText(article.getSection());
        } else
            holder.section.setVisibility(View.GONE);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ShowTopNewsActivity.class);
                Bundle b = new Bundle();
                b.putString(GlobalVariable.KEY_NEWS_TITLE, article.getTitle());
                b.putString(GlobalVariable.KEY_NEWS_SECTION, article.getSection());
                if (article.getAuthor() != null && !article.getAuthor().isEmpty())
                    b.putString(GlobalVariable.KEY_NEWS_OTHER, article.getTime() + " | " + article.getAuthor());
                else
                    b.putString(GlobalVariable.KEY_NEWS_OTHER, article.getTime());
                b.putString(GlobalVariable.KEY_NEWS_IAMGE_URL, article.getImageURL());
                b.putString(GlobalVariable.KEY_NEWS_WEBVIEW_URL,article.getWebViewURL());
                i.putExtra(GlobalVariable.KEY_NEWS_BUNDLE, b);

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title, section;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imageView_foundArticle);
            title = (TextView) itemView.findViewById(R.id.textView_foundArticle_title);
            section = (TextView) itemView.findViewById(R.id.textView_foundArticle_section);
        }
    }
}
