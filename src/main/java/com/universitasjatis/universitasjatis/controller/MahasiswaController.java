package com.universitasjatis.universitasjatis.controller;

import com.universitasjatis.universitasjatis.dto.MahasiswaReqDto;
import com.universitasjatis.universitasjatis.dto.ResponseDto;
import com.universitasjatis.universitasjatis.service.MahasiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/mahasiswa")
public class MahasiswaController {

    @Autowired
    MahasiswaService mahasiswaService;

    @GetMapping
    public ResponseDto<List<MahasiswaReqDto>> getAllMahasiswa() {
        return new ResponseDto<>(HttpStatus.OK, Collections.emptyList(), mahasiswaService.getAllMahasiswaReqDtos());
    }

    @GetMapping("/{npm}")
    public ResponseDto<MahasiswaReqDto> getMahasiswa(@PathVariable("npm")String npm) {
        MahasiswaReqDto mahasiswaReqDto = mahasiswaService.getMahasiswaDto(npm);
        return new ResponseDto<>(HttpStatus.OK, Collections.emptyList(), mahasiswaReqDto);
    }

    @PostMapping
    public ResponseDto<MahasiswaReqDto> saveMahasiswa(@RequestBody @Valid MahasiswaReqDto mahasiswaReqDto){
        mahasiswaService.saveMahasiswaReqDto(mahasiswaReqDto);
        return new ResponseDto<>(HttpStatus.OK, Collections.emptyList(), mahasiswaReqDto);
    }

    @PutMapping
    public ResponseDto<MahasiswaReqDto> updateMahasiswa(@RequestBody @Valid MahasiswaReqDto mahasiswaReqDto){
        mahasiswaService.updateMahasiswaReqDto(mahasiswaReqDto);
        return new ResponseDto<>(HttpStatus.OK, Collections.emptyList(), mahasiswaReqDto);
    }

    @DeleteMapping("/{npm}")
    public ResponseDto<String> deleteMahasiswa(@PathVariable("npm")String npm){
        mahasiswaService.deleteMahasiswaReqDto(npm);
        return new ResponseDto<>(HttpStatus.OK, Collections.emptyList(), npm);
    }
}
