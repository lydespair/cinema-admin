package com.ruoyi.user.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.user.service.IUsersService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.user.domain.Users;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户管理Controller
 * 
 * @author ruoyi
 * @date 2024-08-28
 */
@RestController
@RequestMapping("/users/manage")
public class UsersController extends BaseController
{
    @Autowired
    private IUsersService usersService;

    /**
     * 查询用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('users:manage:list')")
    @GetMapping("/list")
    public TableDataInfo list(Users users)
    {
        startPage();
        List<Users> list = usersService.selectUsersList(users);
        return getDataTable(list);
    }

    /**
     * 导出用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('users:manage:export')")
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Users users)
    {
        List<Users> list = usersService.selectUsersList(users);
        ExcelUtil<Users> util = new ExcelUtil<Users>(Users.class);
        util.exportExcel(response, list, "用户管理数据");
    }

    /**
     * 获取用户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('users:manage:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") String userId)
    {
        return success(usersService.selectUsersByUserId(userId));
    }

    /**
     * 新增用户管理
     */
    @PreAuthorize("@ss.hasPermi('users:manage:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Users users)
    {
        return toAjax(usersService.insertUsers(users));
    }

    /**
     * 修改用户管理
     */
    @PreAuthorize("@ss.hasPermi('users:manage:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Users users)
    {
        return toAjax(usersService.updateUsers(users));
    }

    /**
     * 删除用户管理
     */
    @PreAuthorize("@ss.hasPermi('users:manage:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable String[] userIds)
    {
        return toAjax(usersService.deleteUsersByUserIds(userIds));
    }
}
