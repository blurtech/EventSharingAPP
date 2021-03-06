package tech.blur.eventhub.features.event.assigned.data;

import tech.blur.eventhub.features.core.events.model.Event;
import tech.blur.eventhub.network.Carry;

import java.util.List;

public class AssignedRepositoryImpl implements AssignedRepository {
    private final AssignedDataSource assignEventsDataSource;

    public AssignedRepositoryImpl(AssignedDataSource assignedDataSource) {
        this.assignEventsDataSource = assignedDataSource;
    }


    @Override
    public void getAssignedEventsList(String userHost, Carry<List<Event>> carry) {
        assignEventsDataSource.getAssignedEvents(userHost,carry);
    }
}
