package com.lss.auth.controller;

import com.lss.auth.annotation.PermissionDes;
import com.lss.auth.bean.Menu;
import com.lss.auth.bean.Role;
import com.lss.auth.service.PermissionService;
import com.lss.bean.form.PageForm;
import com.lss.meta.MetaUtils;
import com.lss.meta.bean.MetaData;
import com.lss.rest.API;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@PermissionDes(menu = {"系统设置","角色设置"})
@RestController
@Api(tags = "后台角色")
@RequestMapping("/manage/auth")
public class AuthPermissionAPI {

    private PermissionService permissionService;


    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @ApiOperation("查看所有权限")
    @PermissionDes(name = "查看所有权限")
    @GetMapping("permission")
    public API<List<Menu>> getAllPermission(@RequestParam(required = false) Integer manager, @RequestParam(required = false) Integer role){
        return API.ok(permissionService.getAllPermission(manager,role));
    }



    @ApiOperation("查看所有角色")
    @PermissionDes(name = "查看所有角色")
    @GetMapping("/role")
    public API<Page<Role>>  getRoleList(Role role, PageForm pageForm){

        return  API.ok(permissionService.getRoleList(role,pageForm));
    }

    @ApiOperation("删除角色")
    @PermissionDes(name = "删除角色")
    @DeleteMapping("/role")
    public API<Page<Role>>  deleteRole(@RequestParam String ids){
        permissionService.deleteRole(ids);
        return  API.ok();
    }

    @ApiOperation("查看角色的meta信息")
    @GetMapping("/role/meta")
    public API<MetaData>  getRoleMeta(){

        return  API.ok(MetaUtils.getMeta(Role.class));
    }

    @ApiOperation("增加角色")
    @PermissionDes(name = "增加角色")
    @PostMapping("/role")
    public API<Role>  editRole(@RequestBody Role role){
        return API.ok(permissionService.editRole(role));
    }




}
