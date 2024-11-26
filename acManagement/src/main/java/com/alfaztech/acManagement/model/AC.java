package com.alfaztech.acManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AC {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String brandName;
	String modelName;
	String type;
	String capacity;
	int star;
	String color;
	int year;
	int age;
	String imageUrl;
	
	
	
	public AC() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AC(int id, String brandName, String modelName, String type, String capacity, int star, String color,
			int year, int age, String imageUrl) {
		this.id = id;
		this.brandName = brandName;
		this.modelName = modelName;
		this.type = type;
		this.capacity = capacity;
		this.star = star;
		this.color = color;
		this.year = year;
		this.age = age;
		this.imageUrl = imageUrl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	@Override
	public String toString() {
		return "AC [id=" + id + ", brandName=" + brandName + ", modelName=" + modelName + ", type=" + type
				+ ", capacity=" + capacity + ", star=" + star + ", color=" + color + ", year=" + year + ", age=" + age
				+ ", imageUrl=" + imageUrl + "]";
	}
	
	
	

}
