package publics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;
import publics.model.Categorie;
import publics.model.Commission;
import publics.repository.CategorieRepository;
import publics.repository.CommissionRepository;
import publics.repository.TokenRepository;
import publics.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import publics.service.TokenService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@RequestMapping("/commissions")
@Controller
public class CommissionController {

    @Autowired
    private CategorieService catServ;
    @Autowired
    private CommissionRepository commRepo;
    @Autowired
    private TokenService serv;
    @Autowired
    private TokenRepository tk;


    @GetMapping
    public Object list(Model model, HttpSession session) {
        String token=(String)session.getAttribute("token");
        try{
            boolean tr=serv.verification(token,tk.findAll());
            if(tr==false){
                return new RedirectView("logins");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        model.addAttribute("commission",new ArrayList<>(commRepo.findAll()));
        return "commission";
    }




    @PostMapping
    public Object CreateComm(Commission com, Model model, HttpSession session) throws Exception {
        commRepo.save(com);
        return list(model,session);
    }

}

