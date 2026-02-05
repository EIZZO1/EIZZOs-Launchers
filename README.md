# <gradient:#ffaa00:#ffff55>EIZZOs-Launchers</gradient>

Created by **EIZZO**

A professional, particle-enhanced Minecraft plugin for creating custom launch pads using specific blocks. Designed for Paper 1.21.1+, this plugin allows server admins to configure unique physical responses (Vertical/Horizontal power) for different block types.

## 🚀 Features

- **Block-Based Launchers:** Turn any block into a launch pad.
- **Dynamic Physics:** Configure Vertical and Horizontal velocity separately for each block.
- **Vehicle Support:** Works for both players on foot and those in boats/minecarts.
- **In-Game Editor:** Full GUI-based configuration system.
- **Particle Effects:** Visual feedback when launched.
- **Sound Effects:** Auditory feedback for a more immersive experience.
- **Auto-Config Update:** Robust configuration management that preserves settings across updates.

## 🛠 Commands & Permissions

| Command | Description | Permission |
| :--- | :--- | :--- |
| `/launchers` | Opens the main Launcher Management GUI | `eizzolaunchers.admin` |
| `/launchers reload` | Reloads the plugin configuration | `eizzolaunchers.admin` |

**Aliases:** `/launcher`, `/el`

## ⚙️ Configuration

Launchers are stored in `config.yml`. While you can edit the file manually, using the **In-Game GUI** is recommended for real-time adjustments.

```yaml
config-version: 1.1

launchers:
  GOLD_BLOCK:
    vertical: 50.0
    horizontal: 50.0
    boat-boost: true
  EMERALD_BLOCK:
    vertical: 80.0
    horizontal: 20.0
    boat-boost: false
```

## 🎮 How to Use

1. **Open the GUI:** Type `/launchers`.
2. **Add a Launcher:** Hold the block you want to turn into a launcher and click the "Add from Hand" option (or use the selection menu).
3. **Edit Values:** Click on an existing launcher in the GUI to adjust its Vertical and Horizontal power using the interactive increment/decrement buttons.
4. **Test:** Simply walk over or land on the configured block to be launched!

---
*Developed with ❤️ for the Minecraft Community.*