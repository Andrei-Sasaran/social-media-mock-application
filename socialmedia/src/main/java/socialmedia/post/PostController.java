package socialmedia.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    @Autowired
    public PostController(PostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }

    @GetMapping
    public List<PostResponseDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public PostResponseDTO getPostByIdDTO(@PathVariable Long id) {
        return postService.getPostByIdDTO(id);
    }

    @GetMapping("/user/{user_id}/published")
    public List<PostResponseDTO> getAllPublishedPostsForUser(@PathVariable Long user_id) {
        return postService.getAllPublishedPostsForUser(user_id);
    }

    @GetMapping("/search")
    public List<PostResponseDTO> searchPosts(@RequestParam String keyword) {
        return postService.searchPosts(keyword);
    }

    @GetMapping("/user/{user_id}/pending")
    public List<PostResponseDTO> getAllPendingPostsForUser(@PathVariable Long user_id) {
        return postService.getAllPendingPostsForUser(user_id);
    }

    @PostMapping()
    public Post createPost(@RequestBody PostRequestDTO requestDTO) {
        return postService.createPost(requestDTO);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post post) {
        return postService.updatePost(id, post);
    }

    @PutMapping("/{id}/approve")
    public PostResponseDTO approvePost(@PathVariable Long id) {
        Post approvedPost = postService.approvePost(id);
        return postMapper.mapToPostResponseDTO(approvedPost);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
