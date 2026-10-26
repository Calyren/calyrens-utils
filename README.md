# CXD's Utilities

<details>
<summary>Instalation</summary>

build.gradle:
```
repositories {
	exclusiveContent {
		forRepository {
			maven {
				name = "Modrinth"
				url = "https://api.modrinth.com/maven"
			}
		}
		filter {
			includeGroup "maven.modrinth"
		}
	}
}

dependencies {
    implementation "maven.modrinth:t9HzrmQg:<version_id>"
}
```

Replace <version_id> with an actual Version ID. (Ex. For 1.0-26.2, the Version ID is "2k1jbG7k")

---

Q: Where do I find the Version ID?

A: Go to the version you want install and then click on "Developer information". You should find the Version ID there.

![Version ID](https://cdn.modrinth.com/data/cached_images/b72cf7b03d91e130c0c83c6c310b282970e085be.png)

</details>

<details>
<summary><strong>Mob Effects</strong></summary>

CXD's Utils adds 3 classes related to Mob Effects:
- `CustomEffectDuration`
- `CustomEffectTooltip`
- `StatusEffectBackground`

---

<details>
<summary><strong>CustomEffectDuration</strong></summary>

`CustomEffectDuration` lets you set a custom duration text for Mob Effects.

```java
public class YourMobEffect extends MobEffect implements CustomEffectDuration {
<...>

    @Override
    public Component getDurationText(MobEffectInstance instance) {
        return Component.literal("Hi!!! This is an Example!")
                .withStyle(style -> style.withColor(0xFFFFFF));
    }

}
```

Instead of showing how long the effect lasts, it will display:

```text
Hi!!! This is an Example!
```

</details>

---

<details>
<summary><strong>CustomEffectTooltip</strong></summary>

`CustomEffectTooltip` lets you add a custom tooltip to Mob Effects.

```java
public class YourMobEffect extends MobEffect implements CustomEffectTooltip {
<...>

    @Override
    public List<Component> getTooltipLines(MobEffectInstance instance) {
        return List.of(
                Component.literal("This Text will appear when you hover over the Effect!")
                        .withStyle(ChatFormatting.WHITE),
                Component.literal("It also supports multiple lines!")
                        .withStyle(ChatFormatting.WHITE)
        );
    }

}
```

When hovering over the Mob Effect, the custom tooltip text will appear.

</details>

---

<details>
<summary><strong>StatusEffectBackground</strong></summary>

`CustomEffectTooltip` lets you add a custom texture to your MobEffect's background.

```java
public class YourMobEffect extends MobEffect implements StatusEffectBackground {
<...>

    @Override
    public Identifier inventorySprite() {
        return Identifier.fromNamespaceAndPath(YourMod.MOD_ID, "container/inventory/
effect_background");
    }
    @Override
    public Identifier ambientInventorySprite() {
        return Identifier.fromNamespaceAndPath(YourMod.MOD_ID, "container/inventory/
effect_background_ambient");
    }

}
```

Check https://mcasset.cloud/26.1.2/assets/minecraft/textures/gui/sprites/container/inventory to see how Minecraft does the effect backgrounds. This uses the same logic.

</details>

</details>
