package socialmedia.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import socialmedia.user.User;
import socialmedia.user.UserRepository;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postMapper = postMapper;
    }

    public List<PostResponseDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return postMapper.toPostResponseDTO(posts);
    }

    public Post getPostById(Long id){
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }

    public PostResponseDTO getPostByIdDTO(Long id){
        Post post = getPostById(id);
        return postMapper.mapToPostResponseDTO(post);
    }

    public List<PostResponseDTO> getAllPublishedPostsForUser(Long userId) {
        return postMapper.toPostResponseDTO(postRepository.findByUserIdAndStatus(userId, Status.PUBLISHED));
    }

    public List<PostResponseDTO> searchPosts(String keyword) {
        List<Post> posts = postRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword);
        return postMapper.toPostResponseDTO(posts);
    }

    public List<PostResponseDTO> getAllPendingPostsForUser(Long userId) {
        return postMapper.toPostResponseDTO(postRepository.findByUserIdAndStatus(userId, Status.PENDING));
    }

    public Post createPost(PostRequestDTO postRequestDTO) {
        User user = userRepository.findById(postRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post();
        post.setTitle(postRequestDTO.getTitle());
        post.setContent(postRequestDTO.getContent());
        post.setStatus(postRequestDTO.getStatus());  // sau post.setStatus(Status.PENDING)
        post.setUser(user);

        return postRepository.save(post);
    }

    public Post updatePost(Long id, Post post) {
        Post oldPost = getPostById(id);
        oldPost.setTitle(post.getTitle());
        oldPost.setContent(post.getContent());
        oldPost.setStatus(post.getStatus());
        return postRepository.save(oldPost);
    }

    public Post approvePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id " + postId));

        post.setStatus(Status.PUBLISHED);
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
