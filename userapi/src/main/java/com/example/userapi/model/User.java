//User.java は「ユーザー情報を書くための紙のフォーマット」

package com.example.userapi.model;
//Javaの「住所（フォルダ）」を表す
//Spring Bootは @SpringBootApplication のあるパッケージ配下を探す → model がそこにあるのが大事
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//@Entity や @Id などは JPAのアノテーション、それを使うためにimportする

@Entity
//このテーブルはDBのテーブルになるという目印
@Table(name = "users")
//DB上のテーブル名を users にする
public class User {

    @Id
    //主キー
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //新規登録（INSERT）の時に、IDを 自動採番してくれる設定
    private Long id;

    @Column(nullable = false)
    private String name;
    //name は空にしない（必須）という意味

    // JPAが内部で使うために必須
    protected User() {
    }
    //JPAがDBからデータを読むときの内部処理：まず new User() をする（空で作る）→　そこに id や name をセットする
    //その為に 引数なしコンストラクタが必要。

    // アプリ側で使うコンストラクタ
    public User(String name) {
        this.name = name;
    }
    //新規登録の時、普通は「名前だけ送る」ので、JSON：{"name":"Taro"} → DBがIDを付ける		という流れにしたい。

    // getter / setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) { // 今回は残してOK
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //JSONをUserに変換するとき（@RequestBody）
    //DBのデータをUserに入れるとき（JPA）
    //JSONで返すとき（@RestController）
}
