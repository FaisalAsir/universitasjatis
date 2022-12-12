package com.universitasjatis.universitasjatis.service.impl;

import com.universitasjatis.universitasjatis.dto.MahasiswaReqDto;
import com.universitasjatis.universitasjatis.entity.Mahasiswa;
import com.universitasjatis.universitasjatis.exceptionHandler.DataNotFoundException;
import com.universitasjatis.universitasjatis.exceptionHandler.JatisExeption;
import com.universitasjatis.universitasjatis.repository.MahasiswaRepository;
import com.universitasjatis.universitasjatis.service.MahasiswaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class MahasiswaServiceImpl implements MahasiswaService {

    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    @Override
    public MahasiswaReqDto getMahasiswaDto(String npm) {
        log.info("finding mahasiswa npm : {}",npm);
        Mahasiswa mahasiswa = mahasiswaRepository.findByNpm(npm).orElseThrow(DataNotFoundException::new);
        return convertMahasiswaToDto(mahasiswa,MahasiswaReqDto.class);
    }

    @Override
    public List<MahasiswaReqDto> getAllMahasiswaReqDtos(){
        log.info("finding All mahasiswa");
        return mahasiswaRepository.findAll().stream()
                .map(mahasiswa -> (MahasiswaReqDto)convertMahasiswaToDto(mahasiswa,MahasiswaReqDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public MahasiswaReqDto saveMahasiswaReqDto(MahasiswaReqDto mahasiswaReqDto) {
        mahasiswaRepository.findByNpm(mahasiswaReqDto.getNpm()).ifPresent(mahasiswa -> {
            throw new JatisExeption("Mahasiswa sudah terdaftar");
        });
        Mahasiswa mahasiswaNew = convertMahasiswaToDto(mahasiswaReqDto,Mahasiswa.class);
        log.info("saving mahasiswa npm : {}",mahasiswaReqDto.getNpm());
        mahasiswaRepository.save(mahasiswaNew);
        return mahasiswaReqDto;
    }

    @Override
    public MahasiswaReqDto updateMahasiswaReqDto(MahasiswaReqDto mahasiswaReqDto) {
        Mahasiswa mahasiswa = mahasiswaRepository.findByNpm(mahasiswaReqDto.getNpm()).orElseThrow(DataNotFoundException::new);
        BeanUtils.copyProperties(mahasiswaReqDto,mahasiswa);
        log.info("updating mahasiswa npm : {}",mahasiswaReqDto.getNpm());
        mahasiswaRepository.save(mahasiswa);
        return mahasiswaReqDto;
    }

    @Override
    public void deleteMahasiswaReqDto(String npm) {
        Mahasiswa mahasiswa = mahasiswaRepository.findByNpm(npm).orElseThrow(DataNotFoundException::new);
        log.info("deleting mahasiswa npm : {}",npm);
        mahasiswaRepository.delete(mahasiswa);
    }

    private <R,P> R convertMahasiswaToDto(P source, Class<?> aClass) {
        try {
            R newObj = (R) aClass.newInstance();
            BeanUtils.copyProperties(source,newObj);
            return newObj;

        } catch (Exception e){
            log.error("Fail to convert Data",e);
            throw new JatisExeption("Internal Error : Hubungi Admin");
        }
    }

}
