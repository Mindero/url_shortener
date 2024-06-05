package org.example.repo.entity;

import jakarta.persistence.*;

@Entity(name="url")
@Table(name="urls")
public class UrlEntity {
    @Id
    @Column(name="short_url")
    private String shorturl;
    @Column(name="long_url", nullable = false, length = 100)
    private String longurl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = true)
    private UserEntity user;

    @Column(name="cnt", nullable = false)
    private int cnt;

    public UrlEntity(){

    }
    public UrlEntity(String shorturl, String longurl, UserEntity user, int cnt) {
        this.shorturl = shorturl;
        this.longurl = longurl;
        this.user = user;
        this.cnt = cnt;
    }

    public String getShorturl() {
        return shorturl;
    }

    public void setShorturl(String shorturl) {
        this.shorturl = shorturl;
    }

    public String getLongurl() {
        return longurl;
    }

    public void setLongurl(String longurl) {
        this.longurl = longurl;
    }

    @PreRemove
    public void preRemove(){
        this.user.getUrls().clear();
        this.user = null;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}
