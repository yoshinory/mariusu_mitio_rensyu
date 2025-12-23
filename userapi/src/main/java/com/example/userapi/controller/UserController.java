package com.example.userapi.controller;
//このクラスは controller層 に属しますという宣言

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//POST用
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//|Springが用意している 「アノテーション」 を使うための準備
//| アノテーション    | 役割         	  |
//| ----------------- | ----------		  |
//| `@RestController` | APIの入口    	  |
//| `@RequestMapping` | URLの共通部分     |
//| `@GetMapping`     | GETリクエスト     |
//| `@PathVariable`   | URLの値を受け取る |

import com.example.userapi.model.User;
import com.example.userapi.service.UserService;


@RestController
//@Controller+@ResponseBodyの省略
//@Controller	：「このクラスは REST API用のコントローラ です」とSpringに伝える
//@ResponseBody	： 戻り値（User）を 自動でJSONに変換 してくれる
@RequestMapping("/api/users")
//「このControllerが担当するURLはすべて /api/users から始まるとSpringが解釈する」
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
    //「UserController は UserService を自分で作らず、Springに用意してもらう」
    
    // ★追加①：GET /api/users（一覧取得）
    @GetMapping
    public List<User> getUsers() {
        return service.findAll();
    }

    // 追加②：GET /api/users/{id}
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return service.findById(id);
        //Spring内部処理
        //URLを解析
        //{id} に 1 を当てはめる
        //"1"（文字列）→ Long 1 に変換
        //メソッド引数に渡す
    }
    
    // 追加③：POST /api/users（ユーザー登録）
    @PostMapping
    public User createUser(@RequestBody User user) {
        return service.create(user);
    }
    //@PostMapping：「POST /api/users が来たら、このメソッドを使って」
    //@RequestBody User user：送られてきた JSON を自動で User オブジェクトに変換
    //return service.create(user);ユーザー登録は Service に任せる。登録後の User を返す（JSONになる）
    
 // 追加：PUT /api/users/{id}（名前更新）
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return service.update(id, user);
    }


}
