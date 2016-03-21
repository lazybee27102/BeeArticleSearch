package com.coderschool.beeiscoding.beearticlesearch;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class ShowTopNewsActivity extends AppCompatActivity {
    private FloatingActionButton floatingActionButton_share;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView imageView;
    private TextView textView_title, textView_other, textView_content;

    String section,title,imageUrl,other,webURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_top_news);

        getData();
        registerWidget();
        handleEvent();

    }

    private void handleEvent() {
        floatingActionButton_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareIntent = new Intent(Intent.ACTION_SEND);

                shareIntent.setType("text/plain");

                shareIntent.putExtra(Intent.EXTRA_TEXT, webURL);

                startActivity(Intent.createChooser(shareIntent, "Share via "));
            }
        });


        if (section != null && !section.isEmpty())
            collapsingToolbarLayout.setTitle(section);
        textView_title.setText(title);
        textView_other.setText(other);
        String contentString = "\t\tLorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum." +"\n\n"+
                "\t\tIt is a long established fact that a reader will be distracted by the readable textView_content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, textView_content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)." +"\n\n"+
                "\t\tContrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32." +"\n\n"+
                "\t\tThere are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.\n\n";
        textView_content.setText(contentString);

        Typeface roboto_bold = Typeface.createFromAsset(this.getAssets(),"font/Roboto-Bold.ttf");
        Typeface roboto_regular = Typeface.createFromAsset(this.getAssets(),"font/Roboto-Regular.ttf");

        textView_title.setTypeface(roboto_bold);
        textView_content.setTypeface(roboto_regular);
        Glide.with(this).load(imageUrl).placeholder(R.drawable.placeholderpicasso).into(imageView);
    }

    private void registerWidget() {
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.showTopNews_collapsing_toolbar);
        floatingActionButton_share = (FloatingActionButton)findViewById(R.id.fab_share);
        imageView = (ImageView) findViewById(R.id.showTopNews_expandedImage);
        textView_title = (TextView) findViewById(R.id.textView_showTopNews_title);
        textView_other = (TextView) findViewById(R.id.textView_showTopNews_others);
        textView_content = (TextView) findViewById(R.id.textView_showTopNews_content);
    }

    public void getData() {
        Intent i = getIntent();
        Bundle b = i.getBundleExtra(GlobalVariable.KEY_NEWS_BUNDLE);
        title = b.getString(GlobalVariable.KEY_NEWS_TITLE);
        section = b.getString(GlobalVariable.KEY_NEWS_SECTION);
        imageUrl = b.getString(GlobalVariable.KEY_NEWS_IAMGE_URL);
        other = b.getString(GlobalVariable.KEY_NEWS_OTHER);
        webURL = b.getString(GlobalVariable.KEY_NEWS_WEBVIEW_URL);
    }


}
