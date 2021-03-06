package com.ktvme.module.system.position.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ktvme.common.domain.BaseEntity;
import lombok.Data;

/**
 * 岗位关联关系
 *
 * @author zzr
 */
@Data
@TableName("t_position_relation")
public class PositionRelationEntity extends BaseEntity {

    /**
     * 岗位ID
     */
    private Long positionId;

    /**
     * 员工ID
     */
    private Long employeeId;

}
