package com.hatcher.interfaces;

/**
 * @author hatcher
 * @date 2023/1/5 13:30
 * @description
 */
@FunctionalInterface
public interface BeanUtilCopyCallBack<S, T> {
    /**
     * 定义默认回调方法
     *
     * @param t
     * @param s
     */
    void callBack(S t, T s);
}
