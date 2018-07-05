package com.syberianguys.srggrch.eventsgaring.features.event.add.data;


import com.syberianguys.srggrch.eventsgaring.features.core.events.model.Event;
import com.syberianguys.srggrch.eventsgaring.network.Carry;

import java.util.List;

public interface AddEventRepository {
    void addEvents(Carry<List<Event>> carry );

    void addEvent(String id, Carry<Event> carry);

}