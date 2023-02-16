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
        return Optional.of(post)
                .filter(p -> p.getId() == 0)
                .map(this::addNewPost)
                .orElseGet(() -> {
                    repository.put(post.getId(), post);
                    return post;
                });
    }

    public void removeById(long id) {
        Optional.ofNullable(repository.remove(id))
                .map(post -> {
                    counterPosts.decrementAndGet();
                    return post;
                })
                .orElseThrow(NotFoundException::new);
    }

    private Post addNewPost(Post post){
        post.setId(counterPosts.getAndIncrement());
        repository.put(post.getId(), post);
        return post;
    }
}
