package br.com.fiap.thebestway.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.thebestway.domain.Corrida;

@Repository
public interface CorridaRepository extends JpaRepository<Corrida, Integer> {

}
