package com.quxueyuan.server.dao.manual;


import com.github.pagehelper.Page;
import com.quxueyuan.bean.vo.req.SysUserSearchReq;

import java.util.Map;

/**
 * @Author liuxueliang@juzifenqi.com
 * @Date 2018年07月10日 16:08
 * @Description:
 */
public interface SysUserManualMapper {
    /**
     * 分页查询 pagehelper 使用
     *
     * @param search
     * @return
     */
    Page<Map<String, Object>> findList(SysUserSearchReq search);
}
