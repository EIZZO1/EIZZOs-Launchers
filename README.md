<img src="logo.svg" width="400" alt="EIZZOs-Launchers Logo">

Created by **EIZZO**

---

### 🌟 Overview
**EIZZOs-Launchers** is a high-performance utility plugin designed for Minecraft 1.21.1+ that transforms standard blocks into powerful, customizable launch pads. Whether you're building a lobby parkour course, a fast-travel hub, or a boat-racing circuit, this plugin provides the tools to create seamless and thrilling movement mechanics.

---

### 🚀 Key Capabilities

#### 1. **Precision Launch Mechanics**
- **Dual-Axis Control:** Independently configure **Vertical** (height) and **Horizontal** (distance) velocity.
- **Directional Awareness:** Launches are calculated based on the player's current facing direction, ensuring a natural and intuitive feel.

#### 2. **Professional Visuals & Audio**
- **Dual Particle System:** Each launcher supports two independent particle effects (e.g., Flame and Cloud) for rich visual feedback.
- **Dynamic Soundscape:** Customizable sound effects provide immediate auditory confirmation of the launch.

#### 3. **Advanced In-Game Editor**
- **Clean Layout:** Organized GUI with power controls, boat toggles, and effect selectors.
- **Live Adjustments:** Tweak power levels or cycle through "Nice" particle/sound presets and see results instantly.
- **Item-to-Block Binding:** Simply hold a block in your hand to register it as a new launcher type.

#### 4. **Vehicle & Boat Support**
- **Transport Logic:** Works perfectly with boats and minecarts, allowing for advanced transport systems and "boost pads" for racing.
- **Toggleable Boosts:** Enable or disable boat-specific boosting per launcher type.

---

### 🛠 Technical Details

#### **Command Management**
The plugin features a robust command system with full tab completion for all properties.
- **Set Property:** `/launchers set <material> <property> <value>`
- **Properties:** `vertical`, `horizontal`, `boat`, `particle1`, `particle2`, `sound`.

---

### 📋 Commands & Permissions

| Command | Action | Permission |
| :--- | :--- | :--- |
| `/launchers` | Opens the Management GUI | `eizzolaunchers.admin` |
| `/launchers set ...` | Modifies launcher properties | `eizzolaunchers.admin` |
| `/launchers reload` | Reloads the configuration | `eizzolaunchers.admin` |

**Aliases:** `/launcher`, `/el`

---

### 💾 Configuration Example
```yaml
config-version: 1.1
launchers:
  GOLD_BLOCK:
    vertical: 50.0
    horizontal: 50.0
    boat: true
    particle1: FLAME
    particle2: CLOUD
    sound: ENTITY_FIREWORK_ROCKET_LAUNCH
```

---
*Developed with a focus on performance and player experience.*