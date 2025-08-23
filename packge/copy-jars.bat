@echo off
setlocal enabledelayedexpansion

REM 目标目录
set TARGET_DIR=D:\feiliu28\bclmAdmin\packge

REM 如果目标目录不存在，就创建
if not exist "%TARGET_DIR%" (
    mkdir "%TARGET_DIR%"
)

echo 正在复制 JAR 文件到 %TARGET_DIR% ...

copy /Y "D:\feiliu28\bclmAdmin\system\target\f6mj-system.jar" "%TARGET_DIR%"
copy /Y "D:\feiliu28\bclmAdmin\game\target\f6mj-game.jar" "%TARGET_DIR%"
copy /Y "D:\feiliu28\bclmAdmin\player\target\f6mj-player.jar" "%TARGET_DIR%"

echo.
echo 所有 JAR 已复制完成！
pause
