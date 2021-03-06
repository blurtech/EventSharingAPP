package tech.blur.eventhub.features.event.list.data.localstorage;

import tech.blur.eventhub.features.core.events.model.User;

public class ListLocalRepositoryImpl implements ListLocalRepository {

    private final ListLocalDataSource dataSource;

    public ListLocalRepositoryImpl(ListLocalDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean isAuth() {
        return dataSource.isAuth();
    }

    @Override
    public User getUser() {
        return dataSource.getUser();
    }

    @Override
    public void remove() {
        dataSource.remove_prefernces();
    }


}
