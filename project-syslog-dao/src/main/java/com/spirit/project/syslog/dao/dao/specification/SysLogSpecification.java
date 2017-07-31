package com.spirit.project.syslog.dao.dao.specification;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.spirit.project.commom.util.DateUtils;
import com.spirit.project.syslog.dao.po.SysLogPO;

/**
 * 用户查询规约
 * 
 * @author dante
 *
 */
public class SysLogSpecification {
	
	private SysLogSpecification() {
		throw new IllegalAccessError("UserSpecification 不可实例化！");
	}
	
	/**
	 * 构造多参数查询规范
	 * 
	 * @param filter
	 * @param sortCol
	 * @param sortDir
	 * @return
	 */
	public static Specification<SysLogPO> querySpecification(Map<String, Object> filter) {
		return new Specification<SysLogPO>(){
			@Override
			public Predicate toPredicate(Root<SysLogPO> root, CriteriaQuery<? extends Object> query, CriteriaBuilder cb) {
				List<Predicate> predicates = Lists.newArrayList();
				String ip = (String) filter.get("ip");
				String account = (String) filter.get("account");
				String url = (String) filter.get("url");
				String visitStartTime = (String) filter.get("visitStartTime");
				String visitEndTime = (String) filter.get("visitEndTime");
				
				if(!StringUtils.isEmpty(ip)) {
					Predicate ipLike = cb.like(root.get("ip").as(String.class), "%"+ip.trim()+"%");
					predicates.add(ipLike);
				}
				if(!StringUtils.isEmpty(account)) {
					Predicate accountLike = cb.like(root.get("user_account").as(String.class), "%"+account.trim()+"%");
					predicates.add(accountLike);
				}
				if(!StringUtils.isEmpty(url)) {
					Predicate urlLike = cb.like(root.get("url").as(String.class), "%"+url.trim()+"%");
					predicates.add(urlLike);
				}
				if(!StringUtils.isEmpty(visitStartTime)) {
					Date startVisit = DateUtils.parseDate(visitStartTime);
					Predicate visitTimeGt = cb.greaterThanOrEqualTo(root.get("visitTime").as(Date.class), startVisit);
					predicates.add(visitTimeGt);
				}
				if(!StringUtils.isEmpty(visitEndTime)) {
					Date endVisit = DateUtils.parseDate(visitEndTime);
					Predicate visitTimeLt = cb.lessThanOrEqualTo(root.get("visitTime").as(Date.class), endVisit);
					predicates.add(visitTimeLt);
				}
				return predicates.isEmpty() ? cb.conjunction() : cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}};
	}
	
}