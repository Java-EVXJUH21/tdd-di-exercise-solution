package me.code;

public class Main {

    public static void main(String[] args) {
        var profileModule = new ProfileModule(new MongoDBStorage());
        var profileModule2 = new ProfileModule(new PostgresStorage());
        var profileModule2 = new ProfileModule(new TestStorage());
    }
}
