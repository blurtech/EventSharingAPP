package tech.blur.eventhub.features.event.assigned.domain;

import tech.blur.eventhub.features.core.events.model.Event;
import tech.blur.eventhub.features.event.assigned.data.AssignedRepository;
import tech.blur.eventhub.features.event.assigned.data.localstorage.AssignedLocalRepository;
import tech.blur.eventhub.features.event.my.data.MyEventsRepository;
import tech.blur.eventhub.features.event.my.data.localstorage.MyEventsLocalRepository;
import tech.blur.eventhub.network.Carry;

import java.util.List;

public class AssignInteractorImpl implements AssignInteractor {

    private final AssignedRepository asEventsRepository;
    private final AssignedLocalRepository asEventsLocalRepository;

    public AssignInteractorImpl(AssignedRepository asEventsRepository, AssignedLocalRepository asEventsLocalRepository) {
        this.asEventsRepository = asEventsRepository;
        this.asEventsLocalRepository = asEventsLocalRepository;
    }


    @Override
    public void getMyEvents(String userHost, Carry<List<Event>> carry) {
        asEventsRepository.getAssignedEventsList(userHost,carry);
    }

    @Override
    public String getUserHost() {
        return asEventsLocalRepository.getUserHost();
    }

}
