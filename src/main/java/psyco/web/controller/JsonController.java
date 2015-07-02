package psyco.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import psyco.entity.*;
import psyco.repository.*;
import psyco.web.SearchFormData;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/json")
public class JsonController {

	@Autowired
	TypeTagRepository type;
	@Autowired
	CityRepository city;
	@Autowired
	AntiqueRepository an;
	@Autowired
	FavorRepository favor;
	@Autowired
	AgeRepository age;
	@Autowired
	ExchangeRepository ex;
	@Autowired
	DistinguishRepository dis;
	@Autowired
	NewsDao news;
	@Autowired
	ArticleDao article;

	List<City> CITY_ALL = null;

	@RequestMapping(value = "all", method = RequestMethod.GET)
	public @ResponseBody
	List<Antique> all(@RequestBody String data) {
		return an.findAll();
	}

	@RequestMapping(value = "recommond", method = RequestMethod.GET)
	public @ResponseBody
	List<Antique> recommond() {
		return an.itemsOrderByDateupload(new PageRequest(1, 8));
	}

	@RequestMapping(value = "hot", method = RequestMethod.GET)
	public @ResponseBody
	List<Antique> hot() {
		return an.itemsOrderByDateupload(new PageRequest(0, 8));
	}

	@RequestMapping(value = "search/type/{typeID}", method = RequestMethod.GET)
	public @ResponseBody
	Page<Antique> searchWithType(@PathVariable int typeID) {
		System.out.println(an.searchByType(typeID, new PageRequest(0, AntiqueRepository.PAGE_SIZE)).getContent());
		return an.searchByType(typeID, new PageRequest(0, AntiqueRepository.PAGE_SIZE));
	}

	// @RequestMapping(value = "search", method = RequestMethod.GET)
	// public @ResponseBody
	// /* Page<Antique> search(@RequestParam("key") String key, @RequestParam(required = false, value = "page", defaultValue = "0") int page) { */
	// Page<Antique> search(@ModelAttribute _SearchFormData fd) {
	// if (fd.getType() < 0)
	// return an.search(fd.key, fd.priceMin, fd.priceMax, new PageRequest(fd.page, AntiqueRepository.PAGE_SIZE, new Sort(fd.asc ? Sort.Direction.ASC :
	// Sort.Direction.DESC, fd.sort)));
	// return an.search(fd.key, fd.priceMin, fd.priceMax, fd.getType(), new PageRequest(fd.page, AntiqueRepository.PAGE_SIZE, new Sort(fd.asc ?
	// Sort.Direction.ASC : Sort.Direction.DESC, fd.sort)));
	// }
	@RequestMapping(value = "search", method = RequestMethod.GET)
	public ModelAndView search(@ModelAttribute SearchFormData fd) {
		ModelAndView re = new ModelAndView("search");
		Page<Antique> page = null;
		if (fd.getType() < 0)
			page = an.search(fd.key, fd.priceMin, fd.priceMax, new PageRequest(fd.page, AntiqueRepository.PAGE_SIZE, new Sort(fd.asc ? Sort.Direction.ASC : Sort.Direction.DESC, fd.sort)));
		else
			page = an.search(fd.key, fd.priceMin, fd.priceMax, fd.getType(), new PageRequest(fd.page, AntiqueRepository.PAGE_SIZE, new Sort(fd.asc ? Sort.Direction.ASC : Sort.Direction.DESC, fd.sort)));
		re.addObject("result", page);
		re.addObject("conditions", fd);
		return re;
	}

	@RequestMapping(value = "types", method = RequestMethod.GET)
	public @ResponseBody
	List<TypeTag> typeTags() {
		return type.getRoots();
	}

	public List<City> getCityALL() {
		List<City> re = city.getProvinces();
		for (City p : re) {
			p.setChildren(city.getCities(p.getId()));
			for (City c : p.getChildren())
				c.setChildren(city.getDistricts(c.getId()));
		}
		return re;
	}

	@RequestMapping(value = "city/all", method = RequestMethod.GET)
	public @ResponseBody
	List<City> city_all() {
		if (CITY_ALL == null)
			CITY_ALL = getCityALL();
		return CITY_ALL;
	}

	@RequestMapping(value = "city/provinces", method = RequestMethod.GET)
	public @ResponseBody
	List<City> city_provinces() {
		return city.getProvinces();
	}

	@RequestMapping(value = "city/cities/{provinceID}", method = RequestMethod.GET)
	public @ResponseBody
	List<City> city_cities(@PathVariable int provinceID) {
		return city.getCities(provinceID);
	}

	@RequestMapping(value = "city/districts/{cityID}", method = RequestMethod.GET)
	public @ResponseBody
	List<City> city_districts(@PathVariable int cityID) {
		return city.getDistricts(cityID);
	}

	@RequestMapping(value = "city/id/{ID}", method = RequestMethod.GET)
	public @ResponseBody
	City city_byID(@PathVariable int ID) {
		return city.findOne(ID);
	}

	@RequestMapping(value = "favors/{userID}", method = RequestMethod.GET)
	public @ResponseBody
	List<Favor> favors(@PathVariable int userID) {
		// return favor.findByUserID(userID);
		return new LinkedList<Favor>();
	}

	@RequestMapping(value = "myPublishedItems/{userID}", method = RequestMethod.GET)
	public @ResponseBody
	List<Antique> myPublishedItems(@PathVariable long userID) {
		return an.findByUser(userID);
	}

	@RequestMapping(value = "ages", method = RequestMethod.GET)
	public @ResponseBody
	List<Age> ages() {
		return age.findAll();
	}

	@RequestMapping(value = "distinguish/user/{userID}", method = RequestMethod.GET)
	public @ResponseBody
	List<Distinguish> distinguishByUser(@PathVariable("userID") long userID) {
		return dis.findByUserID(userID);
	}

	@RequestMapping(value = "distinguish/status/{status}", method = RequestMethod.GET)
	public @ResponseBody
	List<Distinguish> distinguishByStatus(@PathVariable("status") int status) {
		return dis.findByStatusLessThan(status);
	}

	@RequestMapping(value = "exchange/status/{status}", method = RequestMethod.GET)
	public @ResponseBody
	List<Exchange> exchangeByStatus(@PathVariable("status") int status) {
		return ex.findByStatusLessThan(status);
	}

	@RequestMapping(value = "exchangeable/{level}", method = RequestMethod.GET)
	public @ResponseBody
	List<Antique> exchangesByUserCapable(@PathVariable("level") int level) {
		return an.findByUserLevel(level);
	}

	@RequestMapping(value = "exchange/user/{userID}", method = RequestMethod.GET)
	public @ResponseBody
	List<Exchange> exchangesByUser(@PathVariable("userID") long userID) {
		return ex.findByUserID(userID);
	}

	@RequestMapping(value = "exchange/usable/{userID}", method = RequestMethod.GET)
	public @ResponseBody
	List<Antique> exchangesMyCapable(@PathVariable("userID") long userID) {
		return an.getMyItemsExchangable(userID);
	}

	@RequestMapping(value = "article/hot", method = RequestMethod.GET)
	public @ResponseBody
	List<Article> articleHot() {
		return article.findAll(new PageRequest(0, 4)).getContent();
	}

	@RequestMapping(value = "news/hot", method = RequestMethod.GET)
	public @ResponseBody
	List<News> newsHot() {
		return news.findAll(new PageRequest(0, 4)).getContent();
	}
}
