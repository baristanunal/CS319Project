package com.ErasmusApplication.ErasmusApp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;

import java.util.Iterator;
import java.util.List;

@Entity
@Data
public class CourseWishList {
    // Properties

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonIgnore
    @OneToMany(
            mappedBy = "courseWishList",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Wish> wishes;

    @JsonIgnore
    @OneToOne(mappedBy = "courseWishList", cascade = CascadeType.ALL, orphanRemoval = true)
    private Form preApproval;

    @JsonIgnore
    @OneToOne(mappedBy = "courseWishList", cascade = CascadeType.ALL, orphanRemoval = true)
    private Form courseTransfer;

    @JsonIgnore
    @OneToOne(orphanRemoval = true)
    @MapsId
    private Application application;

    private boolean isCompleted;
    //TODO @Transient
    @Transient
    private Double totalCredit;

    public CourseWishList() {
    }

    public Double getTotalPoints() {
        //TODO TODO
        // get from wishlist
        return  0.0;
    }







    /**
     * Methods related to wishes
     * */
    public boolean addWish(Wish wish){
        return wishes.add(wish);
    }
    public boolean addWishes(List<Wish> wishList){
        for (int i = 0; i < wishList.size(); i++){
            wishes.add(wishList.get(i));
        }

        return false;
    }
    public boolean removeWishById(Long wishId) {
        Iterator<Wish> iterator = wishes.iterator();
        while (iterator.hasNext()) {
            if(iterator.next().getId().equals(wishId)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public Wish getWishById(Long wishId) {
        Iterator<Wish> iterator = wishes.iterator();
        while (iterator.hasNext()) {
            if(iterator.next().getId().equals(wishId)){
                return iterator.next();
            }
        }
        return null;
    }

    public boolean updateWishByWishId(Long wishId, Wish wish) {
        for (int i = 0; i < wishes.size(); i++){
            if(wishes.get(i).getId() == wishId){
                wishes.get(i).setAll(wish);
                return true;
            }
        }
        return false; // if task does not exist in User return false
    }

    public boolean removeWishFromCourseWishList(Long wishId) {
        Iterator<Wish> iterator = wishes.iterator();
        while (iterator.hasNext()) {
            if(iterator.next().getId().equals(wishId)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

}
