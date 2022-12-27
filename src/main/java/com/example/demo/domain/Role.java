package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Role {
    @Id
    private int id;
    private String name;

    @OneToMany(mappedBy ="role" ,cascade = CascadeType.ALL)
    private List<User> users;



}
