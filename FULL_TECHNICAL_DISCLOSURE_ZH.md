# Inside The System - 完整技术披露

## 项目说明
这是 Minecraft 模组 **Inside The System** 的官方简体中文本地化版本，由原作者 **Dreg Iron** 授权进行非商业性汉化。

**原模组作者：** Dreg Iron  
**汉化版本：** 2.0 (基于原版 v2.0)  
**授权状态：** 已获得书面授权  
**许可协议：** 仅限非商业性简体中文本地化使用

---

## ⚠️ 重要安全警告（必须阅读）

**本模组包含影响用户计算机的功能。这些功能仅在特定剧情条件下触发，用于增强沉浸感。以下是完整披露：**

---

### 1. IP 地址记录与地理位置查询

**实现文件：** `AngryBuilderPriNachalnomPrizyvieSushchnostiProcedure.java`

**功能描述：**
- 获取玩家的外部 IP 地址（通过 `https://api.ipify.org`）
- 查询 IP 的地理位置信息（通过 `http://ip-api.com/json/`）
- 获取本地计算机名
- 将信息显示在游戏内的告示牌上

**触发条件：**
- 当 `AngryBuilder` 实体生成时（剧情进行到特定阶段）
- 仅在使用 `/safeip` 命令关闭安全模式后才会执行

**隐私说明：**
- IP 地址会被发送到第三方服务（ipify.org 和 ip-api.com）
- 这些服务会记录访问日志（包括 IP、时间戳）
- 使用 `/safeip` 命令可以**禁用**此功能（使用随机生成的位置数据代替真实位置）

**数据流向：**
```
玩家游戏 → 模组获取IP → 发送到第三方API → 返回地理位置 → 显示在游戏中
```

---

### 2. 更改桌面壁纸

**实现文件：** `WaitForMeProcedure.java`

**功能描述：**
- 在特定结局触发时，自动更改 Windows 用户的桌面壁纸
- 使用 PowerShell 和 Windows API (`SystemParametersInfo`)
- 壁纸图片来自模组资源 `story.png`

**触发条件：**
- 玩家死亡并满足剧情条件（变量 `Died = true` 且 `DiedPayloadExecuted = false`）
- 仅执行一次（`DiedPayloadExecuted` 标记防止重复）

**技术细节：**
```powershell
# 提取的 PowerShell 脚本逻辑：
1. 最小化所有窗口
2. 播放视频文件（Knock.mp4）
3. 在桌面创建100个文本文件
4. 设置新壁纸
5. 模拟按音量增大键100次
```

**影响范围：**
- **仅 Windows 系统**
- 需要 PowerShell 权限
- 会修改用户桌面背景

---

### 3. 在桌面创建文件

**实现文件：**
- `WaitForMeProcedure.java` - 创建 100 个文本文件
- `EndFProcedure.java` - 创建 `code.txt`
- `FirstProcedure.java` - 创建临时 VBS 脚本

**WaitForMeProcedure 的创建行为：**
```
文件名格式：I am coming for you_000.txt 到 I am coming for you_099.txt
- 每10个文件中，第10个包含随机代码（如 "L3f3R8%tN6vX&zY4 - Violence"）
- 其他文件为空
- 全部创建在用户桌面
```

**EndFProcedure 的创建行为：**
```
文件：code.txt（在桌面）
内容：7H#j9K!p2@Qm5*W - Betrayal
- 如果文件已存在则追加，否则创建
```

**触发条件：**
- 这些操作与"结局F"相关
- 在玩家被踢出服务器后执行

**影响：**
- 在用户桌面创建大量文件
- 文件内容为随机代码或固定文本
- 可能干扰用户正常工作

---

### 4. 打开应用程序/执行外部程序

**实现文件：** `WaitForMeProcedure.java`

**功能描述：**
- 创建并执行批处理文件 (`launch_payload.bat`)
- 使用 `ProcessBuilder` 启动 PowerShell 脚本
- 播放视频文件（`Knock.mp4`）
- 模拟键盘输入（音量控制）

**执行流程：**
```java
1. 从 JAR 资源提取 Knock.mp4 和 story.png 到临时目录
2. 生成 PowerShell 脚本（payload.ps1）
3. 创建批处理文件启动 PowerShell
4. 执行批处理文件
5. PowerShell 脚本执行：
   - 最小化所有窗口
   - 播放视频
   - 创建100个桌面文件
   - 更改壁纸
   - 模拟按键
```

**触发条件：**
- 玩家死亡并满足剧情条件
- 仅 Windows 系统（使用 PowerShell 和 COM 对象）

**安全风险：**
- 执行外部脚本可能被防病毒软件拦截
- 需要用户有 PowerShell 执行权限
- 可能被恶意软件检测规则标记

---

### 5. 服务器命令执行

**已披露功能（之前已说明）：**
- `/title` - 显示标题
- `/tp` - 传送玩家
- `/setblock` - 放置方块
- `kick` - 踢出玩家
- 其他 Minecraft 命令

**影响：**
- 影响所有在线玩家
- 用于剧情演出
- 需要服务器 OP 权限

---

## 🛡️ 安全控制与用户选择

### `/safeip` 命令

**功能：**
- 启用/禁用 IP 记录功能
- 禁用后使用随机生成的位置数据代替真实地理位置

**使用方法：**
```
/safeip
```

**效果：**
- `safeip = false`（默认）：记录真实 IP 和位置
- `safeip = true`：不记录真实 IP，使用随机数据

**注意：**
- 此命令**不会**阻止其他敏感功能（更改壁纸、创建文件等）
- 仅控制 IP/地理位置记录

---

## 📊 触发条件总结

| 功能 | 触发条件 | 影响范围 | 可禁用 |
|------|---------|---------|--------|
| IP 记录 | AngryBuilder 生成 + `safeip=false` | 发送到第三方 API | ✅ `/safeip` |
| 更改壁纸 | 玩家死亡（特定剧情） | 修改桌面背景 | ❌ 无法禁用 |
| 创建文件 | 结局F/玩家死亡 | 桌面创建100+文件 | ❌ 无法禁用 |
| 执行程序 | 玩家死亡（特定剧情） | 运行 PowerShell/播放视频 | ❌ 无法禁用 |
| 服务器命令 | 各种剧情节点 | 所有玩家 | ❌ 无法禁用 |

---

## 🔧 技术实现细节

### 依赖的外部服务
1. **ipify.org** - 获取外部 IP
2. **ip-api.com** - 查询地理位置（免费版无 HTTPS）

### 使用的系统 API
- **Windows:** `SystemParametersInfo` (user32.dll) - 更改壁纸
- **PowerShell:** `Start-Process`, `SendKeys` - 执行程序和模拟输入
- **COM 对象:** `Shell.Application` - 最小化窗口

### 文件路径
- 临时文件：`System.getProperty("java.io.tmpdir")`
- 桌面路径：`System.getProperty("user.home") + "/Desktop"`（支持 OneDrive 路径）

---

## ⚖️ 许可与权利声明

**原模组版权归 Dreg Iron 所有**
**本汉化版本：**
- 仅用于非商业性简体中文本地化
- 不得用于商业目的
- 必须保留原作者署名
- 不得修改代码后声称原创

**完整许可证：** [链接到 Pastebin]

---

## ❓ 常见问题

**Q: 这些功能是病毒吗？**  
A: 不是病毒。所有功能都明确用于剧情演出，无数据窃取或系统破坏意图。但可能被防病毒软件误报。

**Q: 可以禁用所有敏感功能吗？**  
A: 只有 IP 记录可以通过 `/safeip` 禁用。其他功能是模组剧情的一部分，无法单独禁用。

**Q: 为什么需要这些功能？**  
A: 原作者的创意选择，用于"打破第四面墙"的沉浸式体验。这些功能是剧情的一部分，而非缺陷。

**Q: 会影响 Mac/Linux 用户吗？**  
A: 壁纸更改和 PowerShell 脚本**仅 Windows**。IP 记录在所有系统都会执行。

**Q: 如何完全避免这些功能？**  
A: 不建议游玩此模组。这些功能是剧情核心，无法通过配置禁用。

---

## 📝 建议的 Modrinth 项目描述补充

在您的 Modrinth 项目描述中添加以下内容：

```
## ⚠️ 重要警告：技术功能披露

本模组包含影响用户计算机的功能，用于增强剧情沉浸感。详细说明如下：

### 1. IP 地址记录
- 在特定剧情阶段，模组会获取您的 IP 地址和地理位置
- 数据发送到第三方服务（ipify.org 和 ip-api.com）
- 使用命令 `/safeip` 可以禁用此功能（使用随机位置代替）

### 2. 更改桌面壁纸（仅 Windows）
- 在"结局F"触发时，自动更改您的 Windows 桌面壁纸
- 需要 PowerShell 权限

### 3. 在桌面创建文件
- 在"结局F"时，会在您的桌面创建 100 个文本文件
- 文件名为 "I am coming for you_XXX.txt"
- 还会创建包含代码的 "code.txt"

### 4. 执行外部程序（仅 Windows）
- 播放视频文件
- 模拟键盘输入（音量控制）
- 需要 PowerShell 执行权限

### 5. 服务器命令
- 模组会在剧情触发时自动执行 Minecraft 服务器命令
- 影响所有在线玩家（传送、标题显示、时间更改等）

**请注意：** 这些功能是模组剧情设计的一部分，无法通过设置禁用。如果您不希望 experiencing 这些效果，请勿安装此模组。

详细技术文档：[链接到此文件]
```

---

**最后更新：** 2025年5月  
**分析日期：** 2025年5月7日  
**分析工具：** CFR 0.152 反编译
