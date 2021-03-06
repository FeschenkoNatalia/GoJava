package ua.com.goit.gojava.andriidnikitin.MyShop.domain.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="GOOD_TYPE")
public class GoodType implements Serializable {
	
	private static final long serialVersionUID = 5672782960646928322L;
	
	@Id
    @GeneratedValue
    @Column(columnDefinition="serial", name="type_id")
	private Integer id;	

	@Column(name="NAME", unique=true)
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(columnDefinition="integer", name="parent_id")
	private GoodType parent;
	
	@OneToMany(mappedBy="parent", fetch = FetchType.EAGER)
	private Set<GoodType> children;
	
	@OneToMany(mappedBy="type", fetch = FetchType.EAGER)
	private Set<Good> goods;
	
	public Set<Good> getGoods() {
		return goods;
	}

	public void setGoods(Set<Good> goods) {
		this.goods = goods;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
	public GoodType getParent() {
		return parent;
	}	
	
	public void setParent(GoodType parent) {
		this.parent = parent;
	}
	
	public Set<GoodType> getChildren() {
		return children;
	}

	public void setChildren(Set<GoodType> children) {
		this.children = children;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + prime * 3* ((id == null) ? 0 : id.hashCode()) + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GoodType other = (GoodType) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
