package com.example.backend.Entity;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class QuestionOrderID implements Serializable {
    @ManyToOne
    Quiz quiz;
    @ManyToOne
    Question question;
}
