package com.test.bysiness.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "ANSWER_DETAILS")
@EqualsAndHashCode(exclude = {"id", "parentAnswer"})
@ToString(exclude = {"id", "parentAnswer"})
@Data
public class AnswerOptionEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "CONTENT")
    private String content;
    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    @JsonIgnore
    AnswerEntity parentAnswer;
}

