package com.shawn.spring;

public class BeanDefinition {

    /**
     * 类名
     */
    private Class type;

    /*
     * 作用域：singleton, prototype
     */
    private String scope = "singleton";

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }


    
}
