# University 在线考试管理系统

基于 SpringBoot + MyBatis + MySQL + Bootstrap 的在线考试管理系统，支持多科目考试、自动判分、倒计时交卷、重考审批等功能。

## 技术栈

| 层级     | 技术                            |
| -------- | ------------------------------- |
| 框架     | SpringBoot 2.7.18 (内嵌 Tomcat) |
| 持久层   | MyBatis (注解式 SQL)            |
| 数据库   | MySQL 8.0                       |
| 前端     | HTML + Ajax + Bootstrap 5.3     |
| 构建工具 | Maven                           |

## 五层架构

```
Controller（表现层）→ Service（业务逻辑层）→ Mapper（持久层）→ Entity（实体层）→ DB（数据库层）
```

## 功能模块

### 管理员端

- 用户管理：注册、查询、删除，支持班级分配
- 班级管理：创建/删除班级，将学生分配到班级，按班级查看成绩汇总
- 科目管理：创建/编辑/删除考试科目，设置时长、可考次数、抽题数量，发布/下线
- 试题管理：按科目添加/编辑/删除试题，支持单选题和简答题，自定义分值
- 人工批阅：对简答题逐题评分（有分数上限校验）
- 重考管理：查看学生重考申请，批准或拒绝

### 学生端

- 参加考试：选择科目进入考试，单选题+简答题混合，倒计时+超时自动交卷
- 简答题待批阅：含简答题的试卷只显示"待教师批阅"，不提前泄露成绩
- 成绩查询：按科目汇总显示历史成绩，不合格可申请重考
- 错题集：按科目分组展示已批阅的错题（未批阅简答题不出现）
- 服务端计时：退出页面不暂停，重新进入延续剩余时间
- 并发控制：同时在线考试人数 ≤ 50

### 通用

- 角色权限控制（Interceptor）：管理员/学生权限分离
- BCrypt 密码加密，防 SQL 注入
- Bootstrap 深色侧边栏 + 现代化 UI

## 数据库表

| 表名 | 说明 | 关联 |
|------|------|------|
| sys_class | 班级表 | — |
| users | 用户表 (admin/student) | FK → sys_class |
| exam | 考试科目表 | — |
| question | 试题表（单选/简答） | FK → exam |
| exam_record | 答题记录表 | FK → users, question, exam |
| exam_error_question | 错题集表 | FK → users, question |
| paper_question_rel | 试卷题目关联表 | FK → exam, question |
| reset_request | 重考申请表 | FK → users, exam |

## 快速启动

### 环境要求

- JDK 11+
- MySQL 8.0
- Maven 3.9+

### 数据库初始化

```bash
mysql -u root -p < init.sql
```

执行后会自动创建数据库 `bjpowernode`、8 张表、2 个示例科目（含 10 道试题），以及默认管理员账号。

修改 `src/main/resources/application.yml` 中的数据库用户名和密码以匹配你的 MySQL。

### 运行

```bash
cd Unversity_Test
mvn spring-boot:run
```

浏览器访问：`http://localhost:9002/myWeb/login.html`

默认管理员账号：`admin` / `admin123`

### 在 IDEA 中运行

1. `File → Open` → 选择 `Unversity_Test` 文件夹
2. IDEA 会自动识别为 Maven 项目并下载依赖
3. 右键 `OnlineQuizApplication.java` → Run

## 从零开始部署指南

以下操作不需要 IDEA，纯命令行即可运行。

### 1. 安装必需软件

| 软件  | 版本 | 下载地址                               |
| ----- | ---- | -------------------------------------- |
| JDK   | 11+  | https://adoptium.net/                  |
| MySQL | 8.0  | https://dev.mysql.com/downloads/mysql/ |
| Maven | 3.9+ | https://maven.apache.org/download.cgi  |

安装后验证：

```bash
java -version
mysql --version
mvn --version
```

### 2. 解压项目

将收到的压缩包解压到任意目录，例如 `C:\Unversity_Test`。

### 3. 初始化数据库

启动 MySQL 服务，然后执行：

```bash
mysql -u root -p < init.sql
```

这会自动创建数据库、5 张表、默认管理员账号和示例试题。

### 4. 修改数据库密码

打开 `src/main/resources/application.yml`，找到：

```yaml
spring:
  datasource:
    username: root
    password: "050614" # ← 改成你的 MySQL root 密码
```

### 5. 启动项目

```bash
cd Unversity_Test
mvn spring-boot:run
```

首次运行会自动下载依赖，需要几分钟。看到 `Started OnlineQuizApplication` 即启动成功。

### 6. 打开浏览器

访问 `http://localhost:9002/myWeb/login.html`

默认管理员账号：`admin` / `admin123`
