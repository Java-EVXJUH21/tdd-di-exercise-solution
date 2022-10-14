package me.code;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class TestStorage implements DataStorage {

    @Override
    public Optional<Profile> find(UUID id) {
        return Optional.empty();
    }

    @Override
    public Collection<Profile> find(String username) {
        return null;
    }

    @Override
    public void save(Profile profile) {

    }

    @Override
    public void delete(Profile profile) {

    }

    @Override
    public void replace(UUID id, Profile profile) {

    }
}
