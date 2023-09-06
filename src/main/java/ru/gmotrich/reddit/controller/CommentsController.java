package ru.gmotrich.reddit.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gmotrich.reddit.dto.CommentsDto;
import ru.gmotrich.reddit.service.CommentService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Tag(
        name = "Комментарии к постам",
        description = "CommentsController"
)
@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentsController {
    private final CommentService commentService;

    @Operation(
            summary = "Метод для создания коментария"
    )
    @PostMapping("/")
    public ResponseEntity<Void> createComment(@RequestBody CommentsDto commentsDto) {
        commentService.save(commentsDto);
        return new ResponseEntity<>(CREATED);
    }

    @Operation(
            summary = "Метод для получения всех комментариев к посту"
    )
    @GetMapping("/by-post/{postId}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsForPost(@PathVariable Long postId) {
        return ResponseEntity.status(OK)
                .body(commentService.getAllCommentsForPost(postId));
    }

    @Operation(
            summary = "Метод для получения всех комментариев для пользователя"
    )
    @GetMapping(params = "userName")
    public ResponseEntity<List<CommentsDto>> getAllCommentsForUser(@RequestParam String userName){
        return ResponseEntity.status(OK)
                .body(commentService.getAllCommentsForUser(userName));
    }

}
