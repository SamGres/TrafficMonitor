package si.samgres.api.services;

import org.springframework.stereotype.Service;
import si.samgres.api.managers.DatabaseManager;
import si.samgres.api.models.Post;

import java.util.List;

@Service
public class SamGresService {
    public static List getAllPosts() {
        return DatabaseManager.getAll(Post.class);
    }
}
