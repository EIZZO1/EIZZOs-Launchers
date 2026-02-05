# <gradient:#ffaa00:#ffff55>EIZZOs-Launchers</gradient>

Created by **EIZZO**

---

### 🌟 Overview
**EIZZOs-Launchers** is a high-performance utility plugin designed for Minecraft 1.21.1+ that transforms standard blocks into powerful, customizable launch pads. Whether you're building a lobby parkour course, a fast-travel hub, or a boat-racing circuit, this plugin provides the tools to create seamless and thrilling movement mechanics.

---

### 🚀 Key Capabilities

#### 1. **Precision Launch Mechanics**
- **Dual-Axis Control:** Independently configure **Vertical** (height) and **Horizontal** (distance) velocity.
- **Directional Awareness:** Launches are calculated based on the player's current facing direction, ensuring a natural and intuitive feel.

#### 2. **In-Game Visual Editor**
- **Live Adjustments:** No more digging through config files. Use the `/launchers` GUI to tweak power levels and see results instantly.
- **Interactive UI:** Intuitive buttons for adding, removing, and fine-tuning launchers.
- **Item-to-Block Binding:** Simply hold a block in your hand to register it as a new launcher type.

#### 3. **Immersive Feedback**
- **Particle Trails:** Automatic particle effects triggered upon launch to visualize the boost.
- **Dynamic Soundscape:** High-quality sound effects provide immediate auditory confirmation of the launch.

#### 4. **Vehicle & Boat Support**
- **Transport Logic:** Works perfectly with boats and minecarts, allowing for advanced transport systems and "boost pads" for racing.
- **Toggleable Boosts:** Enable or disable boat-specific boosting per launcher type.

---

### 🛠 Technical Details

#### **How it Works**
The plugin monitors player and vehicle movement events with high efficiency. When a transition onto a "Launcher Block" is detected:
1. It retrieves the specific power settings for that material.
2. It calculates the necessary velocity vector based on the entity's look direction.
3. It applies the impulse, triggers a `Flame` and `Cloud` particle burst, and plays a `FIREWORK_LAUNCH` sound.

---

### 📋 Commands & Permissions

| Command | Action | Permission |
| :--- | :--- | :--- |
| `/launchers` | Opens the Management GUI | `eizzolaunchers.admin` |
| `/launchers reload` | Reloads the configuration | `eizzolaunchers.admin` |

**Aliases:** `/launcher`, `/el`

---

### 💾 Configuration Example
```yaml
config-version: 1.1
launchers:
  GOLD_BLOCK:
    vertical: 50.0      # Scale: 0-100 recommended
    horizontal: 50.0    # Scale: 0-100 recommended
    boat-boost: true    # Boost boats/vehicles
  EMERALD_BLOCK:
    vertical: 100.0     # Super Jump
    horizontal: 10.0
    boat-boost: false
```

---
*Developed with a focus on performance and player experience.*