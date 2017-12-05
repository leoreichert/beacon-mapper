package br.furb.tcc.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "beacon")
public class Beacon {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "uid")
    private String uid;

    @Column(name = "urlid")
    private String urlid;
    
    @Column(name = "template_model")
    @Lob
    private String templateModel;
    
    @Column(name = "posicao_x")
    private Integer posicaoX;
    
    @Column(name = "posicao_y")
    private Integer posicaoY;
    
    @Column(name = "estado")
    private String estado;
    
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="idBeacon")   
    private Collection<BeaconAccess> access;
    
    @Transient
    private boolean selecionado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUrlid() {
		return urlid;
	}

	public void setUrlid(String urlid) {
		this.urlid = urlid;
	}
	
	public String getTemplateModel() {
		return templateModel;
	}

	public void setTemplateModel(String templateModel) {
		this.templateModel = templateModel;
	}
	
	public Integer getPosicaoX() {
		return posicaoX;
	}
	
	public void setPosicaoX(Integer posicaoX) {
		this.posicaoX = posicaoX;
	}
	
	public Integer getPosicaoY() {
		return posicaoY;
	}
	
	public void setPosicaoY(Integer posicaoY) {
		this.posicaoY = posicaoY;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@Transient
	public boolean isSelecionado() {
		return selecionado;
	}
	
	@Transient
	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}
	
	@Transient
	public boolean isNew() {
		return id == null;
	}
	
	@Override
	public String toString() {
		return String.format("Beacon[id=%d, uid='%s', urlid='%s', posX='%d', posY='%d']", id, uid, urlid, posicaoX, posicaoY);
	}
	
}
