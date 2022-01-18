package ssf.assessment.openlibraryapp.services;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import ssf.assessment.openlibraryapp.OpenlibraryappApplication;
import ssf.assessment.openlibraryapp.model.Book;
import static ssf.assessment.openlibraryapp.util.Constants.*;

@Service
public class BookService {
    
    private final Logger logger = Logger.getLogger(OpenlibraryappApplication.class.getName());

    public List<Book> search(String searchTerm){
        

        String url = UriComponentsBuilder.fromUriString(URL_OPENLIBRARY)
        .queryParam("q", searchTerm.trim().replace(" ", "+"))
        .queryParam("limit", "20")
        .toUriString();

        logger.log(Level.INFO, ">>>URL output:" + url);

        final RequestEntity<Void> req = RequestEntity.get(url).build();
        final RestTemplate template = new RestTemplate();
        final ResponseEntity<String> resp = template.exchange(req, String.class);

        if (resp.getStatusCode() != HttpStatus.OK)
            throw new IllegalArgumentException(
                "ERROR: status code %s".formatted(resp.getStatusCode().toString())
            );
        
        final String jsonBody = resp.getBody();
        logger.log(Level.INFO, "payload: %s".formatted(jsonBody));
        
        
        try (InputStream is = new ByteArrayInputStream(jsonBody.getBytes())) {
            final JsonReader reader = Json.createReader(is);
            final JsonObject result = reader.readObject();
            final JsonArray readings = result.getJsonArray("docs");
            
            
        return readings.stream()
            .map(v -> (JsonObject)v)
            .map(Book::create)
            .collect(Collectors.toList());
        } catch (Exception ex) { 
        ex.printStackTrace();
    }
    
    return Collections.EMPTY_LIST;
}

    
}
