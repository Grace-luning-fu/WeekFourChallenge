package me.grace.w3resumedb;


import me.grace.w3resumedb.repositories.EducationRepo;
import me.grace.w3resumedb.repositories.ExperienceRepo;
import me.grace.w3resumedb.repositories.PersonRepo;
import me.grace.w3resumedb.repositories.SkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class MainController {

    @Autowired
    PersonRepo personRepo;
    @Autowired
    EducationRepo educationRepo;
    @Autowired
    SkillRepo skillRepo;
    @Autowired
    ExperienceRepo experienceRepo;


    @GetMapping("/")
    public String home()
    {
        return "home";
    }

    @GetMapping("/addperson")
    public String addPerson(Model model)
    {
        if(personRepo.count()>=1)
        {
            return "limit";
        }

        model.addAttribute("newperson", new Person());
        return "addperson";
    }

    @PostMapping("/addperson")
    public String postperson(@Valid @ModelAttribute("newperson") Person person, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "addperson";
        }

        personRepo.save(person);
        return "displayperson";
    }

    @GetMapping("/addeducation")
    public String addeducationto(Model model)
    {
        System.out.println(educationRepo.count());

        if(educationRepo.count()>=10)
        {
            return "limit";
        }

        model.addAttribute("neweducation", new Education());
        return "addeducation";

    }

    @PostMapping("/addeducation")
    public String posteducation(@Valid @ModelAttribute("neweducation") Education education, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "addeducation";
        }

        educationRepo.save(education);
        return "displayeducation";
    }

    @GetMapping("/addskill")
    public String addskill(Model model)
    {

        if(skillRepo.count()>=10)
        {
            return "limit";
        }

        model.addAttribute("newskill", new Skill());
        return "addskill";
    }

    @PostMapping("/addskill")
    public String postskill(@Valid @ModelAttribute("newskill") Skill skill, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "addskill";
        }

        skillRepo.save(skill);
        return "displayskill";
    }

    @GetMapping("/addexperience")
    public String addexperience(Model model)
    {

        if(experienceRepo.count()>=20)
        {
            return "limit";
        }

        model.addAttribute("newexperience", new Experience());
        return "addexperience";
    }

    @PostMapping("/addexperience")
    public String postexperience(@Valid @ModelAttribute("newexperience") Experience experience, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "addexperience";
        }

        experienceRepo.save(experience);
        return "displayexperience";
    }


    @GetMapping("/displayall")
    public String getall(Model model)
    {
        if(personRepo.count()==0)
        {
            return "/addperson";
        }

        if(educationRepo.count()==0)
        {
            return "/addeducation";
        }

        if(skillRepo.count()==0)
        {
            return "/addskill";
        }


        Person person= new Person();
        Iterable<Person> allperson= personRepo.findAll();
        model.addAttribute("person",allperson);


        Iterable<Education> alledu= educationRepo.findAll();
        ArrayList<Education> educa= new ArrayList<>();
        educa= (ArrayList<Education>) alledu;
        person.setEducations(educa);
        model.addAttribute("alledu",person.getEducations());

        Iterable<Skill> allskill= skillRepo.findAll();
        ArrayList<Skill> skills= new ArrayList<>();
        skills= (ArrayList<Skill>) allskill;
        person.setSkills(skills);
        model.addAttribute("allskill",person.getSkills());

        Iterable<Experience> allexp= experienceRepo.findAll();
        ArrayList<Experience> exps= new ArrayList<>();
        exps= (ArrayList<Experience>) allexp;
        person.setExperiences(exps);
        model.addAttribute("allexp",person.getExperiences());

        return "displayall";

    }

}
