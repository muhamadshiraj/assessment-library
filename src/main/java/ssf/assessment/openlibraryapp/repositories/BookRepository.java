package ssf.assessment.openlibraryapp.repositories;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    @Autowired
    @Qualifier("book_cache")
    private RedisTemplate<String, String> template;

    public void save(String key, String value){
        template.opsForValue().set(key,  value,10, TimeUnit.MINUTES);
    }
    public Optional<String>get(String key){
            return Optional.ofNullable(template.opsForValue().get(key));
    }
    

}

