package results;

import java.util.List;

import entities.BasicPackage;

public class FilterResult {
	List<BasicPackage> searchResult;

	public FilterResult(List<BasicPackage> searchResult) {
		super();
		this.searchResult = searchResult;
	}

	public List<BasicPackage> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<BasicPackage> searchResult) {
		this.searchResult = searchResult;
	}
	
}
