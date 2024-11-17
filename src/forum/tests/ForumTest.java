package forum.tests;

import forum.dao.Forum;
import forum.dao.ForumImpl;
import forum.model.Post;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ForumTest {
    private Forum forum;
    private Post[] posts;

    private final int size0 = 3;
    private LocalDate date1;
    private LocalDate date2;


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        forum = new ForumImpl();
        date1 = LocalDate.now();
        posts = new Post[]{
                new Post(1, "qwe", "qw", "tftf"),
                new Post(2, "xrxr", "as", "ijij"),
                new Post(3, "jnujub", "zx", ""),
                new Post(3, "njn", "er", "kmk"),
                new Post(4, "nmn", "dfhu", "ki"),
                new Post(5, "byb", "as", "km"),
        };
        posts[1].setDate(LocalDateTime.now().minusDays(2));
        date2 = LocalDate.now();  // if test is running at 24:00, date2 != date1 :)
        for (int i = 0; i < size0; i++) {
            forum.addPost(posts[i]);
        }
    }

    @org.junit.jupiter.api.Test
    void addPost() {
        assertFalse(forum.addPost(posts[3]));
        assertTrue(forum.addPost(posts[4]));
        assertEquals(forum.size(), 4);
        Post[] added = forum.getPostByAuthor(posts[4].getAuthor());
        assertEquals(added.length, 1);
        assertEquals(added[0], posts[4]);
    }

    @org.junit.jupiter.api.Test
    void removePost() {
        assertFalse(forum.removePost(4));
        assertTrue(forum.removePost(2));
        assertEquals(forum.size(), 2);
    }

    @org.junit.jupiter.api.Test
    void updatePost() {
        assertFalse(forum.updatePost(7, "ll"));
        assertTrue(forum.updatePost(1, "ll"));
        assertEquals(forum.getPostById(1).getContent(), "ll");
    }

    @org.junit.jupiter.api.Test
    void getPostById() {
        assertEquals(forum.getPostById(posts[2].getPostId()), posts[2]);
        assertNull(forum.getPostById(posts[5].getPostId()));
    }

    @org.junit.jupiter.api.Test
    void getPostByAuthor() {
        forum.addPost(posts[5]);
        Post[] posts = forum.getPostByAuthor("as");
        assertEquals(posts.length, 2);
    }

    @org.junit.jupiter.api.Test
    void testGetPostByAuthorDates() {
        forum.addPost(posts[5]);
//        ForumImpl f = (ForumImpl)forum;
//        f.log();
        Post[] posts = forum.getPostByAuthor("as", date1.minusDays(2), date2.plusDays(1));
        assertEquals(posts.length, 2);
        posts = forum.getPostByAuthor("as", date1, date2.plusDays(2));
        assertEquals(posts.length, 1);
    }

    @org.junit.jupiter.api.Test
    void size() {
        assertEquals(forum.size(), size0);
    }

    @org.junit.jupiter.api.Test
    void likesTest() {
        forum.getPostById(1).addLike();
        forum.getPostById(1).addLike();
        assertEquals(forum.getPostById(1).getLikes(), 2);
    }
}