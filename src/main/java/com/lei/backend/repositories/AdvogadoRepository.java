package com.lei.backend.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lei.backend.domain.Categoria;
import com.lei.backend.domain.Advogado;

@Repository
public interface AdvogadoRepository extends JpaRepository<Advogado, Integer>{
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Advogado obj INNER JOIN obj.categorias cat WHERE obj.name LIKE %:name% AND cat IN :categorias")
	Page<Advogado> findDistinctByNameContainingAndCategoriasIn(@Param("name") String name, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);

	@Transactional(readOnly=true)
	Advogado findByInscricao(String inscricao);
}
