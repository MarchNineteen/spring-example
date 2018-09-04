package com.wyb.shiro.controller.admin;

import com.github.pagehelper.PageInfo;
import com.wyb.shiro.config.annotation.SessionUser;
import com.wyb.shiro.dao.model.RoleDo;
import com.wyb.shiro.result.web.WebResult;
import com.wyb.shiro.result.web.WebResultEnum;
import com.wyb.shiro.service.MenuService;
import com.wyb.shiro.service.RoleService;
import com.wyb.shiro.service.UserRoleService;
import com.wyb.shiro.utils.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Kunzite
 */
@RequestMapping("/admin/sys")
@Controller
@Slf4j
public class SystemController {

    @Resource
    private RoleService roleService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private MenuService menuService;

    /**
     * 为用户分配角色
     *
     * @return
     */
    @RequestMapping(value = "/role/user/update", method = RequestMethod.POST)
    @ResponseBody
    public WebResult updateUserRoleByUserId(@RequestParam("userId") Long userId, @RequestParam("roleIds") Long[] roleIds) {
        if (null == userId || null == roleIds) {
            return new WebResult(WebResultEnum.ERROR_PARAMS);
        }
        int i = userRoleService.update(userId, roleIds);
        return WebResult.success();
    }

    /**
     * 添加角色
     *
     * @return
     */
    @RequestMapping(value = "/role/save", method = RequestMethod.POST)
    @ResponseBody
    public WebResult saveRole(RoleDo roleDo) {
        if (StringUtils.isEmpty(roleDo.getRoleName()) || StringUtils.isEmpty(roleDo.getDescription())) {
            return new WebResult(WebResultEnum.ERROR_PARAMS);
        }
        roleService.saveRole(roleDo);
        return WebResult.success();
    }

    /**
     * 角色列表
     *
     * @return
     */
    @RequestMapping(value = "/role/list_{pageCurrent}_{pageSize}_{pageCount}")
    public String listRole(@SessionUser String userName, RoleDo role, @PathVariable Integer pageCurrent,
                           @PathVariable Integer pageSize,
                           @PathVariable Integer pageCount,
                           Model model) {
        log.info(userName);
        if (pageSize == 0) pageSize = 20;
        if (pageCurrent == 0) pageCurrent = 1;
        PageInfo<RoleDo> page = roleService.listRole(pageCurrent, pageSize, role);
        page.getPages();
        String pageHTML = PageUtil.getPageContent("/admin/sys/role/list_${pageCurrent}_${pageSize}_${pageCount}?role.roleName=" + role.getRoleName(), page.getPageNum(), page.getPageSize(), page.getPages());
        model.addAttribute("list", page.getList());
        model.addAttribute("role", role);
        model.addAttribute("pageHTML", pageHTML);
        return "admin/sys/roleList";
    }

    /**
     * 角色列表
     *
     * @return
     */
//    @ResponseBody
//    @RequestMapping(value = "/role/list", method = RequestMethod.GET)
//    public WebResult<List<RoleDo>> listRole() {
//        return WebResult.success(roleService.listRole());
//    }


    /**
     * 根据角色查询角色拥有的权限
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/role/menu/list", method = RequestMethod.POST)
    public WebResult listMenuByRoleId(@RequestParam("roleId") Long roleId) {
        if (roleId <= 0 || null == roleId) {
            return new WebResult(WebResultEnum.ERROR_PARAMS);
        }
        return WebResult.success();
    }

    /**
     * 为角色分配权限
     *
     * @return
     */
    @RequestMapping(value = "/role/menu/save", method = RequestMethod.POST)
    public WebResult saveRoleMenuByRoleId() {

        return WebResult.success();
    }
}
