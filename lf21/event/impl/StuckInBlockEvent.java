/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

import java.util.Objects;
import lf21.event.Event;
import lombok.Generated;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class StuckInBlockEvent
extends Event {
    private BlockState b;
    private Vec3 c;
    private static final String[] d;

    @Generated
    public String toString() {
        String[] stringArray = d;
        return "StuckInBlockEvent(state=" + String.valueOf(this.a()) + ", stuckSpeedMultiplier=" + String.valueOf(this.getMotion()) + ")";
    }

    @Generated
    public boolean equals(Object object) {
        Vec3 vec3;
        BlockState blockState;
        if (object == this) {
            return true;
        }
        if (!(object instanceof StuckInBlockEvent)) {
            return false;
        }
        StuckInBlockEvent stuckInBlockEvent = (StuckInBlockEvent)object;
        if (!stuckInBlockEvent.a(this)) {
            return false;
        }
        BlockState blockState2 = this.a();
        if (!Objects.equals(blockState2, blockState = stuckInBlockEvent.a())) {
            return false;
        }
        Vec3 vec32 = this.getMotion();
        return Objects.equals(vec32, vec3 = stuckInBlockEvent.getMotion());
    }

    @Generated
    protected boolean a(Object object) {
        return object instanceof StuckInBlockEvent;
    }

    @Generated
    public int hashCode() {
        int n2 = 59;
        int n3 = 1;
        BlockState blockState = this.a();
        n3 = n3 * 59 + (blockState == null ? 43 : blockState.hashCode());
        Vec3 vec3 = this.getMotion();
        n3 = n3 * 59 + (vec3 == null ? 43 : vec3.hashCode());
        return n3;
    }

    @Generated
    public StuckInBlockEvent(BlockState blockState, Vec3 vec3) {
        this.b = blockState;
        this.c = vec3;
    }

    @Generated
    public BlockState a() {
        return this.b;
    }

    @Generated
    public void a(BlockState blockState) {
        this.b = blockState;
    }

    @Generated
    public Vec3 getMotion() {
        return this.c;
    }

    @Generated
    public void a(Vec3 vec3) {
        this.c = vec3;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "u4\u00078TH\u0011\nd\u0011)Ef\u000f5`\u001d<MB\u001f+)\u0018\n`\u0001/Jb\u0014\u001bx\u001b/Jn\f<z\u0000dR_\u001b-qI";
        int n3 = "u4\u00078TH\u0011\nd\u0011)Ef\u000f5`\u001d<MB\u001f+)\u0018\n`\u0001/Jb\u0014\u001bx\u001b/Jn\f<z\u0000dR_\u001b-qI".length();
        int n4 = 23;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = StuckInBlockEvent.a(StuckInBlockEvent.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                d = stringArray;
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x7A);
        }
        return cArray;
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
                    case 0 -> 89;
                    case 1 -> 20;
                    case 2 -> 116;
                    case 3 -> 76;
                    case 4 -> 33;
                    case 5 -> 43;
                    default -> 122;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

