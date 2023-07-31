package De.com.example.demoAPIJAVA.controllers;

import De.com.example.demoAPIJAVA.models.ResponseObject;
import De.com.example.demoAPIJAVA.services.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path="api/v1/FileUpload")
public class FileUploadController {
    @Autowired
    private IStorageService iStorageService;

    @PostMapping("")
    // inject service here
    public ResponseEntity<ResponseObject> uploadfile(@RequestParam("file") MultipartFile file) {
        try {
            String generateFilename = iStorageService.storeFile(file);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Upload file successfully", generateFilename)
            );

        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("ok", exception.getMessage(), "")
            );
        }
    }

    // get image's url
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String filename) {
        try {
            byte[] bytes = iStorageService.readFileContent(filename);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        } catch (Exception exception) {
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getUploadedFiles() {
        try {
            List<String> urls = iStorageService.loadAll()
                    .map(path -> {
                        String urlPath = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "readDetailFile", path.getFileName().toString()).build().toUri().toString();
                        return urlPath;
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new ResponseObject("ok", "List file successfully", urls));
        } catch (Exception exception)
        {
            return  ResponseEntity.ok(new ResponseObject("failed","List files failed",new String[]{}));
        }

    }


}
