package skynet.isi.resultmodel;

import java.util.ArrayList;
import java.util.List;

import skynet.isi.model.Category;

public class CategoryModel {

	private String categoryName;
	private List<Category> child = new ArrayList<Category>();

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Category> getChild() {
		return child;
	}

	public void setChild(List<Category> child) {
		this.child = child;
	}

}
