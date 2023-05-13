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

import com.example.a2501974391_uts_mcs.Adaptor.TicketAdaptor;
import com.example.a2501974391_uts_mcs.Database.MainDatabase;
import com.example.a2501974391_uts_mcs.Model.Ticket;

import java.util.Vector;

public class Home_Schedule extends Fragment {


    public Home_Schedule() {
        // Required empty public constructor
    }

    MainDatabase db;
    RecyclerView ticketReView;
    TicketAdaptor ticketAdaptor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home__schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = MainDatabase.getDb(view.getContext());

        ticketAdaptor = new TicketAdaptor(view.getContext());
        ticketAdaptor.setTickets(db.getTickets(), db.getTicketTypes());

        ticketReView = view.findViewById(R.id.fragment_schedule_ReView_Schedule);
        ticketReView.setAdapter(ticketAdaptor);
        ticketReView.setHasFixedSize(true);
        ticketReView.setLayoutManager(new LinearLayoutManager(view.getContext()));

    }
}