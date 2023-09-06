package ru.gmotrich.reddit.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gmotrich.reddit.dto.AuthenticationResponse;
import ru.gmotrich.reddit.dto.LoginRequest;
import ru.gmotrich.reddit.dto.RefreshTokenRequest;
import ru.gmotrich.reddit.dto.RegisterRequest;
import ru.gmotrich.reddit.service.AuthService;
import ru.gmotrich.reddit.service.RefreshTokenService;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(
        name = "Регистрация и аутентификация",
        description = "AuthController"
)
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @Operation(
            summary = "Метод для регистрации"
    )
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity<>("User Registration Successful",
                OK);
    }

    @Operation(
            summary = "Метод для верификации"
    )
    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully", OK);
    }

    @Operation(
            summary = "Метод для аутентификации"
    )
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @Operation(
            summary = "Метод для обновления токена"
    )
    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @Operation(
            summary = "Метод для выхода"
    )
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }
}
