package com.phalaenopsis.phalaenopsis.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Topic {
    @Id
    @GeneratedValue
    private Long Id;
    public String Name;
    public String Description;
    public String Url;
    @ElementCollection // 1
    //unnecessary
    @CollectionTable(name = "Keyword_List", joinColumns = @JoinColumn(name = "Id")) // 2
    @Column(name = "keywords") // 3
    //unnecessary
    List<String> keywords; //'keywords' must start with a lowercase

    public Topic(String name, String description, String url, List<String> keywords) {
        this.Name = name;
        this.Description = description;
        this.Url = url;
        this.keywords = keywords;
    }

    public Topic() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return Objects.equals(Name, topic.Name) && Objects.equals(Description, topic.Description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Name, Description);
    }
}
