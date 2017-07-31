package com.spirit.project.sysmgr.ui.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spirit.feignconfig.FeignClientConfig;
import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.sysmgr.ui.client.fallback.UserFeignClientFallback;
import com.spirit.project.sysmgr.ui.constant.SpiritServiceConsts;
import com.spirit.project.sysmgr.ui.vo.user.UserModifyPasswordVO;
import com.spirit.project.sysmgr.ui.vo.user.UserVO;

/**
 * 用户Feign Client
 * 
 * @author dante
 *
 */
@FeignClient(name = SpiritServiceConsts.PROJECT_SYSMGR_SERVER_NAME, fallback = UserFeignClientFallback.class, configuration = FeignClientConfig.class)
public interface UserFeignClient {

	@RequestMapping(method = RequestMethod.POST, value = "/user/query_page")
	BaseResp<PageResp<UserVO>> findPage(PageReq pageReq);

	@RequestMapping(method = RequestMethod.POST, value = "/user/query_by_id/{id}")
	BaseResp<UserVO> findByUserId(@PathVariable("id") Long id);

	@RequestMapping(method = RequestMethod.POST, value = "/user/query_by_account/{account}")
	BaseResp<UserVO> findByAccount(@PathVariable("account") String account);

	@RequestMapping(method = RequestMethod.POST, value = "/user/query_by_role_id/{roleId}")
	BaseResp<List<UserVO>> findByRoleId(@PathVariable("roleId") Long roleId);

	@RequestMapping(method = RequestMethod.POST, value = "/user/add")
	BaseResp<UserVO> addUser(UserVO userVO);

	@RequestMapping(method = RequestMethod.POST, value = "/user/update")
	BaseResp<UserVO> updateUser(UserVO userVO);

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.DELETE, value = "/user/delete")
	BaseResp deleteUser(UserVO userVO);

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "/user/lock_user")
	BaseResp lockUser(UserVO userVO);

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "/user/release_lock_user")
	BaseResp releaseLockUser(UserVO userVO);

	@RequestMapping(method = RequestMethod.POST, value = "/user/check_password")
	BaseResp<Boolean> checkPassword(UserModifyPasswordVO userModifyPasswordVO);

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "/user/modify_password")
	BaseResp modifyPassword(UserModifyPasswordVO userModifyPasswordVO);
}
