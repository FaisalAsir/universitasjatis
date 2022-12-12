package com.universitasjatis.universitasjatis.service;

import com.universitasjatis.universitasjatis.dto.MahasiswaReqDto;

import java.util.List;

public interface MahasiswaService {
    MahasiswaReqDto getMahasiswaDto(String npm);

    List<MahasiswaReqDto> getAllMahasiswaReqDtos();

    MahasiswaReqDto saveMahasiswaReqDto(MahasiswaReqDto mahasiswaReqDto);
    MahasiswaReqDto updateMahasiswaReqDto(MahasiswaReqDto mahasiswaReqDto);

    void deleteMahasiswaReqDto(String npm);
}
