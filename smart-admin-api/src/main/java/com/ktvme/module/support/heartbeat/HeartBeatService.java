package com.ktvme.module.support.heartbeat;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ktvme.common.domain.PageParamDTO;
import com.ktvme.common.domain.PageResultDTO;
import com.ktvme.common.domain.ResponseDTO;
import com.ktvme.common.heartbeat.AbstractHeartBeatCommand;
import com.ktvme.common.heartbeat.HeartBeatConfig;
import com.ktvme.common.heartbeat.HeartBeatLogger;
import com.ktvme.common.heartbeat.HeartBeatRecordDTO;
import com.ktvme.config.SmartHeartBeatConfig;
import com.ktvme.util.SmartBeanUtil;
import com.ktvme.util.SmartPageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * [  ]
 *
 * @author yandanyang
 * @version 1.0
 * @company 1024lab.net
 * @copyright (c) 2019 1024lab.netInc. All rights reserved.
 * @date
 * @since JDK1.8
 */
@Slf4j
@Service
public class HeartBeatService extends AbstractHeartBeatCommand {

    @Autowired
    private HeartBeatRecordDao heartBeatRecordDao;

    @Autowired
    private SmartHeartBeatConfig heartBeatConfig;

    @PostConstruct
    public void init() {

        HeartBeatConfig config = HeartBeatConfig.builder().delayHandlerTime(heartBeatConfig.getDelayHandlerTime()).intervalTime(heartBeatConfig.getIntervalTime()).build();

        super.init(config, new HeartBeatLogger() {
            @Override
            public void error(String string) {
                log.error(string);
            }

            @Override
            public void error(String string, Throwable e) {
                log.error(string, e);
            }

            @Override
            public void info(String string) {
                log.info(string);
            }
        });
    }

    @PreDestroy
    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void handler(HeartBeatRecordDTO heartBeatRecordDTO) {
        HeartBeatRecordEntity heartBeatRecordEntity = SmartBeanUtil.copy(heartBeatRecordDTO, HeartBeatRecordEntity.class);
        HeartBeatRecordEntity heartBeatRecordOld = heartBeatRecordDao.query(heartBeatRecordEntity);
        if (heartBeatRecordOld == null) {
            heartBeatRecordDao.insertHeartBeat(heartBeatRecordEntity);
        } else {
            heartBeatRecordDao.updateHeartBeatTimeById(heartBeatRecordOld.getId(), heartBeatRecordEntity.getHeartBeatTime());
        }

    }

    public ResponseDTO<PageResultDTO<HeartBeatRecordVO>> pageQuery(PageParamDTO pageParamDTO) {
        Page pageQueryInfo = SmartPageUtil.convert2QueryPage(pageParamDTO);
        List<HeartBeatRecordVO> recordVOList = heartBeatRecordDao.pageQuery(pageQueryInfo);
        PageResultDTO<HeartBeatRecordVO> pageResultDTO = SmartPageUtil.convert2PageResult(pageQueryInfo, recordVOList);
        return ResponseDTO.succData(pageResultDTO);

    }
}
