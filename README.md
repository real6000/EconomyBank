# 💼 EconomyBank

A personal banking plugin for Minecraft (1.21.4) that lets players store items and manage virtual currency in a sleek GUI vault system.

Built with stability, simplicity, and extensibility in mind.

Feel free to use, modify, or extend as is open source.

---

## 📦 Features

- Personal vault with item storage and custom bank GUI
- Decorative borders, click sounds, and particle effects
- Balance display with interest and last access time
- Configurable interest system (e.g. 2% every 10 minutes)
- Withdraw/Deposit buttons (linked to physical items or economy)
- `/bankstats` to show player stats and bank activity
- Clean command structure with tab completion support

---

## 🔧 Commands

| Command        | Description                        | Permission         |
|----------------|------------------------------------|---------------------|
| `/bank`        | Open your personal bank GUI        | `economybank.use`   |
| `/bankstats`   | View your bank info and activity   | `economybank.use`   |

---

## ⚙️ Configuration (config.yml)

```yaml
interest:
  rate: 0.02        # 2% per interval
  interval: 600     # seconds (10 min)
