
package com.example.backend.Services;

import com.example.backend.Entity.Quiz;
import com.example.backend.Entity.Response;
import com.example.backend.Entity.User;
import com.example.backend.Repository.QuizRepository;
import com.example.backend.Repository.ResponseRepository;
import com.example.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ResponseService implements IRespons {
    @Autowired
    ResponseRepository rr;
    @Autowired
    UserRepository pr;
    @Autowired
    QuizRepository qr;
    @Override
    public Response addResponse(Long idP, Long idQ, Long response) {
        Response r=new Response();
        Quiz q=qr.findById(idQ).orElse(null);
        User p=pr.findById(idP).orElse(null);
        r.setUser(p);
        r.setResponseCode(response);
        r.setQuiz(q);
        int score=0;
        long x=q.getCodeReponse();
        while(x>10 ){
            if(x%10==response%10)
                score+=10;
            x=x/10;
            response=response/10;
        }
        if(x%10 ==response%10)
            score+=10;
        r.setScore( score);

        return rr.save(r);

    }
    public int getmaxscore(Long id){
        return rr.getmaxScore(id);
    }
    @Override
    public Set<User> getBestuser(Long idq) {
        return rr.getBestUser(idq,rr.getmaxScore(idq));
    }
    public float  getuserMoyenne( Long id){
        return rr.getMoyenne(id);
    }

    @Override
    public int nbrCorrecte(Long id) {
        return rr.nbrCorrecte(id);
    }

    @Override
    public List<User> top() {
       Set<User> profils=  rr.geUser();
       List<User> list =new ArrayList(profils);
       list=profils.stream().sorted((u,v)->rr.nbrCorrecte(v.getId())-rr.nbrCorrecte(u.getId())).collect(Collectors.toList());
       long n= (long) (list.size()*0.1);
       if(n==0) n=1;
        return list.stream().limit(n).collect(Collectors.toList());
    }





























}
