# Lease - 公寓租赁管理系统

基于 **Spring Boot 3.0.5** + **Java 17** 的现代化公寓租赁管理平台，采用 Maven 多模块架构，包含管理后台和移动端用户端两套接口。

---

## 技术栈

| 类别 | 技术 | 版本 | 说明 |
|------|------|------|------|
| 基础框架 | Spring Boot | 3.0.5 | 核心框架 |
| ORM | MyBatis-Plus | 3.5.3.1 | 增强版 MyBatis，内置分页、乐观锁 |
| 数据库 | MySQL | 8.x | 关系型数据库 |
| 缓存 | Redis | — | Spring Data Redis 集成 |
| 对象存储 | MinIO | 8.2.0 | 图片等非结构化文件存储 |
| API 文档 | Knife4j | 4.1.0 | 基于 OpenAPI 3.0，替代 Swagger |
| 认证 | JWT (jjwt) | 0.11.2 | 无状态登录认证 |
| 验证码 | EasyCaptcha | 1.6.2 | 图形验证码生成 |
| 短信 | 阿里云短信 | 2.0.23 | 短信验证码发送 |
| 连接池 | HikariCP | — | Spring Boot 默认连接池 |
| 工具库 | Lombok | — | 简化实体类代码 |
| JDK | Java | 17 | 运行环境 |
| 构建工具 | Maven | — | 项目构建与依赖管理 |

---

## 模块结构

```
lease/
├── pom.xml                     # 父 POM，统一依赖版本管理
├── model/                      # 数据模型模块
│   ├── entity/                 # 30 个数据库实体类（房间、租约、公寓、用户等）
│   └── enums/                  # 8 个枚举类（租约状态、发布状态、用户类型等）
├── common/                     # 公共组件模块
│   ├── constant/               # Redis 常量等
│   ├── exception/              # 全局异常处理 + 自定义异常
│   ├── result/                 # 统一返回结果 Result<T>
│   ├── login/                  # JWT 登录用户上下文
│   ├── redis/                  # Redis 配置
│   ├── minio/                  # MinIO 配置与属性
│   ├── sms/                    # 阿里云短信配置
│   ├── mybatisplus/            # MyBatis-Plus 配置 + 自动填充
│   ├── utils/                  # JwtUtil、CodeUtil 工具类
│   └── vo/                     # 公共 VO（如秒杀创建 VO）
├── web/                        # Web 层父模块
│   ├── web-admin/              # 管理后台接口（端口 8081）
│   │   ├── controller/         # 控制器
│   │   │   ├── apartment/      # 公寓管理、付款方式、区域、租期、设施、标签、属性、杂费、文件上传
│   │   │   ├── lease/          # 租约管理、预约看房
│   │   │   ├── login/          # 后台登录
│   │   │   ├── user/           # 用户信息管理
│   │   │   ├── system/         # 系统用户、岗位管理
│   │   │   ├── seckill/        # 秒杀活动管理 + 回调
│   │   │   └── schedule/       # 定时任务（租约到期自动标记）
│   │   ├── service/            # 业务接口（40+ 个）
│   │   ├── service/impl/       # 业务实现
│   │   ├── mapper/             # MyBatis-Plus Mapper 接口
│   │   ├── vo/                 # 视图对象（按领域分包）
│   │   └── custom/             # 自定义配置
│   │       ├── config/         # WebMvcConfiguration
│   │       ├── interceptor/    # JWT 认证拦截器
│   │       └── convertor/      # 自定义类型转换器（字符串 → 枚举）
│   └── web-app/                # 移动端/H5 用户接口（端口 8081）
│       ├── controller/         # 控制器
│       │   ├── apartment/      # 公寓浏览
│       │   ├── room/           # 房间浏览
│       │   ├── agreement/      # 租约查看
│       │   ├── appointment/    # 预约看房
│       │   ├── login/          # 短信登录
│       │   ├── payment/        # 支付方式
│       │   ├── region/         # 区域信息
│       │   ├── history/        # 浏览历史
│       │   └── leasaterm/      # 租期
│       ├── service/            # 业务接口（20+ 个）
│       ├── service/impl/       # 业务实现
│       ├── mapper/             # MyBatis-Plus Mapper 接口
│       ├── vo/                 # 视图对象（按领域分包）
│       └── custom/             # 自定义配置
│           ├── config/         # Knife4j、WebMvc 配置
│           └── interceptor/    # JWT 认证拦截器
```

---

## 功能模块

### 管理后台 (web-admin)

| 模块 | 功能 |
|------|------|
| **登录认证** | 后台用户登录、图形验证码、JWT Token |
| **公寓管理** | 公寓信息 CRUD、公寓图片上传（MinIO） |
| **房间管理** | 房间信息维护、房间属性关联、房间标签与设施 |
| **租约管理** | 租约信息 CRUD、状态流转（签约→退租中→到期） |
| **预约看房** | 预约记录查看与管理 |
| **用户管理** | 平台注册用户管理 |
| **系统管理** | 系统用户管理、岗位管理 |
| **基础数据** | 区域（省/市/区）、租期类型、支付方式、设施、标签、属性键值、杂费键值 |
| **秒杀活动** | 创建秒杀活动，转发到秒杀服务 |
| **秒杀回调** | 秒杀成功后接收回调，自动创建租约 |
| **定时任务** | 每日凌晨检查租约到期，自动标记为"已到期" |
| **文件上传** | 通过 MinIO 实现图片等文件上传 |

### 移动端/用户端 (web-app)

| 模块 | 功能 |
|------|------|
| **短信登录** | 阿里云短信验证码登录、JWT Token |
| **公寓浏览** | 公寓列表、公寓详情（含房间信息、标签、设施、杂费） |
| **房间浏览** | 房间详情、属性信息 |
| **预约看房** | 用户预约看房 |
| **租约查看** | 用户查看自己的租约信息 |
| **支付方式** | 查看房间支持的支付方式 |
| **区域查询** | 省市区的级联查询 |
| **浏览历史** | 用户浏览记录 |

---

## 快速开始

### 环境要求

- **JDK** 17+
- **Maven** 3.6+
- **MySQL** 8.0+
- **Redis** 6.0+
- **MinIO**（可选，文件上传需要）

### 1. 初始化数据库

在 MySQL 中创建数据库并导入初始化脚本：

```sql
CREATE DATABASE lease DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
```

> 数据库初始化 SQL 脚本位于 `web/web-admin/src/main/resources/` 目录下（如有提供）。

### 2. 配置修改

配置文件位于各模块的 `src/main/resources/application.yml`：

**管理端** (`web/web-admin/src/main/resources/application.yml`)：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/lease?useUnicode=true&characterEncoding=utf-8
    username: root
    password: your_password
  data:
    redis:
      host: 127.0.0.1
      port: 6379

minio:
  endpoint: http://127.0.0.1:9000
  access-key: your_minio_key
  secret-key: your_minio_secret
  bucket-name: lease
```

**用户端** (`web/web-app/src/main/resources/application.yml`)：

```yaml
aliyun:
  sms:
    access-key-id: your_aliyun_key
    access-key-secret: your_aliyun_secret
    endpoint: dysmsapi.aliyuncs.com
```

### 3. 编译打包

```bash
mvn clean install -DskipTests
```

### 4. 启动服务

**启动管理后台**（默认端口 8081）：

```bash
cd web/web-admin
mvn spring-boot:run
```

**启动用户端**（默认端口 8081）：

```bash
cd web/web-app
mvn spring-boot:run
```

> 注意：两个模块默认端口均为 8081，如需同时启动请修改其中一个的端口。

### 5. 访问 API 文档

启动后访问 Knife4j 接口文档：

- 管理后台：`http://localhost:8081/doc.html`
- 用户端：`http://localhost:8081/doc.html`

---

## 项目架构说明

```
请求 → Controller → Service → Mapper → DB
              ↓
         JWT 拦截器（认证鉴权）
              ↓
       统一异常处理（GlobalExceptionHandler）
              ↓
       统一返回格式（Result<T>）
```

- **Controller 层**：接收请求、参数校验、调用 Service
- **Service 层**：业务逻辑处理
- **Mapper 层**：MyBatis-Plus BaseMapper，数据库操作
- **公共层**：提供通用能力（认证、缓存、文件存储、短信、统一异常/返回）

---

## 重要依赖

| 依赖 | 用途 |
|------|------|
| `mybatis-plus-boot-starter` | ORM 框架，提供分页、乐观锁、自动填充 |
| `knife4j-openapi3-jakarta-spring-boot-starter` | API 文档生成 |
| `jjwt-api/impl/jackson` | JWT Token 签发与校验 |
| `easy-captcha` | 图形验证码 |
| `minio` | 对象存储客户端 |
| `dysmsapi20170525` | 阿里云短信服务 |
| `spring-boot-starter-data-redis` | Redis 缓存集成 |
| `mysql-connector-j` | MySQL JDBC 驱动 |

---

## 开发约定

- JDK 版本：**17**
- 编码格式：**UTF-8**
- 包名规范：`com.atafl.lease.<模块>.<层级>`
- 实体类统一继承 `BaseEntity`（提供 id、createTime、updateTime 等公共字段）
- 枚举类统一实现 `BaseEnum` 接口
- 接口返回统一使用 `Result<T>` 包装
