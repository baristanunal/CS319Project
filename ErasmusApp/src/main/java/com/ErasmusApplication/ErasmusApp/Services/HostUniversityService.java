package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Models.HostUniversity;
import com.ErasmusApplication.ErasmusApp.Models.Student;
import com.ErasmusApplication.ErasmusApp.Repositories.HostUniversityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor

public class HostUniversityService {

    HostUniversityRepository hostUniversityRepository;

    public HostUniversity saveHostUni(HostUniversity hostUniversity) { //TODO BEncrypt..
        Optional<HostUniversity> hostUni = hostUniversityRepository.findByNameOfInstitution(hostUniversity.getNameOfInstitution());
        if( hostUni.isPresent()){
            throw new IllegalStateException("HostUniversity have already exist!");
        }
        return hostUniversityRepository.save(hostUniversity);
    }

    public HostUniversity getHostUni(Long uniId){
        return hostUniversityRepository.findById(uniId)
                .orElseThrow(() -> new IllegalStateException(
                        "HostUniversityRepository with ID: " + uniId + " does not exist."
                ));
    }

    public HostUniversity getHostUniByName(String nameOfUni){
        return hostUniversityRepository.findByNameOfInstitution(nameOfUni)
                .orElseThrow(() -> new IllegalStateException(
                        "HostUniversityRepository with name: " + nameOfUni + " does not exist."
                ));
    }
    public void updateNameOfHostUni(Long uniId,HostUniversity hostUniversity){
        HostUniversity host = getHostUni(uniId);

        hostUniversity.setNameOfInstitution(hostUniversity.getNameOfInstitution());

    }
    public void deleteHostUniById(Long uniId) {
        //TODO add corner-case
        boolean exist = hostUniversityRepository.existsById(uniId);
        if(!exist){
            throw new IllegalStateException("HostUniversity with Id: " + uniId + " does not exist!");
        }
        hostUniversityRepository.deleteById(uniId);
    }


}
