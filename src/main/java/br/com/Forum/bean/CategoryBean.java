package br.com.Forum.bean;

import br.com.Forum.dao.CategoryDAO;
import br.com.Forum.domain.Category;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CategoryBean implements Serializable {
  private Category category;
  private List<Category> categories;
  
  public Category getCategory() {
    return category;
  }
  public void setCategory(Category category) {
    this.category = category;
  }
  public List<Category> getCategories() {
    return categories;
  }
  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }

  @PostConstruct
  public void index() {
    try {
      CategoryDAO categoryDAO = new CategoryDAO();
      categories = categoryDAO.index();
    } catch (RuntimeException e) {
    	Messages.addFlashGlobalError("Ocorreu um erro ao listar");
      e.printStackTrace();
    }
  }

  public void create() {
    category = new Category();
  }

  public void save() {
    try {
      CategoryDAO categoryDAO = new CategoryDAO();
      categoryDAO.save(category);

      category = new Category();
      categories = categoryDAO.index();
      Messages.addGlobalInfo("Salvo com sucesso!");
    } catch (RuntimeException e) {
      Messages.addGlobalInfo("Ocorreu um erro ao tentar salvar");
      e.printStackTrace();
    }
  }

  public void destroy(ActionEvent event) {
    try {
      category = (Category) event.getComponent().getAttributes().get("category-selected");
      CategoryDAO categoryDAO = new CategoryDAO();
      categoryDAO.destroy(category);
        
      categories = categoryDAO.index();
      Messages.addGlobalInfo("Removido com sucesso!");
	  } catch(RuntimeException e) {
      Messages.addGlobalError("Ocorreu um erro ao tentar remover!");
      e.printStackTrace();
	  }
  }

  public void edit(ActionEvent event) {
    try {
      category = (Category) event.getComponent().getAttributes().get("category-selected");
      CategoryDAO categoryDAO = new CategoryDAO();
      categoryDAO.edit(category);

      category = new Category();
      categories = categoryDAO.index();
      Messages.addGlobalInfo("Editado com sucesso!");
    } catch (RuntimeException e) {
	    Messages.addGlobalError("Ocorreu um erro ao tentar editar!");
      e.printStackTrace();
    }
  }
}
