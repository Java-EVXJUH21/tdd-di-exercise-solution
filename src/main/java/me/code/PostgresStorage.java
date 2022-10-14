package me.code;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class PostgresStorage implements DataStorage {

    private Connection connection;

    public PostgresStorage(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Profile> find(UUID id) {
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM profiles WHERE id = " + id);
        while (rs.next()) {
            return rs.get
            System.out.print("Column 1 returned ");
            System.out.println(rs.getString(1));
        }
        rs.close();
        st.close();


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
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("DELETE FROM profiles WHERE id = " + profile.getUniqueId());
    }

    @Override
    public void replace(UUID id, Profile profile) {

    }
}
