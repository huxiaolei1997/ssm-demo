package com.test.ssm.method2.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Properties;

@Intercepts(
        @Signature(
                method = "query", type = Executor.class,
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
)
@Slf4j
public class MyInterceptor implements Interceptor {
    /**
     * @param invocation { target-代理对象，method-被监控方法对象，args-当前被监控方法运行时需要的实参 }
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        Method method = invocation.getMethod();
        Object target = invocation.getTarget();
        Object result = invocation.proceed();
        if (result instanceof Collection) {
            int size = ((Collection) result).size();
            if (size >= 1) {
                log.warn("id = {}, 返回结果长度 = {}", ((MappedStatement) args[0]).getId(), size);
            }
        }
        return result;
    }

    /**
     * @param target 表示被拦截的对象，此处为 Executor 的实例对象
     *               作用：如果被拦截对象所在的类有实现接口，就为当前拦截对象生成一个代理对象
     *               如果被拦截对象所在的类没有指定接口，这个对象之后的行为就不会被代理操作
     * @return
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
