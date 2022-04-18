package com.ktvme.module.system.position.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ktvme.common.domain.BaseEntity;
import lombok.Data;

/**
 * 岗位
 *
 * @author zzr
 */
@Data
@TableName("t_position")
public class PositionEntity extends BaseEntity {

    /**
     * 岗位名称
     */
    private String positionName;

    /**
     * 岗位描述
     */
    private String remark;

}
