package com.manage.pure.apis.personapis.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Team extends AuditEntity{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String teamName;

    private String teamGrade;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    private List<Person> teamMembers;
    
}
