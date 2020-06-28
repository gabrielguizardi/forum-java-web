package br.com.Forum.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.Forum.dao.PageDAO;
import br.com.Forum.dao.PersonDAO;
import br.com.Forum.domain.Page;
import br.com.Forum.domain.Person;

@SuppressWarnings("Serial")
@ManagedBean
@ViewScoped

public class PageBean implements Serializable {
	private Page page;
	private List<Page> pages;
	private Person person;
	private List<Person> people;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Person> getPeople() {
		return people;
	}

	public void setPeople(List<Person> people) {
		this.people = people;
	}

	@PostConstruct
	public void index() {
		try {
			PageDAO pageDAO = new PageDAO();
			pages = pageDAO.index();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar");
			erro.printStackTrace();
		}
	}

	public void create() {
		try {
			person = new Person();

			PersonDAO personDAO = new PersonDAO();
			people = personDAO.index();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar um novo Grupo");
			erro.printStackTrace();
		}
	}

	public void save() {
		try {
			PageDAO pageDAO = new PageDAO();
			pageDAO.merge(page);

			page = new Page();

			PersonDAO personDAO = new PersonDAO();
			people = personDAO.index();

			pages = pageDAO.index();

			Messages.addGlobalInfo("Grupo salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma um grupo");
			erro.printStackTrace();
		}
	}

	public void destroy(ActionEvent evento) {
		try {
			page = (Page) evento.getComponent().getAttributes().get("cidadeSelecionada");

			PageDAO pageDAO = new PageDAO();
			pageDAO.destroy(page);

			pages = pageDAO.index();

			Messages.addGlobalInfo("Grupo removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover um grupo");
			erro.printStackTrace();
		}
	}
	
	public void edit(ActionEvent evento){
		try {
			page = (Page) evento.getComponent().getAttributes().get("GrupoSelecionada");

			PersonDAO personDAO = new PersonDAO();
			people = personDAO.index();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um grupo");
			erro.printStackTrace();
		}	
	}
}