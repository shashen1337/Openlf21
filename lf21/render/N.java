/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.pipeline.TextureTarget;
import com.mojang.blaze3d.vertex.PoseStack;
import java.awt.Color;
import java.util.function.Function;
import java.util.function.Predicate;
import lf21.render.O;
import lf21.utils.misc.ReflectionUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public final class N {
    private TextureTarget c;
    private static final String[] e;
    private boolean d;
    private final O b;
    private final Minecraft a = Minecraft.getInstance();

    public int d() {
        return this.c == null ? 0 : this.c.height;
    }

    public N() {
        this.b = new O();
    }

    public void e() {
        this.d = false;
    }

    private void a(int n2, int n3) {
        if (this.c == null) {
            this.c = new TextureTarget(n2, n3, true, Minecraft.ON_OSX);
            this.c.setClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        } else if (this.c.width != n2 || this.c.height != n3) {
            this.c.resize(n2, n3, Minecraft.ON_OSX);
        }
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String a(char[] cArray) {
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
                    case 0 -> 4;
                    case 1 -> 80;
                    case 2 -> 92;
                    case 3 -> 75;
                    case 4 -> 32;
                    case 5 -> 17;
                    default -> 29;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[4];
                var3_1 = 0;
                var2_2 = "a>(\"ThIe\";.T\fa>(\"ThIe\";.T";
                var4_3 = "a>(\"ThIe\";.T\fa>(\"ThIe\";.T".length();
                var1_4 = 12;
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
                    var5[var3_1++] = N.a(N.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "j5(dMxsa3.*Fe2g<5.Ne2v52/Ecxv\u007f\u0010.VtqV52/Ecxv+j5(dMxsa3.*Fe2g<5.Ne2v52/Ecxv\u007f\u0010.VtqV52/Ecxv";
                    var4_3 = "j5(dMxsa3.*Fe2g<5.Ne2v52/Ecxv\u007f\u0010.VtqV52/Ecxv+j5(dMxsa3.*Fe2g<5.Ne2v52/Ecxv\u007f\u0010.VtqV52/Ecxv".length();
                    var1_4 = 43;
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
                    var5[var3_1++] = N.a(N.a(v1));
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
        N.e = var5;
    }

    public int c() {
        return this.c == null ? 0 : this.c.width;
    }

    public int b() {
        return this.c == null ? 0 : this.c.getColorTextureId();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int a(PoseStack poseStack, float f2, Iterable<Entity> iterable, Predicate<Entity> predicate, Function<Entity, Color> function) {
        if (this.a.level == null || this.a.levelRenderer == null) {
            this.d = false;
            return 0;
        }
        int n2 = Math.max(1, this.a.getWindow().getWidth());
        int n3 = Math.max(1, this.a.getWindow().getHeight());
        this.a(n2, n3);
        RenderTarget renderTarget = this.a.levelRenderer.entityTarget();
        int n4 = GL11.glGetInteger((int)36006);
        int[] nArray = new int[4];
        GL11.glGetIntegerv((int)2978, (int[])nArray);
        int n5 = 0;
        this.c.setClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        this.c.clear(Minecraft.ON_OSX);
        String[] stringArray = e;
        ReflectionUtil.setInstanceField(this.a.levelRenderer, this.c, "entityTarget", "net/minecraft/client/renderer/LevelRenderer");
        try {
            EntityRenderDispatcher entityRenderDispatcher = this.a.getEntityRenderDispatcher();
            Vec3 vec3 = this.a.gameRenderer.getMainCamera().getPosition();
            for (Entity entity : iterable) {
                Color color;
                if (!predicate.test(entity) || (color = function.apply(entity)) == null || color.getAlpha() <= 0) continue;
                this.b.a(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
                double d2 = Mth.lerp((double)f2, (double)entity.xOld, (double)entity.getX()) - vec3.x;
                double d3 = Mth.lerp((double)f2, (double)entity.yOld, (double)entity.getY()) - vec3.y;
                double d4 = Mth.lerp((double)f2, (double)entity.zOld, (double)entity.getZ()) - vec3.z;
                float f3 = Mth.lerp((float)f2, (float)entity.yRotO, (float)entity.getYRot());
                boolean bl = entity.isInvisible();
                entity.setInvisible(false);
                try {
                    entityRenderDispatcher.render(entity, d2, d3, d4, f3, f2, poseStack, (MultiBufferSource)this.b, entityRenderDispatcher.getPackedLightCoords(entity, f2));
                    ++n5;
                }
                finally {
                    entity.setInvisible(bl);
                }
            }
            this.b.a();
            this.d = n5 > 0;
            int n6 = n5;
            return n6;
        }
        finally {
            stringArray = e;
            ReflectionUtil.setInstanceField(this.a.levelRenderer, renderTarget, "entityTarget", "net/minecraft/client/renderer/LevelRenderer");
            GL30.glBindFramebuffer((int)36160, (int)n4);
            GL11.glViewport((int)nArray[0], (int)nArray[1], (int)nArray[2], (int)nArray[3]);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x1D);
        }
        return cArray;
    }

    public boolean a() {
        return this.d && this.c != null;
    }
}

