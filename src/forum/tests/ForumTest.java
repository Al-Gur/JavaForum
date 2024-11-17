package forum.tests;

import forum.dao.Forum;
import forum.dao.ForumImpl;
import forum.model.Post;

import static org.junit.jupiter.api.Assertions.*;

class ForumTest {
    private Forum forum;
    private final Post[] posts={
            new Post(1, "", "qw", ""),
            new Post(2, "", "as", ""),
            new Post(3, "", "zx", ""),
    };
    private final int size0=3;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        forum=new ForumImpl();
        for (int i = 0; i < size0; i++) {
            forum.addPost(posts[i]);
        }
    }

    @org.junit.jupiter.api.Test
    void addPost() {
    }

    @org.junit.jupiter.api.Test
    void removePost() {
    }

    @org.junit.jupiter.api.Test
    void updatePost() {
    }

    @org.junit.jupiter.api.Test
    void getPostById() {
    }

    @org.junit.jupiter.api.Test
    void getPostByAuthor() {
    }

    @org.junit.jupiter.api.Test
    void testGetPostByAuthor() {
    }

    @org.junit.jupiter.api.Test
    void size() {
        assertEquals(forum.size(), size0);
    }
}