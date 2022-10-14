package me.code;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface DataStorage {

    Optional<Profile> find(UUID id);
    Collection<Profile> find(String username);

    void save(Profile profile);

    void delete(Profile profile);

    void replace(UUID id, Profile profile);
}
