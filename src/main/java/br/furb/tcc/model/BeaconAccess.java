package br.furb.tcc.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "beacon_access")
public class BeaconAccess {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name="date_access")
	private Date dateAccess;
	
	@Column(name="time_access")
	private Time timeAccess;
	
	@ManyToOne
	@JoinColumn(name="id_beacon")
	private Beacon idBeacon;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatAccess() {
		return dateAccess;
	}

	public void setDateAccess(Date dateAccess) {
		this.dateAccess = dateAccess;
	}

	public Time getTimeAccess() {
		return timeAccess;
	}

	public void setTimeAccess(Time timeAccess) {
		this.timeAccess = timeAccess;
	}

	public Beacon getIdBeacon() {
		return idBeacon;
	}

	public void setIdBeacon(Beacon idBeacon) {
		this.idBeacon = idBeacon;
	}

}
