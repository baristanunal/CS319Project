package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Models.WaitingBin;
import com.ErasmusApplication.ErasmusApp.Repositories.WaitingBinRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class WaitingBinService {

  WaitingBinRepository waitingBinRepository;

  WaitingBinService(){}

  public WaitingBin saveWaitingBin(WaitingBin waitingBin ){
    return waitingBinRepository.save(waitingBin);
  }

}
