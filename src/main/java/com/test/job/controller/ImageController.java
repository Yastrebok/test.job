package com.test.job.controller;

import com.test.job.entity.FileBucket;
import com.test.job.entity.Image;
import com.test.job.service.ImageService;
import com.test.job.util.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static com.test.job.service.ImageService.ImageDao;

@Controller
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;
    private FileValidator fileValidator;

    @Autowired
    public ImageController(ImageService imageService, FileValidator fileValidator) {
        this.imageService = imageService;
        this.fileValidator = fileValidator;
    }

    @InitBinder("fileBucket")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }

    @GetMapping("/all")
    public String getAllImages(Model model) {
        List<Image> images = imageService.getAllImage();
        model.addAttribute("list", images);
        return "imageList";
    }

    @GetMapping("/add")
    public String createImagePage(Model model) {
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);
        return "imageAdd";
    }

    @PostMapping("/add")
    public String addImage(@Valid FileBucket fileBucket,
                           BindingResult result,
                           ModelMap model)  throws IOException{
        saveDocument(fileBucket, model);
        return "redirect:/image/all";
    }

    private void saveDocument(FileBucket fileBucket,ModelMap model) throws IOException {

        Image document = new Image();

        MultipartFile multipartFile = fileBucket.getFile();

        document.setTitle(model.get("title").toString());
        document.setDescription(model.get("description").toString());
        document.setImage(multipartFile.getBytes());
        document.setCategory(model.get("category").toString());
        imageService.saveImage(document);
    }


    @GetMapping("/id/{id}")
    public String getImageById(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("image", imageService.getById(id));
        return "imageDetails";
    }

    @GetMapping("/delete/{id}")
    public String deleteImage(@PathVariable("id") ImageDao image) {
        imageService.deleteImage(image);
        return "redirect:/image/all";
    }

    @GetMapping("/update/{id}")
    public String updateImage(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("image", imageService.getById(id));
        return "imageEdit";
    }

    @PostMapping("/update")
    public String updateImage(@ModelAttribute("image") ImageDao image) {
        imageService.updateImage(image);
        return "redirect:/image/subject/" + image.getId();
    }


}
