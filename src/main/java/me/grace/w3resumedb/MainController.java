package me.grace.w3resumedb;


import me.grace.w3resumedb.repositories.EducationRepo;
import me.grace.w3resumedb.repositories.ExperienceRepo;
import me.grace.w3resumedb.repositories.PersonRepo;
import me.grace.w3resumedb.repositories.SkillRepo;
import org.aspectj.weaver.Iterators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.expression.Lists;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class MainController {

    //ArrayList<Education> alleducation= new ArrayList<>();


@Autowired
PersonRepo personRepo;
@Autowired
EducationRepo educationRepo;
@Autowired
SkillRepo skillRepo;
@Autowired
ExperienceRepo experienceRepo;

//    @GetMapping("/")
//    public String home(){
//    return "welcome";
//    }
//
//    @GetMapping("/addresume")
//    public String add(Model model){
//        model.addAttribute("newperson", new Person());
//        model.addAttribute("neweducation", new Education());
//        model.addAttribute("newskill", new Skill());
//        model.addAttribute("newexperience", new Experience());
//
//        return "addresume";
//    }
//
//    @PostMapping("/addresume")
//    public String postResume(@Valid @ModelAttribute("newperson") Person person, @Valid @ModelAttribute("neweducation") Education education,
//                             @Valid @ModelAttribute("newskill") Skill skill, @Valid @ModelAttribute("newexperience") Experience experience,
//                             BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "addresume";
//        }
//
//        return "display";
//    }


    @GetMapping("/addperson")
    public String addPerson(Model model)
    {

        model.addAttribute("newperson", new Person());

        return "addperson";
    }

    @PostMapping("/addperson")
    public String postperson(@Valid @ModelAttribute("newperson") Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "addperson";
        }

        personRepo.save(person);

        return "displayperson";
    }

    //Arraylist add one by one
    @GetMapping("/addeducation")
    public String addeducationto(Model model)
    {
        //Iterable<Education> nowedu= educationRepo.findAll();
//        ArrayList<Education> education= new ArrayList<>();
//        nowedu=education;




//        nowedu.spliterator().getExactSizeIfKnown();
//
//        size=Lists.newArrayList(nowedu).size();

        if(educationRepo.count()<10) {


            //Education education = new Education();
            model.addAttribute("neweducation", new Education());

            return "addeducation";
        }

            return "limit";

    }

    @PostMapping("/addeducation")
    public String posteducation(@Valid @ModelAttribute("neweducation") Education education, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "addeducation";
        }

        //Iterable<Education> edulist;

        //alleducation.add(education);

        //edulist=alleducation;

        educationRepo.save(education);


        return "displayeducation";
    }

    @GetMapping("/addskill")
    public String addskill(Model model)
    {

        model.addAttribute("newskill", new Skill());

        return "addskill";
    }

    @PostMapping("/addskill")
    public String postskill(@Valid @ModelAttribute("newskill") Skill skill, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "addskill";
        }

        skillRepo.save(skill);

        return "displayskill";
    }

    @GetMapping("/addexperience")
    public String addexperience(Model model)
    {

        model.addAttribute("newexperience", new Experience());

        return "addexperience";
    }

    @PostMapping("/addexperience")
    public String postexperience(@Valid @ModelAttribute("newexperience") Experience experience, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "addexperience";
        }

        experienceRepo.save(experience);


        return "displayexperience";
    }


    @GetMapping("/displayall")
    public String getall(Model model)
    {

        Iterable<Person> allper= personRepo.findAll();
        model.addAttribute("allper",allper);


//        Iterable<Education> alledu= educationRepo.findAll();
//        Person person= new Person();
//        ArrayList<Education> educa= new ArrayList<>();
//        educa= (ArrayList<Education>) alledu; person.getEducations()
//
//        person.setEducations(educa);




        Iterable<Education> alledu= educationRepo.findAll();
        model.addAttribute("alledu",alledu);


        Iterable<Skill> allskill= skillRepo.findAll();
        model.addAttribute("allskill",allskill);


        Iterable<Experience> allexp= experienceRepo.findAll();
        model.addAttribute("allexp",allexp);


        return "displayall";

    }



}
