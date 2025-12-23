//UserService は「ユーザー情報を管理する担当者」
//Controller（受付）が「この人探して」「全員出して」とお願いすると、実際に探してくれる人

package com.example.userapi.service;
//「このクラスは service という役割ですよ」という住所
//Controller / Model と役割を分けるため

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.userapi.model.User;
//import は「このクラスで使う道具リスト」
//| import      | 意味                  	|
//| ----------- | ------------------- 		|
//| `ArrayList` | 複数Userを入れる箱        |
//| `List`      | 箱のルール                |
//| `Service`   | SpringにServiceだと伝える |
//| `User`      | ユーザー情報の型          |


@Service
public class UserService {
//@Service がやっていること
//Spring が全クラスをスキャン -> @Service を見つける -> UserService のインスタンスを1つ作る -> Springの箱（コンテナ）に保存
//これにより -> {Controller から呼び出せる 、 new しなくてよい 、 使い回される（シングルトン）}

    private List<User> users = new ArrayList<>();
    //これは何か？ ：今だけ使う簡易データベース　（データベースの代役）、メモリ上にある「ユーザー名簿」
    //Listの理由　 ：ユーザーは複数いる為、順番に並べて持てる為
    //privateの理由：勝手に外から書き換えられないようにする為、操作は必ずメソッド経由の為

    public UserService() {
        // 仮データ（今はDBが無いのでメモリに置く）
        users.add(new User(1L, "Taro"));
        users.add(new User(2L, "Hanako"));
    }
    //呼ばれるタイミング：Springが UserServiceを作った瞬間
    //中身の解説		：最初から2人分のユーザーを登録

    // 追加①：IDでユーザーを探す
    public User findById(Long id) {
    //「IDがこれのユーザーを返す」、見つからなければ「いない」と伝える
        for (User user : users) {
        //users の中身を先頭から1人ずつ確認
            if (user.getId().equals(id)) {
                return user;
                //見つかったら、Controller に結果を返して処理終了
            }
        }
        return null; // 見つからなかった場合、「該当ユーザーなし」の合図
    }

    // 追加②：ユーザー登録
    public User create(User user) {
        // 仮のIDを作る（今は「人数＋1」）
        Long newId = (long) (users.size() + 1);
        //今いる人数を数える、次の番号をIDにする
        user.setId(newId);
        //ユーザーにIDをセット
        users.add(user);
        // ユーザーを名簿に追加

        return user;
        //登録したユーザーを返す
    }

    
    // 既存：一覧取得
    public List<User> findAll() {
        return users;
    }
    //保存されているユーザーを 全部返す
}
