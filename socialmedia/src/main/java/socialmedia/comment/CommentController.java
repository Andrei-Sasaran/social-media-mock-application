package socialmedia.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import socialmedia.post.Post;
import socialmedia.post.PostRepository;
import socialmedia.user.User;
import socialmedia.user.UserRepository;

import java.util.List;

@RestController
@RequestMapping("api/comments")
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentController(CommentService commentService, CommentMapper commentMapper, UserRepository userRepository, PostRepository postRepository) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/post/{postId}")
    public List<Comment> getAllCommentsForPost(@PathVariable Long postId) {
        return commentService.getAllCommentsForPost(postId);
    }

    @PostMapping
    public CommentResponseDTO createComment(@RequestBody CommentRequestDTO commentRequestDTO) {
        User user = userRepository.findById(commentRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Post post = postRepository.findById(commentRequestDTO.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment newComment = new Comment();
        newComment.setUser(user);
        newComment.setPost(post);
        newComment.setContent(commentRequestDTO.getContent());

        Comment savedComment = commentService.createComment(newComment);
        return commentMapper.mapToCommentResponseDTO(savedComment);
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody Comment comment) {return commentService.updateComment(id, comment);}

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {commentService.deleteComment(id);}
}
