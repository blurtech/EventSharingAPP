package com.syberianguys.srggrch.eventsgaring.features.core.events;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.syberianguys.srggrch.eventsgaring.R;
import com.syberianguys.srggrch.eventsgaring.features.core.events.model.Event;

import java.util.ArrayList;
import java.util.List;

public class AdapterEvent extends RecyclerView.Adapter<AdapterEvent.EventHolder> {

    private final List<Event> events = new ArrayList<>();
    private final LayoutInflater inflater;
    SelectEventListener selectEventListener;


    public AdapterEvent (Context context, SelectEventListener selectEventListener){
        inflater = LayoutInflater.from(context);
        this.selectEventListener = selectEventListener;
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.item_event, parent, false);
        return new EventHolder(itemView, selectEventListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        holder.bind(events.get(position));

    }

    public void setEvents(List<Event> eventList){
        events.clear();
        events.addAll(eventList);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return events.size();

    }

    public class EventHolder extends RecyclerView.ViewHolder {
        private final TextView eventName;
        private final TextView eventHost;
        private final TextView shortDesription;
        private final TextView eventDate;
        private final Button eventMore;
        private final SelectEventListener selectEventListener;

        EventHolder(View itemView, SelectEventListener selectEventListener) {
            super(itemView);
            this.selectEventListener = selectEventListener;
            eventName = itemView.findViewById(R.id.item_element_textView_eventName);
            eventDate = itemView.findViewById(R.id.item_element_textView_eventDate);
            eventHost = itemView.findViewById(R.id.item_element_textView_eventHost);
            shortDesription = itemView.findViewById(R.id.item_element_textView_shortDescription);
            eventMore = itemView.findViewById(R.id.item_element_eventMore);
        }
        void bind(final Event event){
            eventName.setText(event.getName());
            shortDesription.setText(event.getDescription());
            eventHost.setText(event.getHost());
            eventDate.setText(event.getStart());

            eventMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectEventListener.onEventSelected(event);
                }
            });
        }

    }

    public interface SelectEventListener{
        void onEventSelected(Event event);
    }
}
