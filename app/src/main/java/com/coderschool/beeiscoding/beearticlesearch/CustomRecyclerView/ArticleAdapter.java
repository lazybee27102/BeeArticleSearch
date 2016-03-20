package com.coderschool.beeiscoding.beearticlesearch.CustomRecyclerView;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coderschool.beeiscoding.beearticlesearch.TopNews;
import com.coderschool.beeiscoding.beearticlesearch.R;

import java.util.ArrayList;

/**
 * Created by beeiscoding on 19/03/2016.
 */
public class ArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int MAIN_ARTICLE = 1;
    private static final int NORMAL_ARTICLE = 2;
    private TopNews main_topNews;
    private ArrayList<TopNews> normalTopNewses;
    private Context context;
    private LayoutInflater inflater;

    public ArticleAdapter(Context context, ArrayList<TopNews> normalTopNewses, TopNews main_topNews) {
        this.context = context;
        this.normalTopNewses = normalTopNewses;
        this.main_topNews = main_topNews;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return ( position == 0 ) ? MAIN_ARTICLE : NORMAL_ARTICLE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==1)
        {
            View view = inflater.inflate(R.layout.main_article,parent,false);
            MainArticleViewHolder viewHolder = new MainArticleViewHolder(view);
            return viewHolder;
        }else
        {
            View view = inflater.inflate(R.layout.normal_article,parent,false);
            NormalArticleViewHolder viewHolder = new NormalArticleViewHolder(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }



    @Override
    public int getItemCount() {
        return normalTopNewses.size()+1;
    }

    public class MainArticleViewHolder extends RecyclerView.ViewHolder {
        private TextView title,type,other;
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

    public class NormalArticleViewHolder extends RecyclerView.ViewHolder{
        private TextView title,type,time;
        private ImageView image;
        private RelativeLayout layout_wrapper;
        public NormalArticleViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.textView_normal_article_title);
            type = (TextView) itemView.findViewById(R.id.textView_normal_article_type);
            time = (TextView) itemView.findViewById(R.id.textView_normal_article_publishdate);
            layout_wrapper = (RelativeLayout) itemView.findViewById(R.id.normal_article_layout_wrapper);

        }
    }
}
