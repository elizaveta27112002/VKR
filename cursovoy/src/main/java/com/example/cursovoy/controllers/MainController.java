package com.example.cursovoy.controllers;

import com.example.cursovoy.models.Blog;
import com.example.cursovoy.models.Resume;
import com.example.cursovoy.models.Vakansy;
import com.example.cursovoy.repo.BlogRepository;
import com.example.cursovoy.repo.ResumeRepository;
import com.example.cursovoy.repo.VakansyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class MainController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private VakansyRepository vakansyRepository;
    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private BlogRepository blogRepository;

    @GetMapping("/blog")
    public String home(Model model) {
    Iterable<Blog> blogs = blogRepository.findAll();
    model.addAttribute("blogs", blogs);
        return "blog";
    }
    @GetMapping("/blog/add")
    public String blogAdd(Blog blog) {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String nameblog,
                                 @RequestParam String textmaterial,  Model model){

        Blog blog = new Blog (nameblog, textmaterial);

        blogRepository.save(blog);
        return "redirect:/blog";
    }
    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if(!blogRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Blog> blog = blogRepository.findById(id);
        ArrayList<Blog> res = new ArrayList<>();
        blog.ifPresent(res::add);
        model.addAttribute("blog", res);
        return "blog-details";
    }
    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {
        if(!blogRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<Blog> blog = blogRepository.findById(id);
        ArrayList<Blog> res = new ArrayList<>();
        blog.ifPresent(res::add);
        model.addAttribute("blog", res);
        return "blog-edit";
    }
    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") long id, @RequestParam String nameblog,
                                    @RequestParam String textmaterial, Model model){

        Blog blog = blogRepository.findById(id).orElseThrow();
        blog.setNameblog(nameblog);
        blog.setTextmaterial(textmaterial);
        return "redirect:/blog";
    }
    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") long id, Model model){
        Blog blog = blogRepository.findById(id).orElseThrow();
        blogRepository.delete(blog);
        return "redirect:/blog";
    }


    //для работы с вакансиями
    @GetMapping("/vakansy")
    public String vakansyMain(Model model) {
        Iterable<Vakansy> vakansies = vakansyRepository.findAll();
        model.addAttribute("vakansies", vakansies);
        return "vakansy";
    }

    @GetMapping("/vakansy/add")
    public String vakansyAdd(Vakansy vakansy) {
        return "vakansy-add";
    }

    @PostMapping("/vakansy/add")
    public String vakansyPostAdd(@Valid Vakansy vakansy, BindingResult bindingResult, @RequestParam String namevakansy,
                                 @RequestParam String company, @RequestParam(required=false, name="salary") Integer salary,
                                 @RequestParam String experience, @RequestParam String busyness,
                                 @RequestParam String skills, Model model){
        if (bindingResult.hasErrors()) {
            return "vakansy-add";
        }
        Vakansy vvakansy = new Vakansy (namevakansy, company, salary, experience, busyness,skills);

        vakansyRepository.save(vvakansy);
        return "redirect:/vakansy";
    }
    @GetMapping("/vakansy/{id}")
    public String vakansyDetails(@PathVariable(value = "id") long id, Model model) {
        if(!vakansyRepository.existsById(id)){
            return "redirect:/vakansy";
        }
        Optional<Vakansy> vakansy = vakansyRepository.findById(id);
        ArrayList<Vakansy> res = new ArrayList<>();
        vakansy.ifPresent(res::add);
        model.addAttribute("vakansy", res);
        return "vakansy-details";
    }
    @GetMapping("/vakansy/{id}/edit")
    public String vakansyEdit(@PathVariable(value = "id") long id, Model model) {
        if(!vakansyRepository.existsById(id)) {
            return "redirect:/vakansy";
        }
        Optional<Vakansy> vakansy = vakansyRepository.findById(id);
        ArrayList<Vakansy> res = new ArrayList<>();
        vakansy.ifPresent(res::add);
        model.addAttribute("vakansy", res);
        return "vakansy-edit";
    }
    @PostMapping("/vakansy/{id}/edit")
    public String vakansyPostUpdate(@Valid Vakansy vakansy, BindingResult bindingResult,@PathVariable(value = "id") long id, @RequestParam String namevakansy,
                                  @RequestParam String company, @RequestParam Integer salary,
                                  @RequestParam String experience,@RequestParam String busyness,
                                    @RequestParam String skills, Model model){
        if (bindingResult.hasErrors()) {
            return "vakansy-edit";
        }
        Vakansy vvakansy = vakansyRepository.findById(id).orElseThrow();
        vvakansy.setNamevakansy(namevakansy);
        vvakansy.setCompany(company);
        vvakansy.setSalary(salary);
        vvakansy.setExperience(experience);
        vvakansy.setBusyness(busyness);
        vvakansy.setSkills(skills);
        vakansyRepository.save(vvakansy);
        return "redirect:/vakansy";
    }
    @PostMapping("/vakansy/{id}/remove")
    public String vakansyPostDelete(@PathVariable(value = "id") long id, Model model){
        Vakansy vakansy = vakansyRepository.findById(id).orElseThrow();
        vakansyRepository.delete(vakansy);
        return "redirect:/vakansy";
    }

    @PostMapping("/vakansy/searchByVakansy")
    public String vakansySearch(String search, Model model) {

        if (!search.equals("")) {
            List<Vakansy> result = vakansyRepository.findVakansiesByNamevakansyIsContaining(search);
            model.addAttribute("vakansies", result);
        }
        else {
            Iterable<Vakansy> result = vakansyRepository.findAll();
            model.addAttribute("vakansies", result);
        }
        return "vakansy";
    }

    //работа с резюме
    @GetMapping("/resume")
    public String resumeMain(Model model) {
        Iterable<Resume> resumes = resumeRepository.findAll();
        model.addAttribute("resumes", resumes);
        return "resume";
    }
    @GetMapping("/resume/add")
    public String resumeAdd(Model model) {
        return "resume-add";
    }

    @PostMapping("/resume/add")
    public String resumePostAdd(@RequestParam String fio,
                                @RequestParam String post, @RequestParam Integer dessalary,
                                @RequestParam String schedule, @RequestParam String city,
                                @RequestParam String nameinstitution,
                                @RequestParam String speciality, @RequestParam String workexperience,
                                @RequestParam("file") MultipartFile file, Model model) throws IOException {
        Resume resume = new Resume (fio, post, dessalary, schedule, city, nameinstitution,
                speciality, workexperience);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            resume.setFilename(resultFilename);
        }
        resumeRepository.save(resume);
        return "redirect:/resume";
    }

    @PostMapping("/resume/searchByResume")
    public String resumeSearch(String search, Model model) {

        if (!search.equals("")) {
            List<Resume> result = resumeRepository.findResumesByPostIsContaining(search);
            model.addAttribute("resumes", result);
        }
        else {
            Iterable<Resume> result = resumeRepository.findAll();
            model.addAttribute("resumes", result);
        }
        return "resume";
    }

    @GetMapping("/resume/{id}")
    public String resumeDetails(@PathVariable(value = "id") long id, Model model) {
        if(!resumeRepository.existsById(id)){
            return "redirect:/resume";
        }
        Optional<Resume> resume = resumeRepository.findById(id);
        ArrayList<Resume> res = new ArrayList<>();
        resume.ifPresent(res::add);
        model.addAttribute("resume", res);
        return "resume-details";
    }
    @GetMapping("/resume/{id}/edit")
    public String resumeEdit(@PathVariable(value = "id") long id, Model model) {
        if(!resumeRepository.existsById(id)) {
            return "redirect:/vakansy";
        }
        Optional<Resume> resume = resumeRepository.findById(id);
        ArrayList<Resume> res = new ArrayList<>();
        resume.ifPresent(res::add);
        model.addAttribute("resume", res);
        return "resume-edit";
    }
    @PostMapping("/resume/{id}/edit")
    public String resumePostUpdate(@PathVariable(value = "id") long id, @RequestParam String fio,
                                   @RequestParam String post, @RequestParam Integer dessalary,
                                   @RequestParam String schedule, @RequestParam String city,
                                   @RequestParam String nameinstitution,
                                   @RequestParam String speciality, @RequestParam String workexperience,
                                   @RequestParam("file") MultipartFile file, Model model) throws IOException {
        Resume resume = resumeRepository.findById(id).orElseThrow();

        resume.setFio(fio);
        resume.setPost(post);
        resume.setDessalary(dessalary);
        resume.setSchedule(schedule);
        resume.setCity(city);
        resume.setNameinstitution(nameinstitution);
        resume.setSpeciality(speciality);
        resume.setWorkexperience(workexperience);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            resume.setFilename(resultFilename);
        }
        resumeRepository.save(resume);
        return "redirect:/resume";
    }
    @PostMapping("/resume/{id}/remove")
    public String resumePostDelete(@PathVariable(value = "id") long id, Model model){
        Resume resume = resumeRepository.findById(id).orElseThrow();
        resumeRepository.delete(resume);
        return "redirect:/resume";
    }


}
