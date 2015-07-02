package psyco.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import psyco.entity.Antique;
import psyco.entity.User;
import psyco.web.SearchFormData;

//@Service
@Component
public class AntiqueService {

	@Autowired
	AntiqueRepository an;

	public Page<Antique> search(SearchFormData fd, User u, Pageable pageable) {
		if (fd.isOnlyShowExchange() && u != null)
			return fd.getType() < 0 ? an.searchWithExchange(fd, u, pageable) : an.searchWithTypeExchange(fd, u, pageable);
		return fd.getType() < 0 ? an.search(fd, pageable) : an.searchWithType(fd, pageable);
	}
}
