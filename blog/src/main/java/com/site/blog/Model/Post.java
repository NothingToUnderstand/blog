package com.site.blog.Model;



import javax.persistence.*;

@Entity
@Table(name="post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;
    @Column(name="title")
    private String title;
    @Column(name="anons")
    private String anons;
    @Column(name="fullText")
    private String fullText;
    @Column(name="views")
    private int views;

    public Post(String title, String anons, String fullText) {
    }
    public Post(){

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnons() {
        return anons;
    }

    public void setAnons(String anons) {
        this.anons = anons;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }
}
