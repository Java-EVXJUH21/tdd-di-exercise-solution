package me.code;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ProfileModule {

    private final MongoDatabase mongoDatabase;
    private final MongoCollection<Document> profileCollection;
    private final Map<UUID, Profile> cachedProfiles;

    public ProfileModule(MongoDatabase db) {
        this.mongoDatabase = db;
        this.profileCollection = db.getCollection("profiles");
        this.cachedProfiles = new ConcurrentHashMap<>();
    }

    public Profile loadProfile(UUID profileId, String updatedName) {
        var iterable = profileCollection.find(Filters.eq("_id", profileId));
        for (var document : iterable) {
            var profile = new Profile(profileId, document);
            profile.setUsername(updatedName);
            cachedProfiles.put(profileId, profile);
            return profile;
        }

        var profile = new Profile(profileId, updatedName);
        profileCollection.insertOne(profile.toDocument());
        cachedProfiles.put(profileId, profile);
        return profile;
    }

    public void unloadProfile(UUID profileId) {
        var profile = cachedProfiles.remove(profileId);
        saveProfile(profile);
    }

    public void saveProfile(Profile profile) {
        profileCollection.replaceOne(Filters.eq("_id", profile.getUniqueId()), profile.toDocument());
    }

    public Profile get(UUID profileId) {
        return cachedProfiles.get(profileId);
    }

}
