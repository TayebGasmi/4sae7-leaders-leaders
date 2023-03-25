package com.example.backend.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long qusetionId;
	private String content;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private int answer;




}
