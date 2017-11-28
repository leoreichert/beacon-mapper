package br.furb.tcc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.furb.tcc.model.Beacon;
import br.furb.tcc.model.BeaconAccess;

public interface BeaconAccessRepository extends CrudRepository<BeaconAccess, Long> {
	
	public List<BeaconAccess> findByIdBeacon(Beacon idBeacon);
	 
}
