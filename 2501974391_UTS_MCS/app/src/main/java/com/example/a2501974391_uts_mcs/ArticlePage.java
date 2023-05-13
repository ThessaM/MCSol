package com.example.a2501974391_uts_mcs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a2501974391_uts_mcs.Database.MainDatabase;
import com.example.a2501974391_uts_mcs.Model.Article;

public class ArticlePage extends AppCompatActivity {

    MainDatabase db;
    Integer curArticleId;
    Article curArticle;

    TextView articleTitle, articleDate, articleDesc;
    ImageView articleImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_page);
        curArticleId = getIntent().getIntExtra("articleId",1);
        db = MainDatabase.getDb(this);
        curArticle = db.getArticlesbyId(curArticleId);

        getSupportActionBar().setLogo(AppCompatResources.getDrawable(this, R.drawable.mcsol_logo));
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        articleTitle = findViewById(R.id.article_title);
        articleDate = findViewById(R.id.article_publishedOn);
        articleDesc = findViewById(R.id.article_description);
        articleImage = findViewById(R.id.article_image);

        articleTitle.setText(curArticle.getTitle());
        articleDate.setText(String.format(getString(R.string.published_on), curArticle.getDate()));
        articleImage.setImageResource(curArticle.getImage());
        articleDesc.setText(curArticle.getDescription());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}