package forum.dao;

import forum.model.Post;

import java.util.Comparator;

public class ComparePosts implements Comparator<Post> {
    @Override
    public int compare(Post o1, Post o2) {
        int compareByAuthor = o1.getAuthor().compareTo(o2.getAuthor());
        return compareByAuthor != 0? compareByAuthor: o1.getDate().compareTo(o2.getDate());
    }
}
