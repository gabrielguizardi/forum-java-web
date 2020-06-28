package br.com.Forum.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Post extends GenericDomain {
  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String subTitle;

  @Column(nullable = false)
  private String text;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Page page;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Category category;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Person person;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSubTitle() {
    return subTitle;
  }

  public void setSubTitle(String subTitle) {
    this.subTitle = subTitle;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Page getPage() {
	return page;
}

public void setPage(Page page) {
	this.page = page;
}

public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }
}