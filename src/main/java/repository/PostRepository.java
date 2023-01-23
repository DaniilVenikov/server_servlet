package repository;

import model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Stub
public class PostRepository {

    private static final List<Post> repository = Collections.synchronizedList(new ArrayList<>());

    public List<Post> all() {
        return repository;
    }

    public Optional<Post> getById(long id) {
        return repository.stream()
                .filter((post) -> post.getId() == id)
                .findFirst();
    }

    public Post save(Post post) {
        boolean flag = false;
        for (Post p : repository) {
            if (p.getId() == post.getId()) {
                p.setContent(post.getContent());
                flag = true;
                break;
            }
        }
        if(!flag){
            repository.add(post);
        }
        return post;
    }

    public void removeById(long id) {
        for (int i = 0; i < repository.size(); i++) {
            if(repository.get(i).getId() == id){
                repository.remove(i);
                break;
            }
        }
    }
}
