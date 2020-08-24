package sample.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import sample.vo.ProductVO;

//@Aspect
//@Component
public class ProductAspect {
//	@Before("execution(* sample.service.*Service.*(..))")
//	public void Before() {
//		System.out.println("Hello @Before!");
//	}
	
//	@After("execution(* sample.service.*Service.*(..))")
//	public void After() {
//		System.out.println("Hello @After!");
//	}
	
	@Around("execution(* sample.service.*Service.*(..))")
	public ProductVO Around(ProceedingJoinPoint joinPoint) throws Throwable {
		
		long startTime = System.currentTimeMillis();
		System.out.println("Hello @Around   Before");
		
		try {
			ProductVO product = (ProductVO) joinPoint.proceed();
			System.out.println(product);
			return product;
		} catch (Throwable e) {
			System.out.println(e.getMessage());
			throw e;
		} finally {
			System.out.println("Hello @Around   After");
			long endTime = System.currentTimeMillis();
			String methodName = joinPoint.getSignature().getName();
			System.out.println(methodName + " Method -- spend Time : " + (endTime - startTime) + " ms");
		}
	}
	
	/*@AfterReturning("execution(* sample.service.*Service.*(..))")
	public void AfterReturning(ProductVO product) {
		System.out.println("Hello @AfterReturning!");
		System.out.println(product);
	}*/
	
	/*@AfterThrowing("execution(* sample.service.*Service.*(..))")
	public void AfterThrowing(Throwable ex) {
		System.out.println("Hello @AfterThrowing!");
		System.out.println(ex.getMessage());
	}*/
	
}
