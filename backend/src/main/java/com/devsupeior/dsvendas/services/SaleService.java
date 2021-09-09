package com.devsupeior.dsvendas.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsupeior.dsvendas.dto.SaleDTO;
import com.devsupeior.dsvendas.entities.Sale;
import com.devsupeior.dsvendas.repositories.SaleRepository;
import com.devsupeior.dsvendas.repositories.SellerRepository;

@Service
public class SaleService {
	
	@Autowired
	private SaleRepository repository;
	
	@Autowired
	private SellerRepository sellerRepository;
	
	@Transactional(readOnly = true)
	public Page<SaleDTO> findAll(Pageable pageable){
		sellerRepository.findAll(); //Necessário para que não seja feita 5 consutas de sellers no banco, aqui estamos trazendo todos 
		                           // e armazenando em memoria cash
		Page<Sale> result = repository.findAll(pageable);
		return result.map(x -> new SaleDTO(x));
	}
	
}
