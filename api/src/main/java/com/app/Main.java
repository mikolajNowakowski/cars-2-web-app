package com.app;

import com.app.config.AppConfig;
import com.app.router.CarRouter;
import com.app.router.ErrorRouter;
import com.app.router.SecurityRouter;
import com.app.router.UserRouter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static spark.Spark.initExceptionHandler;
import static spark.Spark.port;

public class Main {
    public static void main(String[] args) {
        initExceptionHandler(err -> System.out.println(err.getMessage()));
        port(8080);

        var context = new AnnotationConfigApplicationContext(AppConfig.class);
        var carRouter = context.getBean("carRouter", CarRouter.class);
        var errorRouter = context.getBean("errorRouter", ErrorRouter.class);
        var usersRouter = context.getBean("userRouter", UserRouter.class);
        var securityRouter = context.getBean("securityRouter", SecurityRouter.class);

        carRouter.routes();
        usersRouter.routes();
        securityRouter.routes();
        errorRouter.routes();



    }
}