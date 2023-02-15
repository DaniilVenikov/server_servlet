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

    private static final ConcurrentHashMap<Long, Post> repository = new ConcurrentHashMap<>();
    private final AtomicLong counterPosts = new AtomicLong();

    public List<Post> all() {
        return new ArrayList<>(repository.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(repository.get(id));
    }

    public Post save(Post post) {
        if(post.getId() == 0){
            post.setId(counterPosts.getAndIncrement());
            repository.put(post.getId(), post);
        } else {
            repository.put(post.getId(), post);
        }
        return post;
    }

    public void removeById(long id) {
        if(repository.containsKey(id)){
            repository.remove(id);
            counterPosts.decrementAndGet();
        } else throw new NotFoundException();
    }
}
