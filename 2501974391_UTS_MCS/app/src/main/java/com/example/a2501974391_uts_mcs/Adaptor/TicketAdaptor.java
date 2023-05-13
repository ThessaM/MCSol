package com.example.a2501974391_uts_mcs.Adaptor;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2501974391_uts_mcs.Database.GlobalData;
import com.example.a2501974391_uts_mcs.Database.MainDatabase;
import com.example.a2501974391_uts_mcs.Model.Ticket;
import com.example.a2501974391_uts_mcs.Model.TicketType;
import com.example.a2501974391_uts_mcs.R;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

public class TicketAdaptor extends RecyclerView.Adapter<TicketAdaptor.ViewHolder> {

    Context context;
    Vector<Ticket> tickets;
    Vector<TicketType> ticketTypes;
    Ticket curTicket;
    TicketType curTicketType;
    Integer curQty, totalPrice;

    MainDatabase db;

    EditText qtyEdtx;
    Spinner typeSpinner;
    Button buyBtn, cancelBtn;
    TextView ticketName, ticketVenue, ticketDateTime, ticketTotalPrice;

    public TicketAdaptor(Context context) {
        this.context = context;
    }

    public void setTickets(Vector<Ticket> tickets, Vector<TicketType> ticketTypes) {
        this.tickets = tickets;
        this.ticketTypes = ticketTypes;
    }

    @NonNull
    @Override
    public TicketAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewLists = LayoutInflater.from(context).inflate(R.layout.item_schedule, parent, false);
        return new ViewHolder(viewLists);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketAdaptor.ViewHolder holder, int position) {
        curTicket = tickets.get(position);
        Resources resources = context.getResources();
        holder.scheduleName.setText(curTicket.getName());
        holder.scheduleVenue.setText(String.format(resources.getString(R.string.venue), curTicket.getVenue()));
        holder.scheduleDatetime.setText(String.format(resources.getString(R.string.date_time), curTicket.getDate(), curTicket.getTime()));
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView scheduleName, scheduleVenue, scheduleDatetime;
        Button buyTicketBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            scheduleName = itemView.findViewById(R.id.item_schedule_text_name);
            scheduleVenue = itemView.findViewById(R.id.item_schedule_text_venue);
            scheduleDatetime = itemView.findViewById(R.id.item_schedule_text_dateTime);
            buyTicketBtn = itemView.findViewById(R.id.item_schedule_button_buyTicket);

            buyTicketBtn.setOnClickListener(v -> showButTicketDialog(itemView.getContext()));

        }

        private void showButTicketDialog(Context context) {
            final Dialog qtyDialog = new Dialog(context);
            qtyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            qtyDialog.setCancelable(true);
            qtyDialog.setContentView(R.layout.dialog_buy_ticket);
            qtyDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            qtyDialog.show();

            ticketName = qtyDialog.findViewById(R.id.dialog_name);
            ticketVenue = qtyDialog.findViewById(R.id.dialog_venue);
            ticketDateTime = qtyDialog.findViewById(R.id.dialog_dateTime);
            ticketTotalPrice = qtyDialog.findViewById(R.id.dialog_Text_TotalPrice);
            typeSpinner = qtyDialog.findViewById(R.id.dialog_spinner);
            qtyEdtx = qtyDialog.findViewById(R.id.dialog_edtx_qty);
            buyBtn = qtyDialog.findViewById(R.id.dialog_button_buy);
            cancelBtn = qtyDialog.findViewById(R.id.dialog_button_cancel);

            db = MainDatabase.getDb(context);
            Resources resources = context.getResources();
            NumberFormat priceFormat = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
            curTicketType = ticketTypes.firstElement();
            curQty = 1;

            ticketName.setText(curTicket.getName());
            ticketVenue.setText(String.format(resources.getString(R.string.venue), curTicket.getVenue()));
            ticketDateTime.setText(String.format(resources.getString(R.string.date_time), curTicket.getDate(), curTicket.getTime()));
            ticketTotalPrice.setText(priceFormat.format((double)curTicketType.getPrice())); //set initial price to 1

            ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(context, R.array.dropDownOption, R.layout.spinner_layout);
            spinnerAdapter.setDropDownViewResource(R.layout.spinner_layout);
            typeSpinner.setAdapter(spinnerAdapter);
            typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    curTicketType = ticketTypes.get(position);

                    if(qtyEdtx.getText().toString().isEmpty()){
                        qtyEdtx.setText("1");
                        curQty = 1;
                    }
                    totalPrice = curQty*curTicketType.getPrice();
                    ticketTotalPrice.setText(priceFormat.format((double)totalPrice));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            qtyEdtx.addTextChangedListener(new TextWatcher(){

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    Integer ticketPrice = curTicketType.getPrice();
                    if(qtyEdtx.getText().toString().isEmpty()){
                        curQty = 0;
                        ticketTotalPrice.setText(priceFormat.format((double)0));
                    }else{
                        curQty = Integer.parseInt(qtyEdtx.getText().toString());
                        totalPrice = curQty * ticketPrice;
                        ticketTotalPrice.setText(priceFormat.format((double)totalPrice));
                    }
                }
            });


            buyBtn.setOnClickListener(v -> {
                if(isQtyFilled()){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String curDate = dateFormat.format(new Date());
                    Integer curUserId = GlobalData.getData().currentUserId;
                    db.insertHistory(curDate, curQty, totalPrice,curUserId, curTicket.getId(), curTicketType.getId());
                    Toast.makeText(context, "Purchase Success", Toast.LENGTH_SHORT).show();
                    qtyDialog.dismiss();
                }
            });
            cancelBtn.setOnClickListener(v -> qtyDialog.dismiss());

        }

        boolean isQtyFilled(){
            if(qtyEdtx.getText().toString().isEmpty()){
                Toast.makeText(context, "Quantity must be filled", Toast.LENGTH_SHORT).show();
                return false;
            }else{
                return true;
            }
        }


    }
}
