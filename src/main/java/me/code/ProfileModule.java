package me.code;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ProfileModule {

    /*

    Koden har en massa buggar och kompileringsfel. Poängen här är att visa
    principerna.

     */

    private final DataStorage storage;
    private final Map<UUID, Profile> cachedProfiles;

    public ProfileModule(DataStorage storage) {
        this.storage = storage;
        this.cachedProfiles = new ConcurrentHashMap<>();
    }

    public Profile loadProfile(UUID profileId, String updatedName) {
        var existing = storage.find(profileId);
        if (existing.isPresent()) {
            var profile = existing.get();
            profile.setUsername(updatedName);
            cachedProfiles.put(profileId, profile);
            return profile;
        }

        var profile = new Profile(profileId, updatedName);
        storage.save(profile);
        cachedProfiles.put(profileId, profile);
        return profile;
    }

    public void unloadProfile(UUID profileId) {
        var profile = cachedProfiles.remove(profileId);
        saveProfile(profile);
    }

    public void saveProfile(Profile profile) {
        storage.replace(profile.getUniqueId(), profile);
    }

    public Profile get(UUID profileId) {
        return cachedProfiles.get(profileId);
    }

}
