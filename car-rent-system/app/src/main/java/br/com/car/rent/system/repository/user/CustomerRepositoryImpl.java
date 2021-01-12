
package br.com.car.rent.system.repository.user;

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

import br.com.car.rent.system.entity.Customer;
import br.com.car.rent.system.entity.Customer_;
import br.com.car.rent.system.filter.UserFilter;

public class CustomerRepositoryImpl implements CustomerRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Customer> searchCustomerFilter(final UserFilter userFilter, final Pageable pageable) {
		final CriteriaBuilder builder = manager.getCriteriaBuilder();
		final CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
		final Root<Customer> root = criteria.from(Customer.class);

		final Predicate[] predicates = createRestrictions(userFilter, builder, root);
		criteria.where(predicates);

		final TypedQuery<Customer> query = manager.createQuery(criteria);
		addPagingRestrictions(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, countRecordAmount(userFilter));
	}

	private Predicate[]
			createRestrictions(final UserFilter userFilter, final CriteriaBuilder builder, final Root<Customer> root) {
		final List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(userFilter.getName())) {
			predicates.add(builder.like(builder.lower(root.get(Customer_.name)),
					"%" + userFilter.getName().toLowerCase() + "%"));
		}

		if (!StringUtils.isEmpty(userFilter.getCpfCnpj())) {
			predicates.add(builder.like(builder.lower(root.get(Customer_.cpfCnpj)),
					"%" + userFilter.getCpfCnpj().toLowerCase() + "%"));
		}

		if (!StringUtils.isEmpty(userFilter.getMail())) {
			predicates.add(builder.like(builder.lower(root.get(Customer_.mail)),
					"%" + userFilter.getMail().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void addPagingRestrictions(final TypedQuery<Customer> query, final Pageable pageable) {
		final int currentPage = pageable.getPageNumber();
		final int totalRecordsPerPage = pageable.getPageSize();
		final int firstPageRegistration = currentPage * totalRecordsPerPage;

		query.setFirstResult(firstPageRegistration);
		query.setMaxResults(totalRecordsPerPage);
	}

	private Long countRecordAmount(final UserFilter userFilter) {
		final CriteriaBuilder builder = manager.getCriteriaBuilder();
		final CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		final Root<Customer> root = criteria.from(Customer.class);

		final Predicate[] predicates = createRestrictions(userFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
