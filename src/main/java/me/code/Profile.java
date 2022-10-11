package me.code;

import lombok.Getter;
import lombok.Setter;
import org.bson.Document;

import java.util.UUID;

@Getter
@Setter
public class Profile {

    private UUID uniqueId;
    private String username;

    /*
    profile info...
     */

    public Profile(UUID uniqueId, Document document) {
        this.uniqueId = uniqueId;
        this.username = document.getString("username");
        /*
        load other info...
         */
    }

    public Profile(UUID id, String username) {
        this.uniqueId = id;
        this.username = username;
        /*
        init other info...
         */
    }

    /*
    other logic...
     */

    Document toDocument() {
        var document = new Document("_id", uniqueId);

        document.append("username", username);
        /*
        append other info...
         */

        return document;

    }

    @Override
    public String toString() {
        return "Profile{" +
                "uniqueId=" + uniqueId +
                ", username='" + username + '\'' +
                '}';
    }
}
