package com.example.a2501974391_uts_mcs.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2501974391_uts_mcs.ArticlePage;
import com.example.a2501974391_uts_mcs.Model.Article;
import com.example.a2501974391_uts_mcs.R;

import java.util.Vector;

public class ArticleAdaptor extends RecyclerView.Adapter<ArticleAdaptor.ViewHolder> {

    Context context;
    Vector<Article> articles;

    public ArticleAdaptor(Context context) {
        this.context = context;
    }

    public void setArticles(Vector<Article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public ArticleAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewLists = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false);
        return new ViewHolder(viewLists);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdaptor.ViewHolder holder, int position) {

        Article curArticle = articles.get(position);
        holder.articleImage.setImageResource(curArticle.getImage());
        holder.articleTitle.setText(curArticle.getTitle());

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView articleTitle;
        ImageView articleImage;

        CardView articleCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            articleTitle = itemView.findViewById(R.id.item_article_title);
            articleImage = itemView.findViewById(R.id.item_article_image);
            articleCard = itemView.findViewById(R.id.card_article);

            articleCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent toArticlePg = new Intent(v.getContext(), ArticlePage.class);
                    toArticlePg.putExtra("articleId", articles.get(getAdapterPosition()).getId());
                    context.startActivity(toArticlePg);
                }
            });
        }
    }
}
