package com.freedom.core.startuprunner;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 初始化资源
 * 
 * @author Bai
 *
 */
@Component
@Order(value = -1) // 越小优先级越高
public class StartupRunner implements ApplicationRunner {
	private static final Logger logger = LoggerFactory.getLogger(StartupRunner.class);

	@Override
	public void run(ApplicationArguments var1) throws Exception {
		logger.info("\n////////////////////////////////////////////////////////////////////\n" +
				"//                          _ooOoo_                               //\n" +
				"//                         o8888888o                              //\n" +
				"//                         88\" . \"88                              //\n" +
				"//                         (| ^_^ |)                              //\n" +
				"//                         O\\  =  /O                              //\n" +
				"//                      ____/`---'\\____                           //\n" +
				"//                    .'  \\\\|     |//  `.                         //\n" +
				"//                   /  \\\\|||  :  |||//  \\                        //\n" +
				"//                  /  _||||| -:- |||||-  \\                       //\n" +
				"//                  |   | \\\\\\  -  /// |   |                       //\n" +
				"//                  | \\_|  ''\\---/''  |   |                       //\n" +
				"//                  \\  .-\\__  `-`  ___/-. /                       //\n" +
				"//                ___`. .'  /--.--\\  `. . ___                     //\n" +
				"//              .\"\" '<  `.___\\_<|>_/___.'  >'\"\".                  //\n" +
				"//            | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |                 //\n" +
				"//            \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /                 //\n" +
				"//      ========`-.____`-.___\\_____/___.-`____.-'========         //\n" +
				"//                           `=---='                              //\n" +
				"//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //\n" +
				"//            佛祖保佑       永不宕机     永无BUG                    //\n" +
				"////////////////////////////////////////////////////////////////////");
	}


	
}
