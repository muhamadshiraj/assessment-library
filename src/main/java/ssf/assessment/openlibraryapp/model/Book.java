package ssf.assessment.openlibraryapp.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Book {


    private String title;
    private String key;
    public String description;
    public String excerpt;


    public Book(){

    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public static Book create(JsonObject o) {
        final Book w = new Book();
        w.setKey(o.getString("key").replace("works", "book"));
        w.setTitle(o.getString("title"));
        return w;
    }


    public static Book create(String jsonString) {
        try (InputStream is = new ByteArrayInputStream(jsonString.getBytes())) {
            final JsonReader reader = Json.createReader(is);
            return create(reader.readObject());
        } catch (Exception ex) { }

        // Need to handle error
        return new Book();
    }

    @Override
    public String toString() {
        return "key: %s, title: %s".formatted(key, title);
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("key", key)
            .add("title", title)
            .build();
    }
    
}
