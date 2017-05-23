package com.spirit.project.sysmgr.ui.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.feignconfig.FeignClientConfig;
import com.spirit.project.sysmgr.ui.client.fallback.UserFeignClientFallback;
import com.spirit.project.sysmgr.ui.constant.SpiritServiceConsts;
import com.spirit.project.sysmgr.ui.vo.user.UserModifyPasswordVO;
import com.spirit.project.sysmgr.ui.vo.user.UserVO;

@FeignClient(name = SpiritServiceConsts.PROJECT_SYSMGR_SERVER_NAME, fallback = UserFeignClientFallback.class, configuration=FeignClientConfig.class)
public interface UserFeignClient {
	
	@RequestMapping(method = RequestMethod.POST, value = "/user/query_page")
	public BaseResp<PageResp<UserVO>> findPage(PageReq pageReq);
	
	@RequestMapping(method = RequestMethod.POST, value = "/user/query_by_id/{id}")
	public BaseResp<UserVO> findByUserId(@PathVariable("id") Long id);
	
	@RequestMapping(method = RequestMethod.POST, value = "/user/query_by_account/{account}")
	public BaseResp<UserVO> findByAccount(@PathVariable("account") String account);
	
	@RequestMapping(method = RequestMethod.POST, value = "/user/query_by_role_id/{roleId}")
	public BaseResp<List<UserVO>> findByRoleId(@PathVariable("roleId") Long roleId);
	
	@RequestMapping(method = RequestMethod.POST, value = "/user/add")
	public BaseResp<UserVO> addUser(UserVO userVO);
	
	@RequestMapping(method = RequestMethod.POST, value = "/user/update")
	public BaseResp<UserVO> updateUser(UserVO userVO);
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/user/delete")
	public BaseResp<? extends Object> deleteUser(UserVO userVO);
	
	@RequestMapping(method = RequestMethod.POST, value = "/user/lock_user")
	public BaseResp<? extends Object> lockUser(UserVO userVO);
	
	@RequestMapping(method = RequestMethod.POST, value = "/user/release_lock_user")
	public BaseResp<? extends Object> releaseLockUser(UserVO userVO);
	
	@RequestMapping(method = RequestMethod.POST, value = "/user/check_password")
	public BaseResp<Boolean> checkPassword(UserModifyPasswordVO userModifyPasswordVO);
	
	@RequestMapping(method = RequestMethod.POST, value = "/user/modify_password")
	public BaseResp<? extends Object> modifyPassword(UserModifyPasswordVO userModifyPasswordVO);
}
