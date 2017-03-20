package com.spirit.project.sysmgr.dao.dao.specification;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.spirit.project.sysmgr.dao.po.RolePO;

public class RoleSpecification {
	public static Specification<RolePO> querySpecification(Map<String, Object> filter) {
		return new Specification<RolePO>() {
			@Override
			public Predicate toPredicate(Root<RolePO> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = Lists.newArrayList();
				String name = (String) filter.get("name");

				if (!StringUtils.isEmpty(name)) {
					Predicate nameLike = cb.like(root.get("name").as(String.class), "%" + name.trim() + "%");
					predicates.add(nameLike);
				}
				return predicates.isEmpty() ? cb.conjunction()
						: cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
}
