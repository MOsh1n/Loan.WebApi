package com.moshin.loan.repository;

import com.moshin.loan.entity.table.ListaNegra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaNegraRepository extends JpaRepository<ListaNegra, Long>{
    
}
