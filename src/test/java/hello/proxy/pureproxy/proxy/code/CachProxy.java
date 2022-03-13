package hello.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CachProxy implements  Subject{

    private Subject target;
    private String cacheVAlue;

    public CachProxy(Subject target) {
        this.target = target;
    }

    @Override
    public String operation() {
        log.info("포록시 호출");
        if(cacheVAlue == null){
            cacheVAlue = target.operation();
        }
        return cacheVAlue;
    }
}
