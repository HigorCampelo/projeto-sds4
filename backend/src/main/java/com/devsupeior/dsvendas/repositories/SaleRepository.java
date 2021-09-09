package com.devsupeior.dsvendas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsupeior.dsvendas.dto.SaleSuccessDTO;
import com.devsupeior.dsvendas.dto.SaleSumDTO;
import com.devsupeior.dsvendas.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long>{
	//usando JPQL
	@Query("SELECT new com.devsupeior.dsvendas.dto.SaleSumDTO(obj.seller, SUM(obj.amount)) FROM Sale AS obj GROUP BY obj.seller")
    List<SaleSumDTO> amountGroupedBySeller();
	
	@Query("SELECT new com.devsupeior.dsvendas.dto.SaleSuccessDTO(obj.seller, SUM(obj.visited), SUM(obj.deals)) FROM Sale AS obj GROUP BY obj.seller")
    List<SaleSuccessDTO> successGroupedBySeller();
}
