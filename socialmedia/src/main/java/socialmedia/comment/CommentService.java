package socialmedia.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import socialmedia.post.Post;
import socialmedia.post.PostRepository;
import socialmedia.user.User;
import socialmedia.user.UserRepository;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public Comment createComment(Comment comment) {
        User user = userRepository.findById(comment.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        comment.setUser(user);

        Post post = postRepository.findById(comment.getPost().getId())
                .orElseThrow(() -> new RuntimeException("Post not found"));
        comment.setPost(post);

        System.out.println("Saving comment: " + comment.getContent());

        return commentRepository.save(comment);
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    public List<Comment> getAllCommentsForPost(Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    public Comment updateComment(Long id, Comment comment) {
        Comment oldComment = getCommentById(id);
        oldComment.setContent(comment.getContent());
        oldComment.setUser(comment.getUser());
        oldComment.setPost(comment.getPost());
        return commentRepository.save(oldComment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
