package com.socialnetwork.microservice.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
public class PostsEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @Column(name = "title")
    @NotBlank
    private String title;
    @Column(name = "content")
    @NotBlank
    private String content;
    @Column(name = "receptor_type_id")
    private int receptorTypeId;
    @Column(name = "author_ref_id")
    private int authorRefId;
    @Column(name = "post_type_id")
    private int postTypeId;
    @Column(name = "created_at")
    private Date created_at;

    public PostsEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReceptorTypeId() {
        return receptorTypeId;
    }

    public void setReceptorTypeId(int receptorTypeId) {
        this.receptorTypeId = receptorTypeId;
    }

    public int getAuthorRefId() {
        return authorRefId;
    }

    public void setAuthorRefId(int authorRefId) {
        this.authorRefId = authorRefId;
    }

    public int getPostTypeId() {
        return postTypeId;
    }

    public void setPostTypeId(int postTypeId) {
        this.postTypeId = postTypeId;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
