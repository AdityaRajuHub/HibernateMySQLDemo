package com.demo.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

@Entity
@Table(name="topic")
//@SelectBeforeUpdate
@DynamicUpdate(value=true)
public class Topic {
	
	public Topic(){}
	
	public Topic(String name, String author, Publication publication, Publication officialPublication) {
		this.name = name;
		this.author = author;
		this.publication = publication;
		this.officialPublication = officialPublication;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="topic_id")
	private int id;
	@Column(name="topic_name")
	private String name;
	@Column(name="topic_author")
	private String author;
	
	@Embedded
	private Publication publication;
	
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name="name", column=@Column(name="off_pub_name")),
			@AttributeOverride(name="address", column=@Column(name="off_address")),
			@AttributeOverride(name="rating", column=@Column(name="off__pub_rating"))
	})
	private Publication officialPublication;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Publication getPublication() {
		return publication;
	}
	public void setPublication(Publication publication) {
		this.publication = publication;
	}
	public Publication getOfficialPublication() {
		return officialPublication;
	}
	public void setOfficialPublication(Publication officialPublication) {
		this.officialPublication = officialPublication;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Topic [id=").append(id).append(", name=").append(name).append(", author=").append(author)
				.append(", publication=").append(publication).append(", officialPublication=")
				.append(officialPublication).append("]");
		return builder.toString();
	}
	
	
}
