package com.share.comment;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.share.enums.BusiTypeEnum;
import com.share.user.IBusiService;

/**
 * @author wk
 * @date 2019/5/6 15:10
 */
@Component
public class BeanOfServiceManager implements ApplicationContextAware {
	
    private ApplicationContext applicationContext;
    private Map<BusiTypeEnum, IBusiService> beanMap = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        Map<String, IBusiService> beansOfType = applicationContext.getBeansOfType(IBusiService.class);
        beansOfType.forEach((k, v) -> beanMap.putIfAbsent(v.getBusiType(), v));
    }

    public IBusiService getBusiService(BusiTypeEnum busiTypeEnum) {
        return beanMap.get(busiTypeEnum);
    }
}
