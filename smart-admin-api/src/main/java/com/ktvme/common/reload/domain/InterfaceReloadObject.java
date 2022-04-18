package com.ktvme.common.reload.domain;

import com.ktvme.common.reload.interfaces.SmartReloadable;
import com.ktvme.common.reload.domain.entity.ReloadItem;
import com.ktvme.common.reload.domain.entity.SmartReloadResult;

/**
 * Reload 处理程序的实现类
 * 用于处理以接口实现的处理类
 *
 * @author zhuoda
 */
public class InterfaceReloadObject extends AbstractSmartReloadObject {

    private SmartReloadable object;

    public InterfaceReloadObject(SmartReloadable object) {
        super();
        this.object = object;
    }

    @Override
    public SmartReloadResult reload(ReloadItem reloadItem) {
        SmartReloadResult reloadResult = new SmartReloadResult();
        reloadResult.setArgs(reloadItem.getArgs());
        reloadResult.setIdentification(reloadItem.getIdentification());
        reloadResult.setTag(reloadItem.getTag());
        try {
            boolean res = object.reload(reloadItem);
            reloadResult.setResult(res);
        } catch (Throwable e) {
            reloadResult.setException(getStackTrace(e));
        }
        return reloadResult;
    }

}
