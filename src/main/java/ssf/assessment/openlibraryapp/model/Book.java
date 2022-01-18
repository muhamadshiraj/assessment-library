package ssf.assessment.openlibraryapp.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Book {

    private String title;
    private String worksId;
    public String description;
    public String excerpt;
    private boolean cached;

    public Book() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWorksId() {
        return worksId;
    }

    public void setWorksId(String worksId) {
        this.worksId = worksId;
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

    public boolean isCached() {
        return cached;
    }

    public void setCached(boolean cached) {
        this.cached = cached;
    }

    public static Book create(JsonObject o) {
        final Book w = new Book();
        w.setWorksId(o.getString("key").replace("works", "book"));
        w.setTitle(o.getString("title"));
        return w;
    }

    public static Book create(String jsonString) {
        try (InputStream is = new ByteArrayInputStream(jsonString.getBytes())) {
            final JsonReader reader = Json.createReader(is);
            return create(reader.readObject());
        } catch (Exception ex) {
        }
    
        
        // Need to handle error
        return new Book();
    }
    public static Book getDetails(JsonObject o) {
        final Book b = new Book();
        if (o.containsKey("key")) {
            b.setWorksId(o.getString("key").replace("/works/", ""));
        }
        if (o.containsKey("title")) {
            try {
                b.setTitle(o.getString("title"));
            } catch (Exception e) {
                b.setTitle("Not available.");
            }
        }
        if (o.containsKey("description")) {
            try {
                b.setDescription(o.getString("description"));
            } catch (Exception e) {
                b.setDescription("Not available.");
            }
        }else{
            b.setDescription("Not available.");
        }
        if (o.containsKey("excerpt")) {
            try {
                b.setExcerpt(o.getString("excerpt"));
            } catch (Exception e) {
                b.setExcerpt("Not available.");
            }
        }else{
            b.setExcerpt("Not available.");
        }
        return b;
    }

    @Override
    public String toString() {
        return "key: %s, title: %s".formatted(worksId, title);
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("key", worksId)
                .add("title", title)
                .build();
    }

}
