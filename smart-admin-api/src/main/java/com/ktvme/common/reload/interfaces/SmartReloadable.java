package com.ktvme.common.reload.interfaces;

import com.ktvme.common.reload.domain.entity.ReloadItem;

/**
 * reload 接口<br>
 * 需要reload的业务实现类
 */
@FunctionalInterface
public interface SmartReloadable {

    /**
     * reload
     *
     * @param reloadItem
     * @return boolean
     */
    boolean reload(ReloadItem reloadItem);
}
