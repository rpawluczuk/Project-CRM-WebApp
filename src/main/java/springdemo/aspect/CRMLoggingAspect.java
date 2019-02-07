package springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    // setup logger
    private Logger logger = Logger.getLogger(getClass().getName());

    // setup pointcut declaration
    @Pointcut("execution(* springdemo.controller.*.*(..)))")
    private void forControllerPackage(){}

    // do the same for service and dao
    @Pointcut("execution(* springdemo.service.*.*(..)))")
    private void forServicePackage(){}

    @Pointcut("execution(* springdemo.dao.*.*(..)))")
    private void forDaoPackage(){}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow(){}

    // add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){

        // display method we are calling
        String method = joinPoint.getSignature().toShortString();
        logger.info("=====>>> in @Before: calling method: " + method);

        // display the arguments to the method

        // get the arguments
        Object[] args = joinPoint.getArgs();

        // loop thru and display args
        for (Object tempArg : args){
            logger.info("====>>> argument: " + tempArg);
        }

    }

    // add @AfterReturning advice
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){

        // display method we are returning from
        String method = joinPoint.getSignature().toShortString();
        logger.info("=====>>> in @AfterReturning: form method" + method);

        // display data returned
        logger.info("=====>>> result: " + result);

    }
}
