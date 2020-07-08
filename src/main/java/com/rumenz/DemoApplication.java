package com.rumenz;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigBeanDefinitionParser;
import org.springframework.context.annotation.Bean;

public class DemoApplication {

    public static void main(String[] args) {
         AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext();
         ac.register(Config1.class);
         ac.refresh();
         ConfigurableListableBeanFactory beanFactory = ac.getBeanFactory();
         System.out.println("父容器"+beanFactory.getParentBeanFactory()); //null

         //层级的BeanFactory,创建一个父容器
         HierarchicalBeanFactory parentBeanFactory = createBeanFactory();

         displayBean(beanFactory,"rumenz");
         displayBean(parentBeanFactory,"superRumenz");

         //设置父容器
         beanFactory.setParentBeanFactory(parentBeanFactory);

        displayBean(beanFactory,"superRumenz");
        displayBean(parentBeanFactory,"rumenz");

        //递归查找Bean,双亲委派，先去父容器查找（parentBeanFactory），找不到最后去本地容器（beanFactory）查找
        displayContainsBean(beanFactory,"superRumenz");
        displayContainsBean(parentBeanFactory,"superRumenz");

         ac.close();
    }
    private static void displayBean(HierarchicalBeanFactory beanFactory,String beanName){
        System.out.println(beanFactory.containsLocalBean(beanName));

    }

    private static void displayContainsBean(HierarchicalBeanFactory beanFactory,String beanName){
        System.out.println(containsBean(beanFactory,beanName));

    }

    private static boolean containsBean(HierarchicalBeanFactory beanFactory,String beanName){


        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if(parentBeanFactory instanceof HierarchicalBeanFactory){
            HierarchicalBeanFactory hierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if(containsBean(hierarchicalBeanFactory,beanName)){
                return true;
            }
        }
        return beanFactory.containsLocalBean(beanName);

    }





    private static HierarchicalBeanFactory createBeanFactory(){
          AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext();
          ac.register(Config.class);
          ac.refresh();
          return ac.getBeanFactory();
    }



}
