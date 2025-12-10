
# SynzMOTD

[![GitHub release (latest by date)](https://img.shields.io/github/v/release/LeSynz/SynzMOTD?style=flat-square)](
# SynzMOTD

A lightweight MOTD plugin for Paper 1.21+ with:
- MiniMessage formatting
- Rotating MOTD entries
- Custom server icon support (`server-icon.png`)
- Hot-reload commands (`/synzmotd reload`, `/synzmotd reloadicon`)

---

## 🚀 Features

### ✔ Rotating MOTD
Define multiple entries in `config.yml`, each sent on ping.

### ✔ MiniMessage Formatting
Supports gradients, hex colors, bold, italic, rainbow, etc.

### ✔ Custom Server Icon
Place a `server-icon.png` inside:

plugins/SynzMOTD/

Use:
```
/synzmotd reloadicon
````

### ✔ Lightweight & fast
No NMS, no reflections — fully API based.

---

## 📦 Installation

1. Download the latest release JAR from the Releases tab.
2. Drop it into your server's `plugins/` folder.
3. Start the server once to generate config files.
4. Customize `/plugins/SynzMOTD/config.yml`.

---

## 🔧 Commands

| Command                  | Permission            | Description |
|-------------------------|------------------------|-------------|
| `/synzmotd reload`      | `synzmotd.reload`      | Reloads config |
| `/synzmotd reloadicon`  | `synzmotd.reloadicon`  | Reloads server icon |

---

## 🧪 Building from source

```sh
./gradlew build
````

The plugin JAR will appear in:

```
build/libs/
```

## ✔ License

This project is licensed under the **MIT License**.

---

## ❤️ Credits

Made by synz.xyz
