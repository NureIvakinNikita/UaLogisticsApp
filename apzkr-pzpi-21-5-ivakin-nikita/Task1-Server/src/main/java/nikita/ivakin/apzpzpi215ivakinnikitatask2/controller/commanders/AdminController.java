package nikita.ivakin.apzpzpi215ivakinnikitatask2.controller.commanders;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.PostDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.ScanningDevice;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/create/post")
    public ResponseEntity<Boolean> createPost(@Valid @RequestBody PostDTO postDTO) {
        boolean result = adminService.createPost(postDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/assign/scanning-device-for-post/{id}")
    public ResponseEntity<Boolean> createScanningDevice(@PathVariable Integer id, @Valid @RequestBody ScanningDevice scanningDevice) {
        boolean result = adminService.createScanningDeviceForPost(id, scanningDevice);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/get/posts")
    public ResponseEntity<List<PostDTO>> getPosts(){
        return new ResponseEntity<>(adminService.getPosts(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/change/IoT-url")
    public ResponseEntity<Boolean> changeIotUrl(@RequestParam String url) {
        boolean result = adminService.changeIotUrl(url);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
