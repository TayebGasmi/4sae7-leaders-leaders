package com.example.backend.Controller;

import com.example.backend.Entity.Response;
import com.example.backend.Entity.User;
import com.example.backend.Services.ResponseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@Api(tags = "Response")
@RequestMapping("/response")

public class ResponseControlleur {
    @Autowired
    ResponseService rs;
    @PostMapping("/add/{idp}/{idq}/{answer}")
    @ResponseBody
    public Response addResponse(@PathVariable("idp") Long idP, @PathVariable("idq") Long idQ, @PathVariable("answer") Long response) {
        return rs.addResponse(idP, idQ, response);
    }
    @GetMapping("/maxscore/{id}")
    @ResponseBody
    public int getmaxscore(@PathVariable("id") Long id){
        return rs.getmaxscore(id);
    }
    @GetMapping("/BestProfil/{id}")
    @ResponseBody
    public Set<User> getBestuser(@PathVariable("id") Long id) {
        return rs.getBestuser(id);
    }
    @GetMapping("/moyenne/{id}")
    @ResponseBody
    float  getMoyenne(@PathVariable("id") Long id){
        return rs.getuserMoyenne(id);
    }
    @GetMapping("/correcte/{id}")
    @ResponseBody
    public int nbrCorrecte(@PathVariable("id")Long id) {
        return rs.nbrCorrecte(id);
    }
    @GetMapping("/tops")
    @ResponseBody
    public List<User> top(){
        return rs.top();
    }

}
