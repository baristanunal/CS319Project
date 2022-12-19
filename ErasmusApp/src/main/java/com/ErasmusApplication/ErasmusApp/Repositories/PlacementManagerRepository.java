package com.ErasmusApplication.ErasmusApp.Repositories;

import com.ErasmusApplication.ErasmusApp.Models.CourseWishList;
import com.ErasmusApplication.ErasmusApp.Models.PlacementManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlacementManagerRepository  extends JpaRepository<PlacementManager, Long> {
}
