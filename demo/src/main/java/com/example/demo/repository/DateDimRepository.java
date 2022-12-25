package com.example.demo.repository;

import com.example.demo.model.entity.DateDim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateDimRepository extends JpaRepository<DateDim, Long> {

    DateDim getDateDimByFullDate(String date);

}
