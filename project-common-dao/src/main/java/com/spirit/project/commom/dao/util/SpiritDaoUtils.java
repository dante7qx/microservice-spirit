package com.spirit.project.commom.dao.util;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;

/**
 * JPA Specification 工具类
 * 
 * @author dante
 *
 */
public class SpiritDaoUtils {

	/**
	 * 构造排序, 默认按照主键（id）倒序（desc）
	 * 
	 * @param sortCol
	 * @param sortDir
	 * @return
	 */
	public static Sort buildJPABasicOrder(String sortCol, String sortDir) {
		if (StringUtils.isEmpty(sortCol)) {
			return new Sort(Direction.DESC, "id");
		}
		Sort sort = null;
		String[] sortColArr = sortCol.trim().split(",");
		String[] sortDirArr = sortDir.trim().split(",");
		int sortColLength = sortColArr.length;
		for (int i = 0; i < sortColLength; i++) {
			String col = sortColArr[i].trim();
			String dir = sortDirArr[i];
			if (i == 0) {
				sort = new Sort(buildDirection(dir), col);
				continue;
			}
			sort.and(new Sort(buildDirection(dir), col));
		}
		return sort;
	}

	/**
	 * 构造排序, 默认按照主键（id）倒序（desc）
	 * 
	 * @param root
	 * @param query
	 * @param cb
	 * @param sortCol
	 * @param sortDir
	 */
	public static void buildSpecificationOrder(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder cb, String sortCol,
			String sortDir) {
		if (!StringUtils.isEmpty(sortCol)) {
			List<Order> orders = Lists.newArrayList();
			String[] sortColArr = sortCol.trim().split(",");
			String[] sortDirArr = sortDir.trim().split(",");
			int sortColLength = sortColArr.length;
			for (int i = 0; i < sortColLength; i++) {
				String col = sortColArr[i].trim();
				String dir = sortDirArr[i].trim().toLowerCase();
				switch (dir) {
				case "asc":
					orders.add(cb.asc(root.get(col)));
					break;
				case "desc":
					orders.add(cb.desc(root.get(col)));
					break;
				}
			}
			query.orderBy(orders);
		} else {
			query.orderBy(cb.desc(root.get("id")));
		}
	}

	private static Direction buildDirection(String sortDir) {
		Direction direction = Direction.ASC;
		switch (sortDir.trim().toLowerCase()) {
		case "asc":
			direction = Direction.ASC;
			break;
		case "desc":
			direction = Direction.DESC;
			break;
		}
		return direction;
	}

}
