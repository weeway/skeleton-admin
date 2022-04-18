package com.ktvme.module.system.login.domain;

import com.ktvme.module.system.privilege.constant.PrivilegeTypeEnum;
import com.ktvme.common.anno.ApiModelPropertyEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * [  ]
 *
 * @author yandanyang
 * @version 1.0
 * @company 1024lab.net
 * @copyright (c) 2018 1024lab.netInc. All rights reserved.
 * @date 2019/8/21 0021 上午 10:28
 * @since JDK1.8
 */
@Data
public class LoginPrivilegeDTO {

    @ApiModelProperty("权限key")
    private String key;

    @ApiModelPropertyEnum(enumDesc = "菜单类型",value = PrivilegeTypeEnum.class)
    private Integer type;

    @ApiModelProperty("url")
    private String url;

    @ApiModelProperty("父级key")
    private String parentKey;

}
