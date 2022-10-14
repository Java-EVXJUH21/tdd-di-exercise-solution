package me.code;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class MongoDBStorage implements DataStorage {

    private MongoCollection<Document> collection;

    public MongoDBStorage(MongoDatabase db) {
        this.collection = db.getCollection("profiles");
    }

    @Override
    public Optional<Profile> find(UUID id) {
        return collection.find(id).getOne();
    }

    @Override
    public Collection<Profile> find(String username) {
        return collection.find(username);
    }

    @Override
    public void save(Profile profile) {
        collection.insertOne(profile.toDocument());
    }

    @Override
    public void delete(Profile profile) {
        collection.deleteOne(profile.getUniqueId())
    }

    @Override
    public void replace(UUID id, Profile profile) {

    }
}
