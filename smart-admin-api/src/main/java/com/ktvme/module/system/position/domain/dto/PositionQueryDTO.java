package com.ktvme.module.system.position.domain.dto;

import com.ktvme.common.domain.PageParamDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 岗位
 *
 * @author zzr
 */
@Data
public class PositionQueryDTO extends PageParamDTO {

    @ApiModelProperty("岗位名称")
    private String positionName;

}
