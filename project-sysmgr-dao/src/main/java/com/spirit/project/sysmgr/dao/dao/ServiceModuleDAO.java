package com.spirit.project.sysmgr.dao.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.spirit.project.sysmgr.dao.po.ServiceModulePO;

public interface ServiceModuleDAO extends JpaRepository<ServiceModulePO, Long>, JpaSpecificationExecutor<ServiceModulePO> {

}
