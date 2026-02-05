<p align="center">
  <img src="logo.svg" width="200" alt="EIZZOs-Launchers Logo">
</p>

# 🚀 EIZZOs-Launchers

**EIZZOs-Launchers** is a lightweight and powerful Minecraft plugin that allows server administrators to create custom launch pads using specific block types. Whether you want a gentle boost or a massive leap, EIZZOs-Launchers gives you full control over player movement.

## ✨ Features

- **Custom Launch Pads:** Define any block as a launch pad.
- **Adjustable Intensity:** Configure both vertical and horizontal launch power per block type.
- **In-Game GUI:** Manage your launchers easily through an intuitive graphical interface.
- **Efficient Performance:** Designed to be lightweight with minimal impact on server resources.
- **Easy Configuration:** Simple YAML configuration for quick setup.

## 🛠️ Commands

| Command | Description | Permission |
|---------|-------------|------------|
| `/launchers` | Opens the Launcher Management GUI | `eizzolaunchers.admin` |

## 🔑 Permissions

- `eizzolaunchers.admin`: Grants access to the `/launchers` command and the ability to manage launch pads.

## 🚀 Installation

1. Download the latest `EIZZOs-Launchers-x.x.x.jar`.
2. Place the JAR file in your server's `plugins/` directory.
3. Restart or reload your server.
4. Configure your launchers in `config.yml` or use the in-game GUI!

## ⚙️ Configuration

Example `config.yml`:

```yaml
config-version: 1.1

launchers:
  GOLD_BLOCK:
    vertical: 50
    horizontal: 50
  EMERALD_BLOCK:
    vertical: 80
    horizontal: 20
```

---
*Created with ❤️ by the EIZZO team.*
