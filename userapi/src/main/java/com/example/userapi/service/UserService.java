// UserService は「ユーザー情報を管理する担当者」
// Controller（受付）が「この人探して」「全員出して」とお願いすると、
// 実際に DB から探してくれる人

package com.example.userapi.service;
// 「このクラスは service という役割ですよ」という住所
// Controller / Model / Repository と役割を分けるため

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.userapi.model.User;
import com.example.userapi.repository.UserRepository;
// import は「このクラスで使う道具リスト」
// | import           | 意味                                     |
// | ---------------- | -----------------------------------------|
// | `List`           | 複数Userをまとめて扱うため               |
// | `Service`        | SpringにServiceだと伝える                |
// | `User`           | ユーザー情報の型（Entity）               |
// | `UserRepository` | DB操作を担当するRepository               |

@Service
public class UserService {

    // Repository は「DBと会話する担当者」
    // Service は直接DBを触らず、必ず Repository 経由で操作する
    private final UserRepository repository;

    // 呼ばれるタイミング：
    // Spring が UserService を作るときに、UserRepository を渡してくれる
    //（DI：依存性注入）
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    // ① 一覧取得
    // GET /api/users
    public List<User> findAll() {
        // DBに保存されているユーザーをすべて取得
        return repository.findAll();
    }

    // ② IDでユーザーを探す
    // GET /api/users/{id}
    public User findById(Long id) {
        // repository.findById は Optional を返すため、
        // 見つからなかった場合は null を返すようにしている
        return repository.findById(id).orElse(null);
    }

    // ③ ユーザー登録
    // POST /api/users
    public User create(User user) {
        // IDは DB 側で自動採番されるため、ここでは設定しない
        // save() は「新規登録」と「更新」の両方を担当する
        return repository.save(user);
    }

    // ④ ユーザー更新（名前更新）
    // PUT /api/users/{id}
    public User update(Long id, User newUser) {
        // まず既存ユーザーを DB から取得
        User existing = repository.findById(id).orElse(null);

        // 見つからない場合は null を返す
        if (existing == null) {
            return null;
        }

        // 更新したい項目だけを書き換える
        existing.setName(newUser.getName());

        // 再度 save() することで DB に更新内容を反映
        return repository.save(existing);
    }

    // ⑤ ユーザー削除
    // DELETE /api/users/{id}
    public boolean delete(Long id) {
        // 先に存在チェックを行う
        if (!repository.existsById(id)) {
            return false;
        }

        // DB から削除
        repository.deleteById(id);
        return true;
    }
}
