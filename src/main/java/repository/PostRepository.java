package repository;

import exception.NotFoundException;
import model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {

    private static final ConcurrentHashMap<Long, String> repository = new ConcurrentHashMap<>();
    private final AtomicLong counterPosts = new AtomicLong();

    public List<Post> all() {
        List<Post> result = new ArrayList<>();
        repository.forEach((k, v) -> result.add(new Post(k,v)));
        return result;
    }

    public Optional<Post> getById(long id) {
        if (id <= counterPosts.get()){
            Optional<Long> keyId = repository.keySet()
                    .stream()
                    .filter((key) -> key == id)
                    .findFirst();
            return keyId.map(aLong -> new Post(aLong, repository.get(aLong)));
        } else throw new NotFoundException();
    }

    public Post save(Post post) {
        if(post.getId() == 0){
            post.setId(counterPosts.incrementAndGet());
            repository.put(post.getId(), post.getContent());
        } else {
            repository.put(post.getId(), post.getContent());
        }
        return post;
    }

    public void removeById(long id) {
        if(id <= counterPosts.get()){
            repository.entrySet()
                    .removeIf(entry -> entry.getKey() == id);
            counterPosts.decrementAndGet();
        } else throw new NotFoundException();
    }
}
