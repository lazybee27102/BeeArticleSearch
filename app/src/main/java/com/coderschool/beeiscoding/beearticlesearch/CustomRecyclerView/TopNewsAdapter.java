package com.coderschool.beeiscoding.beearticlesearch.CustomRecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coderschool.beeiscoding.beearticlesearch.GlobalVariable;
import com.coderschool.beeiscoding.beearticlesearch.R;
import com.coderschool.beeiscoding.beearticlesearch.ShowTopNewsActivity;
import com.coderschool.beeiscoding.beearticlesearch.TopNews;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by beeiscoding on 19/03/2016.
 */
public class TopNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int MAIN_ARTICLE = 1;
    private static final int NORMAL_ARTICLE = 2;
    private ArrayList<TopNews> normalTopNewses;
    private Context context;


    public TopNewsAdapter(Context context, ArrayList<TopNews> normalTopNewses) {
        this.context = context;
        this.normalTopNewses = normalTopNewses;


    }

    @Override
    public int getItemViewType(int position) {
        return (position == 0) ? MAIN_ARTICLE : NORMAL_ARTICLE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 1) {
            View view = LayoutInflater.from(context).inflate(R.layout.main_article, parent, false);
            MainArticleViewHolder viewHolder = new MainArticleViewHolder(view);
            return viewHolder;
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.normal_article, parent, false);
            NormalArticleViewHolder viewHolder = new NormalArticleViewHolder(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case NORMAL_ARTICLE: {
                if (normalTopNewses.size() != 0) {
                    final TopNews topNews = normalTopNewses.get(position);
                    NormalArticleViewHolder viewHolder = (NormalArticleViewHolder) holder;
                    viewHolder.title.setText(topNews.getTitle());
                    if (!topNews.getSubSection().equals(""))
                        viewHolder.type.setText(topNews.getSubSection());
                    else
                        viewHolder.type.setVisibility(View.GONE);
                    String otherText = topNews.getPubdate() + " | " + topNews.getAuthor();
                    viewHolder.time.setText(otherText);
                    Glide.with(context).load(topNews.getImageURL()).placeholder(R.drawable.placeholderpicasso).into(viewHolder.image);

                    viewHolder.title.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(context, ShowTopNewsActivity.class);
                            Bundle b = new Bundle();
                            b.putString(GlobalVariable.KEY_NEWS_TITLE, topNews.getTitle());
                            b.putString(GlobalVariable.KEY_NEWS_SECTION, topNews.getSubSection());
                            if (topNews.getAuthor() != null && !topNews.getAuthor().isEmpty())
                                b.putString(GlobalVariable.KEY_NEWS_OTHER, topNews.getPubdate() + " | " + topNews.getAuthor());
                            else
                                b.putString(GlobalVariable.KEY_NEWS_OTHER, topNews.getPubdate());
                            if (topNews.getImageHighQuality() != null && !topNews.getImageHighQuality().isEmpty())
                                b.putString(GlobalVariable.KEY_NEWS_IAMGE_URL, topNews.getImageHighQuality());
                            else
                                b.putString(GlobalVariable.KEY_NEWS_IAMGE_URL, topNews.getImageURL());

                            b.putString(GlobalVariable.KEY_NEWS_WEBVIEW_URL,topNews.getWebURL());
                            i.putExtra(GlobalVariable.KEY_NEWS_BUNDLE, b);
                            context.startActivity(i);
                        }
                    });
                }

            }
            break;
            case MAIN_ARTICLE: {
                MainArticleViewHolder viewHolder = (MainArticleViewHolder) holder;
                if (normalTopNewses.size() != 0) {
                    final TopNews main_topNews = normalTopNewses.get(0);
                    viewHolder.title.setText(main_topNews.getTitle());
                    Log.d("SUBSECTION", main_topNews.getSubSection());
                    if (!main_topNews.getSubSection().equals(""))
                        viewHolder.type.setText(main_topNews.getSubSection());
                    else
                        viewHolder.type.setVisibility(View.GONE);

                    String otherText = main_topNews.getPubdate() + " | " + main_topNews.getAuthor();
                    viewHolder.other.setText(otherText);

                    if (main_topNews.getImageHighQuality() != null && !main_topNews.getImageHighQuality().isEmpty())
                        Glide.with(context).load(main_topNews.getImageHighQuality()).placeholder(R.drawable.placeholderpicasso).into(viewHolder.image);
                    else
                        Glide.with(context).load(main_topNews.getImageURL()).placeholder(R.drawable.placeholderpicasso).into(viewHolder.image);


                    viewHolder.go.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(context, ShowTopNewsActivity.class);
                            Bundle b = new Bundle();
                            b.putString(GlobalVariable.KEY_NEWS_TITLE, main_topNews.getTitle());
                            b.putString(GlobalVariable.KEY_NEWS_SECTION, main_topNews.getSubSection());
                            if (main_topNews.getAuthor() != null && !main_topNews.getAuthor().isEmpty())
                                b.putString(GlobalVariable.KEY_NEWS_OTHER, main_topNews.getPubdate() + " | " + main_topNews.getAuthor());
                            else
                                b.putString(GlobalVariable.KEY_NEWS_OTHER, main_topNews.getPubdate());
                            if (main_topNews.getImageHighQuality() != null && !main_topNews.getImageHighQuality().isEmpty())
                                b.putString(GlobalVariable.KEY_NEWS_IAMGE_URL, main_topNews.getImageHighQuality());
                            else
                                b.putString(GlobalVariable.KEY_NEWS_IAMGE_URL, main_topNews.getImageURL());

                            b.putString(GlobalVariable.KEY_NEWS_WEBVIEW_URL,main_topNews.getWebURL());
                            i.putExtra(GlobalVariable.KEY_NEWS_BUNDLE, b);
                            context.startActivity(i);
                        }
                    });
                }

            }
            break;

        }
    }


    @Override
    public int getItemCount() {
        return normalTopNewses.size();
    }

    public class MainArticleViewHolder extends RecyclerView.ViewHolder {
        private TextView title, type, other;
        private ImageView image;
        private FloatingActionButton go;

        public MainArticleViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.textView_main_article_title);
            type = (TextView) itemView.findViewById(R.id.textView_main_article_type);
            other = (TextView) itemView.findViewById(R.id.textView_main_article_publishdate);
            image = (ImageView) itemView.findViewById(R.id.imageView_main_article);
            go = (FloatingActionButton) itemView.findViewById(R.id.button_main_article_go);
        }
    }

    public class NormalArticleViewHolder extends RecyclerView.ViewHolder {
        private TextView title, type, time;
        private ImageView image;
        //private RelativeLayout layout_wrapper;

        public NormalArticleViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imageView_normal_article);
            title = (TextView) itemView.findViewById(R.id.textView_normal_article_title);
            type = (TextView) itemView.findViewById(R.id.textView_normal_article_type);
            time = (TextView) itemView.findViewById(R.id.textView_normal_article_publishdate);
            //layout_wrapper = (RelativeLayout) itemView.findViewById(R.id.normal_article_layout_wrapper);

        }
    }
}
