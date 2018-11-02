package com.dreamer.service.mobile.factory;

import com.dreamer.domain.wechat.WxConfig;
import com.dreamer.service.mobile.WxConfigHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by huangfei on 12/07/2017.
 */
@Component
public class WxConfigFactory {

    @Autowired
    private WxConfigHandler wxConfigHandler;

    private WxConfig baseConfig;//基本配置

    private WxConfig payConfig;//基本配置如上保持一直 集群

    public WxConfig getBaseConfig() {//几乎不变
        if(baseConfig==null){
            baseConfig =   wxConfigHandler.get("baseConfig",true);
        }
        return baseConfig;
    }


    public WxConfig getPayConfig() {//暂时都改变  如果影响性能 就不改变
        //
        payConfig = wxConfigHandler.get("payConfig",true);
        return payConfig;
    }

}
