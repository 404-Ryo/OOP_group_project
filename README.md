# Smart Health Tracker - 大学OOP課題プロジェクト

大学のオブジェクト指向プログラミング(OOP)の授業で、4人グループで取り組んだ初めてのJavaプロジェクトです。

---

## 📝 この課題について

大学の課題として、**SDG 3 (Good Health and Well-being)** に関連したJavaアプリを開発するというお題が出されました。私たちのグループでは「**Smart Health Tracker**」という、毎日の健康データ(食事・運動・スクリーンタイム)をシンプルに記録できるコンソールアプリケーションを作りました。

課題の目的は単に動くアプリを作ることではなく、**OOPの4つの基本概念(抽象化・カプセル化・継承・ポリモーフィズム)を実際のコードでどう表現するか**を学ぶことでした。

---

## 🎯 何を作ったか

ユーザーが日々の以下のデータを記録・閲覧できるシンプルなコンソールアプリです。

- **食事記録** … カロリー摂取量、消費量、サプリ(プロテイン/クレアチン/ビタミン)の有無
- **運動記録** … 1日の歩数(10,000歩を目標にフィードバック)
- **スクリーンタイム記録** … スマホ等の使用時間(2時間を超えると警告メッセージ)
- 全レコードの閲覧、日付による絞り込み

スクリーンタイム機能は課題の必須要件にはなかったのですが、「現代人のメンタルヘルスにスマホ依存も関係する」という観点から、グループで話し合って追加した拡張機能です。

---

## 🧠 OOPの概念をどう意識したか

### 1. 抽象化 (Abstraction)

`HealthRecord` を **abstract クラス** として定義しました。「健康記録には必ず日付がある」「必ず表示できる」という共通点だけを抽象的に定義し、具体的に何を表示するかは各サブクラスに任せる、という設計です。

```java
public abstract class HealthRecord {
    private String date;
    public HealthRecord(String date) { this.date = date; }
    public String getDate() { return date; }
    public abstract void displayRecord();  // 中身は子クラスが決める
}
```

### 2. カプセル化 (Encapsulation)

データメンバはすべて `private` にして、外からは getter / setter 経由でしかアクセスできないようにしました。

```java
private int caloriesIntake;
public int getCaloriesIntake() { return caloriesIntake; }
public void setCaloriesIntake(int caloriesIntake) {
    this.caloriesIntake = caloriesIntake;
}
```


### 3. 継承 (Inheritance)


`DietRecord` `ActivityRecord` `ScreenTimeRecord` の3つはすべて `HealthRecord` を継承しています。

```java
public class ActivityRecord extends HealthRecord {
    private int steps;

    public ActivityRecord(String date, int steps) {
        super(date);  // 親クラスのコンストラクタを呼ぶ
        this.steps = steps;
    }
    // ...
}
```


### 4. ポリモーフィズム (Polymorphism)


このアプリでは、メインクラスで以下のように `HealthRecord` 型のリストにすべての記録を入れています。

```java
ArrayList<HealthRecord> records = new ArrayList<>();
records.add(new DietRecord(...));
records.add(new ActivityRecord(...));
records.add(new ScreenTimeRecord(...));

// ↓ これがポリモーフィズム
for (HealthRecord r : records) {
    r.displayRecord();  // 実際の型に応じて違う出力になる
}
```

---

## 👥 グループでの取り組み

4人グループでの作業は、コミュニケーションに苦労しました。タスクをどう割り振るか、どうやってプロジェクトを進めるかなど、一人で作業することと違って、意見のすり合わせが大変でした。

進め方
- 最初に **UMLクラス図** を全員で書きながら設計を共有
- クラスごとに担当を分け、Gitで合流


```
src/
└── com/mycompany/healthtrackerapp/
    ├── HealthTrackerApp.java           # main / メニュー制御
    └── records/
        ├── HealthRecord.java           # abstract 基底クラス
        ├── ActivityRecord.java         # 歩数記録
        ├── DietRecord.java             # 食事・カロリー・サプリ記録
        └── ScreenTimeRecord.java       # スクリーンタイム記録
```

### UMLクラス図

```
              ┌─────────────────────┐
              │   HealthRecord      │  «abstract»
              ├─────────────────────┤
              │ - date: String      │
              ├─────────────────────┤
              │ + getDate(): String │
              │ + displayRecord()   │  «abstract»
              └──────────▲──────────┘
                         │
        ┌────────────────┼────────────────┐
        │                │                │
┌───────┴────────┐ ┌─────┴──────┐ ┌───────┴──────────┐
│ ActivityRecord │ │ DietRecord │ │ ScreenTimeRecord │
└────────────────┘ └────────────┘ └──────────────────┘
                         ▲
                         │ uses
                ┌────────┴─────────┐
                │ HealthTrackerApp │
                └──────────────────┘
```

---

## 🚀 実行方法

### 必要環境
- Java JDK 17 以上 (開発時は JDK 21 を使用)

### コンパイル
```bash
cd src
javac com/mycompany/healthtrackerapp/records/*.java com/mycompany/healthtrackerapp/HealthTrackerApp.java
```

### 実行
```bash
java com.mycompany.healthtrackerapp.HealthTrackerApp
```

### 実行例

```
=== Health Tracker Menu ===
1. Add Diet Record (Intake & Burned + Supplements + Feedback)
2. Add Step Record
3. Add Screen Time Record
4. View All Records
5. View Records by Date
6. Exit
Choose (1-6): 1
Enter date (YYYY-MM-DD): 2025-12-15
Enter calorie intake (kcal): 3000
Enter calories burned (kcal): 2500
Did you take protein? (y/n): y
Did you take creatine? (y/n): y
Did you take vitamins? (y/n): y
Diet record added.
```

`View All Records` を選ぶと、ポリモーフィズムによって各レコードが自分の形式で出力されます:

```
--- All Records ---
[Diet Record]
Date: 2025-12-15
Calories Intake : 3000 kcal
Calories Burned : 2500 kcal
Balance         : 500 kcal
Protein Taken   : Yes
Creatine Taken  : Yes
Vitamins Taken  : Yes
Feedback        : You consumed more calories than you burned. Try to burn more calories tomorrow!

[Steps]
Date: 2025-12-15
Steps: 5000
Feedback: You walked less than 10,000 steps. Try to walk more tomorrow!

[Screen Time]
Date: 2025-12-15
Screen Time: 4.0 hours
Feedback: Your screen time is 2 hours or more. Try to reduce it tomorrow!
```

---

