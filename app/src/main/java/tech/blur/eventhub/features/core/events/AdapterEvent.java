package tech.blur.eventhub.features.core.events;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import tech.blur.eventhub.R;
import tech.blur.eventhub.features.core.events.model.Event;
import tech.blur.eventhub.features.event.list.presentation.EventsListActivity;

import java.util.ArrayList;
import java.util.List;

import tech.blur.eventhub.features.core.events.model.Event;

public class AdapterEvent extends RecyclerView.Adapter<AdapterEvent.EventHolder> {

    private final List<Event> events = new ArrayList<>();
    private final LayoutInflater inflater;
    SelectEventListener selectEventListener;
    private final Context context;


    public AdapterEvent (Context context, SelectEventListener selectEventListener){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.selectEventListener = selectEventListener;
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(tech.blur.eventhub.R.layout.item_event, parent, false);
        return new EventHolder(itemView, selectEventListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        holder.bind(events.get(position));

    }

    public void setEvents(List<Event> eventList){
        events.clear();
        if(eventList != null) events.addAll(eventList);
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
        private final TextView counter;

        EventHolder(View itemView, SelectEventListener selectEventListener) {
            super(itemView);
            this.selectEventListener = selectEventListener;
            counter = itemView.findViewById(tech.blur.eventhub.R.id.event_counter);
            eventName = itemView.findViewById(tech.blur.eventhub.R.id.item_element_textView_eventName);
            eventDate = itemView.findViewById(tech.blur.eventhub.R.id.item_element_textView_eventDate);
            eventHost = itemView.findViewById(tech.blur.eventhub.R.id.item_element_textView_eventHost);
            shortDesription = itemView.findViewById(tech.blur.eventhub.R.id.item_element_textView_shortDescription);
            eventMore = itemView.findViewById(tech.blur.eventhub.R.id.item_element_eventMore);
        }
        void bind(final Event event){
            eventName.setText(event.getName());
            counter.setText(Integer.toString(event.getGuests().size()));
            shortDesription.setText(event.getDescription());
            eventHost.setText(event.getHost_name());
            if (event.getStart() != null)
            eventDate.setText(DateUtils.formatDateTime(context,
                    Long.parseLong(event.getStart()),
                    DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                            | DateUtils.FORMAT_SHOW_TIME));

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
