package nikita.ivakin.apzpzpi215ivakinnikitatask2.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PatchMapping
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('BRIGADE_COMMANDER', 'ADMIN', 'BATTALION_COMMANDER')")
    @GetMapping("/find")
    public ResponseEntity<User> findUserByEmail(@RequestParam String email){
        User user = service.findUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
