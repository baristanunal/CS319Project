package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Models.Application;
import com.ErasmusApplication.ErasmusApp.Models.PlacementManager;
import com.ErasmusApplication.ErasmusApp.Models.PlacementTable;
import com.ErasmusApplication.ErasmusApp.Repositories.PlacementTableRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class PlacementTableService {

  PlacementTableRepository placementTableRepository;


  public PlacementTable savePlacementTable( PlacementTable placementTable ){
    return placementTableRepository.save( placementTable );
  }

}
