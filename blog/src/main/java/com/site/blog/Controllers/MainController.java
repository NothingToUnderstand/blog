package com.site.blog.Controllers;
import com.site.blog.Model.Post;
import com.site.blog.Repositories.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;


@Controller
public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);


    private PostRepository postRepository;

    @Autowired
    public MainController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @GetMapping("/blog")
    public String blogMain(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@ModelAttribute Post post) {
        postRepository.save(post);
        LOGGER.info("Added successfully");
        return "redirect:/blog";

    }

    @GetMapping("/blog/{id}")
    public String blogInfo(@PathVariable(value = "id") int id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> list = new ArrayList<>();
        post.ifPresent(list::add);
        model.addAttribute("post", list);
        return "info";
    }

    @GetMapping("/blog/{id}/delete")
    public String blogDelete(@PathVariable(value = "id") int id, Model model) {
        postRepository.deleteById(id);
        LOGGER.info("Delete successful");
        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostEdit(
//            @ModelAttribute Post post, @RequestParam String title,@RequestParam String anons,
//                               @RequestParam String fullText, @PathVariable(value = "id") int id, Model model) {
//        model.addAttribute("post", postRepository.findById(id));
//        post.setTitle(title);
//        post.setAnons(anons);
//        post.setFullText(fullText);
//        postRepository.save(post);

//            @PathVariable(value = "id") int id, Model model) {
            @ModelAttribute Post post){
//
        LOGGER.info("Edit post: {}",post);
        postRepository.save(post);
//        Optional<Post> post = postRepository.findById(id);
//        ArrayList<Post> list = new ArrayList<>();
//        post.ifPresent(list::add);
//        model.addAttribute("post", list);
//
//        LOGGER.info("Edit successful");

        return "redirect:/blog";
    }
//
    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") int id, Model model) {
        Optional<Post> post = postRepository.findById(id);
        post.ifPresent(value -> model.addAttribute("post", value));
        return "edit";
    }
}



