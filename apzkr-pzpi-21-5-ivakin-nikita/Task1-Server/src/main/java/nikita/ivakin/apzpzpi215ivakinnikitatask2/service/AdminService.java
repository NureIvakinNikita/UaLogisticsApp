package nikita.ivakin.apzpzpi215ivakinnikitatask2.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.PostDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.Post;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.ScanningDevice;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.PostCreationException;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.ScanningDeviceCreationException;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PostService postService;
    private final ScanningDeviceService scanningDeviceService;

    public boolean createPost(PostDTO postDTO) {
        try {
            postService.createPost(postDTO);
            return true;
        } catch (Exception e){
            throw new PostCreationException("Error in creating post in admin service.");
        }

    }

    public boolean createScanningDeviceForPost(Integer id, ScanningDevice scanningDeviceDTO) {
        Post post = postService.findPostById(id);
        try {
            ScanningDevice scanningDevice = ScanningDevice.builder()
                    .post(post).tier(scanningDeviceDTO.getTier()).build();
            scanningDeviceService.save(scanningDevice);
            post.setScanningDevice(scanningDeviceService.findScanningDeviceByPost(post));
            postService.save(post);
            return true;
        } catch (Exception e) {
            throw new ScanningDeviceCreationException("Error in creation scanning device for post with id " + id);
        }

    }

    public List<PostDTO> getPosts() {
        List<Post> posts = postService.findAll();
        List<PostDTO> postDTOS = new ArrayList<>();
        for (Post post : posts) {
            postDTOS.add(postService.mapPostToDto(post));
        }
        return postDTOS;
    }

    public boolean changeIotUrl(String url) {
        try {
            URL adminUrl = new URL("http://localhost:8081/update-url");
            HttpURLConnection con = (HttpURLConnection) adminUrl.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "text/plain; utf-8");
            con.setRequestProperty("Accept", "text/plain");
            con.setDoOutput(true);

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = url.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                return true;
            } else {
                System.err.println("Failed to update IoT URL, response code: " + responseCode);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
