package com.quanht.demospringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
@SpringBootApplication
public class DemoSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBootApplication.class, args);
	}

	/**
	 * http://localhost:8100/hello
	 */
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String index() {
		return "Hello Wold";
	}

	/**
	 * http://localhost:8100/test
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ResponseBodyEmitter test(HttpServletResponse response) {
		response.setContentType("text/event-stream");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");

		ResponseBodyEmitter emitter = new ResponseBodyEmitter();

		String[] words = new String[]{"恭喜，", "你的", "项目", "部", "署", "测", "试", "成", "功", "了！"};
		new Thread(() -> {
			for (String word : words) {
				try {
					emitter.send(word);
					Thread.sleep(250);
				} catch (IOException | InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}).start();

		return emitter;
	}
}
