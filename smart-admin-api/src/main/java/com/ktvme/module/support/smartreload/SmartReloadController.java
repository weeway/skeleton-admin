package com.ktvme.module.support.smartreload;

import com.ktvme.common.anno.NoValidPrivilege;
import com.ktvme.common.anno.OperateLog;
import com.ktvme.common.domain.ResponseDTO;
import com.ktvme.constant.SwaggerTagConst;
import com.ktvme.module.support.smartreload.domain.dto.ReloadItemUpdateDTO;
import com.ktvme.module.support.smartreload.domain.dto.ReloadItemVO;
import com.ktvme.module.support.smartreload.domain.dto.ReloadResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Smart Reload 路由
 *
 * @author listen
 * @date 2018/02/10 09:18
 */
@Api(tags = {SwaggerTagConst.Admin.MANAGER_SMART_RELOAD})
@OperateLog
@RestController
public class SmartReloadController {

    @Autowired
    private SmartReloadService smartReloadService;

    @ApiOperation(value = "获取全部Smart-reload项", notes = "获取全部Smart-reload项")
    @GetMapping("/smartReload/all")
    @NoValidPrivilege
    public ResponseDTO<List<ReloadItemVO>> listAllReloadItem() {
        return smartReloadService.listAllReloadItem();
    }

    @ApiOperation(value = "获取reload result", notes = "获取reload result")
    @GetMapping("/smartReload/result/{tag}")
    @NoValidPrivilege
    public ResponseDTO<List<ReloadResultVO>> queryReloadResult(@PathVariable("tag") String tag) {
        return smartReloadService.listReloadItemResult(tag);
    }

    @ApiOperation("通过tag更新标识")
    @PostMapping("/smartReload/update")
    @NoValidPrivilege
    public ResponseDTO<String> updateByTag(@RequestBody @Valid ReloadItemUpdateDTO updateDTO) {
        return smartReloadService.updateByTag(updateDTO);
    }
}
