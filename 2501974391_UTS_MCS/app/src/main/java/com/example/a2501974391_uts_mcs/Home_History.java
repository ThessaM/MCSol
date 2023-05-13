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

import com.example.a2501974391_uts_mcs.Adaptor.HistoryAdaptor;
import com.example.a2501974391_uts_mcs.Database.GlobalData;
import com.example.a2501974391_uts_mcs.Database.MainDatabase;

public class Home_History extends Fragment {


    public Home_History() {
        // Required empty public constructor
    }
    
    MainDatabase db;
    RecyclerView historyReView;
    HistoryAdaptor historyAdaptor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home__history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        db = MainDatabase.getDb(view.getContext());
        Integer curUserId = GlobalData.getData().currentUserId; 
        
        historyAdaptor = new HistoryAdaptor(view.getContext());
        historyAdaptor.setUserHistoriesAndTickets(db.getHistoriesUserById(curUserId));
        
        historyReView = view.findViewById(R.id.fragment_history_ReView_History);
        historyReView.setAdapter(historyAdaptor);
        historyReView.setHasFixedSize(true);
        historyReView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }
}