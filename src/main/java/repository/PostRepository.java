package repository;

import model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

// Stub
public class PostRepository {

    private static final List<Post> repository = Collections.synchronizedList(new ArrayList<>());
    public List<Post> all() {
        return repository;
    }

    public Optional<Post> getById(long id)
    {
        return Optional.empty();
    }

    public Post save(Post post) {
        return post;
    }

    public void removeById(long id) {
    }
}
