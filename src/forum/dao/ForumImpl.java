package forum.dao;

import forum.model.Post;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Arrays;

public class ForumImpl implements Forum {
    private Post[] posts;
    private int size;
    private final ComparePosts comparator = new ComparePosts();

    public ForumImpl() {
        posts=new Post[0];
        size = 0;
    }

    @Override
    public boolean addPost(Post post) {
        if (post == null || getPostById(post.getPostId()) != null) {
            return false;
        }

        int index = Arrays.binarySearch(posts, post, comparator);
        if (index < 0) {
            index = -index - 1;
        }

        Post[] postsNew = new Post[size + 1];
        System.arraycopy(posts, 0, postsNew, 0, index);
        postsNew[index] = post;
        System.arraycopy(posts, index, postsNew, index + 1, size - index);
        posts = postsNew;
        size++;
        return true;
    }

    private int indexPostById(int postId) {
        for (int i = 0; i < size; i++) {
            if (posts[i].getPostId() == postId) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean removePost(int postId) {
        int index = indexPostById(postId);
        if (index < 0) {
            return false;
        }
        Post[] postsNew = new Post[size - 1];
        System.arraycopy(posts, 0, postsNew, 0, index);
        System.arraycopy(posts, index + 1, postsNew, index, size - index - 1);
        posts = postsNew;
        size--;
        return true;
    }

    @Override
    public boolean updatePost(int postId, String content) {
        int index = indexPostById(postId);
        if (index < 0) {
            return false;
        }
        posts[index].setContent(content);
        return true;
    }

    @Override
    public Post getPostById(int postId) {
        int index = indexPostById(postId);
        return index >= 0 ? posts[index] : null;
    }

    @Override
    public Post[] getPostByAuthor(String author) {
        return getPostByAuthor(author, LocalDate.MIN, LocalDate.MAX);
    }

    @Override
    public Post[] getPostByAuthor(String author, LocalDate dateFrom, LocalDate dateTo) {
        Post post = new Post(0, "", author, "");
        post.setDate(dateFrom.atStartOfDay());
        int i1 = Arrays.binarySearch(posts, post, comparator);
        if (i1 < 0) {
            i1 = -i1 - 1;
        }
        post.setDate(dateTo.atStartOfDay());
        int i2 = Arrays.binarySearch(posts, post, comparator);
        if (i2 < 0) {
            i2 = -i2 - 1;
        }
        return Arrays.copyOfRange(posts, i1, i2);
    }

    @Override
    public int size() {
        return size;
    }

//    public void log(){
//        for (int i = 0; i < posts.length; i++) {
//            System.out.println(posts[i]);
//        }
//    }
}
