package com.example.a2501974391_uts_mcs.Adaptor;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2501974391_uts_mcs.Database.MainDatabase;
import com.example.a2501974391_uts_mcs.Model.History;
import com.example.a2501974391_uts_mcs.Model.Ticket;
import com.example.a2501974391_uts_mcs.Model.TicketType;
import com.example.a2501974391_uts_mcs.R;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Vector;

public class HistoryAdaptor extends RecyclerView.Adapter<HistoryAdaptor.ViewHolder> {

    Context context;
    Vector<History> userHistories;

    MainDatabase db;

    public HistoryAdaptor(Context context) {
        this.context = context;
    }

    public void setUserHistoriesAndTickets(Vector<History> userHistories) {
        this.userHistories = userHistories;
    }

    @NonNull
    @Override
    public HistoryAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewLists = LayoutInflater.from(context).inflate(R.layout.item_history, parent, false);
        return new ViewHolder(viewLists);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdaptor.ViewHolder holder, int position) {
        db = MainDatabase.getDb(context);
        History history = userHistories.get(holder.getAdapterPosition());
//        Ticket curTicket = tickets.get(history.getTicketId());
//        TicketType curTicketType = ticketTypes.get(curTicket.getTicketTypeId());

        Ticket curTicket = db.getTicketbyId(history.getTicketId());
        TicketType curTicketType = db.getTicketTypebyId(history.getTicketTypeId());

        Resources resources = context.getResources();
        NumberFormat priceFormat = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));

        holder.historyBuyDate.setText(history.getDate());
        holder.historyName.setText(curTicket.getName());
        holder.historyVenue.setText(String.format(resources.getString(R.string.venue), curTicket.getVenue()));
        holder.historyDateTime.setText(String.format(resources.getString(R.string.date_time), curTicket.getDate(), curTicket.getTime()));
        holder.historyId.setText(String.format(resources.getString(R.string.order_number), history.getId()));
        holder.historyType.setText(String.format(resources.getString(R.string.ticket_type), curTicketType.getType()));
        holder.historyQty.setText(String.format(resources.getString(R.string.qty), history.getQty()));
        holder.historyTotalPrice.setText(priceFormat.format((double)history.getTotalPrice()));

    }

    @Override
    public int getItemCount() {
        return userHistories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView historyBuyDate, historyName, historyVenue, historyDateTime, historyId, historyType, historyQty, historyTotalPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            historyBuyDate = itemView.findViewById(R.id.item_history_text_date);
            historyName = itemView.findViewById(R.id.item_history_text_name);
            historyVenue = itemView.findViewById(R.id.item_history_text_venue);
            historyDateTime = itemView.findViewById(R.id.item_history_text_dateTime);
            historyId = itemView.findViewById(R.id.item_history_text_orderNumber);
            historyType = itemView.findViewById(R.id.item_history_text_ticketType);
            historyQty = itemView.findViewById(R.id.item_history_text_qty);
            historyTotalPrice = itemView.findViewById(R.id.item_history_text_totalPrice);
        }
    }
}
