package com.example.egovernmentportal.controller;

import com.example.egovernmentportal.model.InformationResponse;
import com.example.egovernmentportal.model.User;
import com.example.egovernmentportal.repository.BookRepository;
import com.example.egovernmentportal.service.UserService;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.IDToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**/
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ViewController {

    private final HttpServletRequest request;
    private final BookRepository bookRepository;

    private UserService userService;



    public ViewController(HttpServletRequest request, BookRepository bookRepository, UserService userService) {
        this.request = request;
        this.bookRepository = bookRepository;
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String getHome() {
        return "index";
    }


    @GetMapping(value = "/books")
    public String getBooks(Model model) {
        configCommonAttributes(model);
        model.addAttribute("books", bookRepository.readAll());
        return "books";
    }

    @GetMapping(value = "/users")
    public String getUser(Model model) {
        IDToken idToken = getKeycloakSecurityContext().getIdToken();

        //User user = userService.getUserbyIdentityId(idToken.getSubject());
        model.addAttribute("user",userService.getUserDetails(idToken));
        configCommonAttributes(model);
        return "users";
    }

    @GetMapping(value = "/print")
    public String getPrint(Model model) {
        IDToken idToken = getKeycloakSecurityContext().getIdToken();

        //User user = userService.getUserbyIdentityId(idToken.getSubject());
        model.addAttribute("user",userService.getUserDetails(idToken));
        configCommonAttributes(model);
        return "printnew";
    }

    @GetMapping(value = "/edit")
    public String editUser(Model model) {
        IDToken idToken = getKeycloakSecurityContext().getIdToken();
        model.addAttribute("user",userService.getUserDetails(idToken));
        configCommonAttributes(model);
        return "edit";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        userService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @PostMapping(value = "/save")
    public String saveUser(@ModelAttribute User user, Model model,@RequestParam("file") MultipartFile file,
                           RedirectAttributes redirectAttributes) {
        userService.saveUser(user,file);
        model.addAttribute("user",user);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        //configCommonAttributes(model);
        return "redirect:/users";
    }

    @GetMapping(value = "/manager")
    public String getManager(Model model) {
        configCommonAttributes(model);
        model.addAttribute("books", bookRepository.readAll());
        return "manager";
    }



    @GetMapping(value = "/logout")
    public String logout() throws ServletException {
        request.logout();
        return "redirect:/";
    }

    private void configCommonAttributes(Model model) {
        model.addAttribute("name", getKeycloakSecurityContext().getIdToken().getGivenName());
    }

    private KeycloakSecurityContext getKeycloakSecurityContext() {
        return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
    }

    @RequestMapping(value = { "/check" }, method = RequestMethod.POST)
    public String controlMapping1(
            @ModelAttribute("mapping1Form") final InformationResponse mapping1FormObject,
            final Model model,
            final RedirectAttributes redirectAttributes) {
        mapping1FormObject.setResult("Hallo chung may");
        redirectAttributes.addAttribute("response",mapping1FormObject);
  //      redirectAttributes.addAttribute(mapping1FormObject);
 //       redirectAttributes.addFlashAttribute("mapping1Form", mapping1FormObject);

        return "redirect:http://localhost:8080/users/mapping2";
    }
}
