package repository;

import model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

// Stub
public class PostRepository {

    private static final ConcurrentHashMap<Long, String> repository = new ConcurrentHashMap<>();

    public List<Post> all() {
        List<Post> result = new ArrayList<>();
        repository.forEach((k, v) -> result.add(new Post(k,v)));
        return result;
    }

    public Optional<Post> getById(long id) {
        Optional<Long> keyId = repository.keySet()
                .stream()
                .filter((key) -> key == id)
                .findFirst();
        return keyId.map(aLong -> new Post(aLong, repository.get(aLong)));
    }

    public Post save(Post post) {
        repository.put(post.getId(), post.getContent());
        return post;
    }

    public void removeById(long id) {
        repository.entrySet()
                .removeIf(entry -> entry.getKey() == id);
    }
}
