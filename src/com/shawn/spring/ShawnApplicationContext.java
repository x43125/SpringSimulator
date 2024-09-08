package com.shawn.spring;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ShawnApplicationContext {
    private Class<?> configClass;

    private Map<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>();
    private Map<String, Object> singletons = new ConcurrentHashMap<>();

    public ShawnApplicationContext(Class<?> configClass) {
        // 引入配置类
        this.configClass = configClass;
        System.out.println("开始初始化, 读取配置类...");
        // 解析配置类, 读取到可扫描的范围
        if (configClass.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan componentScan = configClass.getAnnotation(ComponentScan.class);
            String scanArea = componentScan.value();
            System.out.println("获取扫描区域: " + scanArea);
            scanArea = scanArea.replace(".", "/");
            ClassLoader classLoader = ShawnApplicationContext.class.getClassLoader();
            URL url = classLoader.getResource(scanArea);
            try {
                File file = new File(url.toURI());
                // TODO:递归
                if (file.isDirectory()) {
                    File[] files = file.listFiles();
                    for (File f : files) {
                        System.out.println("扫描文件: " + f.getName());
                        String fileName = f.getAbsolutePath();
                        if (fileName.endsWith(".class")) {
                            fileName = fileName.substring(fileName.indexOf("com"), fileName.indexOf(".class"));
                            fileName = fileName.replace("/", ".");
                            // 双亲寻
                            Class<?> nowClass = classLoader.loadClass(fileName);
                            System.out.println("加载类: " + nowClass);
                            if (nowClass.isAnnotationPresent(Component.class)) {
                                System.out.println("component实例, 装载beanDefinition: ");
                                // 实例化类
                                // newInstance需要类有无参构造器
                                Component componentAnno = nowClass.getAnnotation(Component.class);
                                BeanDefinition beanDefinition = new BeanDefinition();
                                beanDefinition.setType(nowClass);

                                if (nowClass.isAnnotationPresent(Scope.class)) {
                                    Scope scopeAnno = nowClass.getAnnotation(Scope.class);
                                    beanDefinition.setScope(scopeAnno.value());
                                }
                                System.out.println("scope: " + beanDefinition.getScope());
                                // 对象实例名: 
                                String beanName = !"".equals(componentAnno.value()) ? componentAnno.value()
                                        : Introspector.decapitalize(nowClass.getSimpleName());
                                if ("singleton".equals(beanDefinition.getScope())) {
                                    singletons.put(beanName, createBean(beanName, beanDefinition));
                                }
                                beanDefinitions.put(beanName, beanDefinition);
                            }
                        }
                    }
                }
            } catch (URISyntaxException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public Object getBean(String beanName) {
        System.out.println("获取bean: " + beanName);
        if (!beanDefinitions.containsKey(beanName)) {
            throw new NoClassDefFoundError();
        }

        BeanDefinition beanDefinition = beanDefinitions.get(beanName);
        String scope = beanDefinition.getScope();
        if ("singleton".equals(scope)) {
            System.out.println("单例bean, 从singletons中获取或者创建");
            if (singletons.containsKey(beanName)) {
                System.out.println("singletons中获取");
                return singletons.get(beanName);
            } else {
                Object o = createBean(beanName, beanDefinition);
                singletons.put(beanName, o);
                System.out.println("singletons中不存在, 重新创建");
                return o;
            }
        } else {
            System.out.println("多例bean, 直接创建");
            return createBean(beanName, beanDefinition);
        }
    }

    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        System.out.println("实例化bean: " + beanName);
        Class clazz = beanDefinition.getType();

        try {
            Object o = clazz.getConstructor().newInstance();
            return o;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

}
