# EIZZOs-Launchers

**Launch Pad System** with customizable velocity, particles, sounds, and boat launching support.

---

## 🎯 Features

### 🚀 Launch Mechanics
- **Two Block Detection** - Stand on two matching blocks to launch
- **Dual Launch Modes** - Direct player launch or boat launch
- **Velocity Control** - Separate vertical and horizontal power settings
- **Direction-Based** - Launches in the direction you're facing

### 🎨 Visual & Audio Effects
- **Launch Particles** - Two-layered particle burst on launch
- **Trail Particles** - Two parallel particle streams while flying
- **Sound Effects** - Customizable launch sounds
- **Particle Presets** - 13+ particle types to choose from

### ⚙️ Management
- **GUI Editor** - Visual interface for creating and editing launchers
- **In-Game Adjustments** - Modify properties without reloading
- **Command Control** - Set properties via commands
- **Auto-Save** - Configuration saved automatically

---

## 📝 Commands

| Command | Arguments | Permission | Description |
|---------|-----------|-----------|-------------|
| `/launchers` | - | `eizzolaunchers.admin` | Open Launcher Manager GUI |
| `/launchers help` | - | - | Display help menu |
| `/launchers reload` | - | `eizzolaunchers.admin` | Reload configuration |
| `/launchers set <material> <property> <value>` | See properties below | `eizzolaunchers.admin` | Set launcher property |

**Aliases:** `/launcher`, `/el`

### Set Command Properties

| Property | Type | Description | Default |
|----------|------|-------------|---------|
| `vertical` | 0-100 | Launch height/upward velocity multiplier | 50 |
| `horizontal` | 0-100 | Launch distance/forward velocity multiplier | 50 |
| `boat` | true/false | Enable/disable boat launching mode | false |
| `particle1` | Particle name | Primary launch particle effect | FLAME |
| `particle2` | Particle name | Secondary launch particle effect | CLOUD |
| `trail_particle1` | Particle name | Primary trail particle (while flying) | SOUL_FIRE_FLAME |
| `trail_particle2` | Particle name | Secondary trail particle (while flying) | WHITE_ASH |
| `sound` | Sound name | Launch sound effect | ENTITY_FIREWORK_ROCKET_LAUNCH |

---

## 🔐 Permissions

| Permission | Description |
|-----------|-------------|
| `eizzolaunchers.admin` | Access to all launcher commands and GUI management |

---

## 🛠️ Configuration

### Main Config (`config.yml`)

```yaml
config-version: 1.1

launchers:
  GOLD_BLOCK:
    vertical: 50              # Launch height (0-100)
    horizontal: 50            # Launch distance (0-100)
    boat: false              # Enable boat launching
    particle1: FLAME         # Primary launch particle
    particle2: CLOUD         # Secondary launch particle
    trail_particle1: SOUL_FIRE_FLAME  # Primary trail particle
    trail_particle2: WHITE_ASH         # Secondary trail particle
    sound: ENTITY_FIREWORK_ROCKET_LAUNCH  # Launch sound

  EMERALD_BLOCK:
    vertical: 80
    horizontal: 20
    boat: false
    particle1: DRAGON_BREATH
    particle2: HAPPY_VILLAGER
    trail_particle1: SOUL_FIRE_FLAME
    trail_particle2: WHITE_ASH
    sound: ENTITY_FIREWORK_ROCKET_LAUNCH
```

**Configuration Notes:**
- Material names must be valid Minecraft block names
- Numeric range: 0-100 recommended (scales velocity)
- Invalid particle/sound names fallback to defaults
- Config auto-migrates when version changes

---

## ⚡ Launch Mechanics

### Activation

Launchers trigger when:
1. Player/boat moves onto launcher blocks
2. **Two consecutive blocks** of same material detected:
   - Block at feet level (layer 1)
   - Block below feet (layer 2)
3. Material matches configured launcher type

### Two Launch Modes

#### Mode 1: Direct Player Launch (Default)
- **Activation:** Walk/stand on launcher blocks
- **Direction:** Player's facing direction (yaw)
- **Velocity Calculation:**
  - Vertical: `(vertical_config / 100.0) * 2.5` blocks/tick
  - Horizontal: `(horizontal_config / 100.0) * 3.0` blocks/tick

#### Mode 2: Boat Launch
- **Activation:** Boat moves over launcher blocks (requires `boat: true`)
- **Direction:** Boat's facing direction
- **Velocity Calculation:**
  - Vertical: `(vertical_config / 100.0) * 1.5` blocks/tick (lower than player)
  - Horizontal: `(horizontal_config / 100.0) * 4.0` blocks/tick (higher than player)

### Cooldown System

- **Duration:** 10 ticks (0.5 seconds)
- **Per-Entity:** Each player/boat has independent cooldown
- **Purpose:** Prevents repeated launches from quick passages

---

## 🎨 Visual & Audio Effects

### Launch Effects

When launched, players experience:

| Effect | Details |
|--------|---------|
| **Particle 1** | 20 particles, 0.2 spread, 0.1 speed |
| **Particle 2** | 15 particles, 0.3 spread, 0.05 speed |
| **Sound** | Volume 1.0, pitch 1.2 (players) / 0.8 (boats) |

### Trail Effects (While Flying)

- **Duration:** Runs every tick until landing or entering liquid
- **Minimum Air Time:** 3 ticks before landing detection
- **Particle Streams:** Two parallel trails on left/right sides
- **Side Offset:** 0.3 blocks perpendicular to flight direction
- **Landing Detection:** Player touches ground or enters liquid

---

## 🎮 Launcher Manager GUI

### Main Menu

Open with `/launchers` - displays all configured launchers.

**Actions:**
- **Left-Click** launcher → Open editor
- **Right-Click** launcher → Remove launcher
- **Click Emerald** (slot 25) → Add new launcher (hold block in hand)
- **Click Barrier** (slot 26) → Close menu

### Editor Menu

Edit properties for selected launcher:

| Slot | Item | Function |
|------|------|----------|
| 10 | Lever | **Vertical Height:** +10 (Left) / -10 (Right) |
| 19 | Feather | **Horizontal Flick:** +10 (Left) / -10 (Right) |
| 13 | Boat | Toggle boat mode ON/OFF |
| 22 | Jukebox | Cycle through sound presets |
| 15 | Blaze Powder | Cycle large particles (Particle 1) |
| 24 | White Dye | Cycle large particles (Particle 2) |
| 16 | Glowstone Dust | Cycle small particles (Trail 1) |
| 25 | Gunpowder | Cycle small particles (Trail 2) |
| 40 | Arrow | Back to Main Menu |
| 42 | Barrier | Close Editor |

**GUI Behavior:**
- Values clamped to 0-100 range
- Auto-saves on close
- Sound feedback on all clicks

---

## 🎨 Particle & Sound Presets

### Large Particles (Launch Effects)

```
FLAME, SOUL_FIRE_FLAME, DRAGON_BREATH, HEART, ENCHANT,
HAPPY_VILLAGER, CLOUD, REDSTONE, SOUL, TOTEM_OF_UNDYING,
WITCH, LAVA, GLOW
```

### Small Particles (Trail Effects)

```
WITCH, CRIT, ENCHANTED_HIT, WHITE_ASH, ASH,
REVERSE_PORTAL, SOUL_FIRE_FLAME, FLAME, HAPPY_VILLAGER, GLOW
```

### Sound Presets

```
ENTITY_FIREWORK_ROCKET_LAUNCH, ENTITY_EXPERIENCE_ORB_PICKUP,
ENTITY_PLAYER_LEVELUP, BLOCK_NOTE_BLOCK_CHIME,
ENTITY_ENDER_DRAGON_FLAP, ENTITY_WITHER_SHOOT
```

---

## 📚 Examples

### Basic Jump Pad

```bash
# Place two gold blocks vertically
# Stand on them to test default launcher (50/50)
/launchers set GOLD_BLOCK vertical 60
/launchers set GOLD_BLOCK horizontal 40
```

**Result:** Medium height, medium distance launch

### Super Jump Pad

```bash
/launchers set EMERALD_BLOCK vertical 100
/launchers set EMERALD_BLOCK horizontal 20
/launchers set EMERALD_BLOCK particle1 DRAGON_BREATH
/launchers set EMERALD_BLOCK sound ENTITY_ENDER_DRAGON_FLAP
```

**Result:** Very high jump, minimal forward movement, epic effects

### Water Channel Boat Launcher

```bash
/launchers set DIAMOND_BLOCK boat true
/launchers set DIAMOND_BLOCK vertical 30
/launchers set DIAMOND_BLOCK horizontal 80
/launchers set DIAMOND_BLOCK particle1 SOUL_FIRE_FLAME
/launchers set DIAMOND_BLOCK particle2 CLOUD
```

**Result:** Boat launches far across water with lower arc

### Parkour Course Launcher

```bash
# Short hop launcher
/launchers set QUARTZ_BLOCK vertical 40
/launchers set QUARTZ_BLOCK horizontal 50
/launchers set QUARTZ_BLOCK particle1 ENCHANT
/launchers set QUARTZ_BLOCK trail_particle1 HAPPY_VILLAGER
```

**Result:** Precise jumps for parkour courses with visual trail

### Multi-Launcher System

Create different launchers for different purposes:

```bash
# Low power (beginner)
/launchers set COAL_BLOCK vertical 30
/launchers set COAL_BLOCK horizontal 30

# Medium power (intermediate)
/launchers set IRON_BLOCK vertical 60
/launchers set IRON_BLOCK horizontal 50

# High power (expert)
/launchers set NETHERITE_BLOCK vertical 90
/launchers set NETHERITE_BLOCK horizontal 70
```

---

## 🔧 Troubleshooting

### Launcher not working?
- Ensure **two blocks** of same material stacked vertically
- Check player is standing on top block (not in it)
- Verify material is configured in config.yml
- Check for cooldown (0.5 second delay between launches)

### No particles showing?
- Verify particle names are valid Minecraft particle types
- Particles are client-side - restart client if needed
- Check console for errors on invalid particle names
- Plugin falls back to FLAME if particle invalid

### Sound not playing?
- Verify sound name is valid Minecraft sound enum
- Check client sound settings (not muted)
- Plugin falls back to ENTITY_FIREWORK_ROCKET_LAUNCH if invalid
- Sound plays at launch location, volume 1.0

### Boat launcher not working?
- Verify `boat: true` in configuration for that material
- Boat must be moving over blocks (not stationary)
- Check boat is actually in water (works on land too)
- Boat pitch is 0.8 (lower than player's 1.2)

### Launch velocity too weak/strong?
- Adjust vertical and horizontal values (0-100)
- Values multiply with base velocity:
  - Player: vertical ×2.5, horizontal ×3.0
  - Boat: vertical ×1.5, horizontal ×4.0
- Test in creative mode for safety

### Trail particles not showing?
- Trails only appear while entity is in air
- Minimum 3 ticks in air before landing detection
- Trails stop when landing or entering liquid
- Check trail_particle1 and trail_particle2 are valid

### Can't add new launcher?
- Hold the block in your hand
- Click the Emerald in GUI slot 25
- Material must not already exist as launcher
- Requires `eizzolaunchers.admin` permission

---

## ⚠️ Important Notes

### Critical Information

1. **Two-Block Requirement:** Launchers need matching blocks at feet level AND one block below
2. **Direction-Based:** Launch direction follows player/boat facing direction
3. **Cooldown System:** 0.5 second cooldown prevents repeated launches
4. **GUI Blocking:** Launches blocked while inventory GUI is open
5. **Velocity Scaling:** Values 0-100 scale with different multipliers for player/boat
6. **Trail Rendering:** Trails use perpendicular offset vectors for side-by-side streams
7. **Fallback System:** Invalid particles/sounds automatically fallback to defaults

### Velocity Multipliers

**Player Mode:**
- Vertical: `config × 2.5 ÷ 100` blocks/tick
- Horizontal: `config × 3.0 ÷ 100` blocks/tick

**Boat Mode:**
- Vertical: `config × 1.5 ÷ 100` blocks/tick (lower for water travel)
- Horizontal: `config × 4.0 ÷ 100` blocks/tick (higher for distance)

### Landing Detection

Trails stop when:
- Player/entity touches ground (`isOnGround()`)
- Entity enters liquid (`getBlock().isLiquid()`)
- Entity removed/despawned

### Trail Offset Calculation

```java
Vector dir = entity.getDirection().setY(0).normalize();
Vector sideOffset = new Vector(-dir.getZ(), 0, dir.getX()).normalize().multiply(0.3);
// Spawns particles at:
// - entity_location + sideOffset (right side)
// - entity_location - sideOffset (left side)
```

---

## 🎨 Chat Formatting

All plugin messages use **MiniMessage** format with gradient coloring:

**Gradient:** Bright Red (#FF5555) → Dark Red (#FF0000)

**Format:** `[EIZZOs-Launchers] » Message`

Examples:
- Help menu headers
- Property change confirmations
- Error messages
- Admin notifications

---

## 📊 Configuration Examples

### Speed Runner Course

```yaml
launchers:
  GOLD_BLOCK:
    vertical: 45
    horizontal: 70
    boat: false
    particle1: SOUL_FIRE_FLAME
    particle2: FLAME
    trail_particle1: SOUL_FIRE_FLAME
    trail_particle2: CRIT
    sound: ENTITY_FIREWORK_ROCKET_LAUNCH
```

### Water Park Ride

```yaml
launchers:
  PRISMARINE:
    vertical: 35
    horizontal: 85
    boat: true
    particle1: CLOUD
    particle2: HAPPY_VILLAGER
    trail_particle1: WHITE_ASH
    trail_particle2: ASH
    sound: ENTITY_PLAYER_SPLASH
```

### Vertical Cannon

```yaml
launchers:
  REDSTONE_BLOCK:
    vertical: 95
    horizontal: 10
    boat: false
    particle1: LAVA
    particle2: FLAME
    trail_particle1: SOUL_FIRE_FLAME
    trail_particle2: WHITE_ASH
    sound: ENTITY_WITHER_SHOOT
```

---

## 📄 License

Part of the EIZZOs plugin suite for Minecraft multiplayer networks.

---

**Version:** 1.0-SNAPSHOT
**Minecraft:** 1.21.1 (Paper)
**Author:** EIZZO
