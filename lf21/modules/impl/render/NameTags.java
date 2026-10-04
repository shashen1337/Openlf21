/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render;

import lf21.event.EventTarget;
import lf21.event.impl.PacketEvent;
import lf21.event.impl.Render2DEvent;
import lf21.event.impl.RenderEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.render.b.a;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.NumberSetting;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class NameTags
extends Module {
    public final BooleanSetting invisibles;
    private static final String[] p;
    public final BooleanSetting showArtifacts;
    public final NumberSetting scale;
    public static NameTags INSTANCE;
    public final ModeSetting style;
    public final NumberSetting maxDistance;
    public final BooleanSetting hideTeammates;

    @Override
    public void onDisable() {
        a a2 = a.a((String)this.style.getValue());
        if (a2 != null) {
            a2.c();
        }
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String c(char[] cArray) {
        char[] cArray2;
        block11: {
            int n2;
            int n3;
            block10: {
                cArray2 = cArray;
                n3 = cArray.length;
                n2 = 0;
                if (!true) break block10;
                n3 = n3;
                if (n3 <= n2) break block11;
            }
            do {
                cArray2 = cArray2;
                int n4 = n2;
                char c2 = cArray2[n4];
                cArray2[n4] = (char)(c2 ^ (switch (n2 % 7) {
                    case 0 -> 86;
                    case 1 -> 101;
                    case 2 -> 77;
                    case 3 -> 72;
                    case 4 -> 81;
                    case 5 -> 113;
                    default -> 66;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    @EventTarget
    public void a(PacketEvent packetEvent) {
        a a2 = a.a((String)this.style.getValue());
        if (a2 != null) {
            a2.a(packetEvent);
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[10];
                var3_1 = 0;
                var2_2 = "\u0019\u0015,$\u0005\u0005\u0006,$4\f\u001b\u00045h\u0015\u00181\"\u0004#+4\u0004\u0019\u0015,$\n\u001f\u000b;!\"\u0018 :\u0000>\u000e\u0005\r\"?q00\"\f+)2\u00051\u0005\u0005\u00114$4\u000e\u001e\f)-q%'7\b )%\u00141";
                var4_3 = "\u0019\u0015,$\u0005\u0005\u0006,$4\f\u001b\u00045h\u0015\u00181\"\u0004#+4\u0004\u0019\u0015,$\n\u001f\u000b;!\"\u0018 :\u0000>\u000e\u0005\r\"?q00\"\f+)2\u00051\u0005\u0005\u00114$4\u000e\u001e\f)-q%'7\b )%\u00141".length();
                var1_4 = 4;
                var0_5 = -1;
lbl7:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl12:
                // 1 sources

                while (true) {
                    var5[var3_1++] = NameTags.c(NameTags.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u0005\f 8=\u0014\b\u0018\u0004 -\u0005\u0010%%";
                    var4_3 = "\u0005\f 8=\u0014\b\u0018\u0004 -\u0005\u0010%%".length();
                    var1_4 = 6;
                    var0_5 = -1;
lbl21:
                    // 2 sources

                    while (true) {
                        v3 = ++var0_5;
                        v1 = var2_2.substring(v3, v3 + var1_4);
                        v2 = 0;
                        break block9;
                        break;
                    }
                    break;
                }
lbl26:
                // 1 sources

                while (true) {
                    var5[var3_1++] = NameTags.c(NameTags.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    break block10;
                    break;
                }
            }
            switch (v2) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl37:
                // 1 sources

                ** continue;
            }
        }
        NameTags.p = var5;
    }

    @Override
    public void onEnable() {
        a a2 = a.a((String)this.style.getValue());
        if (a2 != null) {
            a2.b();
        }
    }

    @EventTarget
    public void a(Render2DEvent render2DEvent) {
        a a2 = a.a((String)this.style.getValue());
        if (a2 != null) {
            a2.a(render2DEvent);
        }
    }

    public boolean handlesEntity(Entity entity) {
        return entity instanceof Player && mc.getConnection() != null && mc.getConnection().getPlayerInfo(entity.getUUID()) != null;
    }

    @EventTarget
    public void a(RenderEvent renderEvent) {
        a a2 = a.a((String)this.style.getValue());
        if (a2 != null) {
            a2.a(renderEvent);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x42);
        }
        return cArray;
    }

    public NameTags() {
        String[] stringArray = p;
        super("NameTags", Category.RENDER);
        this.style = new ModeSetting("Style", "Opal", "Simple").withDefault("Opal");
        this.scale = new NumberSetting("Scale", 0.3, 0.1, 1.0, 0.01);
        this.maxDistance = new NumberSetting("Max Distance", 64.0, 8.0, 256.0, 1.0);
        this.invisibles = new BooleanSetting("Invisibles", false);
        this.showArtifacts = new BooleanSetting("Show Artifacts", true);
        this.hideTeammates = new BooleanSetting("Hide Teammates", false);
        INSTANCE = this;
        a.a();
    }
}

