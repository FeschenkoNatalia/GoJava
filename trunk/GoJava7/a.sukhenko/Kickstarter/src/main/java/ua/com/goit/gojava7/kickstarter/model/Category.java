package ua.com.goit.gojava7.kickstarter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@Table(name = "categories")
@NamedQueries({@NamedQuery(name = "Category.getAll", query = "select c from Category c")})
public class Category implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -1332987330114040046L;
    @Column
    private String            categoryName;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int               categoryId;

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Project> projects = new ArrayList<>();

    public Category() {
    }

    public Category(String categoryName, int categoryId) {
        super();
        this.categoryName = categoryName;
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category. categoryId: " + categoryId + " categoryName: " + categoryName;
    }
}
