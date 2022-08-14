package com.wimdeblauwe.examples.formhandlingthymeleaf.user.web;

import com.wimdeblauwe.examples.formhandlingthymeleaf.user.InformationResponse;
import com.wimdeblauwe.examples.formhandlingthymeleaf.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller

@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/index")
    public String getDeaufl() {
        return "/users/index";
    }

    @GetMapping("/index2")
    public String getResult() {
        return "/users/index";
    }

    @GetMapping("/created")
    public String showCreateUserForm(InformationResponse res) {
        //Object o  = modelAndView.getModel().get(0);
        //model.addAttribute("formData", new CreateUserFormData());
        return "/users/index";
    }

    /*@GetMapping("/create")
    public String showCreateUserForm(Model model) {
        model.addAttribute("formData", new CreateUserFormData());
        return "users/create";
    }*/

    @PostMapping("/create")
    public String doCreateUser(@Valid @ModelAttribute("formData") CreateUserFormData formData,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            return "users/create";
        }

        service.createUser(formData.toParameters());

        return "redirect:/users";
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", service.getUsers());

        return "users/list";
    }

    @RequestMapping(value = "/mapping2", method = RequestMethod.GET)
    public String controlMapping2(
            @ModelAttribute("response") final InformationResponse mapping1FormObject,
            final Model model) {

        System.out.println(mapping1FormObject.getResult());
        model.addAttribute("transformationForm", mapping1FormObject);

        return "/users/index";
    }
}
