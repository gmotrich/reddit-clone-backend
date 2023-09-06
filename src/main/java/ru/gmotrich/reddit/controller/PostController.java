package ru.gmotrich.reddit.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gmotrich.reddit.dto.PostRequest;
import ru.gmotrich.reddit.dto.PostResponse;
import ru.gmotrich.reddit.service.PostService;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@Tag(
        name = "Посты",
        description = "PostController"
)
@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @Operation(
            summary = "Метод для создания поста"
    )
    @PostMapping("/")
    public ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest) {
        postService.save(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(
            summary = "Метод для получения всех постов"
    )
    @GetMapping("/")
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return status(HttpStatus.OK).body(postService.getAllPosts());
    }

    @Operation(
            summary = "Метод для получения поста"
    )
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        return status(HttpStatus.OK).body(postService.getPost(id));
    }

    @Operation(
            summary = "Метод для получения сообщения по субреддиту"
    )
    @GetMapping(params = "subredditId")
    public ResponseEntity<List<PostResponse>> getPostsBySubreddit(@RequestParam Long subredditId) {
        return status(HttpStatus.OK).body(postService.getPostsBySubreddit(subredditId));
    }

    @Operation(
            summary = "Метод для получения сообщения по имени пользователя"
    )
    @GetMapping(params = "username")
    public ResponseEntity<List<PostResponse>> getPostsByUsername(@RequestParam String username) {
        return status(HttpStatus.OK).body(postService.getPostsByUsername(username));
    }
}
