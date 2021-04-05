package com.example.put_api.put_api;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileController {

    @Autowired
    FileService fileService;


    @GetMapping("/")
    public String index() {
        return "upload";
    }

//    @PostMapping("/uploadFile")
//    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("stage_name") String stage_name, RedirectAttributes redirectAttributes) {
//
//        fileService.uploadFile(file,stage_name);
//
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded and staged " + file.getOriginalFilename() + "!");
//
//        return "redirect:/";
//    }

    @PostMapping("/uploadFiles")
    public String uploadFiles(@RequestParam("files") MultipartFile[] files, @RequestParam("stage_name") String stage_name, RedirectAttributes redirectAttributes) {

        Arrays.stream(files)
                .forEach(file -> fileService.uploadFile(file,stage_name));

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded the file(s) and staged them as well!!");

        return "redirect:/";
    }
}