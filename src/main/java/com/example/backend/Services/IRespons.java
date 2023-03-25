package com.example.backend.Services;

import com.example.backend.Entity.Response;
import com.example.backend.Entity.User;


import java.util.List;
import java.util.Set;

public interface IRespons {
    public Response addResponse(Long idP, Long idQ, Long reponse);
    public Set<User> getBestuser(Long id);
    public float  getuserMoyenne( Long id);
    public int nbrCorrecte(Long id);
    public List<User> top();

}
