
package br.com.car.rent.system.repository.car;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import br.com.car.rent.system.entity.Car;
import br.com.car.rent.system.entity.Car_;
import br.com.car.rent.system.filter.CarFilter;

public class CarRepositoryImpl implements CarRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Car> searchCarFilter(final CarFilter carFilter, final Pageable pageable) {
		final CriteriaBuilder builder = manager.getCriteriaBuilder();
		final CriteriaQuery<Car> criteria = builder.createQuery(Car.class);
		final Root<Car> root = criteria.from(Car.class);

		final Predicate[] predicates = createRestrictions(carFilter, builder, root);
		criteria.where(predicates);

		final TypedQuery<Car> query = manager.createQuery(criteria);
		addPagingRestrictions(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, countRecordAmount(carFilter));
	}

	private Predicate[]
			createRestrictions(final CarFilter carFilter, final CriteriaBuilder builder, final Root<Car> root) {
		final List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(carFilter.getBoard())) {
			predicates.add(
					builder.like(builder.lower(root.get(Car_.board)), "%" + carFilter.getBoard().toLowerCase() + "%"));
		}

		if (!StringUtils.isEmpty(carFilter.getBrand())) {
			predicates.add(
					builder.like(builder.lower(root.get(Car_.brand)), "%" + carFilter.getBrand().toLowerCase() + "%"));
		}

		if (!StringUtils.isEmpty(carFilter.getModel())) {
			predicates.add(
					builder.like(builder.lower(root.get(Car_.model)), "%" + carFilter.getModel().toLowerCase() + "%"));
		}

		if (carFilter.getMinYear() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(Car_.year), carFilter.getMinYear()));
		}

		if (carFilter.getMaxYear() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(Car_.year), carFilter.getMaxYear()));
		}

		if (carFilter.getMinPrice() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(Car_.price), carFilter.getMinPrice()));
		}

		if (carFilter.getMaxPrice() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(Car_.price), carFilter.getMaxPrice()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void addPagingRestrictions(final TypedQuery<Car> query, final Pageable pageable) {
		final int currentPage = pageable.getPageNumber();
		final int totalRecordsPerPage = pageable.getPageSize();
		final int firstPageRegistration = currentPage * totalRecordsPerPage;

		query.setFirstResult(firstPageRegistration);
		query.setMaxResults(totalRecordsPerPage);
	}

	private Long countRecordAmount(final CarFilter carFilter) {
		final CriteriaBuilder builder = manager.getCriteriaBuilder();
		final CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		final Root<Car> root = criteria.from(Car.class);

		final Predicate[] predicates = createRestrictions(carFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
