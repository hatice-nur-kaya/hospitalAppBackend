package com.kodhnk.base.auth;

import com.kodhnk.base.dataAccess.UserRepository;
import com.kodhnk.base.entities.User;
import com.kodhnk.base.exceptions.UnexpectedErrorException;
import com.kodhnk.base.exceptions.UserAlreadyExistsException;
import com.kodhnk.base.security.dto.CreateUserRequest;
import com.kodhnk.base.security.services.JwtService;
import com.kodhnk.base.security.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository repository;

//    @PostMapping("/register")
//    public ResponseEntity<?> addUser(@RequestBody CreateUserRequest request) {
//        try {
//            User user = userService.createUser(request);
//            var jwtToken = jwtService.generateToken(user.getUsername());
//            return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
//        } catch (UserAlreadyExistsException e) {
//            log.error("Kullanıcı zaten mevcut: {}", e.getMessage());
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Bu kullanıcı adı zaten kullanılıyor.");
//        } catch (UnexpectedErrorException e) {
//            log.error("İşlem sırasında beklenmeyen bir hata oluştu: {}", e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("İşlem sırasında bir hata oluştu.");
//        } catch (Exception e) {
//            log.error("Genel hata: {}", e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Beklenmeyen bir hata oluştu.");
//        }
//    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            Optional<User> userOptional = repository.findByEmail(request.getUsername());
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                var jwtToken = jwtService.generateToken(user.getUsername());
                return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
            } else {
                throw new UsernameNotFoundException("Kullanıcı adı veya şifre hatalı.");
            }
        } catch (AuthenticationException e) {
            log.error("Kimlik doğrulama başarısız: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Kimlik doğrulama başarısız.");
        }
    }
}