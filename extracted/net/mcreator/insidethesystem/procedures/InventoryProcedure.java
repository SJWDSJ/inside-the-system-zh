/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ArmorItem
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.Mirror
 *  net.minecraft.world.level.block.Rotation
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate
 *  net.minecraft.world.level.material.FluidState
 *  net.minecraft.world.phys.Vec3
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.entity.player.PlayerInteractEvent$EntityInteract
 *  net.neoforged.neoforge.event.tick.PlayerTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import net.mcreator.insidethesystem.entity.CoolPlayer303Entity;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber
public class InventoryProcedure {
    @SubscribeEvent
    public static void onRightClickEntity(PlayerInteractEvent.EntityInteract event) {
        if (event.getHand() != event.getEntity().getUsedItemHand()) {
            return;
        }
        InventoryProcedure.execute((Event)event, (LevelAccessor)event.getLevel(), event.getTarget(), (Entity)event.getEntity(), event.getHand());
    }

    public static void execute(LevelAccessor world, Entity entity, Entity sourceentity, InteractionHand hand) {
        InventoryProcedure.execute(null, world, entity, sourceentity, hand);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity, InteractionHand hand) {
        EquipmentSlot targetSlot;
        if (entity == null || sourceentity == null) {
            return;
        }
        if (!(entity instanceof CoolPlayer303Entity) || !(sourceentity instanceof Player)) {
            return;
        }
        Player player = (Player)sourceentity;
        ItemStack heldItem = player.getItemInHand(hand);
        boolean changed = false;
        if (player.isShiftKeyDown() && heldItem.isEmpty()) {
            List<EquipmentSlot> slots = Arrays.asList(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND);
            for (EquipmentSlot slot : slots) {
                ItemStack equipped = ((LivingEntity)entity).getItemBySlot(slot);
                if (equipped.isEmpty() || InventoryProcedure.isUnremovable(equipped)) continue;
                InventoryProcedure.returnItemToPlayer(player, equipped.copy());
                ((LivingEntity)entity).setItemSlot(slot, ItemStack.EMPTY);
                changed = true;
                break;
            }
        } else if (!heldItem.isEmpty() && (targetSlot = InventoryProcedure.getTargetSlotForItem(heldItem, (LivingEntity)entity)) != null) {
            LivingEntity coolEntity = (LivingEntity)entity;
            ItemStack currentItem = coolEntity.getItemBySlot(targetSlot);
            if (!currentItem.isEmpty()) {
                if (InventoryProcedure.isUnremovable(currentItem)) {
                    return;
                }
                InventoryProcedure.returnItemToPlayer(player, currentItem.copy());
                coolEntity.setItemSlot(targetSlot, ItemStack.EMPTY);
                changed = true;
            }
            ItemStack copy = heldItem.copy();
            copy.setCount(1);
            coolEntity.setItemSlot(targetSlot, copy);
            heldItem.shrink(1);
            if (heldItem.isEmpty()) {
                player.setItemInHand(hand, ItemStack.EMPTY);
            }
            changed = true;
        }
        if (changed) {
            InventoryProcedure.updateAllItemsAndTriggerStructure(world, entity);
        }
    }

    private static void updateAllItemsAndTriggerStructure(LevelAccessor world, Entity entity) {
        if (!(entity instanceof LivingEntity)) {
            return;
        }
        LivingEntity living = (LivingEntity)entity;
        ItemStack mainHand = living.getItemBySlot(EquipmentSlot.MAINHAND);
        ItemStack offHand = living.getItemBySlot(EquipmentSlot.OFFHAND);
        ItemStack chest = living.getItemBySlot(EquipmentSlot.CHEST);
        ResourceLocation mainId = BuiltInRegistries.ITEM.getKey((Object)mainHand.getItem());
        ResourceLocation offId = BuiltInRegistries.ITEM.getKey((Object)offHand.getItem());
        ResourceLocation chestId = BuiltInRegistries.ITEM.getKey((Object)chest.getItem());
        boolean hasBloodyKnife = mainId != null && mainId.getPath().equals("bloodyknife");
        boolean hasGerd = offId != null && offId.getPath().equals("gerd");
        boolean hasShell = chestId != null && chestId.getPath().equals("shellnecklace_chestplate");
        boolean allItems = hasBloodyKnife && hasGerd && hasShell;
        InsideTheSystemModVariables.MapVariables vars = InsideTheSystemModVariables.MapVariables.get(world);
        vars.AllItems = allItems;
        if (allItems && !vars.generated && world instanceof ServerLevel) {
            ServerLevel serverWorld = (ServerLevel)world;
            InventoryProcedure.generateStructure(world, serverWorld, vars);
        }
        vars.syncData(world);
    }

    private static void generateStructure(LevelAccessor world, ServerLevel serverWorld, InsideTheSystemModVariables.MapVariables vars) {
        RandomSource random = serverWorld.getRandom();
        double x = 0.0;
        double z = 0.0;
        int y = 0;
        boolean found = false;
        for (int i = 0; i < 200; ++i) {
            double angle = random.nextDouble() * Math.PI * 2.0;
            double dist = random.nextDouble() * 1000.0;
            x = Math.cos(angle) * dist;
            z = Math.sin(angle) * dist;
            y = random.nextInt(21) + 20;
            BlockPos pos = BlockPos.containing((double)x, (double)y, (double)z);
            BlockState state = world.getBlockState(pos);
            FluidState fluid = world.getFluidState(pos);
            BlockState below = world.getBlockState(pos.below());
            FluidState belowFluid = world.getFluidState(pos.below());
            if (state.isAir() || !fluid.isEmpty() || below.isAir() || !belowFluid.isEmpty() || InventoryProcedure.isExposed(world, pos)) continue;
            found = true;
            break;
        }
        if (!found) {
            return;
        }
        StructureTemplate template = serverWorld.getStructureManager().getOrCreate(ResourceLocation.parse((String)"inside_the_system:stonehouse"));
        if (template == null) {
            return;
        }
        BlockPos structurePos = BlockPos.containing((double)x, (double)y, (double)z);
        template.placeInWorld((ServerLevelAccessor)serverWorld, structurePos, structurePos, new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false), random, 3);
        InventoryProcedure.createStonePillar(serverWorld, x, y, z);
        vars.x = x;
        vars.y = y;
        vars.z = z;
        vars.generated = true;
        vars.chatTriggered = false;
    }

    private static void createStonePillar(ServerLevel world, double x, double y, double z) {
        for (int yy = (int)y; yy <= 90; ++yy) {
            BlockPos pos = BlockPos.containing((double)x, (double)yy, (double)z);
            if (!world.getBlockState(pos).isAir()) continue;
            world.setBlock(pos, Blocks.STONE.defaultBlockState(), 3);
        }
    }

    private static boolean isExposed(LevelAccessor world, BlockPos pos) {
        int air = 0;
        for (int dx = -1; dx <= 1; ++dx) {
            for (int dy = -1; dy <= 1; ++dy) {
                for (int dz = -1; dz <= 1; ++dz) {
                    if (dx == 0 && dy == 0 && dz == 0 || !world.getBlockState(pos.offset(dx, dy, dz)).isAir()) continue;
                    ++air;
                }
            }
        }
        return air > 10;
    }

    private static boolean isUnremovable(ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        ResourceLocation id = BuiltInRegistries.ITEM.getKey((Object)stack.getItem());
        if (id == null) {
            return false;
        }
        return id.getPath().equals("bloodyknife") || id.getPath().equals("gerd") || id.getPath().equals("shellnecklace_chestplate");
    }

    private static EquipmentSlot getTargetSlotForItem(ItemStack stack, LivingEntity entity) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey((Object)stack.getItem());
        if (id == null) {
            return null;
        }
        String path = id.getPath();
        if (path.equals("bloodyknife")) {
            return EquipmentSlot.MAINHAND;
        }
        if (path.equals("gerd")) {
            return EquipmentSlot.OFFHAND;
        }
        if (path.equals("shellnecklace_chestplate")) {
            return EquipmentSlot.CHEST;
        }
        Item item = stack.getItem();
        if (item instanceof ArmorItem) {
            ArmorItem armor = (ArmorItem)item;
            return armor.getEquipmentSlot();
        }
        if (InventoryProcedure.isWeaponOrTool(path)) {
            return EquipmentSlot.MAINHAND;
        }
        if (path.contains("shield")) {
            return EquipmentSlot.OFFHAND;
        }
        return EquipmentSlot.MAINHAND;
    }

    private static boolean isWeaponOrTool(String path) {
        return path.contains("sword") || path.contains("axe") || path.contains("pickaxe") || path.contains("shovel") || path.contains("hoe") || path.contains("bow") || path.contains("trident");
    }

    private static void returnItemToPlayer(Player player, ItemStack stack) {
        if (!player.getInventory().add(stack)) {
            Vec3 pos = player.position();
            ItemEntity drop = new ItemEntity(player.level(), pos.x, pos.y + 0.5, pos.z, stack);
            player.level().addFreshEntity((Entity)drop);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        double dz;
        double dx;
        double horizontalDistSq;
        Player player = event.getEntity();
        Level world = player.level();
        InsideTheSystemModVariables.MapVariables vars = InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world);
        if (vars.AllItems && vars.generated && !vars.chatTriggered && (horizontalDistSq = (dx = player.getX() - vars.x) * dx + (dz = player.getZ() - vars.z) * dz) <= 100.0) {
            if (!world.isClientSide()) {
                player.sendSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u597d\u4e86\uff0c\u6211\u4eec\u5230\u4e86\u2026\u2026\u6211\u771f\u7684\u6307\u671b\u4f60\u4e86"));
            }
            vars.chatTriggered = true;
            vars.syncData((LevelAccessor)world);
        }
    }
}

