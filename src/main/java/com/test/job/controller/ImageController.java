package com.test.job.controller;

import com.test.job.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/all")
    public String getAllImages(Model model) {
        model.addAttribute("list", imageService.getAllImage());
        return "imageList";
    }

    @GetMapping("/add")
    public String createImagePage() {
        return "imageAdd";
    }

    @PostMapping("/add")
    public String addImage(@RequestParam("image") MultipartFile file,
                           @RequestParam("title") String title,
                           @RequestParam("description") String description,
                           @RequestParam("category") String category) throws MultipartException {
        imageService.saveImage(file, title, description, category);
        return "redirect:/image/all";
    }

    @GetMapping("/id/{id}")
    public String getImageById(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("image", imageService.getById(id));
        return "imageDetails";
    }

    @GetMapping("/delete/{id}")
    public String deleteImage(@PathVariable("id") Integer id) {
        imageService.deleteImage(id);
        return "redirect:/image/all";
    }

    @GetMapping("/update/{id}")
    public String updateImage(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("image", imageService.getById(id));
        return "imageEdit";
    }

    @PostMapping("/update")
    public String updateImage(@RequestParam("image") MultipartFile file,
                              @RequestParam("title") String title,
                              @RequestParam("description") String description,
                              @RequestParam("category") String category,
                              @RequestParam("id") Integer id) throws MultipartException {
        imageService.updateImage(file, title, description, category, id);
        return "redirect:/image/all";
    }
}
