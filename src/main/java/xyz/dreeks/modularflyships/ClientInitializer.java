package xyz.dreeks.modularflyships;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import xyz.dreeks.modularflyships.entities.MFSEntities;
import xyz.dreeks.modularflyships.events.KeyEvents;
import xyz.dreeks.modularflyships.render.FlyshipRendererEntity;

public class ClientInitializer implements ClientModInitializer {

    public static KeyBinding KeyForward, KeyBackward, KeyLeft, KeyRight, KeyUp;
    public static FabricKeyBinding KeyDown;

    public static void setKeybinds(MinecraftClient mc) {
        // If one key isn't set, none are
        if (KeyForward == null) {
            KeyForward = mc.options.keyForward;
            KeyBackward = mc.options.keyBack;
            KeyLeft = mc.options.keyLeft;
            KeyRight = mc.options.keyRight;
            KeyUp = mc.options.keyJump;
        }
    }

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(MFSEntities.flyshipType, (dispatcher, ctx) -> new FlyshipRendererEntity(dispatcher));

        ClientTickCallback.EVENT.register(new KeyEvents());

        KeyBindingRegistry.INSTANCE.addCategory(Constants.MOD_NAME);
        KeyDown = FabricKeyBinding.Builder.create(
                Constants.id("down"),
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_W,
                Constants.MOD_NAME
        ).build();

        KeyBindingRegistry.INSTANCE.register(KeyDown);
    }

}
