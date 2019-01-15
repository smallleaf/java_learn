package com.share1024.java.chainResponsibility;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018-12-24
 */
public abstract class FilterChain {

    private FilterChain filterChain;


    public FilterChain(){

    }

    public FilterChain setFilterChain(FilterChain filterChain){
        this.filterChain = filterChain;
        return filterChain;
    }
    abstract void before();

    public void say(){
        if(filterChain != null){
            filterChain.say();
        }
        before();
    }


    public FilterChain getFilterChain() {
        return filterChain;
    }
}
