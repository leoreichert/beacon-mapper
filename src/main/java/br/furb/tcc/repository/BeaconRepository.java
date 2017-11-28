package br.furb.tcc.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.furb.tcc.model.Beacon;

public interface BeaconRepository extends CrudRepository<Beacon, Long> {
	
	Optional<Beacon> findByUrlid(String urlid);
	
	Optional<Beacon> findByUid(String uid);
	
}
