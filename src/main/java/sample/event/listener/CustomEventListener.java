package sample.event.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.web.context.support.RequestHandledEvent;

public class CustomEventListener implements ApplicationListener<ApplicationEvent> {
	private Logger LOGGER = LoggerFactory.getLogger(CustomEventListener.class);
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof ContextRefreshedEvent) {
			LOGGER.info("### " + event.getClass().getSimpleName() + " ###");
		}
		else if (event instanceof ContextStartedEvent) {
			LOGGER.info("### " + event.getClass().getSimpleName() + " ###");
		}
		else if (event instanceof ContextStoppedEvent) {
			LOGGER.info("### " + event.getClass().getSimpleName() + " ###");
		}
		else if (event instanceof ContextClosedEvent) {
			LOGGER.info("### " + event.getClass().getSimpleName() + " ###");
		}
		else if (event instanceof RequestHandledEvent) {
			RequestHandledEvent e = (RequestHandledEvent) event;
			LOGGER.info("### " + event.getClass().getSimpleName() + " ###");
			LOGGER.info(e.toString());
		}
		else {
			LOGGER.info("### EVENT? ###");
		}
	}
	
	public void onApplicationEvent(ContextRefreshedEvent event) {
		LOGGER.info("*** " + event.getClass().getSimpleName() + " ***");
	}

}
