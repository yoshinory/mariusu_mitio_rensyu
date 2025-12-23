//Spring Boot アプリを起動するための「スイッチ」

package com.example.userapi;
//このクラスの「住所」
//Spring Boot は このクラスを基準点 にして、下の階層をすべて自動で探す。

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//SpringApplication　　：Spring Boot を 起動するためのクラス
//SpringBootApplication：「このクラスが Spring Boot の起点です」という目印

@SpringBootApplication
public class UserapiApplication {

	public static void main(String[] args) {
	//Javaプログラムの 開始地点、ここから実行が始まる
		SpringApplication.run(UserapiApplication.class, args);
		//Spring Boot を起動せよ、という命令
	}
}
//run()がやっていること
//1. Spring の世界を作る
//2. @SpringBootApplication を探す
//3. @ComponentScan でクラスを探す
//4. @RestController を登録
//5. @Service を登録
//6. Webサーバ（Tomcat）起動
//7. http://localhost:8080 を開ける状態にする
