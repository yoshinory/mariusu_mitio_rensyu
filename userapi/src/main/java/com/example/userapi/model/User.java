//User.java は「ユーザー情報を書くための紙のフォーマット」

package com.example.userapi.model;

public class User {
    private Long id;
    //id	：ユーザー番号
    private String name;
    //name	：ユーザー名
    //privateの理由：外から勝手に書き換えられないようにするため、必ず決まった方法（getter/setter）経由で管理する為

    public User() {
        // JSONを受け取る時に必要（Spring専用の入口）
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
        // 仮のユーザーデータを作るとき、テスト用データを作るときに必要（これは人間用）
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
