package com.example.a2501974391_uts_mcs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a2501974391_uts_mcs.Adaptor.ArticleAdaptor;
import com.example.a2501974391_uts_mcs.Database.MainDatabase;
import com.example.a2501974391_uts_mcs.Model.Ticket;

import java.util.Vector;

public class Home_Home extends Fragment {

    public Home_Home() {
        // Required empty public constructor
    }

    MainDatabase db;
    View itemScheduleLayoutView;
    TextView itemSchedule_name, itemSchedule_venue, itemSchedule_dateTime;
    Button itemSchedule_buyTicket;
    RecyclerView articleReView;
    ArticleAdaptor articleAdaptor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home__home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = MainDatabase.getDb(view.getContext());

        itemScheduleLayoutView = view.findViewById(R.id.fragment_home_item_schedule);
        itemSchedule_name = itemScheduleLayoutView.findViewById(R.id.item_schedule_text_name);
        itemSchedule_venue = itemScheduleLayoutView.findViewById(R.id.item_schedule_text_venue);
        itemSchedule_dateTime = itemScheduleLayoutView.findViewById(R.id.item_schedule_text_dateTime);;
        itemSchedule_buyTicket = itemScheduleLayoutView.findViewById(R.id.item_schedule_button_buyTicket);

        itemSchedule_buyTicket.setVisibility(view.GONE);

        Vector<Ticket> schedules = db.getTickets();
        Ticket firstSchedule = schedules.firstElement();

        itemSchedule_name.setText(firstSchedule.getName());
        itemSchedule_venue.setText(String.format(getString(R.string.venue), firstSchedule.getVenue()));
        itemSchedule_dateTime.setText(String.format(getString(R.string.date_time), firstSchedule.getDate(), firstSchedule.getTime()));

        articleAdaptor = new ArticleAdaptor(view.getContext());
        articleAdaptor.setArticles(db.getArticles());

        articleReView = view.findViewById(R.id.fragment_home_ReView_article);
        articleReView.setAdapter(articleAdaptor);
        articleReView.setHasFixedSize(true);
        articleReView.setLayoutManager(new LinearLayoutManager(view.getContext()));

    }
}