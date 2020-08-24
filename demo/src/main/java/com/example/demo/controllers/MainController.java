package com.example.demo.controllers;

import com.example.demo.entities.Cat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/main")
public class MainController {
    @GetMapping("/index")
    public String doSomething(){
        System.out.println("!!!!!");
        return "index";
    }
    //Exc 1
    //    @GetMapping("/hello")
//    @ResponseBody
//    public Cat helloRequest(){
//        return new Cat(1L,"БАРСИК","ЧЁРНЫЙ");
//    }


    // Exc 2
 //   @GetMapping("/hello")
 //   public String helloRequest(Model model){
 //       model.addAttribute("name", "Bob");
 //       return "hello";
 //   }

//Exc 3 !(Spring инжектит всё что мы прокидываем в качестве параметров метода helloRequest)
//@GetMapping("/hello")
//public String helloRequest(Model model,@RequestParam(value="name") String name){
//    model.addAttribute("name", "Bob");
//    model.addAttribute("name", name);
//    return "hello";
//}

    //Exc 4 !(Spring инжектит всё что мы прокидываем в качестве параметров метода helloRequest)
    @GetMapping("/hello/{name}/{color}")
    public String helloRequest(Model model
            ,@PathVariable(value="name") String name
            ,@PathVariable(value="color") String color){
//    model.addAttribute("name", "Bob");
        model.addAttribute("name", name);
        model.addAttribute("color", color);
    return "/hello";
    }

        // Exc5
        @GetMapping("/form")
        public String showForm(){
            return "simple-form";
        }

        @PostMapping("/form")
        public String SaveForm(@RequestParam(value="name") String name
                                ,@RequestParam(value="email") String email){
            System.out.println(name);
            System.out.println(email);

            return "redirect:/index";// Перевести на корень нашего приложения
    }

    //Exc 6
    @GetMapping("/addcat")
    public String showAddCatForm(Model model){
        Cat cat= new Cat(1L,"Barsic","Black");
        model.addAttribute("cat",cat);
        return "cat-form";
    }

    @PostMapping("/addcat")
    public String SaveCat(@ModelAttribute(value="cat") Cat cat){
        System.out.println(cat.getId());
        System.out.println(cat.getName());
        System.out.println(cat.getColor());
        return "redirect:/index";
    }
}
