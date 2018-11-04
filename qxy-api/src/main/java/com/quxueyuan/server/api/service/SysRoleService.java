package com.quxueyuan.server.api.service;


import com.quxueyuan.bean.domain.SysRole;
import com.quxueyuan.bean.domain.SysRoleDTO;
import com.quxueyuan.bean.vo.req.SysRoleSearch;
import com.quxueyuan.bean.vo.req.SysRoleVo;

import java.util.List;
import java.util.Map;


public interface SysRoleService {
    boolean save(SysRoleVo sysRoleVo);
    boolean update(SysRoleVo sysRoleVo);
    String batchRemove(String ids);
    List<SysRole> getByProject(String project);
    List<SysRole> getByName(String name);
    SysRole getByNameEqual(String name);
    SysRole getByCode(String code);
    List<SysRoleDTO> getByPage(SysRoleSearch sysRoleSearch);
    String getResourceNames(String resourceIds);
    int getPageCount(SysRoleSearch sysRoleSearch);
    List<Map<String, Object>> getOptionsAll();
    void removeById(Integer id);
}
