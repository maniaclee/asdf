package psyco.web;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class SearchFormData {
	public static final int PAGE_SIZE = 40;
	public String key = "";
	public double priceMin = 0;
	public double priceMax = Integer.MAX_VALUE;
	public String sort = "id";
	public boolean asc = true;
	public int page = 0;
	public int type = -1;
	public boolean onlyShowExchange = false;

	public SearchFormData() {
	}

	public PageRequest createPageRequest() {
		return new PageRequest(this.page, PAGE_SIZE, new Sort(this.asc ? Sort.Direction.ASC : Sort.Direction.DESC, this.sort));
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public double getPriceMin() {
		return priceMin;
	}

	public void setPriceMin(double priceMin) {
		this.priceMin = priceMin;
	}

	public double getPriceMax() {
		return priceMax;
	}

	public void setPriceMax(double priceMax) {
		this.priceMax = priceMax;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isOnlyShowExchange() {
		return onlyShowExchange;
	}

	public void setOnlyShowExchange(boolean onlyShowExchange) {
		this.onlyShowExchange = onlyShowExchange;
	}

}