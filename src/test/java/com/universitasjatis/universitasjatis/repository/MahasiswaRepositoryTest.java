package com.universitasjatis.universitasjatis.repository;

import com.universitasjatis.universitasjatis.entity.Mahasiswa;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Transactional
class MahasiswaRepositoryTest {

    @Autowired
    MahasiswaRepository mahasiswaRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByNpmSuccess(){
        String npm = "08728272";
        Optional<Mahasiswa> mahasiswa = mahasiswaRepository.findByNpm(npm);

        Assertions.assertThat(mahasiswa.isPresent()).isTrue();
        Assertions.assertThat(mahasiswa.get().getNpm()).isEqualTo(npm);

    }
}