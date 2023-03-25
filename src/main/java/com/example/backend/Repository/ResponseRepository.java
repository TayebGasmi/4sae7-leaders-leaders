package com.example.backend.Repository;

import com.example.backend.Entity.Response;
import com.example.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Set;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {
    @Query("select R.user from Response R where R.quiz.IdQuiz =:id and R.score=:max")
    Set<User> getBestUser(@Param("id") Long id, @Param("max") int max);
    @Query("select max(R.score) from Response R where R.quiz.IdQuiz =:id ")
    int  getmaxScore(@Param("id") Long id);
    @Query("select sum(R.score)/count(R) from Response R where R.user.id =:id")
    float  getMoyenne(@Param("id") Long id);
    @Query("select count(R) from Response R where R.user.id =:id and R.score=100")
    public Integer nbrCorrecte(@Param("id")Long id);
    @Query("select R.user from Response R ")
    Set<User> geUser();
}
