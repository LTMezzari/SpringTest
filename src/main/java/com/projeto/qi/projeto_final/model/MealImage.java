package com.projeto.qi.projeto_final.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity(name="tb_meal_image")
public class MealImage implements Serializable{

	private static final long serialVersionUID = -5959488128848894419L;

	@Id
	@GeneratedValue
	@Column(name="pk_meal_image")
	private Long id;
	
	@Lob
	@Column(name="image", columnDefinition="LONGBLOB")
	private Blob image;
	
	@OneToOne
	@NotNull(message="É necessário enviar a refeição cadastrada")
	private Meal meal;

	@Transient
	@NotNull(message="É necessário enviar uma imagem")
	private String imageBase64;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Meal getMeal() {
		return meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public String getImageBase64() {
		return imageBase64;
	}

	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}
}
